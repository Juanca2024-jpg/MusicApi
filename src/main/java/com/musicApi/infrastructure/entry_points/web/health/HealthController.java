package com.musicApi.infrastructure.entry_points.web.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthController {

    @GetMapping()
    public ResponseEntity<Object> health() { return ResponseEntity.ok().body("Ok."); }

}
