package com.guztaver.nexz;

import com.guztaver.nexz.restservice.controllers.RpgCharacterController;
import com.guztaver.nexz.restservice.entities.RpgCharacter;
import com.guztaver.nexz.restservice.repositories.RpgCharacterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class RpgCharacterControllerTests {

	@Mock
	private RpgCharacterRepository rpgCharacterRepository;

	@InjectMocks
	private RpgCharacterController rpgCharacterController;

	@Test
	void testGetUserById() {
		int id = 1; RpgCharacter character = RpgCharacter.builder().id(id).name("Test Character").age(25).build();

		when(rpgCharacterRepository.findById(id)).thenReturn(Optional.of(character));

		ResponseEntity<?> response = rpgCharacterController.getUserById(id);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); assertThat(response.getBody()).isEqualTo(character);
	}

	@Test
	void testGetUserByIdNotFound() {
		int id = 1;

		when(rpgCharacterRepository.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<?> response = rpgCharacterController.getUserById(id);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND); assertThat(response.getBody()).isEqualTo("Personagem com ID " + id + " não encontrado.");
	}

	@Test
	void testDeleteUserById() {
		int id = 1; RpgCharacter character = RpgCharacter.builder().id(id).name("Test Character").age(25).build();

		when(rpgCharacterRepository.findById(id)).thenReturn(Optional.of(character));

		ResponseEntity<?> response = rpgCharacterController.deleteUserById(id);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); assertThat(response.getBody()).isEqualTo("DELETED"); verify(rpgCharacterRepository, times(1)).deleteById(id);
	}

	@Test
	void testUpdateUserById() {
		// Mock data
		int id = 1; String updatedName = "UpdatedName"; int updatedAge = 25;

		// Mock repository behavior
		RpgCharacter existingCharacter = new RpgCharacter(); Mockito.when(rpgCharacterRepository.findById(id)).thenReturn(Optional.of(existingCharacter));

		// Execute the update
		ResponseEntity<String> response = rpgCharacterController.updateUserById(id, updatedName, updatedAge);

		// Verify repository interactions
		Mockito.verify(rpgCharacterRepository).findById(id); Mockito.verify(rpgCharacterRepository).save(existingCharacter);

		// Verify the response
		assertEquals(HttpStatus.OK, response.getStatusCode()); assertEquals("UPDATED", response.getBody());
	}

	@Test
	void testDeleteUserByIdNotFound() {
		int id = 1;

		when(rpgCharacterRepository.findById(id)).thenReturn(Optional.empty());

		ResponseEntity<?> response = rpgCharacterController.deleteUserById(id);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND); assertThat(response.getBody()).isEqualTo("Personagem com ID " + id + " não encontrado. Não foi possível deletar.");
		verify(rpgCharacterRepository, never()).deleteById(id);
	}
}
