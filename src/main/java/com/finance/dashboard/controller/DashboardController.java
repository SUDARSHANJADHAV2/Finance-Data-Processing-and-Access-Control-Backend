package com.finance.dashboard.controller;

import com.finance.dashboard.dto.DashboardSummaryDTO;
import com.finance.dashboard.service.FinancialRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final FinancialRecordService service;

    public DashboardController(FinancialRecordService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDTO> getSummary() {
        return ResponseEntity.ok(service.getDashboardSummary());
    }
}
