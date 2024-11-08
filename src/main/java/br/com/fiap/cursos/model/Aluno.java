package br.com.fiap.cursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotBlank(message = "O nome do aluno não pode ser vazio.")
    @Size(max = 100, message = "O nome do aluno deve ter no máximo 100 caracteres.")
    @Column(name = "nm_aluno", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O e-mail não pode ser vazio.")
    @Email(message = "O e-mail deve ser válido.")
    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O curso do aluno não pode ser vazio.")
    @Size(max = 50, message = "O curso do aluno deve ter no máximo 50 caracteres.")
    @Column(name = "ds_curso", nullable = false, length = 50)
    private String curso;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Matricula> matriculas;
}
