package io.github.joyoungc.environment.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

/**
 * Properties 테스트용
 */
@Getter
@Setter
@Component
@ConfigurationProperties
public class MyProperties {

    private InetAddress remoteAddress;

    private boolean enabled;

    private Security security;

    private List<MyPojo> list;

    private List<Map<String, Object>> map;

    private RandomPojo myRandom;

    @Setter
    @Getter
    public static class Security {
        private String username;
        private List<String> roles;
    }

    @Setter
    @Getter
    public static class MyPojo {
        private String name;
        private String desc;
    }

    @Setter
    @Getter
    @ToString
    public static class RandomPojo {
        private String secret;
        private Integer number;
        private Long bignumber;
        private String uuid;
        private Integer numberLessThanTen;
        private Integer numberInRange;
    }

}
