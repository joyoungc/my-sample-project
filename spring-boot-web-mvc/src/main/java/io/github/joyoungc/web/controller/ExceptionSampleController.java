package io.github.joyoungc.web.controller;

import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionSampleController {

	@PostMapping("/post")
	public void throwsExceptionPost(@RequestBody Map<String, String> body) {

		if (!StringUtils.isEmpty(body.get("title"))) {
			throw new BaseException("Post : 일부러 낸 오류다!!!");
		}
	}

	@GetMapping("/get")
	public void throwsExceptionGet(@RequestParam Map<String, String> params) {
		log.debug("## params : {}", params);
		throw new BaseException("Get : 일부러 낸 오류다!!!");
	}

}
