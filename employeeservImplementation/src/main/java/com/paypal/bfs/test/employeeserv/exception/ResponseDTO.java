package com.paypal.bfs.test.employeeserv.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = -4881971654439738529L;

	private String REQ_STATUS;

	private String ERROR_MESSAGE;

	private HttpStatus ERROR_CODE;
	
	public ResponseDTO(String code,String message,HttpStatus status ) {
		this.REQ_STATUS = code;
		this.ERROR_MESSAGE = message;
		this.ERROR_CODE = status;
	}

}
