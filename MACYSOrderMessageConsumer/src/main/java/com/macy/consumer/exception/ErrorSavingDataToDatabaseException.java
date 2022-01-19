package com.macy.consumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.macy.consumer.utils.ExceptionConstants;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class ErrorSavingDataToDatabaseException extends RuntimeException {

    @Override
    public String toString() {
        return ExceptionConstants.ERROR_SAVING_IN_DATABASE;
    }
}
