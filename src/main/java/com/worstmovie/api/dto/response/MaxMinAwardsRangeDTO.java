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
        description = "Object used to return Max and Min Awards Range.",
        name = "MaxMinAwardsRangeDTO",
        type = SchemaType.OBJECT
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MaxMinAwardsRangeDTO {
    private List<AwardsRangeDTO> min;
    private List<AwardsRangeDTO> max;
}
