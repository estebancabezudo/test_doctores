package net.cabezudo.internal.doctores.persistence;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class SpecialityEntity {
  private final Integer id;
  private final String name;

  // Sé que puede hacer esto Lombok, pero no me gusta el riesgo de que
  // si cambia el orden de las propiedades cambie el orden del constructor
  public SpecialityEntity(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
