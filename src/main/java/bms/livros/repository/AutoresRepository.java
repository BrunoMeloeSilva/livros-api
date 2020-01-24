package bms.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bms.livros.domain.Autor;

//O crud para o banco de dados da classe Autor é implementado pra mim pelo JpaRepository
public interface AutoresRepository extends JpaRepository<Autor, Long> {

}
