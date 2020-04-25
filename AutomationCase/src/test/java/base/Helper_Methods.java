package base;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper_Methods {

	//This method combines two equal sized arrays to a HashMap object
	public HashMap<String, String> combineArrays(ArrayList<String> list1, ArrayList<String> list2){
		HashMap<String, String> combinedMap = new HashMap<String, String>();
		if(list1.size() == list2.size()) {	
			for (int i = 0; i < list1.size(); i++) {
				combinedMap.put(list1.get(i), list2.get(i));
			}
		}
		return combinedMap;
	}
	
	//This method adds one HashMap object's values to other
	public HashMap<String, String> combineHashMaps(HashMap<String, String> map1, HashMap<String, String> map2){
		for (String key : map2.keySet()) {
			map1.put(key, map2.get(key));
		}
		return map1;
	}
	
	//This method calculates how many pages the search result is
	public int calculateTotalPageNumber(int totalResults) {
		int totalPageNumber = 0;
		if(totalResults > 10) {
			totalPageNumber = totalResults / 10;
			if(totalResults - (totalPageNumber * 10) > 0) {
				totalPageNumber += 1;
			}
		}
		return totalPageNumber;
	}
}
