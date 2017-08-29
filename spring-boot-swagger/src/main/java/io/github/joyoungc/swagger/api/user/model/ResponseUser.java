package io.github.joyoungc.swagger.api.user.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import io.github.joyoungc.common.Code;
import io.github.joyoungc.common.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = Code.Constants.USER)
public class ResponseUser extends User {

	public ResponseUser() {
	}

	public ResponseUser(String userId, String userName, Integer userNumber, String birthday) {
		super.setUserId(userId);
		super.setUserName(userName);
		super.setUserNumber(userNumber);
		super.setBirthday(birthday);
	}

	@ApiModelProperty(value = Code.Constants.USER_ID, required = true, example = "joyoungc", position = Code.Constants.USER_ID_POS)
	@NotBlank
	@Size(max = 20)
	public String getUserId() {
		return super.getUserId();
	}

	@ApiModelProperty(value = Code.Constants.USER_NAME, required = true, example = "조영씨", position = Code.Constants.USER_NAME_POS)
	public String getUserName() {
		return super.getUserName();
	}

	@ApiModelProperty(value = Code.Constants.USER_NUMBER, required = true, position = Code.Constants.USER_NUMBER_POS)
	public Integer getUserNumber() {
		return super.getUserNumber();
	}

	@ApiModelProperty(value = Code.Constants.BIRTHDAY, required = false, example = "20170101", position = Code.Constants.BIRTHDAY_POS)
	public String getBirthday() {
		return super.getBirthday();
	}

}
