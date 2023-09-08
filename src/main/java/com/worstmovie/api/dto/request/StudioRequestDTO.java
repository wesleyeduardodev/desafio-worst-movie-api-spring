package com.worstmovie.api.dto.request;
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
        description = "Object used to map studio datas.",
        name = "StudioRequestDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudioRequestDTO {
    @Schema(
            description = "Studio name.",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String name;
}
