package com.amsidh.mvc.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.amsidh.mvc.model.error.ErrorType;
import com.amsidh.mvc.model.error.FieldValidationError;
import com.amsidh.mvc.model.error.FieldValidationErrorDetails;

import lombok.RequiredArgsConstructor;


@ControllerAdvice
@RequiredArgsConstructor
public class RestValidationHandler {

	private final MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<FieldValidationErrorDetails> handeValidationError(MethodArgumentNotValidException methodArgumentNotValidException,
			HttpServletRequest httpServletRequest) {
		FieldValidationErrorDetails fieldValidationErrorDetails = new FieldValidationErrorDetails();

		fieldValidationErrorDetails.setError_timeStamp(LocalDateTime.now());
		fieldValidationErrorDetails.setError_status(HttpStatus.BAD_REQUEST.value());
		fieldValidationErrorDetails.setError_title("Field Validation Error");
		fieldValidationErrorDetails.setError_detail("Inut Field Validation Failed");
		fieldValidationErrorDetails.setError_developer_Message(fieldValidationErrorDetails.getClass().getName());
		fieldValidationErrorDetails.setError_path(httpServletRequest.getRequestURI());

		BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
		Map<String, List<FieldValidationError>> errors = bindingResult.getFieldErrors().parallelStream()
		                              .map(fieldError-> new FieldValidationError(fieldError.getField(),messageSource.getMessage(fieldError.getDefaultMessage(),null, LocaleContextHolder.getLocale()),ErrorType.ERROR))
		                              .collect(Collectors.groupingBy(FieldValidationError::getField));
		
		fieldValidationErrorDetails.getErrors().putAll(errors);
		
		return new ResponseEntity<FieldValidationErrorDetails>(fieldValidationErrorDetails, HttpStatus.BAD_REQUEST);
	}
}
