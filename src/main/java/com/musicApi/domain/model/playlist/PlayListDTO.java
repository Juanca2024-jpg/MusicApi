package com.musicApi.domain.model.playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private List<SongDTO> canciones;

}
