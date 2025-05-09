package net.cabezudo.internal.doctores.persistence;

import net.cabezudo.internal.doctores.mappers.DoctorRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepository {
  private static final Logger log = LoggerFactory.getLogger(DoctorRepository.class);

  public DoctorEntity findById(JdbcTemplate jdbcTemplate, int id) {
    log.debug("Search doctor with id {}", id);
    String query = "SELECT id, name, lastName, motherLastName, speciality_id FROM doctors WHERE id = ?";
    return jdbcTemplate.query(query, new DoctorRowMapper(), id).stream().findFirst().orElse(null);
  }

  public List<DoctorEntity> findAll(JdbcTemplate jdbcTemplate) {
    log.debug("Retrieve all doctors");
    return jdbcTemplate.query("SELECT id, name, lastName, motherLastName, speciality_id FROM doctors", new DoctorRowMapper());
  }
}
