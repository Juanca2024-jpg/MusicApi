package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity(name = "playlist")
@Table(name = "playlist", schema = "music_schema")
@NoArgsConstructor
@AllArgsConstructor
public class PlayListData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean estado;

}
