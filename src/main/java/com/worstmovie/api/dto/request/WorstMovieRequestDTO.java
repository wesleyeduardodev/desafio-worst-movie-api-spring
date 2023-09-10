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
        description = "Object used to map Worst Movie creation data.",
        name = "WorstMovieRequestDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WorstMovieRequestDTO {

    @Schema(
            description = "Worst Movie year.",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    @NotNull(message = "Year is required.")
    private String year;

    @Schema(
            description = "Worst Movie title.",
            implementation = String.class,
            type = SchemaType.STRING
    )
    @NotBlank(message = "Title is required.")
    private String title;

    @Schema(
            description = "Worst Movie winner.",
            implementation = Boolean.class,
            type = SchemaType.BOOLEAN
    )
    @NotNull(message = "Winner is required.")
    private Boolean winner;
}
