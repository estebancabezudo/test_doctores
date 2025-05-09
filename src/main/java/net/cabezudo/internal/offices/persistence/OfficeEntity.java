package net.cabezudo.internal.offices.persistence;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class OfficeEntity {
  private Integer id;
  private Integer number;
  private Integer floor;

  public OfficeEntity(Integer id, Integer number, Integer floor) {
    this.id = id;
    this.number = number;
    this.floor = floor;
  }
}
