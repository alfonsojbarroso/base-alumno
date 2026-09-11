package com.base_alumno.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.base_alumno.model.AlumnoModel;

@Repository 
public interface AlumnoRepository extends JpaRepository<AlumnoModel, Integer> {

}
