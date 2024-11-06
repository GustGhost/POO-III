package br.com.sistematizacao.pooIII.factory;

import br.com.sistematizacao.pooIII.entity.Animal;
import br.com.sistematizacao.pooIII.entity.StatusAdocao;
import com.github.javafaker.Faker;

public class AnimalFactory {
    static Faker faker = new Faker();

    public static Animal novoCachorro() {
    return Animal.builder()
            .nome(faker.dog().name())
            .tipo("Cachorro")
            .idade(faker.number().numberBetween(1,10))
            .raca(faker.dog().breed())
            .status(StatusAdocao.DISPONIVEL)
            .descricao(faker.dog().memePhrase())
            .imagemUrl(faker.internet().image())
            .build();
    }

    public static Animal novoGato() {
        return Animal.builder()
                .nome(faker.cat().name())
                .tipo("Gato")
                .idade(faker.number().numberBetween(1,10))
                .raca(faker.cat().breed())
                .status(StatusAdocao.DISPONIVEL)
                .descricao(faker.dog().memePhrase())
                .imagemUrl(faker.internet().image())
                .build();
    }
}
