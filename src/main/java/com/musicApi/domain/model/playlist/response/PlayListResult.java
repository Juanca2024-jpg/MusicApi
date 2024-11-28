package com.musicApi.domain.model.playlist.response;

import com.musicApi.domain.model.song.SongDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlayListResult {

    private String nombre;

    private String descripcion;

    private List<SongDTO> canciones;

}
