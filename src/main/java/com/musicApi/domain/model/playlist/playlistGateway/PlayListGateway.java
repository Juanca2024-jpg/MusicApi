package com.musicApi.domain.model.playlist.playlistGateway;

import com.musicApi.domain.model.playlist.PlayListDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayListGateway {

    Flux<PlayListDTO> getPlayListAll();
    Mono<PlayListDTO> getPlayListById(String id);
    Mono<PlayListDTO> savePlayList(PlayListDTO playListDTO);
}
