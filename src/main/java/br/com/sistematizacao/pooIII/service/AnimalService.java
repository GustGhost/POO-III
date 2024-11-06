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
                    if (animalAtualizado.getNome() != null) {
                        animal.setNome(animalAtualizado.getNome());
                    }
                    if (animalAtualizado.getTipo() != null) {
                        animal.setTipo(animalAtualizado.getTipo());
                    }
                    if (animalAtualizado.getIdade() != null) {
                        animal.setIdade(animalAtualizado.getIdade());
                    }
                    if (animalAtualizado.getRaca() != null) {
                        animal.setRaca(animalAtualizado.getRaca());
                    }
                    if (animalAtualizado.getStatus() != null) {
                        animal.setStatus(animalAtualizado.getStatus());
                    }
                    if (animalAtualizado.getDescricao() != null) {
                        animal.setDescricao(animalAtualizado.getDescricao());
                    }
                    if (animalAtualizado.getImagemUrl() != null) {
                        animal.setImagemUrl(animalAtualizado.getImagemUrl());
                    }
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
