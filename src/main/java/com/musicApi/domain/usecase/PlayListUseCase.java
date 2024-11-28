package com.musicApi.domain.usecase;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.playlistGateway.PlayListGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PlayListUseCase {

    private final PlayListGateway playListGateway;

    public Flux<PlayListDTO> getPlaylistAll (){
        return playListGateway.getPlayListAll() ;
    }

}
