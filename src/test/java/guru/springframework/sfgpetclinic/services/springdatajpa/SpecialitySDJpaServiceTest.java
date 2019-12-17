package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;
    
    @Test
    void  findById() {
        Speciality speciality = new Speciality();
        
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
        
        Speciality foundSpeciality = service.findById(1L);
        assertThat(foundSpeciality).isNotNull();
        
        verify(specialtyRepository).findById(anyLong());
    }
    
    @Test
    void findByIdBDD() {
        Speciality speciality = new Speciality();
        
        given(specialtyRepository.findById(anyLong())).willReturn(Optional.of(speciality));
        
        Speciality foundSpeciality = service.findById(1L);
        
        assertThat(foundSpeciality).isNotNull();
        
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }
    
    @Test
    void deleteByIdVariants() {
        // given - none
        
        // when
        service.deleteById(1L);
        service.deleteById(1L);
        
        // then
        then(specialtyRepository).should(times(2)).deleteById(anyLong());
        
        verify(specialtyRepository, times(2)).deleteById(1L);
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
        verify(specialtyRepository, atMost(5)).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void delete() {
        
        service.delete(new Speciality());
        
        verify(specialtyRepository).delete(any(Speciality.class));
    }
    
    @Test
    void doThrowText() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).deleteById(anyLong());
    
        assertThrows(RuntimeException.class, () -> specialtyRepository.deleteById(anyLong()));
        
        verify(specialtyRepository).deleteById(anyLong());
    }
    
    @Test
    void doThrowBDD() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));
        
        assertThrows(RuntimeException.class, () -> specialtyRepository.findById(1L));
        
        then(specialtyRepository).should().findById(1L);
    }
    
    @Test
    void deleteBDD() {
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());
        
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(any()));
        
        then(specialtyRepository).should().delete(any());
    }
}