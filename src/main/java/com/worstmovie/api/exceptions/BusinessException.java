package com.worstmovie.api.exceptions;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessException extends Exception {
    private String code;
    private String orientation;
}
