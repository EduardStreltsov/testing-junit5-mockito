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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
	
	@Mock
	private VisitRepository repository;
	
	@InjectMocks
	private VisitSDJpaService service;
	
	@Test
	void findAll() {
		// given
		Set<Visit> visits = new HashSet<>();
		visits.add(new Visit());
		given(repository.findAll()).willReturn(visits);
		
		// when
		Set<Visit> foundVisits = service.findAll();
		
		// then
		assertThat(foundVisits).hasSize(1);
		then(repository).should().findAll();
		then(repository).shouldHaveNoMoreInteractions();
	}
	
	@Test
	void findById() {
		
		given(repository.findById(anyLong())).willReturn(Optional.of(new Visit()));
		
		// when
		Visit found = service.findById(anyLong());
		
		// then
		assertThat(found).isNotNull();
		then(repository).should().findById(anyLong());
		then(repository).shouldHaveNoMoreInteractions();
	}
	
	@Test
	void save() {
		
		// given
		Visit visit = new Visit();
		given(repository.save(any(Visit.class))).willReturn(visit);
		
		// when
		Visit saved = service.save(visit);
		
		// then
		assertThat(saved).isNotNull();
		then(repository).should().save(any(Visit.class));
		then(repository).shouldHaveNoMoreInteractions();
	}
	
	@Test
	void delete() {
		// given
		Visit visit = new Visit();
		
		// when
		service.delete(visit);
		
		// then
		then(repository).should().delete(any(Visit.class));
		then(repository).shouldHaveNoMoreInteractions();
	}
	
	@Test
	void deleteById() {
		// given - none
		
		// when
		service.deleteById(anyLong());
		
		// then
		then(repository).should().deleteById(anyLong());
	}
}