import java.security.MessageDigest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import static spark.Spark.*;

import spark.QueryParamsMap;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;

import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;
import spark.utils.StringUtils;

public class Main
{

	public static void main(String[] args)
	{
		port(Integer.valueOf(System.getenv("PORT")));
		staticFileLocation("/public");

		get("/hello", (req, res) -> "Hello World");

		get("/", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();

			User user = request.session().attribute("User");
			if (user != null)
			{
				attributes.put("userName", user.getName());
			}

			return new ModelAndView(attributes, "index.ftl");
		}, new FreeMarkerEngine());

		get("/db", (req, res) -> {
			Connection          connection = null;
			Map<String, Object> attributes = new HashMap<>();
			try
			{
				connection = DatabaseUrl.extract().getConnection();

				Statement stmt = connection.createStatement();
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
				stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
				ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

				ArrayList<String> output = new ArrayList<String>();
				while (rs.next())
				{
					output.add("Read from DB: " + rs.getTimestamp("tick"));
				}

				attributes.put("results", output);
				return new ModelAndView(attributes, "db.ftl");
			} catch (Exception e)
			{
				attributes.put("message", "There was an error: " + e);
				return new ModelAndView(attributes, "error.ftl");
			} finally
			{
				if (connection != null) try
				{
					connection.close();
				} catch (SQLException e)
				{
				}
			}
		}, new FreeMarkerEngine());

		post("/login", (request, response) -> {
			String email = request.queryMap().get("email").value();
			String password = hashSha256(request.queryMap().get("password").value());

			Map<String, Object> attributes = new HashMap<>();
			Connection connection = null;

			try
			{
				connection = DatabaseUrl.extract().getConnection();

				Statement stmt = connection.createStatement();
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS societies (\n" +
				                   "ID BIGSERIAL PRIMARY KEY,\n" +
				                   "name varchar(255),\n" +
				                   "email varchar(255),\n" +
				                   "password text);");


//				stmt.executeUpdate("INSERT INTO " +
//				                   "societies (name, email, password) " +
//				                   "VALUES ('Some society', 'several27@icloud.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8')");

				PreparedStatement pS = connection.prepareStatement("SELECT ID, name, email " +
				                                                   "FROM societies " +
				                                                   "WHERE email = ? AND password = ?");
				pS.setString(1, email);
				pS.setString(2, password);

				ResultSet rs = pS.executeQuery();

				User user = null;
				while (rs.next())
				{
					user = new User(rs.getInt("ID"), rs.getString("email"), rs.getString("name"));
				}

				if (user != null)
				{
					request.session().attribute("User", user);
					attributes.put("successMessage", user.getName() + " login correctly !");
					attributes.put("userName", user.getName());
				}
				else
				{
					attributes.put("errorMessage", "Wrong email or password");
				}

				return new ModelAndView(attributes, "index.ftl");
			} catch (Exception e)
			{
				attributes.put("errorMessage", "There was an error: " + e);
				return new ModelAndView(attributes, "error.ftl");
			} finally
			{
				if (connection != null) try
				{
					connection.close();
				} catch (SQLException e)
				{
				}
			}
		}, new FreeMarkerEngine());


		get("/findFreeTime", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();

			User user = request.session().attribute("User");
			if (user == null)
			{
				response.redirect("/");
				return new ModelAndView(attributes, "error.ftl");
			}
			attributes.put("userName", user.getName());

			QueryParamsMap date = request.queryMap().get("date");
			QueryParamsMap dateSubmit = request.queryMap().get("date_submit");
			String         dateS, dateSubmitS;
			Date           dateD;

			if (date.hasValue() && dateSubmit.hasValue())
			{
				dateS = date.value();
				dateSubmitS = dateSubmit.value();
			}
			else
			{
				dateS = new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date());
				dateSubmitS = new SimpleDateFormat("MMMM dd, yyyy").format(new java.util.Date());
			}

			dateD = new Date(
					Integer.parseInt(dateS.substring(0, 4)),
					Integer.parseInt(dateS.substring(5, 7)),
					Integer.parseInt(dateS.substring(8, 10))
			);

			ArrayList<TreeMap<Long, Integer>> events = null;
			try
			{
				DataParser.Main main = new DataParser.Main();
				events = main.start();
			} catch(Exception e) {}

			TreeMap<Long, Integer> eventsForDay = getEventsByDate(events, dateD);

			StringBuilder labels = new StringBuilder();
			StringBuilder data = new StringBuilder();
			for (Map.Entry<Long, Integer> event : eventsForDay.entrySet())
			{
				Date eventDate = new Date(event.getKey());
				labels.append("\"");
				if (eventDate.getHours() < 10) labels.append("0");
				labels.append(eventDate.getHours());
				labels.append(":");
				labels.append(eventDate.getMinutes());
				labels.append("\"");
				labels.append(",");

				data.append(event.getValue());
				data.append(", ");
			}

			attributes.put("labels", labels.toString());
			attributes.put("data", data.toString());

			attributes.put("date",  dateS);
			attributes.put("dateSubmit", dateSubmitS);

			attributes.put("activeMenu", "");

			return new ModelAndView(attributes, "findFreeTime.ftl");
		}, new FreeMarkerEngine());

//		get("/signup", (request, response) -> {
//			Map<String, Object> attributes = new HashMap<>();
//			attributes.put("message", "Hello World!");
//
//			return new ModelAndView(attributes, "index.ftl");
//		}, new FreeMarkerEngine());

	}

	private static TreeMap<Long, Integer> getEventsByDate(ArrayList<TreeMap<Long, Integer>> events, Date date)
	{
		for (TreeMap<Long, Integer> eventsForSingleDay : events)
		{
			for (Map.Entry<Long, Integer> event : eventsForSingleDay.entrySet())
			{
				Date eventDate = new Date(event.getKey());
				if (eventDate.getYear() == date.getYear() && eventDate.getMonth() == date.getMonth() && eventDate.getDate() == date.getDate())
				{
					return eventsForSingleDay;
				}
			}
		}

		return null;
	}

	private static String hashSha256(String toHash)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(toHash.getBytes());

			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();
		}
		catch (Exception e) { }

		return "";
	}

}
