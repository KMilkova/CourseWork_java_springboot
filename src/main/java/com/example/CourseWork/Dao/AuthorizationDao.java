package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.Authorization;

public interface AuthorizationDao {
    public Authorization findByLogin(String login);
    public Authorization saveAuthorization(Authorization authorization);

}
