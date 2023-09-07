package com.worstmovie.api.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
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
        description = "Object used to return Ranking Producer.",
        name = "RankingProducerDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RankingProducerDTO {

    @Schema(
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String name;

    @Schema(
            implementation = List.class,
            type = SchemaType.ARRAY
    )
    private List<Integer> years;

    @Schema(
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer interval;
}
