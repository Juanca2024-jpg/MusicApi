package com.musicApi.domain.model.spotify.gateway;

import com.musicApi.domain.model.spotify.GeneroDTO;
import reactor.core.publisher.Mono;

public interface GeneroGateway {
    Mono<GeneroDTO> getGeneros();
}
