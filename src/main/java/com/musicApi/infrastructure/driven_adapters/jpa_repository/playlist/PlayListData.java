package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import com.musicApi.infrastructure.driven_adapters.jpa_repository.song.SongData;
import jakarta.persistence.*;
import lombok.*;
@Data
@Builder
@Entity
@Table(name = "playlist", schema = "musicdb")
@NoArgsConstructor
@AllArgsConstructor
public class PlayListData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nombre;

    private String descripcion;

    private Boolean estado;
}
