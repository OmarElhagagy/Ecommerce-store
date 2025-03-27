package com.example.demo.repositories;

import com.example.demo.entities.AuditLog;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    List<AuditLog>  findByUser_Id(Integer userId);
    List<AuditLog>  findByEmployee_Id(Integer employeeId);
    List<AuditLog>  findByTableName(String tableName);
    List<AuditLog>  findByRecordId(Integer recordId);
    List<AuditLog>  findByTimestampBetween(Instant start, Instant end);
    List<AuditLog>  findByTableNameAndRecordId(String tableName, Integer recordId);
}
