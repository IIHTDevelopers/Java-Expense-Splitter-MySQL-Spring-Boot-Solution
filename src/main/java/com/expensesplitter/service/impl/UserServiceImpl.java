package com.expensesplitter.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensesplitter.dto.UserDTO;
import com.expensesplitter.entity.User;
import com.expensesplitter.exception.ResourceNotFoundException;
import com.expensesplitter.repo.UserRepository;
import com.expensesplitter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = convertToEntity(userDto);
		User savedUser = userRepository.save(user);
		return convertToDto(savedUser);
	}

	@Override
	public UserDTO getUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return convertToDto(user);
	}

	@Override
	public UserDTO updateUser(Long userId, UserDTO userDto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		User updatedUser = userRepository.save(user);
		return convertToDto(updatedUser);
	}

	@Override
	public boolean deleteUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
		return true;
	}

	@Override
	public List<UserDTO> listUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private UserDTO convertToDto(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		return dto;
	}

	private User convertToEntity(UserDTO userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		return user;
	}
}
