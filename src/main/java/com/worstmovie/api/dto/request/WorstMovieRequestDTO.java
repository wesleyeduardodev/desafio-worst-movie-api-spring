package com.worstmovie.api.dto.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Object used to map the creation data of a new Worst Movie.",
        name = "WorstMovieRequestDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WorstMovieRequestDTO {

    @Schema(
            description = "WorstMovie year.",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    @NotNull(message = "Year is required.")
    private String year;

    @Schema(
            description = "WorstMovie title.",
            implementation = String.class,
            type = SchemaType.STRING
    )
    @NotBlank(message = "Title is required.")
    private String title;

    @Schema(
            description = "WorstMovie winner.",
            implementation = Boolean.class,
            type = SchemaType.BOOLEAN
    )
    @NotNull(message = "Winner is required.")
    private Boolean winner;
}
