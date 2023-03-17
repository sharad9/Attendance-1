package com.attendance.enums;

import java.util.Arrays;
import java.util.List;

public class Months {
	private List<String> months ;

	public Months() {
		super();
		this.months =  Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");;
	}

	public String getMonthWithIntValue(int month) {
		try {
		return this.months.get(month);
		}catch (Exception e) {
			return "";
		}
	}

	public int getMonthWithIntValue(String month) {
		return this.months.indexOf(month);
	}

	
	
}
