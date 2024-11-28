package com.musicApi.domain.model.playlist.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayListParam {

    private String nombre;
    private String descripcion;

}
