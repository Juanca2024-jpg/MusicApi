package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;



@Data
@Builder
@Entity(name = "playlistsong" )
@Table(schema = "music_schema")
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSongData {

    @Id
    @GeneratedValue
    private Long id;

    private Long nameList;

    private Long idSong;

}
