package com.musicApi.domain.model.song.gateway;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.song.SongDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SongGateway {

    Flux<SongDTO> getSongByNombreList(String listName);
}
