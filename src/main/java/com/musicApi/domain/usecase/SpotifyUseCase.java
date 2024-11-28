package com.musicApi.domain.usecase;

import com.musicApi.domain.model.song.SongDTO;
import com.musicApi.domain.model.spotify.TrackDTO;
import com.musicApi.domain.model.spotify.gateway.SpotifyGateway;
import com.musicApi.domain.model.spotify.gateway.TrackGateway;
import com.musicApi.infrastructure.driven_adapters.reactive_web.playlist.TrackService;
import com.musicApi.infrastructure.helpers.common.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SpotifyUseCase {

    private final SpotifyGateway spotifyGateway;

    private final TrackGateway trackGateway;

    public Mono<Void> getToken(){
        return spotifyGateway.getToken().flatMap(tokenResult ->
        {
            TokenUtil.TOKEN = tokenResult.getAccess_token();
            return Mono.empty();
        });
    }

    public Flux<TrackDTO> getPlaylistAll() {
        return getToken()
                .doOnSuccess(token -> System.out.println("Token retrieved: " + TokenUtil.TOKEN))
                .thenMany(trackGateway.getTrack())
                .onErrorResume(e -> {
                    System.err.println("Error retrieving token or playlists: " + e.getMessage());
                    return Flux.empty(); // Devuelve un Flux vacío en caso de error, ajusta según tu necesidad.
                });
    }

}
