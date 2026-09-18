package com.base_alumno.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base_alumno.dto.AlumnoDto;
import com.base_alumno.service.AlumnoService;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AlumnoDto> alumnoDtos = alumnoService.findAll();
        return new ResponseEntity<>(alumnoDtos, HttpStatus.OK);
    }

}
