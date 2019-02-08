package io.github.joyoungc.web.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ContentTypeExample {

    @NotNull
    private Long id;
    @NotBlank
    private String title;
    private String content;

}
