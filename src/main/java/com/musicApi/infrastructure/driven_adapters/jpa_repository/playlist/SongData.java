package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@Entity(name = "song")
@Table(name = "song", schema = "musicdb")
@NoArgsConstructor
@AllArgsConstructor
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
