package com.keepsafe.bankcardserver;

import com.keepsafe.bankcardserver.bean.UserInfo;
import com.keepsafe.bankcardserver.utils.HexConverter;
import com.keepsafe.bankcardserver.utils.JwtUtil;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;

@SpringBootTest
class BankcardserverApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(BankcardserverApplicationTests.class);

    private String secret = "d96581e5d499b551e59dc46a4eadab93bbc74258d90e2f728962a31b719a09a6";
    private long expiration = 86400000;
    private SecretKey secretKey = Keys.hmacShaKeyFor(HexConverter.INSTANCE.hexStringToByteArray(secret));
    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", secret);
        ReflectionTestUtils.setField(jwtUtil, "expiration", expiration);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testGenToken() {
        String token = jwtUtil.generateToken(new UserInfo(1));
        log.info("token: {}", token);
    }

}
