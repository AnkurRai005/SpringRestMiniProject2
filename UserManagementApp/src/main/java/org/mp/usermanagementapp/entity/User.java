package org.mp.usermanagementapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "userId")
	Integer userId;
	
	@Column(name="firstName")
	String firstName;
	
	@Column(name="secondName")
	String secondName;
	
	@Column(name="phoneNo")
	Long phoneNo;
	
	@Column(name="dateOfBirth")
	String dateOfBirth;
	
	@Column(name="email")
	String email;
	
	@Column(name="company")
	String company;
}
