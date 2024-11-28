package com.musicApi.infrastructure.entry_points.web.playlist;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.request.PlayListParam;
import com.musicApi.domain.usecase.PlayListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/list")
public class PlayListController {

    private final PlayListUseCase playListUseCase;

    @GetMapping
    public ResponseEntity<Object> getServicioAll() {
        return ResponseEntity.ok().body(playListUseCase.getPlaylistAll());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Object> getPlayListByNombre(@PathVariable("nombre") String nombre) {
        return ResponseEntity.ok().body(playListUseCase.getPlayListByNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Object> savePlaylist(@RequestBody PlayListParam playListParam) {
        return ResponseEntity.ok().body(playListUseCase
                .savePlayList(playListParam));
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<Object> deletePlayListByNombre(@PathVariable("nombre") String nombre) {
        return ResponseEntity.ok().body(playListUseCase.deletePlayList(nombre));
    }
}