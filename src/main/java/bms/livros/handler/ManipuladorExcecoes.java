package bms.livros.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import bms.livros.service.exceptions.LivroNaoEncontradoException;
import bms.livros.service.exceptions.AutorExistenteException;
import bms.livros.service.exceptions.AutorNaoEncontradoException;

@ControllerAdvice
public class ManipuladorExcecoes {
	
	//Para quando se esquece de informar algum atributo JSON a ser passado
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
	
	//Para quando se esquece de separar os atributos JSON por virgula..
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<DetalhesErro> handleBadJson(HttpMessageNotReadableException e
														, HttpServletRequest request){
		
		DetalhesErro detalhesErro = new DetalhesErro(
				"A requisição foi mal construída: " + e.getCause()
				, 400l
				, new Date()
				, "http://www.livros-api.com/erros/400");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detalhesErro);
	}
	
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(LivroNaoEncontradoException e,
																	HttpServletRequest request){
		
		DetalhesErro detalhesErro = new DetalhesErro(
				e.getMessage()
				, 404l
				, new Date()
				, "http://www.livros-api.com/erros/404");
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistenteException(AutorExistenteException e,
																	HttpServletRequest request){
		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setTitulo("Esse autor já existe abestalhado.");
		detalhesErro.setStatus(409l);
		detalhesErro.setTimestamp(new Date());
		detalhesErro.setMensagemDesenvolvedor("http://erro.socialbooks.com/409");
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhesErro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoException(AutorNaoEncontradoException e,
																	HttpServletRequest request){
		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setTitulo("Esse autor não foi encontrado abestalhado.");
		detalhesErro.setStatus(404l);
		detalhesErro.setTimestamp(new Date());
		detalhesErro.setMensagemDesenvolvedor("http://erro.socialbooks.com/404");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}
	
	
}
