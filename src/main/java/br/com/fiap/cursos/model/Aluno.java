package br.com.fiap.cursos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_IEEE_ALUNO")
@Getter
@Setter
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno", length = 9)
    private Long id;

    @Column(name = "nm_aluno", nullable = false, length = 100)
    private String nome;

    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @Column(name = "ds_curso", nullable = false, length = 50)
    private String curso;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Matricula> matriculas;

}
