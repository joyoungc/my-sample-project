package io.github.joyoungc.jpa.product.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue
	private String productId;
	
	private String productName;
	private Integer price;
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

}
