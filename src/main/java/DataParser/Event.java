package DataParser;

import java.util.Date;

public class Event
	{
		private Date dateStart;
		private Date dateEnd;

		public Event(String dateStart, String dateEnd)
		{
			this.dateStart = new Date(
					Integer.parseInt(dateStart.substring(0, 4)),
					Integer.parseInt(dateStart.substring(4, 6)) - 1,
					Integer.parseInt(dateStart.substring(6, 8)),
					Integer.parseInt(dateStart.substring(9, 11)),
					Integer.parseInt(dateStart.substring(11, 13)),
					Integer.parseInt(dateStart.substring(13, 15))
			);
			this.dateEnd = new Date(
					Integer.parseInt(dateEnd.substring(0, 4)),
					Integer.parseInt(dateEnd.substring(4, 6)) - 1,
					Integer.parseInt(dateEnd.substring(6, 8)),
					Integer.parseInt(dateEnd.substring(9, 11)),
					Integer.parseInt(dateEnd.substring(11, 13)),
					Integer.parseInt(dateEnd.substring(13, 15))
			);
		}

		public Date getDateEnd()
		{
			return dateEnd;
		}

		public Date getDateStart()
		{
			return dateStart;
		}
	}
