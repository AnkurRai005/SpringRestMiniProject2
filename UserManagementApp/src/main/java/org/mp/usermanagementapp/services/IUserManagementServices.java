package org.mp.usermanagementapp.services;

import java.util.List;

import org.mp.usermanagementapp.entity.User;


public interface IUserManagementServices {

	User saveUserDetails(User user);
	
	User updateUserDetails(User user);
	
	User updateUserDOB(User user);
	
	String deleteUser(Integer userId);
	
	User getUserDetails(Integer userId);
	
	List<User> getUserByFirstNameAndCompany(String firstName, String company);
	
	List<User> getAllUser();
}
