//package com.example.parkingLot2.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.file.AccessDeniedException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//  private final ObjectMapper objectMapper = new ObjectMapper();
//
//  @Override
//  public void handle(HttpServletRequest request, HttpServletResponse response,
//      org.springframework.security.access.AccessDeniedException accessDeniedException)
//      throws IOException, ServletException {
//    response.setStatus(HttpStatus.FORBIDDEN.value());
//    response.setContentType("application/json");
//
//    Map<String, Object> responseBody = new HashMap<>();
//    responseBody.put("error", "Access Denied");
//    responseBody.put("message", "You don't have permission to access this resource.");
//
//    OutputStream out = response.getOutputStream();
//    objectMapper.writeValue(out, responseBody);
//    out.flush();
//  }
//}
