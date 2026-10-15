package com.base_alumno.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.base_alumno.dto.AlumnoDto;
import com.base_alumno.dto.AlumnoRsDto;
import com.base_alumno.service.AlumnoService;

@RestController
@RequestMapping("/alumno")
@CrossOrigin(origins = { "http://127.0.0.1:4173", "http://localhost:4173" })
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

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "id") Integer id) {
        AlumnoDto alumnoDto = alumnoService.findById(id);
        return new ResponseEntity<>(alumnoDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlumnoRsDto> save(@RequestBody AlumnoDto alumnoDto) {
        AlumnoRsDto alumnoRsDto = alumnoService.save(alumnoDto);
        return new ResponseEntity<>(alumnoRsDto, HttpStatus.OK);
    }

}
