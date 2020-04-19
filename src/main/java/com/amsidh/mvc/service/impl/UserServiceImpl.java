package com.amsidh.mvc.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amsidh.mvc.entities.UserEntity;
import com.amsidh.mvc.mapper.UserMapper;
import com.amsidh.mvc.model.UserDto;
import com.amsidh.mvc.repositories.UserRepository;
import com.amsidh.mvc.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, Serializable {
	private static final long serialVersionUID = 8161683204063680210L;
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		log.info("Creating user with details " + userDto);
		return userMapper.toUserDto(userRepository.saveAndFlush(userMapper.toUserEntity(userDto)));
	}

	@Override
	public UserDto getUserById(Long id) {
		log.info("Fetching user by id " + id);
		/*
		 * return userMapper.toUserDto( userRepository.findById(id).orElseThrow(() ->
		 * new RuntimeException("No User Found with ID " + id)));
		 */
		return userMapper.toUserDto(userRepository.findById(id).orElse(null));
	}

	@Override
	public List<UserDto> getAllUsers() {
		log.info("Retrive all the users");
		return userMapper.toUserDtos(userRepository.findAll());
	}

	@Override
	public UserDto updateUser(Long id, UserDto userDto) {
		log.info("Fetching User with id " + id);
		UserEntity oldUserEntity = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No User Found with ID " + id));
		log.info("Old user details " + oldUserEntity);
		oldUserEntity.setName(userDto.getName());
		oldUserEntity.setAddress(userDto.getAddress());
		oldUserEntity.setEmail(userDto.getEmail());
		log.info("Update old user with new user " + oldUserEntity);
		return userMapper.toUserDto(userRepository.saveAndFlush(oldUserEntity));
	}

	@Override
	public void deleteUserById(Long id) {
		log.info("Deleting user by id " + id);
		userRepository.deleteById(id);
	}

	@Override
	public UserDto findUserByName(String name) {
		return userMapper.toUserDto(userRepository.findUserByName(name).orElse(null));
	}

}
