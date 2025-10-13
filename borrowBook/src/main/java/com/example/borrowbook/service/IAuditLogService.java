package com.example.borrowbook.service;

import com.example.borrowbook.entity.AuditLog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAuditLogService {
    void saveLog(Long bookId, String action, int delta, String note);
    List<AuditLog> findAll();
    Page<AuditLog> page(int page, int size);
}
