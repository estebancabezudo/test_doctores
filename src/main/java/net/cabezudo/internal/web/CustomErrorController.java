package net.cabezudo.internal.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

  private final ErrorAttributes errorAttributes;

  public CustomErrorController(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Model model) {
    ServletWebRequest webRequest = new ServletWebRequest(request);
    Map<String, Object> errors = errorAttributes.getErrorAttributes(
        webRequest, ErrorAttributeOptions.of(
            ErrorAttributeOptions.Include.MESSAGE,
            ErrorAttributeOptions.Include.EXCEPTION,
            ErrorAttributeOptions.Include.STACK_TRACE,
            ErrorAttributeOptions.Include.BINDING_ERRORS
        )
    );

    model.addAttribute("timestamp", LocalDateTime.now());
    model.addAttribute("status", errors.get("status"));
    model.addAttribute("error", errors.get("error"));
    model.addAttribute("message", errors.get("message"));
    model.addAttribute("path", errors.get("path"));
    model.addAttribute("exception", errors.get("exception"));
    model.addAttribute("trace", errors.get("trace"));

    return "error";
  }
}

