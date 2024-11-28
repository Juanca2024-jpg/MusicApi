package com.musicApi.domain.model.spotify.gateway;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.spotify.TrackDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrackGateway {
    Flux<TrackDTO> getTrack();
}
