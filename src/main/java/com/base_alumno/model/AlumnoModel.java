package com.base_alumno.model;

import com.base_alumno.dto.AlumnoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumno")
public class AlumnoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String matricula;
    private String name;
    private String lastName;
    private String phone;
    private String eMail;
    private String address;

    public AlumnoDto toDto() {
        return AlumnoDto.builder()
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
