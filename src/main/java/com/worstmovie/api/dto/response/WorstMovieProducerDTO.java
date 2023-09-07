package com.worstmovie.api.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Object used to return WorstMovie Producer.",
        name = "WorstMovieProducerDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WorstMovieProducerDTO {

    @Schema(
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String name;

    @Schema(
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer year;
}
