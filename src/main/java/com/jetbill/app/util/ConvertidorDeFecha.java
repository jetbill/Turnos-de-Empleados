package com.jetbill.app.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class ConvertidorDeFecha {
	
	private ConvertidorDeFecha() {
	}

	public static Timestamp timestampConverter(LocalDateTime localDateTime) {
		Timestamp timestamp = null;
		if(localDateTime!=null) {
			timestamp = Timestamp.valueOf(localDateTime);
		}
		return timestamp;
	}

	public static LocalDateTime localDateTimeConverter(Timestamp timestamp) {
		LocalDateTime localDateTime = null;
		if(timestamp != null) {
			localDateTime = timestamp.toLocalDateTime();
		}
		return localDateTime;
	}

}
