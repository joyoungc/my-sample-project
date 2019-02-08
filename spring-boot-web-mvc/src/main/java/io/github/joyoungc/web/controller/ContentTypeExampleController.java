package io.github.joyoungc.web.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.web.model.ContentTypeExample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/content")
public class ContentTypeExampleController {

    @PostMapping("/form/model")
    public ContentTypeExample postFormModel(ContentTypeExample ex) {
        log.info("## postFormModel ex : {}", ex);
        return ex;
    }

    @PostMapping("/form/map")
    public Map<String, Object> postFormMap(@RequestParam Map<String, Object> ex) {
        log.info("## postFormMap ex : {}", ex);
        return ex;
    }

    @PostMapping("/json/model")
    public ContentTypeExample postJsonModel(@RequestBody ContentTypeExample ex) {
        log.info("## postJsonModel ex : {}", ex);
        return ex;
    }

    @PostMapping("/json/map")
    public Map<String, Object> postJsonMap(@RequestBody Map<String, Object> ex) {
        log.info("## postJsonMap ex : {}", ex);
        return ex;
    }

}
