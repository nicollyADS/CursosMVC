package br.com.fiap.cursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "TB_IEEE_MATRICULA")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long id;

    @NotNull(message = "O aluno é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @NotNull(message = "O curso é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @NotNull(message = "A data de matrícula é obrigatória.")
    @CreatedDate
    @Column(name = "dt_matricula", nullable = false)
    private LocalDate dataMatricula;

    @NotNull(message = "O status da matrícula é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(name = "st_matricula", nullable = false)
    private Status status;

}
