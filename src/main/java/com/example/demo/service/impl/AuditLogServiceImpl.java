package com.example.demo.service.impl;

import com.example.demo.entities.AuditLog;
import com.example.demo.repositories.AuditLogRepository;
import com.example.demo.service.AuditLogService;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;


@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public AuditLog createAuditLog(AuditLog auditLog) {
        // Could add validation or preprocessing before saving maube later idk
        auditLog.setTimestamp(Instant.now());
        return auditLogRepository.save(auditLog);
    }

    @Override
    public List<AuditLog> findByUserId(Integer userId) {
        return auditLogRepository.findByUser_Id(userId);
    }

    @Override
    public List<AuditLog> findByEmployeeId(Integer employeeId) {
        return auditLogRepository.findByEmployee_Id(employeeId);
    }

    @Override
    public List<AuditLog> findByTableName(String tableName) {
        return auditLogRepository.findByTableName(tableName);
    }
}
