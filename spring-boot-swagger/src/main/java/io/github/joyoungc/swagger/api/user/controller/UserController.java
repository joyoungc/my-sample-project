package io.github.joyoungc.swagger.api.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.common.Code;
import io.github.joyoungc.swagger.api.user.model.ResponseUser;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin/users")
public class UserController {
	
	List<ResponseUser> users;
	
	@PostConstruct
	public void init() {
		users = new ArrayList<>();
		ResponseUser user1 = new ResponseUser("joyoungc", "Aiden", 10000001, null);
		ResponseUser user2 = new ResponseUser("sampleuser1", "User1", 10000002, "19881111");
		ResponseUser user3 = new ResponseUser("sampleuser2", "User2", 10000003, "19920123");
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}

	@ApiOperation(value="사용자 조회", tags = {Code.Constants.API_TAG_USER})
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseUser getUser(@PathVariable String userId) {
		ResponseUser resultUser = users.stream().filter(p -> p.getUserId().equals(userId)).findFirst().orElse(null);
		return resultUser;
	}
	
	@ApiOperation(value = "사용자 리스트", tags = {Code.Constants.API_TAG_USER})
	@RequestMapping(method = RequestMethod.GET)
	public List<ResponseUser> selectUsers() {
		return users;
	}

}