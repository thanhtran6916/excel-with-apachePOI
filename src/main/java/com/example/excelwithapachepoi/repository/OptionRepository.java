package com.example.excelwithapachepoi.repository;

import com.example.excelwithapachepoi.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, String> {
}