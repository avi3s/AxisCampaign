
/**
 * This class is use for sorting purpose
 * */

package com.axis.common;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {
	
	
	/**
	 * User give a list and size 
	 * */
	public static <T> List<T> limit(List<T> t,int size){
		
		List<T> list =new ArrayList<T>();
		
		if(t!=null){
			for(int i=0;i<t.size();i++){
				if(i+1>size)
					break;
				
				list.add(t.get(i));
			}
		}
		
		return list;
	}

}
