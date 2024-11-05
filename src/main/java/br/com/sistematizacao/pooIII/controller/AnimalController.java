package br.com.sistematizacao.pooIII.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistematizacao.pooIII.entity.Animal;
import br.com.sistematizacao.pooIII.service.AnimalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animais")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> listarAnimais() {
        return animalService.listarAnimais();
    }

    @PostMapping
    public ResponseEntity<Animal> cadastrarAnimal(@Valid @RequestBody Animal animal) {
        Animal savedAnimal = animalService.cadastrarAnimal(animal);
        return new ResponseEntity<>(savedAnimal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Animal atualizarAnimal(@PathVariable Long id, @Valid @RequestBody Animal animal) {
        return animalService.atualizarAnimal(id, animal);
    }

    @DeleteMapping("/{id}")
    public void removerAnimal(@PathVariable Long id) {
        animalService.removerAnimal(id);
    }

    @GetMapping("/{id}")
    public Animal buscarAnimalPorId(@PathVariable Long id) {
        return animalService.buscarPorId(id);
    }
}
