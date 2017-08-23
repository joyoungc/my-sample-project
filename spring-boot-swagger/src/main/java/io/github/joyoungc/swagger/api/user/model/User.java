package io.github.joyoungc.swagger.api.user.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.github.joyoungc.common.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value=Code.Constants.USER)
public class User {

	private String userId;
	private String userName;
	private Integer userNumber;
	private String birthday;

	public User() {
	}

	public User(String userId, String userName, Integer userNumber, String birthday) {
		this.userId = userId;
		this.userName = userName;
		this.userNumber = userNumber;
		this.birthday = birthday;
	}

	@ApiModelProperty(value = Code.Constants.USER_ID, required = true, example = "joyoungc", position = Code.Constants.USER_ID_POS)
	@NotEmpty
	@Size(max = 20)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ApiModelProperty(value = Code.Constants.USER_NAME, required = true, example = "조영씨", position = Code.Constants.USER_NAME_POS)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ApiModelProperty(value = Code.Constants.USER_NUMBER, required = true, position = Code.Constants.USER_NUMBER_POS)
	public Integer getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}

	@ApiModelProperty(value = Code.Constants.BIRTHDAY, required = false, example = "19881108", position = Code.Constants.BIRTHDAY_POS)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
