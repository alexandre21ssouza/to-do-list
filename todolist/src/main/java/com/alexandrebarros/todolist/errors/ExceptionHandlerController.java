package com.alexandrebarros.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/*Anotação @ControllerAdvice faz com que toda exception que tiver, 
 * passe por aqui primeiro, até que surja uma anotação que deve ser
 * tratada por este método*/
@ControllerAdvice
public class ExceptionHandlerController {
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

}
