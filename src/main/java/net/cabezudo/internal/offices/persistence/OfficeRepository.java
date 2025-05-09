package net.cabezudo.internal.offices.persistence;

import net.cabezudo.internal.offices.mappers.OfficeRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfficeRepository {
  private static final Logger log = LoggerFactory.getLogger(OfficeRepository.class);

  public OfficeEntity findById(JdbcTemplate jdbcTemplate, int id) {
    log.debug("Search office with id {}", id);
    String query = "SELECT id, number, floor FROM offices WHERE id = ?";
    return jdbcTemplate.query(query, new OfficeRowMapper(), id).stream().findFirst().orElse(null);
  }

  public List<OfficeEntity> findAll(JdbcTemplate jdbcTemplate) {
    log.debug("Retrieve all offices");
    return jdbcTemplate.query("SELECT id, number, floor FROM offices", new OfficeRowMapper());
  }
}
