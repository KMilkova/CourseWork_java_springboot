package com.example.CourseWork.Service;

import com.example.CourseWork.Dto.AuthorizationEmployeeDTO;
import com.example.CourseWork.Dto.RegistrationEmployeeDTO;
import com.example.CourseWork.Dto.JwtAuthenticationResponseDTO;
import com.example.CourseWork.Model.Entity.Authorization;
import com.example.CourseWork.Model.Entity.CarOwner;
import com.example.CourseWork.Model.Enum.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthorizationService employeeService;
    private final CarService carService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public Integer signUp(RegistrationEmployeeDTO dto) {
        employeeService.isExistsByUsername(dto.getUsername());

        var carOwner = CarOwner.builder()
                .fullName(dto.getFullName())
                .ownerEmail(dto.getEmail())
                .telephoneNumber(dto.getTelephone())
                .gender(dto.getGender())
                .build();

        carService.saveCarOwner(carOwner);
        var lastOwner = carService.getLast();

        var authorization = Authorization.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ROLE_USER)
                .roleId(lastOwner.getIdOwner())
                .status(carService.getStatusByID(1))
                .build();

        return employeeService.create(authorization);
    }

    public JwtAuthenticationResponseDTO signIn(AuthorizationEmployeeDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = employeeService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        var isAdmin = employeeService.isAdmin(user);
        return new JwtAuthenticationResponseDTO(jwt, isAdmin);
    }
}
