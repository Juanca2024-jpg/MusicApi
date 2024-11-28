package com.musicApi.infrastructure.driven_adapters.reactive_web.base.playlist;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.gateway.PlayListGateway;
import com.musicApi.domain.model.spotify.GeneroDTO;
import com.musicApi.domain.model.spotify.gateway.GeneroGateway;
import com.musicApi.infrastructure.driven_adapters.reactive_web.base.Consumer;
import com.musicApi.infrastructure.helpers.common.TokenUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Setter
@Service
public class GeneroService extends Consumer implements GeneroGateway {

    private static final String ERROR_SEND_STATUS_OFFER = "ERROR en el consumo del servicio Spotify";

    private static final String URI = "/recommendations/available-genre-seeds";

    @Autowired
    public GeneroService(@Value("${api.spotify.url}") String spotifyUrl) {
        super(spotifyUrl);
    }

    @Override
    public void configureHeaders(HttpHeaders headers) {
        headers.set("Authorization", "Bearer " + TokenUtil.TOKEN);
    }

    @Override
    public Mono<GeneroDTO> getGeneros() {
        return getRequest(URI, GeneroDTO.class,new HashMap<>());
    }
}
