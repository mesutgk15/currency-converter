package dev.mesut.currencyconverter.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExceptionResponse {

    private String message;
    private long timeStamp;
    private HttpStatus status;

}
