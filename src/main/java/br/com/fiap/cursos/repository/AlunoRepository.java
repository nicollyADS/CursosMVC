package br.com.fiap.cursos.repository;

import br.com.fiap.cursos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
