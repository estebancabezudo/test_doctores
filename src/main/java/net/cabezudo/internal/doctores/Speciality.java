package net.cabezudo.internal.doctores;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Speciality {
  private final Integer id;
  private final String name;

  public Speciality(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
