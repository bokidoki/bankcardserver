package com.keepsafe.bankcardserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableWebSocket
public class BankcardserverApplication {

    private static final Logger log = LoggerFactory.getLogger(BankcardserverApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(BankcardserverApplication.class, args);
        Environment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        log.info("\n-------------------------------------------------------\n\tApplication Declare is running! Access URLs:\n\tlocal: \t\thttp://{}:{}/{}\n\texternal: \thttp://{}:{}{}\n\t", ip, port, contextPath, ip, port, contextPath);
    }

}
