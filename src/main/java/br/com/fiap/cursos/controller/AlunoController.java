package br.com.fiap.cursos.controller;

import br.com.fiap.cursos.model.Aluno;
import br.com.fiap.cursos.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Aluno aluno, Model model){
        return "aluno/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Aluno aluno, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "aluno/cadastrar";
        }
        alunoRepository.save(aluno);
        redirectAttributes.addFlashAttribute("mensagem", "aluno cadastrado com sucesso!");
        return "redirect:/aluno/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        return "aluno/listar";
    }



    @GetMapping("detalhes/{id}")
    public String detalhesAluno(@PathVariable Long id, Model model) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            model.addAttribute("aluno", optionalAluno.get());
        } else {
            model.addAttribute("erro", "aluno não encontrado");
            return "error";
        }
        return "aluno/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("aluno", alunoRepository.findById(id));
        return "aluno/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Aluno aluno, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "aluno/editar";
        }
        alunoRepository.save(aluno);
        redirectAttributes.addFlashAttribute("mensagem", "o aluno foi atualizado!");
        return "redirect:/aluno/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        alunoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "aluno removido com sucesso");
        return "redirect:/aluno/listar";
    }


    @GetMapping("pesquisar")
    public String pesquisarAlunos(@RequestParam String query, Model model) {
        List<Aluno> alunos = alunoRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("alunos", alunos);
        return "aluno/pesquisar";
    }


}
