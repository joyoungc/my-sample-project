package io.github.joyoungc.jpa.user.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.github.joyoungc.jpa.product.model.Dept;
import io.github.joyoungc.jpa.product.model.Product;
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
