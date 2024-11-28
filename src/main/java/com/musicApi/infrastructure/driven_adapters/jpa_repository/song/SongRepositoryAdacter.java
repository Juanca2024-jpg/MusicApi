package com.musicApi.infrastructure.driven_adapters.jpa_repository.song;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.gateway.PlayListGateway;
import com.musicApi.domain.model.song.SongDTO;
import com.musicApi.domain.model.song.gateway.SongGateway;
import com.musicApi.infrastructure.helpers.utils.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SongRepositoryAdacter
        extends AdapterOperations<SongDTO, SongData, Long, SongRepository>
        implements SongGateway {


    @Autowired
    protected SongRepositoryAdacter(SongRepository repository, ObjectMapper mapper) {
        super(repository, mapper,
                d -> mapper.mapBuilder(d, SongDTO.SongDTOBuilder.class).build());
    }

    @Override
    public Flux<SongDTO> getSongByNombreList(String listName) {
        return doQueryMany(() -> repository.findSongDataByListName(listName));
    }
}
