package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {
	
	@Mock
	private VetRepository vetRepository;
	
	@InjectMocks
	private VetSDJpaService service;
	
	@Test
	void delete() {
		
		service.deleteById(1L);
		
		verify(vetRepository, times(1)).deleteById(1L);
	}

}