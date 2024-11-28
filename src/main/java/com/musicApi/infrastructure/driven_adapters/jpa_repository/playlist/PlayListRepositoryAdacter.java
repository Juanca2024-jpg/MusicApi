package com.musicApi.infrastructure.driven_adapters.jpa_repository.playlist;

import com.musicApi.domain.model.playlist.PlayListDTO;
import com.musicApi.domain.model.playlist.playlistGateway.PlayListGateway;
import com.musicApi.infrastructure.helpers.utils.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayListRepositoryAdacter
        extends AdapterOperations<PlayListDTO, PlayListData, Long, PlayListRepository>
        implements PlayListGateway {


    @Autowired
    protected PlayListRepositoryAdacter(PlayListRepository repository, ObjectMapper mapper) {
        super(repository, mapper,
                d -> mapper.mapBuilder(d, PlayListDTO.PlayListDTOBuilder.class).build());
    }



}
