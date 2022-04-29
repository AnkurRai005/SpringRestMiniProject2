package org.mp.usermanagementapp.controller;

import java.util.List;

import org.mp.usermanagementapp.entity.User;
import org.mp.usermanagementapp.services.IUserManagementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserManagementAppController {

	@Autowired
	private IUserManagementServices userService;
	
	@PostMapping("/users")
	public ResponseEntity<String> saveUserDetails(@RequestBody User user){
		User u = userService.saveUserDetails(user);
		return new ResponseEntity<String>("User: "+u.getUserId()+" saved successfully", HttpStatus.CREATED);
	}
	
	@PutMapping("/userc")
	public ResponseEntity<String> updateUserDetails(@RequestBody User user){
		User u = userService.updateUserDetails(user);
		return new ResponseEntity<String>("User: "+u.getUserId()+" updated successfully", HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/dobc")
	public ResponseEntity<String> updateDOB(@RequestBody User user){
		User u = userService.updateUserDOB(user);
		return new ResponseEntity<String>("User: "+u.getUserId()+" Date of Birth updated successfully", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/userd/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<String>("User: "+userId+" Deleted", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/userg/{userId}")
	public ResponseEntity<User> getUserDetails(@PathVariable("userId") Integer userId){
		User u = userService.getUserDetails(userId);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@GetMapping("/userg/{fname}/{company}")
	public ResponseEntity<List<User>> getUserByFirstNameAndCompany(@PathVariable("fname") String fname, @PathVariable("company") String company){
		List<User> u = userService.getUserByFirstNameAndCompany(fname, company);
		return new ResponseEntity<List<User>>(u, HttpStatus.OK);
	}
	
	@GetMapping("/usera")
	public ResponseEntity<List<User>> getUserDetails(){
		List<User> u = userService.getAllUser();
		return new ResponseEntity<List<User>>(u, HttpStatus.OK);
	}
}
