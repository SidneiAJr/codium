package com.mycompany.mavenproject1;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final Repository repository;

    public UsuarioController(Repository repository) {
        this.repository = repository;
    }

    // ===== LISTAR TODOS (GET) =====
    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // ===== BUSCAR POR ID (GET) =====
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // ===== CRIAR (POST) =====
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    // ===== ATUALIZAR (PUT) =====
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return repository.save(usuario);
    }

    // ===== DELETAR (DELETE) =====
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "Usuário deletado com sucesso!";
    }
}
