package com.example.demo.repositories;

import com.example.demo.entities.AuditLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    List<AuditLog>  findByUser_Id(Integer userId);
    List<AuditLog>  findByEmployee_Id(Integer employeeId);
}
