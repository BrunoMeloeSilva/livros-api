package bms.livros.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManipuladorExcecoes {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DetalhesErro> handleBadRequest(MethodArgumentNotValidException e
														, HttpServletRequest request){
		
		DetalhesErro detalhesErro = new DetalhesErro(
				"A requisição foi mal construída: " + e.getBindingResult().getFieldError().getDefaultMessage()
				, 400l
				, new Date()
				, "http://www.livros-api.com/erros/400");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detalhesErro);
	}
}
