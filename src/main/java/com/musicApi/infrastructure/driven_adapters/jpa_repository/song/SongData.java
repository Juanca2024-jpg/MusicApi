package com.musicApi.infrastructure.driven_adapters.jpa_repository.song;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity(name = "song")
@Table(name = "song", schema = "music_schema")
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

}
