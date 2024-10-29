package br.com.sistematizacao.pooIII.service;

import br.com.sistematizacao.pooIII.entity.Animal;
import br.com.sistematizacao.pooIII.exception.ResourceNotFoundException;
import br.com.sistematizacao.pooIII.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> listarAnimais() {
        return animalRepository.findAll();
    }

    public Animal cadastrarAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal atualizarAnimal(Long id, Animal animalAtualizado) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animal.setNome(animalAtualizado.getNome());
                    animal.setTipo(animalAtualizado.getTipo());
                    animal.setIdade(animalAtualizado.getIdade());
                    animal.setRaca(animalAtualizado.getRaca());
                    animal.setStatus(animalAtualizado.getStatus());
                    animal.setDescricao(animalAtualizado.getDescricao());
                    animal.setImagemUrl(animalAtualizado.getImagemUrl());
                    return animalRepository.save(animal);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
    }

    public void removerAnimal(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
        animalRepository.delete(animal);
    }

    public Animal buscarPorId(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
    }
}
