package io.github.joyoungc.environment.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.github.joyoungc.environment.model.Sample;

public class CommonUtilsTest {

    @Test
    public void testPropertyAccessor() {
        Sample sample = new Sample();
        Map<String, Object> sampleMap = new HashMap<>();
        sampleMap.put("id", 1);
        sampleMap.put("name", "asdf");
        CommonUtils.propertyAccessor(sample, sampleMap);

        assertThat(sample).hasFieldOrPropertyWithValue("name", "asdf");
    }

}
