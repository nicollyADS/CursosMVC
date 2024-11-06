package br.com.fiap.cursos.repository;

import br.com.fiap.cursos.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
