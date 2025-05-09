package net.cabezudo.internal.doctores.persistence;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class DoctorEntity {
  private final Integer id;
  private final String name;
  private final String lastName;
  private final String motherLastName;
  private final Integer speciality;

  // Sé que puede hacer esto Lombok, pero no me gusta el riesgo de que
  // si cambia el orden de las propiedades cambie el orden del constructor
  public DoctorEntity(Integer id, String name, String lastName, String motherLastName, Integer speciality) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.motherLastName = motherLastName;
    this.speciality = speciality;
  }
}
