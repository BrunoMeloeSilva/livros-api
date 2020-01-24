package bms.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bms.livros.domain.Comentario;

//O crud para o banco de dados da classe Comentario é implementado pra mim pelo JpaRepository
public interface ComentariosRepository extends JpaRepository<Comentario, Long>{

}
