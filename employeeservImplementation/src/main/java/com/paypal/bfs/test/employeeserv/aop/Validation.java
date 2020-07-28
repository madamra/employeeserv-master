package com.paypal.bfs.test.employeeserv.aop;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.paypal.bfs.test.employeeserv.annatation.FieldValidator;
import com.paypal.bfs.test.employeeserv.exception.ValidationFailedException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Validation {

	public static Set<String> validate(Object object, HashSet<String> errorMessages) throws ValidationFailedException {

		Assert.notNull(object, "Object should not be null");

		Stream.of(object.getClass().getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(FieldValidator.class))
				.map(field -> getAnnotationFieldValue(field, object)).forEach(map -> map.forEach((key, valueObj) -> {

					valueObj.forEach((k, v) -> {

						Field field = key;

						Object fieldObj = null;

						try {
							fieldObj = field.get(object);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}

						FieldValidator fieldValidator = k;

						Object value = v;

						String message = fieldValidator.message();

						if (null != value) {

							if (fieldObj instanceof List || fieldObj instanceof Set) {
								Collection<?> collection = (Collection<?>) fieldObj;
								collection.forEach(
										obj -> analyse(fieldValidator, obj, errorMessages, message, field));
							} else {
								analyse(fieldValidator, value, errorMessages, message, field);
							}

						} else {

							if (fieldValidator.required())
								errorMessages.add(isRequired(fieldValidator, field));

						}
					});

				}));

		return errorMessages;
	}


	private static void analyse(FieldValidator fieldValidator, Object value, HashSet<String> errorMessages,
			String message, Field field) {
		String val = String.valueOf(value);

		if (fieldValidator.required()) {
			if (StringUtils.isEmpty(val)) {
				if (message.isEmpty())
					message = field.getName().concat(" is required,it should not be empty");
				errorMessages.add(message);
			}
		}

		if (!StringUtils.isEmpty(fieldValidator.regex()) && !StringUtils.isEmpty(val)) {
			boolean validValue = isValidValue(fieldValidator.regex(), val);
			if (!validValue) {
				if (message.isEmpty())
					message = field.getName().concat(" Value does not match with Regex Configured");

				errorMessages.add(message);
			}
		}

		if (fieldValidator.min() > 0) {

			if (val.length() < fieldValidator.min()) {
				if (message.isEmpty())
					message = field.getName().concat(
							" length should be greater than or equal to " + fieldValidator.min() + " characters");
				errorMessages.add(message);
			}

		}
		if (fieldValidator.max() > 0) {

			if (val.length() > fieldValidator.max()) {
				if (message.isEmpty())
					message = field.getName()
							.concat(" length should be less than or equal to " + fieldValidator.max() + " characters");
				errorMessages.add(message);
			}

		}
	}

	public static Map<Field, Map<FieldValidator, Object>> getAnnotationFieldValue(Field field, Object object) {

		try {

			Map<Field, Map<FieldValidator, Object>> result = new HashMap<Field, Map<FieldValidator, Object>>();

			Map<FieldValidator, Object> map = new HashMap<FieldValidator, Object>();

			FieldValidator fieldValidator = field.getAnnotation(FieldValidator.class);

			field.setAccessible(true);

			map.put(fieldValidator, field.get(object));

			result.put(field, map);

			return result;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	public static String isRequired(FieldValidator fieldValidator, Field field) {
		if (fieldValidator.message().isEmpty())
			return field.getName().concat(" is required.");
		else
			return fieldValidator.message();

	}

	public static boolean isValidValue(String regex, String value) {
		try {
			if (Pattern.compile(regex) != null) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(value);
				return matcher.matches();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Invalid Regex Configured: " + regex
					+ " Validtion is skipping, Handle it in the Application Side.");
		}
		return false;
	}
}
