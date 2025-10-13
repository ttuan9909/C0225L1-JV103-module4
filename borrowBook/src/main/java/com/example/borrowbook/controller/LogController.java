package com.example.borrowbook.controller;

import com.example.borrowbook.service.AuditLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogController {

    private final AuditLogService auditLogService;

    public LogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping("/logs")
    public String listLogs(@RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "10") int size,
                           Model model) {
        var logs = auditLogService.page(page, size);
        model.addAttribute("logs", logs);        // Page<AuditLog>
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "logs/list";
    }
}