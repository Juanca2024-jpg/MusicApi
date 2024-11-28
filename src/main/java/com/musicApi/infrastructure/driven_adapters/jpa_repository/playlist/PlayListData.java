package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@Entity(name = "playlist")
@Table(name = "playlist", schema = "musicdb")
@NoArgsConstructor
@AllArgsConstructor
public class PlayListData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongData> canciones;
}
