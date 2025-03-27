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
}
