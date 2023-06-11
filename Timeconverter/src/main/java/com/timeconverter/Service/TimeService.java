package com.timeconverter.Service;

import org.springframework.stereotype.Service;

@Service

public class TimeService {
	private static final String[] NUMBERS = { "", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	private static final String[] TENS = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninety" };

	public String convertToWord(String time) {
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(3));

		if (hour >= 0 && hour < 12 && minute >= 0 && minute <= 59) {
			return "It's Midday";
		} else if (hour >= 12 && hour <= 23 && minute >= 0 && minute <= 59) {
			return "It's Midnight";
		} else {
			return "Invalid time";
		}
	}

	private String convertHourToWords(int hour) {
		if (hour > 12) {
			hour -= 12;
		}

		if (hour < 20) {
			return NUMBERS[hour];
		} else {
			int tensDigit = hour / 10;
			int onesDigit = hour % 10;
			return TENS[tensDigit] + (onesDigit > 0 ? " " + NUMBERS[onesDigit] : "");
		}
	}

	private String convertMinuteToWords(int minute) {
		if (minute < 20) {
			return NUMBERS[minute];
		} else {
			int tensDigit = minute / 10;
			int onesDigit = minute % 10;
			return TENS[tensDigit] + (onesDigit > 0 ? " " + NUMBERS[onesDigit] : "");
		}
	}
}
