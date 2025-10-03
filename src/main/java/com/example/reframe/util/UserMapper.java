package com.example.reframe.util;

import com.example.reframe.dto.UserDTO;
import com.example.reframe.entity.User;

public class UserMapper {
	public User toEntity(UserDTO userDTO) {
		if(userDTO == null) return null;
		
		User userEntity = new User();
		
		userEntity.setId(userDTO.getId());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setName(userDTO.getName());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPhone(userDTO.getPhone());
		userEntity.setUsertype(userDTO.getUsertype());
		userEntity.setRole(userDTO.getRole());
		
		return userEntity;
	}
	
	public UserDTO toDTO(User userEntity) {
		if(userEntity == null) return null;
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(userEntity.getId());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setName(userEntity.getName());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setPhone(userEntity.getPhone());
		userDTO.setUsertype(userEntity.getUsertype());
		userDTO.setRole(userEntity.getRole());
		
		return userDTO;
	}
}
