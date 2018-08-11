package com.water.user.springboot.util;

import java.util.Map;

public class StringUtils {
	
	
	public static boolean isValidRequest(Map<String, String> queryMap, String key) {
		if(queryMap.get(key) == null || queryMap.get(key).isEmpty())
			return false;
		return true;
	}

}
