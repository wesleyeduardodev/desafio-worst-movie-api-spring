package com.worstmovie.api.dto.reesponse;
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
public class MaxMinAwardsRangeDTO {
    private List<AwardsRangeDTO> min;
    private List<AwardsRangeDTO> max;
}
