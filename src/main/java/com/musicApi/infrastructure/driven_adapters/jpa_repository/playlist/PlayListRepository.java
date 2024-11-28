package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayListRepository extends JpaRepository<PlayListData, Long> {

    @Query("SELECT p FROM playlist p WHERE p.estado = true")
    List<PlayListData> findAllPlayList();

    @Query("SELECT p FROM playlist p WHERE p.estado = true AND p.nombre= :nombre")
    Optional<PlayListData> findPlayListByNombre(@Param("nombre") String nombre);

}
