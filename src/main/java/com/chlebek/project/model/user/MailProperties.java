package com.chlebek.project.model.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class MailProperties {
    private final String host = "smtp.gmail.com";
    private final String port = "587";
    private final String username = "towebapplication@gmail.com";
    private final String password = "zppppseikxbktaux";
    private final String from = "mypage.com";
    private final String fromName = "mypage.com";
    private final String verificationapi = "";

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFrom() {
        return from;
    }

    public String getFromName() {
        return fromName;
    }

    public String getVerificationapi() {
        return verificationapi;
    }
}
