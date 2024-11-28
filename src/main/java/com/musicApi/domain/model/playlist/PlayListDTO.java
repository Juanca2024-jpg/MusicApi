package com.musicApi.domain.model.playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListDTO {

    private String nombre;

    private String descripcion;

    private Boolean estado;

}
