package com.musicApi.infrastructure.driven_adapters.jpa_repository.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<SongData, Long> {

    @Query()
    List<SongData> findSongDataByListName(String nombre);

}
