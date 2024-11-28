package com.musicApi.infrastructure.driven_adapters.reactive_web.base.playlist;

import com.musicApi.domain.model.spotify.TrackDTO;
import com.musicApi.domain.model.spotify.gateway.TrackGateway;
import com.musicApi.infrastructure.driven_adapters.reactive_web.base.Consumer;
import com.musicApi.infrastructure.helpers.common.TokenUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;

@Setter
@Service
public class TrackService extends Consumer implements TrackGateway {

    private static final String ERROR_SEND_STATUS_OFFER = "ERROR en el consumo del servicio Spotify";

    private static final String URI = "/recommendations?limit=10";

    @Autowired
    public TrackService(@Value("${api.spotify.url}") String spotifyUrl) {
        super(spotifyUrl);
    }

    @Override
    public void configureHeaders(HttpHeaders headers) {
        headers.set("Authorization", "Bearer " + TokenUtil.TOKEN);
    }

    @Override
    public Flux<TrackDTO> getTrack() {
        return getRequestFlux(URI, TrackDTO.class,new HashMap<>());
    }
}
