package DataParser;

import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;

public class TimeArray {
	TreeMap<Long, Integer> timeArray = new TreeMap<Long, Integer>();
	
	public void addTime(Event _event){
		Long startTime = _event.getDateStart().getTime();
		Long endTime = _event.getDateEnd().getTime();
		if (timeArray.containsKey(startTime)){
			Integer newSum =timeArray.get(startTime) + 1;
			if (newSum != 0){
			timeArray.put(startTime, newSum);
			} else {
				timeArray.remove(startTime);
			}
		} else {
		timeArray.put(_event.getDateStart().getTime(), 1);
		}
		if (timeArray.containsKey(endTime)){
			Integer newSum =timeArray.get(endTime) - 1;
			if (newSum != 0){
			timeArray.put(endTime, newSum);
			} else {
				timeArray.remove(endTime);
			}
		} else {
		timeArray.put(_event.getDateEnd().getTime(), -1);
		}
	}
	
	public TreeMap<Long, Integer> toBlock(){
		TreeMap<Long, Integer> timeBlocks = new TreeMap<Long, Integer>();
		for (Entry<Long, Integer> entry : timeArray.entrySet())
		{
			Long time = entry.getKey();
			Integer sum = entry.getValue();
			/*if(timeBlocks.containsKey(time)){
				Integer newSum = timeBlocks.get(time) + sum;
	    		timeBlocks.put(time, newSum);
	    	} else {*/
			//Entry<Long, Integer> blockEntry = timeBlocks.lowerEntry(time);
			if (timeBlocks.isEmpty()){
				timeBlocks.put(time, sum);
			} else {
				Integer newSum = timeBlocks.lowerEntry(time).getValue() + sum;
	    		timeBlocks.put(time, newSum);
			}
	    	//}
			
			
		    /*if(type){
		    	if(timeBlocks.containsKey(time)){
		    		timeBlocks.put(time, timeBlocks.get(time)+1);
		    	} else {
		    		timeBlocks.put(time,  1);
		    	}
			
			} else { //type is false > time is end time
				if(timeBlocks.containsKey(time)){
		    		timeBlocks.put(time, timeBlocks.get(time)-1);
		    	} else {
		    		timeBlocks.put(time,  -1);
		    	}
			}*/
		}
		return timeBlocks;	
	}
	
	
}
