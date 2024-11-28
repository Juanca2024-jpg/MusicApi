package com.musicApi.domain.model.playlist;

import com.musicApi.domain.model.song.SongDTO;
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

    private String nombre;

    private String descripcion;

    private List<SongDTO> canciones;

    private Boolean estado;

}
