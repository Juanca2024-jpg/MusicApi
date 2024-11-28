package com.musicApi.domain.model.spotify.gateway;

import com.musicApi.domain.model.spotify.SpotifyTokenDTO;
import reactor.core.publisher.Mono;

public interface SpotifyGateway {
    Mono<SpotifyTokenDTO> getToken();
}
