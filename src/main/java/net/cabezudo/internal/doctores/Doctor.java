package net.cabezudo.internal.doctores;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Doctor {
  private final Integer id;
  private final String name;
  private final String lastName;
  private final String motherLastName;
  private final Speciality speciality;

  // Sé que puede hacer esto Lombok, pero no me gusta el riesgo de que
  // si cambia el orden de las propiedades cambie el orden del constructor
  public Doctor(Integer id, String name, String lastName, String motherLastName, Speciality speciality) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.motherLastName = motherLastName;
    this.speciality = speciality;
  }
}
