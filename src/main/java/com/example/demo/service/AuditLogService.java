package com.example.demo.service;

import com.example.demo.entities.AuditLog;
import java.time.Instant;
import java.util.List;

public interface AuditLogService {
    AuditLog        createAuditLog(AuditLog auditLog);
    List<AuditLog>  findByUserId(Integer userId);
    List<AuditLog>  findByEmployeeId(Integer employeeId);
    List<AuditLog>  findByTableName(String tableName);
    List<AuditLog>  findByTimeRange(Instant start, Instant end);
    List<AuditLog>  findByTableNameAndRecordId(String tableName, Integer recordId);
    List<AuditLog>  findAll();
}
