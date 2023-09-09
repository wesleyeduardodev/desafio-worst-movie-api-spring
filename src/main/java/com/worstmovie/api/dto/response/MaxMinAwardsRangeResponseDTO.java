package com.worstmovie.api.dto.response;
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
        description = "Object used to return Maximum and Minimum Awards Range.",
        name = "MaxMinAwardsRangeResponseDTO",
        type = SchemaType.OBJECT
)
public class MaxMinAwardsRangeResponseDTO {
    private List<AwardsRangeDTO> min;
    private List<AwardsRangeDTO> max;
}
