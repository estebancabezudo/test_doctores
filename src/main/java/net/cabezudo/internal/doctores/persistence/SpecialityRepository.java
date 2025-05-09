package net.cabezudo.internal.doctores.persistence;

import net.cabezudo.internal.doctores.mappers.SpecialityRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialityRepository {
  private static final Logger log = LoggerFactory.getLogger(SpecialityRepository.class);

  public SpecialityEntity findById(JdbcTemplate jdbcTemplate, int id) {
      log.debug("Search speciality with id {}", id);
      String query = "SELECT id, name FROM specialities WHERE id = ?";
      return jdbcTemplate.query(query, new SpecialityRowMapper(), id).stream().findFirst().orElse(null);
  }
}
