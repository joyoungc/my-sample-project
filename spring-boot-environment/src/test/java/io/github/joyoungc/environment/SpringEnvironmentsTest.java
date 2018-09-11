package io.github.joyoungc.environment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringEnvironmentsTest {
	
	@Autowired
	MessageSource messageSource;
	
	@Value("${spring.messages.basename}")
	String basename;
	
	@Test
	public void testMessageSource() {
		System.out.println("##### basename : " + basename);
		String message = messageSource.getMessage("test", null, LocaleContextHolder.getLocale());
		assertThat(basename).isNotBlank().isEqualTo("messages/message");
		assertThat(message).isNotBlank();
	}

}
