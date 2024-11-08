package br.com.fiap.cursos.controller;

import br.com.fiap.cursos.model.Matricula;
import br.com.fiap.cursos.model.Status;
import br.com.fiap.cursos.repository.AlunoRepository;
import br.com.fiap.cursos.repository.CursoRepository;
import br.com.fiap.cursos.repository.MatriculaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("matricula")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Matricula matricula, Model model){
        model.addAttribute("statusOptions", Status.values());
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "matricula/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Matricula matricula, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "matricula/cadastrar";
        }
        matriculaRepository.save(matricula);
        model.addAttribute("statusOptions", Status.values());
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        redirectAttributes.addFlashAttribute("mensagem", "matricula cadastrada com sucesso!");
        return "redirect:/matricula/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("matriculas", matriculaRepository.findAll());
        return "matricula/listar";
    }



    @GetMapping("detalhes/{id}")
    public String detalhesMatricula(@PathVariable Long id, Model model) {
        Optional<Matricula> optionalMatricula = matriculaRepository.findById(id);
        if (optionalMatricula.isPresent()) {
            model.addAttribute("matricula", optionalMatricula.get());
            model.addAttribute("statusOptions", Status.values());
            model.addAttribute("alunos", alunoRepository.findAll());
            model.addAttribute("cursos", cursoRepository.findAll());
        } else {
            model.addAttribute("erro", "matricula não encontrada");
            return "error";
        }
        return "matricula/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("statusOptions", Status.values());
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        model.addAttribute("matricula", matriculaRepository.findById(id));
        return "matricula/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Matricula matricula, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "matricula/editar";
        }
        matriculaRepository.save(matricula);
        model.addAttribute("statusOptions", Status.values());
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        redirectAttributes.addFlashAttribute("mensagem", "a matricula foi atualizado!");
        return "redirect:/matricula/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        matriculaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "matricula removidas com sucesso");
        return "redirect:/matricula/listar";
    }
}
