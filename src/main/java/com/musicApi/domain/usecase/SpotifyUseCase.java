package com.musicApi.domain.usecase;

import com.musicApi.domain.model.song.SongDTO;
import com.musicApi.domain.model.spotify.gateway.SpotifyGateway;
import com.musicApi.infrastructure.helpers.common.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SpotifyUseCase {

    private final SpotifyGateway spotifyGateway;

    public Mono<Void> getToken(){
        return spotifyGateway.getToken().flatMap(tokenResult ->
        {
            TokenUtil.TOKEN = tokenResult.getAccess_token();
            return Mono.empty();
        });
    }

}
