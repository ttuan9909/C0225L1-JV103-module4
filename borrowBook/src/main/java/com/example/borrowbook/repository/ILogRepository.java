package com.example.borrowbook.repository;

import com.example.borrowbook.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogRepository extends JpaRepository<AuditLog, Integer> {
}
