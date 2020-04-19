package com.amsidh.mvc.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto implements Serializable {

	private static final long serialVersionUID = 5879998031811847470L;
	private Long id;
	
	@NotEmpty(message = "error.user.name.empty")
	@Length(min = 2, max = 50, message = "error.user.name.length")
	private String name;
	
	@NotEmpty(message = "error.user.address.empty")
	@Length(min = 5, max = 500, message = "error.user.address.length")
	private String address;
	
	@NotEmpty(message = "error.user.email.empty")
	@Email(message = "error.user.email.email")
	@Length(min = 5, max = 50, message = "error.user.email.length")
	private String email;
}
