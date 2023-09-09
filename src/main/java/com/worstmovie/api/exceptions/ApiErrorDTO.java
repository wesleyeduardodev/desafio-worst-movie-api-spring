package com.worstmovie.api.exceptions;
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
        description = "Object used map Api Error",
        name = "ApiErrorDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiErrorDTO {

    @Schema(
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String code;

    @Schema(
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String message;

    @Schema(
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String orientation;
}
