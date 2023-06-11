package com.timeconverter.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timeconverter.Exception.TimeConversionException;
import com.timeconverter.Service.TimeService;

import jakarta.servlet.http.HttpServlet;

@RestController
@RequestMapping("/time")
public class Timecontroller {

	private final TimeService timeService;

	@Autowired
	public Timecontroller(TimeService timeService) {
		this.timeService = timeService;
	}

	@GetMapping("/current")
	public String convertCurrentTimeToWords() {
		LocalDateTime currentTime = LocalDateTime.now();
		String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		return timeService.convertToWord(formattedTime);
	}

	@GetMapping("/now")
	public String convertToWord(@RequestParam("time") String time) {
		return timeService.convertToWord(time);
	}

	@GetMapping("/input")
	public String convertUserInputToWord(HttpServlet request) {
		String time = request.getInitParameter("time");
		return timeService.convertToWord(time);
	}

	@ExceptionHandler(TimeConversionException.class)
	public ResponseEntity<String> handleTimeConversionException(TimeConversionException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

	}
}
