package io.github.joyoungc.swagger.api.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.common.Code;
import io.github.joyoungc.swagger.api.user.model.User;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin/users")
public class UserController {
	
	List<User> users;
	
	@PostConstruct
	public void init() {
		users = new ArrayList<>();
		User user1 = new User("joyoungc", "Aiden", 10000001, null);
		User user2 = new User("sampleuser1", "User1", 10000002, "19881111");
		User user3 = new User("sampleuser2", "User2", 10000003, "19920123");
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}

	@ApiOperation(value="사용자 조회", tags = {Code.Constants.API_TAG_USER})
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		User resultUser = users.stream().filter(p -> p.getUserId().equals(userId)).findFirst().orElse(null);
		return resultUser;
	}
	
	@ApiOperation(value = "사용자 리스트", tags = {Code.Constants.API_TAG_USER})
	@RequestMapping(method = RequestMethod.GET)
	public List<User> selectUsers() {
		return users;
	}

}