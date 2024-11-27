package com.musicApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicApiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MusicApiApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run();
    }

}
