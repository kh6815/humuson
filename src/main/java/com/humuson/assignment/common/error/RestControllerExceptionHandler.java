package com.humuson.assignment.common.error;

import com.humuson.assignment.common.model.ApiResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler({
        Exception.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody public ApiResponseModel ExceptionHandler(Exception ex) throws Exception {

        log.error(ex.getClass().getSimpleName() + " Handling : {}", ex);

        ApiResponseModel apiResponse = new ApiResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        apiResponse.putError(ex.getMessage());

        return apiResponse;
    }

    @ExceptionHandler({CustomException.class})
    @ResponseBody public ResponseEntity<ApiResponseModel> CustomExceptionHandler(CustomException ex) throws Exception {

        log.error("CustomException Handling : {}", ex);

        ExceptionCode exceptionCode = ex.getExceptionCode();

        ApiResponseModel apiResponse = new ApiResponseModel(exceptionCode.getResultCode(), exceptionCode.getResultMessage());
        apiResponse.putError(ex.getExceptionData());

        return new ResponseEntity<>(apiResponse, exceptionCode.getStatusCode());
    }
}
