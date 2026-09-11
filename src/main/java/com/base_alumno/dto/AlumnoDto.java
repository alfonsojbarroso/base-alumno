package com.base_alumno.dto;

import com.base_alumno.model.AlumnoModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDto {

    private Integer id;
    private String matricula;
    private String name;
    private String lastName;
    private String phone;
    private String eMail;
    private String address;

    public AlumnoModel toModel() {
        return AlumnoModel.builder()
                .id(this.id)
                .matricula(this.matricula)
                .name(this.name)
                .lastName(this.lastName)
                .phone(this.phone)
                .eMail(this.eMail)
                .address(this.address)
                .build();
    }
}
