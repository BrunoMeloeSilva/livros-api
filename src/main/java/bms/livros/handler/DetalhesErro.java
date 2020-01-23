package bms.livros.handler;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetalhesErro {
	
	private String titulo;
	private Long status;
	@JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
	private Date timestamp;
	private String mensagemDesenvolvedor;
	
	
	public DetalhesErro() {}
	
	public DetalhesErro(String titulo, Long status, Date timestamp, String mensagemDesenvolvedor) {
		super();
		this.titulo = titulo;
		this.status = status;
		this.timestamp = timestamp;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}
	public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}
	
	
}
