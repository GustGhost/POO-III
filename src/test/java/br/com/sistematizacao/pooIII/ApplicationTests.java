package br.com.sistematizacao.pooIII;

import br.com.sistematizacao.pooIII.entity.Animal;
import br.com.sistematizacao.pooIII.factory.AnimalFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@SpringBootTest
class ApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCreateAnimal() throws Exception {
		// Criação de um novo animal
		Animal cachorro = AnimalFactory.novoCachorro();

		given()
				.contentType("application/json")
				.body(objectMapper.writeValueAsString(cachorro))
		.when()
				.post("http://localhost:8080/animais")
		.then()
				.statusCode(201)
				.body("nome", equalTo(cachorro.getNome()))
				.body("raca", equalTo(cachorro.getRaca()));
	}

	@Test
	public void testUpdateAnimal() throws Exception {
		// Criação de um novo animal e extrai o ID
		Integer id = createAnimalAndGetId();

		//Alteração de dados
		Animal updatedAnimal = Animal.builder()
				.nome("Spark")
				.imagemUrl("Spark.jpg")
				.build();

		given()
				.contentType("application/json")
				.body(objectMapper.writeValueAsString(updatedAnimal))
		.when()
				.put("http://localhost:8080/animais/" + id)
		.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", equalTo(updatedAnimal.getNome()))
				.body("imagemUrl", equalTo(updatedAnimal.getImagemUrl()));
	}

	@Test
	public void testDeleteAnimal() throws Exception {
		// Criação de um novo animal e extrai o ID
		Integer id = createAnimalAndGetId();

		//Deleção do animal
		given()
				.contentType("application/json")
		.when()
				.delete("http://localhost:8080/animais/" + id)
		.then()
				.statusCode(HttpStatus.OK.value());
	}

	private Integer createAnimalAndGetId() throws JsonProcessingException {
		Integer animalId = 	given()
									.contentType("application/json")
									.body(objectMapper.writeValueAsString(AnimalFactory.novoGato()))
							.when()
									.post("http://localhost:8080/animais")
							.then()
									.statusCode(HttpStatus.CREATED.value())
									.extract().path("id");
		return animalId;
	}
}
