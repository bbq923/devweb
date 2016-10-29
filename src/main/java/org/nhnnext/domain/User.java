package org.nhnnext.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable=false, length=15)
	private String userId;
	
	@Column(nullable=false, length=20, unique=true)
	private String password;
	private String name;
	private String email;

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean matchPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}
		
		return newPassword.equals(password);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}

	public void update(User user) {
		this.name = user.name;
		this.email = user.email;
	}



	public boolean matchId(User user) {
		if (user.id == null) {
			return false;
		}
		
		return user.id.equals(this.id);
	}
	
	
}
