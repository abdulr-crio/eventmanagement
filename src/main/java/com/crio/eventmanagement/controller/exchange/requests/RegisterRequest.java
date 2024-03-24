package com.crio.eventmanagement.controller.exchange.requests;

import com.crio.eventmanagement.model.enums.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank private String firstName;
  @NotBlank private String lastName;
  @NotBlank private String email;
  @NotBlank private String password;
  private Role role;
}
