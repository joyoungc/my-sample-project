package io.github.joyoungc.swagger.api.user.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.common.Constants;
import io.github.joyoungc.swagger.api.user.model.ResponseUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {Constants.API_TAG_USER})
@RestController
@RequestMapping("/admin/users")
public class UserController {

    final List<ResponseUser> users = Arrays.asList(
            new ResponseUser("joyoungc", "Aiden", 10000001, null),
            new ResponseUser("sampleuser1", "User1", 10000002, "19881111"),
            new ResponseUser("sampleuser2", "User2", 10000003, "19920123"));

    @ApiOperation(value = "사용자 조회")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseUser getUser(@PathVariable String userId) {
        ResponseUser resultUser = users.stream().filter(p -> p.getUserId().equals(userId)).findFirst().orElse(null);
        return resultUser;
    }

    @ApiOperation(value = "사용자 리스트")
    @RequestMapping(method = RequestMethod.GET)
    public List<ResponseUser> selectUsers() {
        return users;
    }

}