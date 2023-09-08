package com.worstmovie.api.dto.reesponse;
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
        description = "Object used to map studio datas.",
        name = "StudioResponseDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudioResponseDTO {

    @Schema(
            description = "Studio code.",
            implementation = Long.class,
            type = SchemaType.NUMBER
    )
    private Long id;

    @Schema(
            description = "Studio name.",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String name;

    @Schema(
            implementation = List.class,
            type = SchemaType.ARRAY
    )
    private List<LinkDTO> links;
}
