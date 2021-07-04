package demo.utils;

import java.util.Map;

public class MapUtil {
	public static String mapToString(Map<String, String> map) {
		StringBuilder stringBuilder = new StringBuilder();

		for (String key : map.keySet()) {
			
			String value = map.get(key);
			stringBuilder.append(value);
		}

		return stringBuilder.toString();
	}

	
}
