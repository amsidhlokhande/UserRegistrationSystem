package com.amsidh.mvc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amsidh.mvc.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query(name = "UserEntity.findUserByName", value = "select u from UserEntity u where u.name = :userName")
	Optional<UserEntity> findUserByName(@Param("userName") String name);

}
