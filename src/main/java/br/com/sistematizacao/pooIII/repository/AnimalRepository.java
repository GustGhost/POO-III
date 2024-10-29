package br.com.sistematizacao.pooIII.repository;

import br.com.sistematizacao.pooIII.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
