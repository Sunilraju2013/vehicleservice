package com.ford.vehicle.service;

import org.springframework.http.HttpStatus;

public class ServiceException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private HttpStatus httpStatus;
	private String message;
	
	

	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getErrorCode() {
		return errorCode;
	}
	
	

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public ServiceException(String message, String errorCode) {
//		super(message);
		this.message=message;
		this.errorCode = errorCode;
	}
	
	public ServiceException() {
		// TODO Auto-generated constructor stub
	}

	/*public ServiceException(String message) {
		super(message);
		
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(HttpStatus httpStatus,String message) {
		this.httpStatus=httpStatus;
		
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}*/

}
