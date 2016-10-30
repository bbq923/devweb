package org.nhnnext.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity {
	
	//@Column(unique=true, nullable=false, length=15)
	
	@Column(nullable=false, length=20, unique=true)
	@JsonProperty
	private String userId;
	
	@JsonIgnore // 명시적으로 JSON으로 처리하지 않겠다고 표시
	private String password;

	@JsonProperty
	private String name;
	
	@JsonProperty
	private String email;

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
		return "User [" + super.toString() + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}

	public void update(User user) {
		this.name = user.name;
		this.email = user.email;
	}
	
	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}
		
		return newId.equals(getId());
	}
	
}
