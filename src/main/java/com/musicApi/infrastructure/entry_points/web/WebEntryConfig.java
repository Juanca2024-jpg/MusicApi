package com.musicApi.infrastructure.entry_points.web;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.ContextPathCompositeHandler;
import org.springframework.http.server.reactive.HttpHandler;

import java.util.HashMap;
import java.util.Map;

@Setter
@Configuration
@RequiredArgsConstructor
public class WebEntryConfig {

    @Value("${app.context}")
    private String path;

    @Bean
    public NettyReactiveWebServerFactory nettyReactiveWebServerFactory() {
        return new NettyReactiveWebServerFactory() {
            @Override
            public WebServer getWebServer(HttpHandler httpHandler) {
                Map<String, HttpHandler> map = new HashMap<>();
                map.put(path, httpHandler);
                return super.getWebServer(new ContextPathCompositeHandler(map));
            }
        };
    }
}
