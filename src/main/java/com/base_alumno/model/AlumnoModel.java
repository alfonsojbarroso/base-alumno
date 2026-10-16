package com.base_alumno.model;

import com.base_alumno.dto.AlumnoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public AlumnoModel() {
    }

    public AlumnoModel(Integer id, String matricula, String name, String lastName, String phone, String eMail,
            String address) {
        this.id = id;
        this.matricula = matricula;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.eMail = eMail;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AlumnoDto toDto() {
        return new AlumnoDto(id, matricula, name, lastName, phone, eMail, address);
    }

}
