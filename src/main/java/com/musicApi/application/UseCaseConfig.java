package com.musicApi.application;

import com.musicApi.domain.model.playlist.gateway.PlayListGateway;
import com.musicApi.domain.usecase.PlayListUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ObjectMapper objectMapper() {return new ObjectMapperImp();}

    @Bean
    public PlayListUseCase playListUseCase(PlayListGateway playListGateway) {
        return new PlayListUseCase(playListGateway);
    }
}