package net.cabezudo.internal.offices;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Office {
    private final Integer id;
    private final Integer number;
    private final Integer floor;

    // Sé que puede hacer esto Lombok, pero no me gusta el riesgo de que
    // si cambia el orden de las propiedades cambie el orden del constructor
    public Office(Integer id, Integer number, Integer floor) {
      this.id = id;
      this.number = number;
      this.floor = floor;
    }
}
