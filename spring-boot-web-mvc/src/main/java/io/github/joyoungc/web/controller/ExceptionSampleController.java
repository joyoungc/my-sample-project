package io.github.joyoungc.web.controller;

import java.util.Map;

import io.github.joyoungc.common.CommonError;
import io.github.joyoungc.common.exception.ApplicationException;
import io.github.joyoungc.web.model.ContentTypeExample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionSampleController {

	@PostMapping("/post")
	public void throwsExceptionPost(@Valid @RequestBody ContentTypeExample body) {
	/*	if (!StringUtils.isEmpty(body.get("title"))) {
			throw new BaseException("Post : 일부러 낸 오류다!!!");
		}*/
	}

	@GetMapping("/get")
	public void throwsExceptionGet(@RequestParam Map<String, String> params) {
		log.debug("## params : {}", params);
		throw new ApplicationException(CommonError.COMMON_BAD_REQUEST);
	}

	@GetMapping("/params")
	public void throwsExceptionParams(@RequestParam("title") String title) {
		throw new ApplicationException(CommonError.COMMON_BAD_REQUEST);
	}

}
