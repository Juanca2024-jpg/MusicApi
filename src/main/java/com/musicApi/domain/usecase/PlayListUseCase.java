package com.musicApi.domain.usecase;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.gateway.PlayListGateway;
import com.musicApi.domain.model.playlist.request.PlayListParam;
import com.musicApi.domain.model.playlist.response.PlayListResult;
import com.musicApi.domain.model.song.gateway.SongGateway;
import com.musicApi.domain.model.spotify.gateway.TrackGateway;
import com.musicApi.infrastructure.helpers.common.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayListUseCase {

    private final PlayListGateway playListGateway;

    private final SongGateway songGateway;

    private final TrackGateway trackGateway;

    private final SpotifyUseCase spotifyUseCase;

    public Mono<PlayListDTO> getPlayListByNombre(String nombre) {
        return playListGateway.getPlayListByNombre(nombre);
    }

    public Flux<PlayListDTO> getPlaylistAll() {
        return playListGateway.getPlayListAll();
    }


    public Mono<PlayListResult> savePlayList (PlayListParam playListParam){
        return playListGateway.savePlayList(PlayListDTO
                        .builder()
                        .nombre(playListParam.getNombre())
                        .descripcion(playListParam.getDescripcion())
                        .estado(true)
                        .build())
                .flatMap(p -> Mono.just(PlayListResult.builder()
                                .nombre(p.getNombre())
                                .descripcion(p.getDescripcion())
                                .build())
                );
    }

    public Mono<Void> deletePlayList (String nameList){
        return playListGateway.getPlayListByNombre(nameList)
                .switchIfEmpty(Mono.error(new Exception("La lista no existe")))
                .flatMap(playListDTO -> {
                    playListDTO.setEstado(false);
                    return playListGateway.savePlayList(playListDTO).thenReturn(Mono.empty());
                }).then();
    }

}
