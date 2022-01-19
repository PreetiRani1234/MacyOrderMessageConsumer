package com.macy.consumer.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import com.macy.consumer.utils.ExceptionConstants;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class ErrorParsingDataException extends RuntimeException {

    @Override
    public String toString() {
        return ExceptionConstants.ERROR_PARSING_DATA;
    }
}
