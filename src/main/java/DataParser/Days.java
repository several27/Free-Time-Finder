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
		Iterator TEST = eventBlocks.keySet().iterator(); 
		Long firstEntry =  (Long) it.next();
		daySubTree.put(firstEntry, eventBlocks.get(firstEntry));
		prevEntry = (Long) firstEntry;
		
		while (it.hasNext()){
			
			Long entry =  (Long) it.next();
			day1 = (new Date(entry)).getDay();
			day2 = (new Date(prevEntry)).getDay();
			if(day1 == day2){
				daySubTree.put(entry, eventBlocks.get(entry));
			}else{
				TreeMap<Long, Integer> additionalArray = new TreeMap<Long, Integer>(new MyComp());
				additionalArray.putAll(daySubTree);

				dayARRAY.add(additionalArray);
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
}


