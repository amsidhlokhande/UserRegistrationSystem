package com.amsidh.mvc.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserError extends UserDto {
	private static final long serialVersionUID = 8790204208375861042L;
	private String errorMessage;

	public UserError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
