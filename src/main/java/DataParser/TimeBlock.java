package DataParser;

import java.sql.Time;

public class TimeBlock {
	private Time startTime;
	private int noOfEvents;
	
	public TimeBlock(Long _startTime, int _noOfEvents){
		this.startTime = new Time(_startTime);
		this.noOfEvents = _noOfEvents;
	}
	
}
