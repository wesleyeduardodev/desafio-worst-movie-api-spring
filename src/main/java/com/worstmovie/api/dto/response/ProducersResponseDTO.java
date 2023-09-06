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
        description = "Object used to map producer data.s",
        name = "ProducersResponseDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProducersResponseDTO {

    @Schema(
            description = "Producer code.",
            implementation = Long.class,
            type = SchemaType.NUMBER
    )
    private Long id;

    @Schema(
            description = "Producer name.",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String name;
}
