package com.musicApi.domain.model.spotify;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotifyTokenDTO {
    private String access_token;
    private String token_type;
    private int expires_in;
}