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
        description = "Object used to return Awards Range.",
        name = "AwardsRangeDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AwardsRangeDTO {

    @Schema(
            description = "Producer name.",
            implementation = String.class,
            type = SchemaType.STRING
    )
    private String producer;

    @Schema(
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer interval;

    @Schema(
            description = "Interval award.",
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer previousWin;

    @Schema(
            implementation = Integer.class,
            type = SchemaType.INTEGER
    )
    private Integer followingWin;
}
