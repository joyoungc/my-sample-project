package io.github.joyoungc.environment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.joyoungc.common.exception.handler.ExceptionResponse;
import io.github.joyoungc.common.exception.handler.RestTemplateExceptionResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringEnvironmentsTest {

    @Autowired
    MessageSource messageSource;

    @Value("${spring.messages.basename}")
    String basename;

    @Autowired
    ApplicationContext context;

    // @Test
    public void testMessageSource() {
        System.out.println("##### basename : " + basename);
        String message = messageSource.getMessage("test", null, LocaleContextHolder.getLocale());
        assertThat(basename).isNotBlank().isEqualTo("messages/message");
        assertThat(message).isNotBlank();
    }

    // @Test
    public void testExceptionBean() {
        Map<String, ExceptionResponse> matchingBeans = BeanFactoryUtils
                .beansOfTypeIncludingAncestors(context, ExceptionResponse.class, true, false);
        assertThat(matchingBeans.isEmpty()).isFalse();

        RestTemplateExceptionResponse res = (RestTemplateExceptionResponse) matchingBeans.get(
        		"restTemplateExceptionResponse");

        res.getErrorCode(null, null, null);
    }

    @Value("${application.exception.default-error-code}")
    String defaultErrorCode;


    @Test
    public void testApplicationYml() {
        System.out.println("##### defaultErrorCode : " + defaultErrorCode);
        assertThat(defaultErrorCode).isEqualTo("COM0000");
    }

}
