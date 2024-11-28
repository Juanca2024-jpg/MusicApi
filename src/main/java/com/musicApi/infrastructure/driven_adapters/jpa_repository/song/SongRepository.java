package com.musicApi.infrastructure.driven_adapters.jpa_repository.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<SongData, Long> {

    @Query( """
            select s.titulo, s.anno, s.album, s.artista from song s join playlistsong ps on s.id=ps.id where ps.nameList=:nombre
            """)
    List<SongData> findSongDataByListName(@Param("nombre") String nombre);

}
