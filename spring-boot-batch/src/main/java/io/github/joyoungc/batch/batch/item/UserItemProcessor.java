package io.github.joyoungc.batch.batch.item;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.joyoungc.batch.config.client.RestClient;
import io.github.joyoungc.common.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author joyoungc
 * @date 2018.06.15
 */
@Slf4j
public class UserItemProcessor implements ItemProcessor<User, User> {

    @Autowired
    private RestClient<User, User> rest;

    @Override
    public User process(User item) throws Exception {
        log.debug("## item : {}", item);
        return this.rest.connection(item, "/login");
    }

}
