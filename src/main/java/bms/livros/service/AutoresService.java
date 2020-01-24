package bms.livros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bms.livros.domain.Autor;
import bms.livros.repository.AutoresRepository;
import bms.livros.service.exceptions.AutorExistenteException;
import bms.livros.service.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {
	
	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Optional<Autor> autorAux = autoresRepository.findById(autor.getId());
			if(autorAux.get() != null) {
				throw new AutorExistenteException("O autor já existe");
			}
		}
		return autoresRepository.save(autor);
	}
	
	public Optional<Autor> buscar(Long id) {
		Optional<Autor> autor = autoresRepository.findById(id);
		if(autor == null) {
			throw new AutorNaoEncontradoException("Não existe Autor com o ID indicado.");
		}
		return autor;
	}

}
