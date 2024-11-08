package br.com.fiap.cursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "TB_IEEE_CURSO")
@Getter
@Setter
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;

    @NotBlank(message = "O nome do curso não pode ser vazio.")
    @Size(max = 100, message = "O nome do curso deve ter no máximo 100 caracteres.")
    @Column(name = "nm_curso", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "A descrição do curso não pode ser vazia.")
    @Size(max = 255, message = "A descrição do curso deve ter no máximo 255 caracteres.")
    @Column(name = "ds_curso", length = 255, nullable = false)
    private String descricao;

    @NotNull(message = "A data de início do curso é obrigatória.")
    @Future(message = "A data de início do curso deve ser uma data futura.")
    @Column(name = "dt_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull(message = "A carga horária do curso é obrigatória.")
    @Column(name = "ds_horario", nullable = false)
    private int cargaHoraria;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Matricula> matriculas;
}
