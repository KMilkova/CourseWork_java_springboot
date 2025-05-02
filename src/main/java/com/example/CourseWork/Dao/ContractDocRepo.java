package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.Contractdocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractDocRepo extends JpaRepository<Contractdocument, Integer> {

    List<Contractdocument> findAll();

}
