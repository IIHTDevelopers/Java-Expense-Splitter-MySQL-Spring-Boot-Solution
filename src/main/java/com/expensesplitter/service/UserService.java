package com.expensesplitter.service;

import java.util.List;

import com.expensesplitter.dto.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO userDto);

	UserDTO getUserById(Long userId);

	UserDTO updateUser(Long userId, UserDTO userDto);

	boolean deleteUser(Long userId);

	List<UserDTO> listUsers();
}
