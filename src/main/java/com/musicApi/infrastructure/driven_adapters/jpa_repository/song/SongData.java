package com.musicApi.infrastructure.driven_adapters.jpa_repository.song;

import com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist.PlayListData;
import com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist.PlaylistSongData;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;


@Getter
@Setter
@Builder
@Entity
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

}
