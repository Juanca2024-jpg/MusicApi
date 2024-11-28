package com.musicApi.infrastructure.driven_adapters.reactive_web.playlist;

import com.musicApi.domain.model.spotify.SpotifyTokenDTO;
import com.musicApi.domain.model.spotify.gateway.SpotifyGateway;
import com.musicApi.infrastructure.driven_adapters.reactive_web.base.Consumer;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Setter
@Service
public class tokenService extends Consumer implements SpotifyGateway {

    private static final String ERROR_SEND_STATUS_OFFER = "ERROR en el consumo del servicio Spotify";

    @Value("${api.spotify.tokenUri}")
    private String uri;

    @Value("${api.spotify.clienteId}")
    private String clientId;

    @Value("${api.spotify.clienteSecret}")
    private String clientSecret;

    @Autowired
    public tokenService(@Value("${api.spotify.tokenUrl}") String spotifyUrl) {
        super(spotifyUrl);
    }

    @Override
    public void configureHeaders(HttpHeaders headers) {
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.set("Authorization", "Basic " + encodedCredentials);
        headers.set("Content-Type", "application/x-www-form-urlencoded");
    }

    public Mono<SpotifyTokenDTO> getToken(){
        String body = "grant_type=client_credentials";
        return postRequest(uri, body, SpotifyTokenDTO.class)
                .onErrorResume(throwable -> Mono.error(new Exception(ERROR_SEND_STATUS_OFFER)));
    }
}
