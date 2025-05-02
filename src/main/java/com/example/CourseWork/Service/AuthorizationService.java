package com.example.CourseWork.Service;

import com.example.CourseWork.Dao.*;
import com.example.CourseWork.Model.Entity.Authorization;
import com.example.CourseWork.Model.Enum.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private AuthorizationDao userDao = new AuthorizationDaoImpl();
    private final AuthorizationRepo repository;

    public Authorization save(Authorization user) {
        return repository.save(user);
    }

    public Integer create(Authorization user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        return save(user).getId();
    }

    public Authorization getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public Authorization getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public boolean isAdmin(UserDetails userDetails){
        var isAdmin= false;
        if (userDetails instanceof Authorization customUserDetails) {
            isAdmin = customUserDetails.getRole().equals(Role.ROLE_ADMIN);
        }
        return isAdmin;
    }


    public void isExistsByUsername(String username) {
        if (repository.existsByUsername(username)) {
            throw new EntityNotFoundException(String.format("Exists Employee %s", username));
        }
    }

}
