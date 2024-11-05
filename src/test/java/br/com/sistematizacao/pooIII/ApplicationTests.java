package br.com.sistematizacao.pooIII;

import br.com.sistematizacao.pooIII.entity.Animal;
import br.com.sistematizacao.pooIII.entity.StatusAdocao;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCreateAnimal() throws Exception {
		// Criação de um novo animal
		Animal newAnimal = Animal.builder()
				.nome("Rex")
				.tipo("Cachorro")
				.idade(3)
				.raca("Pastor Alemão")
				.status(StatusAdocao.DISPONIVEL)
				.descricao("Cachorro ativo e amigável")
				.imagemUrl("rex.jpg")
				.build();

		given()
				.contentType("application/json")
				.body(objectMapper.writeValueAsString(newAnimal))
		.when()
				.post("/animais")
		.then()
				.statusCode(201)
				.body("nome", equalTo("Rex"))
				.body("tipo", equalTo("Cachorro"));
	}

}
