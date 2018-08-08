package com.example.helloworld.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.User;
import com.example.helloworld.UserDAO;
import com.example.helloworld.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(User.class);

	@Autowired
	private UserRepository repository;

	// Get All Users
	@GetMapping("/users")
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = repository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// Get single user
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") int id) {
		return repository.findById(id);

	}

	// Add user
	@PostMapping("/users")
	public User createUser(User user) {
		UserDAO.addUser(user);
		return repository.save(user);
	}

	// Delete user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		repository.delete(repository.findById(id));

	}

	// Update user
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user){
		  User currentUser = repository.findById(id);
		  if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	       }
		  currentUser.setFirstName(user.getFirstName());
		  currentUser.setLastName(user.getLastName());
		  UserDAO.updateUser(currentUser);
		  repository.save(currentUser);
	      return new ResponseEntity<User>(currentUser, HttpStatus.OK);
		//return repository.save(user);
	}

	/*
	 * public String welcome(@ModelAttribute User user, Model model) {
	 * 
	 * List<User> users = repository.findAll(); model.addAttribute("users", users);
	 * 
	 * return "userform"; }
	 */

	@PostMapping("/save")
	public String save(@ModelAttribute User user, Model model) {
		model.addAttribute("user", user);
		repository.save(user);

		return "result";
	}

}