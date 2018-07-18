package io.github.joyoungc.batch.batch.item;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import io.github.joyoungc.common.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author joyoungc
 * @date 2018.06.15
 */
@Slf4j
public class UserItemWriter implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> items) throws Exception {
		// TODO 필요시 후처리 차후 등록
		log.debug("## write size : {}", items.size());
	}

}
