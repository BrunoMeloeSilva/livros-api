package bms.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bms.livros.domain.Livro;

//O crud para o banco da classe Livro é implementado pra mim pelo JpaRepository
public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
