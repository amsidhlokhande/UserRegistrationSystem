package com.amsidh.mvc.model.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class FieldValidationErrorDetails {
	private String error_title;
	private int error_status;
	private String error_detail;
	private LocalDateTime error_timeStamp;
	private String error_path;
	private String error_developer_Message;
	private Map<String, List<FieldValidationError>> errors = new HashMap<String, List<FieldValidationError>>();

}
