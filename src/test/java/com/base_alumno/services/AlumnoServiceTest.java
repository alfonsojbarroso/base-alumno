package com.base_alumno.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.base_alumno.dto.AlumnoDto;
import com.base_alumno.dto.AlumnoRsDto;
import com.base_alumno.model.AlumnoModel;
import com.base_alumno.respository.AlumnoRepository;
import com.base_alumno.service.AlumnoService;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoService alumnoService;

    @Test
    public void findAllTest() {
        AlumnoModel alumnoModel = mock(AlumnoModel.class);
        AlumnoDto alumnoDto = new AlumnoDto(1,
                "112233",
                "Espergencia",
                "Johansen",
                "627 123456789",
                "e.johansen@email.com",
                "Foo Street");

        when(alumnoRepository.findAll()).thenReturn(List.of(alumnoModel));
        when(alumnoModel.toDto()).thenReturn(alumnoDto);

        List<AlumnoDto> list = alumnoService.findAll();

        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(alumnoDto, list.get(0));

        verify(alumnoRepository, times(1)).findAll();
    }

    @Test
    public void saveTest() {

        // Se maquetan los objetos
        AlumnoDto alumnoDto = mock(AlumnoDto.class);
        AlumnoModel alumnoModel = mock(AlumnoModel.class);
        AlumnoModel savedModel = mock(AlumnoModel.class);

        Integer generatedId = 42;

        when(alumnoDto.toModel()).thenReturn(alumnoModel);
        when(alumnoRepository.save(alumnoModel)).thenReturn(savedModel);
        when(savedModel.getId()).thenReturn(generatedId);

        AlumnoRsDto result = alumnoService.save(alumnoDto);

        assertNotNull(generatedId);
        assertEquals("Alumno agregado", result.message());

    }

}
