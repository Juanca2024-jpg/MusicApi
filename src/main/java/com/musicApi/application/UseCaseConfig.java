package com.musicApi.application;

import com.musicApi.domain.model.playlist.gateway.PlayListGateway;
import com.musicApi.domain.model.song.gateway.SongGateway;
import com.musicApi.domain.model.spotify.gateway.TrackGateway;
import com.musicApi.domain.model.spotify.gateway.SpotifyGateway;
import com.musicApi.domain.usecase.PlayListUseCase;
import com.musicApi.domain.usecase.SpotifyUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ObjectMapper objectMapper() {return new ObjectMapperImp();}

    @Bean
    public PlayListUseCase playListUseCase(PlayListGateway playListGateway,
                                           TrackGateway trackGateway,
                                           SpotifyUseCase spotifyUseCase,
                                           SongGateway songGateway
    ) {
        return new PlayListUseCase(playListGateway,songGateway, trackGateway, spotifyUseCase);
    }

    @Bean
    public SpotifyUseCase spotifyUseCase(SpotifyGateway spotifyGateway){
        return new SpotifyUseCase(spotifyGateway);
    }
}