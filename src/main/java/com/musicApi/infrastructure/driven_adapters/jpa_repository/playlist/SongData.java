package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "song")
@Setter
@Getter
public class SongData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String artista;

    private String album;

    private int anno;

    private String genero;
}
