package com.amsidh.mvc.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.amsidh.mvc.entities.UserEntity;
import com.amsidh.mvc.model.UserDto;

@Mapper
public interface UserMapper {

	UserDto toUserDto(UserEntity userEntity);

	UserEntity toUserEntity(UserDto userDto);

	List<UserDto> toUserDtos(List<UserEntity> userEntities);

}
