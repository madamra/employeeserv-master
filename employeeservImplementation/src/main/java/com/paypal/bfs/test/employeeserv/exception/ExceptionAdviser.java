package com.paypal.bfs.test.employeeserv.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdviser extends ResponseEntityExceptionHandler {
	
	private String error = "ERROR";

  @ExceptionHandler(ValidationFailedException.class)
  public ResponseEntity<ResponseDTO> responseEntity(ValidationFailedException exception) {
    return new ResponseEntity<ResponseDTO>((ResponseDTO.builder().REQ_STATUS(error))
        .ERROR_MESSAGE(exception.getMessage()).ERROR_CODE(HttpStatus.BAD_REQUEST).build(), HttpStatus.OK);
  }

  @ExceptionHandler({ConstraintViolationException.class, ServiceException.class,RepositoryException.class})
  public ResponseEntity<ResponseDTO> constraintViolationException(Exception exception) {
    return new ResponseEntity<ResponseDTO>(
        ResponseDTO.builder().REQ_STATUS(HttpStatus.OK.name()).ERROR_MESSAGE(exception.getMessage()).ERROR_CODE(HttpStatus.CONFLICT).build(),
        HttpStatus.OK);
  }



  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDTO> ExceptionHandler(Exception exception) {
    return new ResponseEntity<>(
        ResponseDTO.builder().REQ_STATUS(error).ERROR_MESSAGE(exception.getMessage()).ERROR_CODE(HttpStatus.INTERNAL_SERVER_ERROR).build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    return new ResponseEntity<Object>(ResponseDTO.builder().REQ_STATUS(error)
        .ERROR_MESSAGE(ex.getMessage()).ERROR_CODE(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    return new ResponseEntity<Object>(ResponseDTO.builder().REQ_STATUS(HttpStatus.BAD_REQUEST.name())
        .ERROR_MESSAGE(ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
            .collect(Collectors.toList()).toString())
        .build(), HttpStatus.BAD_REQUEST);

  }

}
