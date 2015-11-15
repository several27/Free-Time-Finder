package DataParser;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Set;
import java.sql.Time;
import java.util.Date;

public class Days {
	ArrayList<TreeMap<Long, Integer>> dayARRAY = new ArrayList<>();
	
	public Days(TreeMap<Long, Integer> eventBlocks){
		//splits time into days, adds each subTreeMap (each day) to a separate arrayList index
		//for (Map.Entry<Long, Integer> entry : info.entrySet()){
		Long prevEntry;
		Iterator it = eventBlocks.keySet().iterator();
		int dayIndex = 0;
		TreeMap<Long, Integer> daySubTree = new TreeMap<Long, Integer>(new MyComp());
		int day1, day2;
			//first time:
		Iterator TEST = eventBlocks.keySet().iterator(); //**************
//		System.out.println(TEST);
		Long firstEntry =  (Long) it.next();
		daySubTree.put(firstEntry, eventBlocks.get(firstEntry));
		prevEntry = (Long) firstEntry;
		
		while (it.hasNext()){
			
			Long entry =  (Long) it.next();
			day1 = (new Date(entry)).getDay();
			day2 = (new Date(prevEntry)).getDay();
//			System.out.println("Last event: Day: " + day2 + "   This event: Day: " + day1);
			if(day1 == day2){
				daySubTree.put(entry, eventBlocks.get(entry));
			}else{
				TreeMap<Long, Integer> additionalArray = new TreeMap<Long, Integer>(new MyComp());
				additionalArray.putAll(daySubTree);

				dayARRAY.add(additionalArray);
//					System.out.println("Events today: " + daySubTree.size());
					daySubTree.clear();
				
			}
			prevEntry = (Long) entry;
		}
	}
	
	class MyComp implements Comparator<Long>{
		 
	    @Override
	    public int compare(Long l1, Long l2) {
	        return l1.compareTo(l2);
	    }
	     
	}

	
	//public ArrayList<int> returnDayCount(){
		
		
	//}
	
	
	/*
	public void printDays(){
		int dayNum = 0;
		for (TreeMap<Long, Integer> _days: dayARRAY){
			dayNum ++;
			System.out.println("NEW DAY");
			System.out.print("   Day num" + dayNum);
			Iterator<Long> it = _days.keySet().iterator();
			while(it.hasNext()){
				Long block = (Long) it.next();
				System.out.print("Time Value: " + new Date(block).toString());	
				System.out.println("   Events : " + _days.get(block));
			}
			
		}
		System.out.println("ALL EVENTS ADDED");
	}
	
	
	*/
	
	
	
	
	
	
	
	// OLD CODE:::::::::::::::::::::::::::::::::
	/*
	public void addEvent(Event _event){
		long startTime = _event.getDateStart().getTime();
		long endTime = _event.getDateEnd().getTime();
		System.out.println("Doing one now");
		if (timeBlocks.containsKey(startTime)){
			Integer i = timeBlocks.get(startTime) + 1;
			timeBlocks.put(startTime, i);
		} else {
			if (timeBlocks.size() ==0 ){
				timeBlocks.put(startTime, 1 );
				System.out.println("CRAP1");
			} else {
				Integer previousVal = timeBlocks.get(timeBlocks.lowerKey(startTime));
				timeBlocks.put(startTime, previousVal +1);
			}
		}
		 System.out.println("Start Done, End Time: " + new Time (endTime).toString().substring(0, 5) );
		if (timeBlocks.containsKey(endTime)){
			Integer i = timeBlocks.get(endTime) - 1;
			timeBlocks.put(endTime, i);
		} else {
			if (timeBlocks.size() == 1 ){
				timeBlocks.put(endTime, 0);
				System.out.println("CRAP2");
			} else {
				Integer previousVal = timeBlocks.get(timeBlocks.lowerKey(endTime));
				timeBlocks.put(endTime, previousVal -1);
			}
		
		}
		System.out.println("Done add 1");
	}
	public void printAll(){
		System.out.println("PRINTING");
		Iterator it = timeBlocks.keySet().iterator();
		while(it.hasNext()){
			Long block = (Long) it.next();
			System.out.print("Time Value: " + new Time(block).toString().substring(0, 5));	
			System.out.println("   Events : " + timeBlocks.get(block));
		}
		//System.out.println(timeBlocks.toString());
	}
	
		TimeBlock timeBlock = new TimeBlock(
				_event.getDateStart().getTime(),
				_event.getDateEnd().getTime(), (int) 1);
		
		timeBlocks.add(timeBlock);
		System.out.println(_event.getDateEnd().getTime());
	
	
	/*
	ArrayList<TimeBlock> timeBlocks = new ArrayList<TimeBlock>();
	
	public void addEvent(Event _event){
		long startTime = _event.getDateStart().getTime();
		long endTime = _event.getDateEnd().getTime();
		if (timeBlocks.contains())
		/*TimeBlock timeBlock = new TimeBlock(
				_event.getDateStart().getTime(),
				_event.getDateEnd().getTime(), (int) 1);
		
		timeBlocks.add(timeBlock);
		System.out.println(_event.getDateEnd().getTime());
		//create days object in main
	//send each event to day object to add to group of TimeBlocks
		
	}
	*/
}


