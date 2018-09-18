package io.github.joyoungc.jpa.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	
	@Id
	private String userId;
	
	@Column(nullable=false)
	private String name;
	
	@Column(length = 3)
	private Integer age;
	
	private String email;
	
	private String birthday;
	
	/*
	@OneToMany
	List<Product> products;
	
	@ManyToMany
	List<Dept> depts;
	*/
	
}
