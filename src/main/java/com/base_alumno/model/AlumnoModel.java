package com.base_alumno.model;

import com.base_alumno.dto.AlumnoDto;

import jakarta.persistence.Column;
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
    @Column(name = "matricula", columnDefinition = "varchar(20)")
    private String matricula;
    @Column(name = "name", columnDefinition = "varchar(80)")
    private String name;
    @Column(name = "lastname", columnDefinition = "varchar(80)")
    private String lastName;
    @Column(name = "phone", columnDefinition = "varchar(20)")
    private String phone;
    @Column(name = "email", columnDefinition = "varchar(80)")
    private String eMail;
    @Column(name = "address", columnDefinition = "varchar(110)")
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
