package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
	
	@Mock
	private VisitRepository repository;
	
	@InjectMocks
	private VisitSDJpaService service;
	
	@Test
	void findAll() {
		Set<Visit> visits = new HashSet<>();
		visits.add(new Visit());
		
		when(repository.findAll()).thenReturn(visits);
		
		Set<Visit> foundVisits = service.findAll();
		
		verify(repository).findAll();
		
		assertThat(foundVisits).hasSize(1);
	}
	
	@Test
	void findById() {
		
		when(repository.findById(anyLong())).thenReturn(Optional.of(new Visit()));
		
		Visit found = service.findById(anyLong());
		assertThat(found).isNotNull();
		
		verify(repository).findById(anyLong());
		
	}
	
	@Test
	void save() {
		
		Visit visit = new Visit();
		when(repository.save(any(Visit.class))).thenReturn(visit);
		
		Visit saved = service.save(visit);
		
		assertThat(saved).isNotNull();
		
		verify(repository).save(any(Visit.class));
	}
	
	@Test
	void delete() {
		Visit visit = new Visit();
		service.delete(visit);
		verify(repository).delete(any(Visit.class));
	}
	
	@Test
	void deleteById() {
		Visit visit = new Visit();
		service.deleteById(anyLong());
		verify(repository).deleteById(anyLong());
	
	}
}