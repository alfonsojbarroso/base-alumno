package com.base_alumno.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base_alumno.dto.AlumnoDto;
import com.base_alumno.dto.AlumnoRsDto;
import com.base_alumno.model.AlumnoModel;
import com.base_alumno.respository.AlumnoRepository;

@Service
public class AlumnoService {

    // Esta onda regresa puros Model
    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    // Este metodo regresa puros dtos
    public List<AlumnoDto> findAll() {
        return alumnoRepository.findAll().stream().map(AlumnoModel::toDto).toList();
    }

    public AlumnoDto findById(Integer id) {
        return alumnoRepository.findById(id).get().toDto();
    }

    public AlumnoRsDto save(AlumnoDto alumnoDto) {
        Integer id = alumnoRepository.save(alumnoDto.toModel()).getId();
        AlumnoRsDto alumnoRsDto = new AlumnoRsDto("Alumno agregado", id);
        return alumnoRsDto;

    }

}
