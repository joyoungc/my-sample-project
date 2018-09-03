package io.github.joyoungc.jpa.product.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Dept {
	
	@Id
	@GeneratedValue
	private Long deptId;
	
	@Column(nullable=true)
	private String name;
	
/*	@ManyToMany
	List<User> users;*/
	

}
