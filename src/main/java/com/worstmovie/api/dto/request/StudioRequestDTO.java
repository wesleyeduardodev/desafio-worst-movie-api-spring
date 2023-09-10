package com.worstmovie.api.dto.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Object used to map Studio creation data.",
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
    @NotBlank(message = "Studio name is required.")
    private String name;
}
