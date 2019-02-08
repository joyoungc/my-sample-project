package io.github.joyoungc.cloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SpringBootCloudApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testSleuthRest() throws Exception {
        String result = restTemplate.getForObject("/sleuth/hello", String.class);
        log.debug("## result : {} ", result);
    }

}
