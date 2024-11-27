package theCatApi.domain.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseDTO {

    private Boolean ok;
    private String message;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<?> data;
    private Integer status;
    private Integer currentPage;
    private Integer totalPages;
}
