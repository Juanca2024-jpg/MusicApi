package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.playlistGateway.PlayListGateway;
import com.musicApi.infrastructure.helpers.utils.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PlayListRepositoryAdacter
        extends AdapterOperations<PlayListDTO, PlayListData, Long, PlayListRepository>
        implements PlayListGateway {


    @Autowired
    protected PlayListRepositoryAdacter(PlayListRepository repository, ObjectMapper mapper) {
        super(repository, mapper,
                d -> mapper.mapBuilder(d, PlayListDTO.PlayListDTOBuilder.class).build());
    }


    @Override
    public Flux<PlayListDTO> getPlayListAll() {
        return doQueryMany(() -> repository.findAll());
    }

    @Override
    public Mono<PlayListDTO> getPlayListById(String id) {
        return doQuery(() -> repository.findById(1L));
    }

    @Override
    public Mono<PlayListDTO> savePlayList(PlayListDTO playListDTO) {
        return save(playListDTO);
    }
}
