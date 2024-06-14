package com.expensesplitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensesplitter.dto.UserDTO;
import com.expensesplitter.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Environment env;

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {
		UserDTO createdUser = userService.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDTO userDto) {
		UserDTO updatedUser = userService.updateUser(userId, userDto);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
		UserDTO userDto = userService.getUserById(userId);
		return ResponseEntity.ok(userDto);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> listUsers() {
		List<UserDTO> users = userService.listUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/profile")
	public ResponseEntity<String> getProfile() {
		String profileData = env.getProperty("profile.validate.data", "This is default profile");
		return new ResponseEntity<>(profileData, HttpStatus.OK);
	}
}
