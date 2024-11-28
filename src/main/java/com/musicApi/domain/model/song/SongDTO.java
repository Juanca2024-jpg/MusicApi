package com.musicApi.domain.model.song;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {

    private Long id;

    private String titulo;

    private String artista;

    private String album;

    private int anno;

    private String genero;

}
