package net.cabezudo.internal.web;

import lombok.AllArgsConstructor;
import net.cabezudo.internal.appointments.Appointment;
import net.cabezudo.internal.appointments.AppointmentManager.AppointmentManager;
import net.cabezudo.internal.doctores.Doctor;
import net.cabezudo.internal.doctores.DoctorManager;
import net.cabezudo.internal.offices.Office;
import net.cabezudo.internal.offices.OfficeManager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class AppointmentController {

  private final DoctorManager doctorManager;
  private final OfficeManager officeManager;
  private final AppointmentManager appointmentManager;

  @GetMapping("/appointments")
  public String showForm(Model model) {
    model.addAttribute("doctors", doctorManager.findAll());
    model.addAttribute("offices", officeManager.findAll());
    return "appointments";
  }

  @GetMapping("/appointments/search")
  public String searchAppointments(
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
      @RequestParam(required = false) String hour,
      @RequestParam(required = false) Integer doctorId,
      @RequestParam(required = false) Integer officeId,
      Model model) {

    LocalDateTime dateTime = null;
    if (date != null && hour != null && !hour.isEmpty()) {
      dateTime = LocalDateTime.parse(date + "T" + hour);
    }

    List<Appointment> appointments = appointmentManager.search(dateTime, doctorId, officeId);

    model.addAttribute("appointments", appointments);
    model.addAttribute("doctors", doctorManager.findAll());
    model.addAttribute("offices", officeManager.findAll());

    model.addAttribute("selectedDate", date);
    model.addAttribute("hour", hour);
    model.addAttribute("selectedDoctorId", doctorId);
    model.addAttribute("selectedOfficeId", officeId);

    return "query";
  }

  @PostMapping("/appointments")
  public String saveAppointment(@RequestParam String patient,
                                @RequestParam int doctorId,
                                @RequestParam int officeId,
                                @RequestParam String date,
                                @RequestParam String hour,
                                Model model
  ) {
    Office office = officeManager.findById(officeId);
    Doctor doctor = doctorManager.findById(doctorId);
    LocalDateTime dateTime = LocalDateTime.parse(date + "T" + hour);
    Appointment appointment = new Appointment(null, office, doctor, dateTime, patient);
    Appointment newAppointment = appointmentManager.save(appointment);
    model.addAttribute("appointment", newAppointment);
    return "redirect:/";
  }

  @PostMapping("/appointments/cancel")
  public String cancelAppointment(@RequestParam int id) {
    appointmentManager.cancelIfFuture(id);
    return "redirect:/appointments/search";
  }

  @GetMapping("/appointments/edit/{id}")
  public String editForm(@PathVariable int id, Model model) {
    Appointment appointment = appointmentManager.findById(id);
    model.addAttribute("appointment", appointment);
    model.addAttribute("doctors", doctorManager.findAll());
    model.addAttribute("offices", officeManager.findAll());
    return "edit";
  }

  @PostMapping("/appointments/edit")
  public String editAppointment(@ModelAttribute Appointment updatedAppointment) {
    appointmentManager.updateWithValidation(updatedAppointment);
    return "redirect:/appointments/search";
  }
}



