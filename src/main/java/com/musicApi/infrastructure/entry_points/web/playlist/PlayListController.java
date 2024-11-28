package com.musicApi.infrastructure.entry_points.web.playlist;

import com.musicApi.domain.usecase.PlayListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/list")
public class PlayListController {

    private final PlayListUseCase playListUseCase;

    @GetMapping
    public ResponseEntity<Object> getServicioAll() {
        return ResponseEntity.ok().body(playListUseCase.getPlaylistAll());
    }



}