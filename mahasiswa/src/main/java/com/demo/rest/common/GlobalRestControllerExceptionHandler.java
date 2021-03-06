package com.demo.rest.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GlobalRestControllerExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalRestControllerExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Map handleException(Exception exception) {
		logger.error("error", exception);
		return getExceptionAsMap(exception, new ArrayList<Throwable>());
	}

	private Map getExceptionAsMap(Throwable exception, List<Throwable> exceptions) {
		Map exceptionAsMap = new HashMap();
		exceptionAsMap.put("message", exception.getMessage());
		
		if(exception.getCause() != null && !exceptions.contains(exception)) {
			exceptions.add(exception);
			Map causeAsMap = getExceptionAsMap(exception.getCause(), exceptions);
			exceptionAsMap.put("cause", causeAsMap);
		}
		
		return exceptionAsMap;
	}
	
}