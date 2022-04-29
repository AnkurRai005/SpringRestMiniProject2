package org.mp.usermanagementapp.dao;

import java.util.List;

import org.mp.usermanagementapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserManagementDao extends JpaRepository<User, Integer>{

	public List<User> findByFirstNameOrCompany(String f, String c);
	
	public List<User> findAllByOrderByFirstNameAsc();
}
