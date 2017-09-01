package io.github.joyoungc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class CallController {
	
	@Autowired
	CallService callService;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public void callUser(@RequestParam(required=true) String msg) {
		callService.callUser(msg);
	}

}
