package DataParser;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Time;
import java.util.*;

public class Main
{
    public void organiseEvents(){
    	
    }

	public ArrayList<TreeMap<Long, Integer>> start() throws Exception
	{
		InputStream fin = new URL("http://id.southampton.ac.uk/dataset/events-diary/latest.ics").openStream();
		CalendarBuilder builder = new CalendarBuilder();
		Calendar calendar = builder.build(fin);
		
		TreeSet<Event> events = new TreeSet<Event>(new DateStartComparator());

		for (Iterator i = calendar.getComponents().iterator(); i.hasNext();)
		{
			Component component = (Component) i.next();
			String dateStart = "", dateEnd = "";

			for (Iterator j = component.getProperties().iterator(); j.hasNext();)
			{
				Property property = (Property) j.next();
				
				if (property.getName().equals("DTSTART"))
				{
					dateStart = property.getValue();
				}

				if (property.getName().equals("DTEND"))
				{
					dateEnd = property.getValue();
				}
				
			}
			
			Event event = new Event(dateStart, dateEnd);
			if( event.getDateStart().compareTo(event.getDateEnd()) <=0 ){
				events.add(event);
			} else {
				System.out.println("INVALID DATE" + event.toString());
			}
		}
		
		// prints all events in date format
		/*
		for (Event _event: events){
			System.out.print(_event.getDateStart().toString().substring(0, 16) + "  " + (_event.getDateStart().getYear()) + " --- ");
			System.out.println(_event.getDateEnd().toString().substring(0, 16) + "  " + (_event.getDateEnd().getYear()));
		}
		*/
//___________________________________________________________________________________________*****
		
		//Days days = new Days();
		TimeArray timeMap = new TimeArray();
		//all event start and end times stored in an ordered Map (time, (start/end)
		for (Event _event : events){
			timeMap.addTime(_event);
		}
		
		//adds all times from TimeArray to TimeBlock
		TreeMap<Long, Integer> _daMap = timeMap.toBlock();
		System.out.println("ALL EVENTS ADDED");
		Days ALL_DAYS = new Days(_daMap);
		return ALL_DAYS.dayARRAY;
		
	}
	
	class DateStartComparator implements Comparator<Event>
	{
		public int compare(Event _firstEvent, Event _secondEvent)
		{
			
			int compareDateStart = _firstEvent.getDateStart().compareTo(_secondEvent.getDateStart());
			int compareDateEnd = _firstEvent.getDateEnd().compareTo(_secondEvent.getDateEnd());
			if (compareDateStart == 0)
			{
				return compareDateEnd == 0 ? -1 : compareDateEnd;
			}
			return compareDateStart;
		}
	}
}
