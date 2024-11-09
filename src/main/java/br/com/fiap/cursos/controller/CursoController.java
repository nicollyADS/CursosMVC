package br.com.fiap.cursos.controller;

import br.com.fiap.cursos.model.Aluno;
import br.com.fiap.cursos.model.Curso;
import br.com.fiap.cursos.repository.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("curso")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;




    @GetMapping("cadastrar")
    public String cadastrar(Curso curso, Model model){
        return "curso/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Curso curso, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "curso/cadastrar";
        }
        cursoRepository.save(curso);
        redirectAttributes.addFlashAttribute("mensagem", "curso cadastrado com sucesso!");
        return "redirect:/curso/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "curso/listar";
    }



    @GetMapping("detalhes/{id}")
    public String detalheCurso(@PathVariable Long id, Model model) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            model.addAttribute("curso", optionalCurso.get());
        } else {
            model.addAttribute("erro", "curso não encontrado");
            return "error";
        }
        return "curso/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("curso", cursoRepository.findById(id));
        return "curso/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Curso curso, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "curso/editar";
        }
        cursoRepository.save(curso);
        redirectAttributes.addFlashAttribute("mensagem", "o curso foi atualizado!");
        return "redirect:/curso/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        cursoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "curso removido com sucesso");
        return "redirect:/curso/listar";
    }

    @GetMapping("pesquisar")
    public String pesquisarCursos(@RequestParam String query, Model model) {
        List<Curso> cursos = cursoRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("cursos", cursos);
        return "curso/pesquisar";
    }
}
