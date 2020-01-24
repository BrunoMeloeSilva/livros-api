package bms.livros.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import bms.livros.domain.Comentario;
import bms.livros.domain.Livro;
import bms.livros.repository.ComentariosRepository;
import bms.livros.repository.LivrosRepository;
import bms.livros.service.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livrosRepository;
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	
	//Se eu não criar no handler para recepção e tratamento para este erro disparado (LivroNaoEncontradoException)
	//O objeto de erro retornado ao cliente final será o padrão do Spring Boot e não o meu DetalhesErro. 
	//A única coisa minha será a mensagem "Não existe livro com o ID indicado a ser delerado.", conforme abaixo.
	public void deletar(Long id) {
		try {
			livrosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { 
			throw new LivroNaoEncontradoException("Não existe livro com o ID indicado a ser delerado.");
			//Vejo que eu nem precisaria ter criado esta classe LivroNaoEncontradoException, poderia 
			//apenas alterar a mensagem do EmptyResultDataAccessException e por ele para ser capturado
			//no handler, ao inves do LivroNaoEncontradoException.
		}
	}
	
	public Livro buscar(Long id) {
		Livro livro = null;
		try {
			livro = livrosRepository.getOne(id);
			livro.toString(); 
			// O erro do JPA só dispara ao referenciar o objeto.
			// A chamada ao metodo toString é para saber se o objeto foi retornado.
			//Pois se voce fizer if(livro == null) dará falso, pois ele possui referencia, só que para VOID.
		}catch (EntityNotFoundException e) {
			throw new LivroNaoEncontradoException("Não existe livro com o ID indicado.");
		}
		return livro;
	}
	
	public void atualizar(Livro livro) {
		buscar(livro.getId());//Verifica a existencia do livro
		livrosRepository.save(livro);//O save do JPA é um merge, se existir ele atualiza, senao cria um novo.
	}
	
	//COMENTARIOS
	public Comentario salvarComentario(Long IdLivro, Comentario comentario) {
		Livro livro = buscar(IdLivro);
		comentario.setLivro(livro);
		comentario.setDataComentario(new Date());
		return comentariosRepository.save(comentario);
	}
	
	public List<Comentario> listarComentarios(Long idLivro) {
		Livro livro = buscar(idLivro);
		return livro.getComentarios();
	}

}
