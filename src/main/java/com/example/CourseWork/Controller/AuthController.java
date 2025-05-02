package com.example.CourseWork.Controller;

import com.example.CourseWork.Dto.AuthorizationEmployeeDTO;
import com.example.CourseWork.Dto.JwtAuthenticationResponseDTO;
import com.example.CourseWork.Dto.RegistrationEmployeeDTO;
import com.example.CourseWork.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public Integer signUp(@RequestBody RegistrationEmployeeDTO request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponseDTO signIn(@RequestBody AuthorizationEmployeeDTO request) {
        return authenticationService.signIn(request);
    }
}

