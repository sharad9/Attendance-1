package com.attendance.enums;


import java.util.Arrays;
import java.util.List;

public class Days {
	private List<String> days ;

	public Days() {
		super();
		this.days =  Arrays.asList("SUNDAY,MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY");
	}

	public String getDayWithIntValue(int day) {
		try {
		return this.days.get(day);
		}catch (Exception e) {
			return "";
		}
	}

	public int getDayWithIntValue(String day) {
		return this.days.indexOf(day);
	}

	
	
}
