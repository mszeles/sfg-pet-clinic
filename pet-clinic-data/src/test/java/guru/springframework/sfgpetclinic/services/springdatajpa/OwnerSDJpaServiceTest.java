package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

	private static final String SMITH = "Smith";

	@Mock
	private OwnerRepository ownerRepository;
	
	@Mock
	private PetRepository petRepository;
	
	@Mock
	private PetTypeRepository petTypeRepository;
	
	@InjectMocks
	OwnerSDJpaService service;
	
	Owner returnOwner;
	
	@BeforeEach
	void setUp() throws Exception {
		returnOwner = Owner.builder().id(1L).lastName(SMITH).build();
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
		
		Owner owner = service.findById(1L);
		
		assertNotNull(owner);
	}

	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner owner = service.findById(1L);
		
		assertNull(owner);
	}
	
	@Test
	void testFindAll() {
		Set<Owner> returnOwnerSet = new HashSet<>();
		returnOwnerSet.add(Owner.builder().id(1L).build());
		returnOwnerSet.add(Owner.builder().id(2L).build());
		
		when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
		
		Set<Owner> owners = service.findAll();
		
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void testSave() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		
		Owner savedOwner = service.save(ownerToSave);
		
		assertNotNull(savedOwner);
		
		verify(ownerRepository).save(any());
	}

	@Test
	void testDelete() {
		service.delete(returnOwner);
		verify(ownerRepository).delete(any());
	}

	@Test
	void testDeleteById() {
		service.deleteById(1L);
		verify(ownerRepository).deleteById(anyLong());
	}

	@Test
	void testFindByLastName() {
		Owner returnOwner = Owner.builder().id(1L).lastName(SMITH).build();
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		Owner smith = service.findByLastName(SMITH);
		assertEquals(SMITH, smith.getLastName());
		verify(ownerRepository).findByLastName(any());
	}

}
