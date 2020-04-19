package com.amsidh.mvc.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amsidh.mvc.model.UserDto;
import com.amsidh.mvc.model.UserError;
import com.amsidh.mvc.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationController {

	private final UserService userService;

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> createUserRegistration(@Valid @RequestBody UserDto userDto) {
		log.info("UserRegistrationController's createUserRegistration method called");
		return Optional.ofNullable(userService.findUserByName(userDto.getName())).map(user -> {
			return new ResponseEntity<UserDto>(
					new UserError("Unable to create new user. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}).orElseGet(() -> new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED));
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getAllUsers() {
		log.info("UserRegistrationController's getAllUsers method called");
		List<UserDto> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
		log.info("UserRegistrationController's getUser method called");
		UserDto userById = userService.getUserById(id);
		return Optional.ofNullable(userById).map(user -> new ResponseEntity<UserDto>(user, HttpStatus.OK)).orElseGet( ()->
				new ResponseEntity<UserDto>(new UserError("User with id " + id + " not found"), HttpStatus.NOT_FOUND));
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> updateUserRegistration(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
		log.info("UserRegistrationController's updateUserRegistration method called");
		return Optional.ofNullable(userService.getUserById(id))
				.map(user -> new ResponseEntity<UserDto>(userService.updateUser(id, userDto), HttpStatus.OK))
				.orElseGet( ()-> new ResponseEntity<UserDto>(
						new UserError("Unable to upate. User with id " + id + " not found."), HttpStatus.CONFLICT));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDto> deleteUserRegistration(@PathVariable("id") Long id) {
		log.info("UserRegistrationController's deleteUserRegistration method called");
		return Optional.ofNullable(userService.getUserById(id)).map(user -> {
			userService.deleteUserById(id);
			return new ResponseEntity<UserDto>(HttpStatus.OK);
		}).orElseGet(()-> new ResponseEntity<UserDto>(new UserError("Unable to delete. User with id " + id + " not found."),
				HttpStatus.NOT_FOUND));
	}

}
