package com.worstmovie.api.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.worstmovie.api.dto.LinkDTO;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Object used to map WorstMovie datas.",
        name = "WorstMovieResponseDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WorstMovieResponseDTO {

    @Schema(
            description = "Worst Movie code.",
            implementation = Long.class,
            type = SchemaType.NUMBER
    )
    private Long id;

    @Schema(
            description = "Worst Movie year.",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private String year;

    @Schema(
            description = "Worst Movie title.",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String title;

    @Schema(
            description = "Worst Movie winner.",
            implementation = Boolean.class,
            type = SchemaType.BOOLEAN
    )
    private boolean winner;

    @Schema(
            implementation = List.class,
            type = SchemaType.ARRAY
    )
    private List<LinkDTO> links;
}
