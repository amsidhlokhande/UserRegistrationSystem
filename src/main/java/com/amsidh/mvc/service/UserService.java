package com.amsidh.mvc.service;

import java.util.List;

import com.amsidh.mvc.model.UserDto;

public interface UserService {

	//UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	UserDto createUser(UserDto userDto);

	UserDto getUserById(Long id);

	List<UserDto> getAllUsers();

	UserDto updateUser(Long id, UserDto userDto);

	void deleteUserById(Long id);
	
	UserDto findUserByName(String name);

}
