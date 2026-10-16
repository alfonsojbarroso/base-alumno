package com.base_alumno.dto;

import com.base_alumno.model.AlumnoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record AlumnoDto(
        @JsonIgnore Integer id,
        String matricula,
        String name,
        String lastName,
        String phone,
        String eMail,
        String address) {

    public AlumnoModel toModel() {

        return new AlumnoModel(
                this.id,
                this.matricula,
                this.name,
                this.lastName,
                this.phone,
                this.eMail,
                this.address);

    }
}
