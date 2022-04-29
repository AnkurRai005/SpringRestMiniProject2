package org.mp.usermanagementapp.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.mp.usermanagementapp.dao.UserManagementDao;
import org.mp.usermanagementapp.entity.User;
import org.mp.usermanagementapp.exceptions.InvalidEmailIdException;
import org.mp.usermanagementapp.exceptions.InvalidPhoneNoException;
import org.mp.usermanagementapp.exceptions.MinimumAgeException;
import org.mp.usermanagementapp.exceptions.UserNotAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServices implements IUserManagementServices{

	@Autowired
	private UserManagementDao userDao;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	LocalDate today = LocalDate.now();
	
	Logger LOGGER = LoggerFactory.getLogger("UserManagementServices.class");
	
	@Override
	public User saveUserDetails(User user) {
		LocalDate dob = LocalDate.parse(user.getDateOfBirth());
		LOGGER.info("Saving User details for User Id: {}.",user.getUserId());
		if((Period.between(dob, today).getYears())< 18) {
			throw new MinimumAgeException("User should be of minimum 18 Years");
		}
		else if(!Long.toString(user.getPhoneNo()).matches("^\\d{10}$")) {
			throw new InvalidPhoneNoException("Invalid Phone Number");
		}
		else if(!user.getEmail().matches("^(.+)@(.+)$")) {
			throw new InvalidEmailIdException("Invalid Email Id");
		}
		User u = userDao.save(user);
		kafkaTemplate.send("netsurfingzone-topic-1",u);
		LOGGER.info("User details for User Id: {} saved.",user.getUserId());
		return u;
	}

	@Override
	public User updateUserDetails(User user) {
		LOGGER.info("Updating User details for User Id: {}.",user.getUserId());
		userDao.save(user);
		LOGGER.info("User details for User Id: {} updated.",user.getUserId());
		return user;
	}

	@Override
	public User updateUserDOB(User user) {
		LOGGER.info("Updating Date Of Birth for User Id: {}.",user.getUserId());
		Optional<User> userOpt = userDao.findById(user.getUserId());
		User u = userOpt.get();
		u.setDateOfBirth(user.getDateOfBirth());
		userDao.save(u);
		LOGGER.info("Date Of Birth for User Id: {} updated.",u.getUserId());
		return u;
	}

	@Override
	public String deleteUser(Integer userId) {
		LOGGER.info("Deleting User details for User Id: {}.",userId);
		Optional<User> userRecord = userDao.findById(userId);
		if(userRecord.isEmpty()) {
			throw new UserNotAvailableException("User not available for User Id: "+userId);
		}
		userDao.deleteById(userId);
		LOGGER.info("User details for User Id: {} deleted.",userId);
		return "User Deleted Successfully";
	}

	@Override
	public User getUserDetails(Integer userId) {
		LOGGER.info("Finding User details for User Id: {}.",userId);
		Optional<User> userRecord = userDao.findById(userId);
		if(userRecord.isEmpty()) {
			throw new UserNotAvailableException("User not available for User Id: "+userId);
		}
		LOGGER.info("User details for User Id: {} found.",userId);
		return userRecord.get();
	}

	@Override
	public List<User> getUserByFirstNameAndCompany(String firstName, String company) {
		LOGGER.info("Finding All User details with First Name: '{}' and Company: '{}'.",firstName,company);
		List<User> userRecord = userDao.findByFirstNameOrCompany(firstName, company);
		LOGGER.info("All User details with First Name: '{}' and Company: '{}' found.",firstName,company);
		return userRecord;
	}

	@Override
	public List<User> getAllUser() {
		List<User> u = userDao.findAllByOrderByFirstNameAsc();
		return u;
	}

	
}
