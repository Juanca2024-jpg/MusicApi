package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;



@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSongData {

    @Id
    @GeneratedValue
    private Long id;

    private String nameList;

    private Long idSong;


}
