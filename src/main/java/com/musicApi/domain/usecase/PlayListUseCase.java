package com.musicApi.domain.usecase;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.gateway.PlayListGateway;
import com.musicApi.domain.model.playlist.request.PlayListParam;
import com.musicApi.domain.model.playlist.response.PlayListResult;
import com.musicApi.domain.model.song.gateway.SongGateway;
import com.musicApi.domain.model.spotify.gateway.TrackGateway;
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


    public Mono<Object> savePlayList (PlayListParam playListParam){
        return playListGateway.getPlayListByNombre(playListParam.getNombre())
                .flatMap(existingPlayList -> Mono.error(new Exception("La lista ya existe")))
                .switchIfEmpty(
                        playListGateway.savePlayList(PlayListDTO.builder()
                                .nombre(playListParam.getNombre())
                                .descripcion(playListParam.getDescripcion())
                                .estado(true)
                                .build()
                        ).flatMap(savedPlayList -> Mono.just(PlayListResult.builder()
                                .nombre(savedPlayList.getNombre())
                                .descripcion(savedPlayList.getDescripcion())
                                .build()))
                );
    }

    public Mono<Void> deletePlayList (String nombre){
        return playListGateway.getPlayListByNombre(nombre)
                .flatMap(existingPlayList -> {
                    if (!existingPlayList.getEstado()) {
                        return Mono.error(new Exception("La playlist ya está desactivada"));
                    }
                    existingPlayList.setEstado(false);
                    return playListGateway.savePlayList(existingPlayList);
                })
                .switchIfEmpty(Mono.error(new Exception("Playlist no encontrada"))).then();
    }

}
