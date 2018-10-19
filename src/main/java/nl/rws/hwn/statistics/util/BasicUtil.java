package nl.rws.hwn.statistics.util;

import java.util.UUID;

public class BasicUtil {

	public static String getUniqueId(){
		return UUID.randomUUID().toString();
	}
}
