package com.example.borrowbook.service;

import com.example.borrowbook.entity.AuditLog;
import com.example.borrowbook.repository.ILogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService implements IAuditLogService {
    private final ILogRepository auditLogRepository;

    public AuditLogService(ILogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public void saveLog(Long bookId, String action, int delta, String note) {
        AuditLog log = new AuditLog();
        log.setBookId(bookId);
        log.setAction(action);
        log.setDelta(delta);
        log.setAt(LocalDateTime.now());
        log.setNote(note);
        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }

    @Override
    public Page<AuditLog> page(int page, int size) {
        return auditLogRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "at"))
        );
    }
}
