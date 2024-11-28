package com.musicApi.domain.usecase;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.gateway.PlayListGateway;
import com.musicApi.domain.model.playlist.request.PlayListParam;
import com.musicApi.domain.model.playlist.response.PlayListResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PlayListUseCase {

    private final PlayListGateway playListGateway;

    public Flux<PlayListDTO> getPlaylistAll (){
        return playListGateway.getPlayListAll() ;
    }

    public Mono<PlayListDTO> getPlayListByNombre (String nombre){
        return playListGateway.getPlayListByNombre(nombre) ;
    }

    public Mono<PlayListResult> savePlayList (PlayListParam playListParam){
        return playListGateway.savePlayList(PlayListDTO
                .builder()
                .nombre(playListParam.getNombre())
                .descripcion(playListParam.getDescripcion())
                .canciones(new ArrayList<>())
                        .estado(true)
                .build())
                .flatMap(p -> Mono.just(PlayListResult.builder()
                                .nombre(p.getNombre())
                                .descripcion(p.getDescripcion())
                                .canciones(p.getCanciones())
                                .build())
                        );
    }

}
