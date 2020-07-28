package com.paypal.bfs.test.employeeserv;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.paypal.bfs.test.employeeserv.exception.ValidationFailedException;

public class Utility {

	public static ZonedDateTime convertFromString(String dateInddMMyyyy, boolean isStartDate) {
		try {

			if (StringUtils.isBlank(dateInddMMyyyy)) {
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				dateInddMMyyyy = formatter.format(date);
			}
			if (isStartDate)
				return ZonedDateTime.of(LocalDate.parse(dateInddMMyyyy, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
						LocalTime.of(0, 00), ZoneId.systemDefault());
			else
				return ZonedDateTime.of(LocalDate.parse(dateInddMMyyyy, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
						LocalTime.of(23, 59), ZoneId.systemDefault());
		} catch (Exception e) {
			throw new ValidationFailedException("DATE_IS_NOT_VALID_OR_NOT_IN_DD/MM/YYYY_FORMAT");
		}
	}
}
