package com.musicApi.domain.model.playlist.gateway;

import com.musicApi.domain.model.playlist.PlayListDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface PlayListGateway {

    Flux<PlayListDTO> getPlayListAll();
    Mono<PlayListDTO> getPlayListByNombre(String nombre);
    Mono<PlayListDTO> savePlayList(PlayListDTO playListDTO);
}
