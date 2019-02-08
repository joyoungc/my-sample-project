package io.github.joyoungc.environment.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleAopTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SampleAopUtils sampleAopUtils;

    @Test
    public void aopTest() {
        sampleAopUtils.executeEcho("hello!!!");
    }

    @Ignore
    public void testSpringBeanAop() throws Exception {
        mockMvc.perform(get("/aop/hello?input=hello"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
