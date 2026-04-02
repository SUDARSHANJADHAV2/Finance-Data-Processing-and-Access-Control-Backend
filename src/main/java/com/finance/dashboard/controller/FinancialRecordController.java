package com.finance.dashboard.controller;

import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.service.FinancialRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class FinancialRecordController {

    private final FinancialRecordService service;

    public FinancialRecordController(FinancialRecordService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FinancialRecord>> getAllRecords() {
        return ResponseEntity.ok(service.getAllRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialRecord> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecordById(id));
    }

    @PostMapping
    public ResponseEntity<FinancialRecord> createRecord(@Valid @RequestBody FinancialRecord record) {
        return new ResponseEntity<>(service.createRecord(record), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialRecord> updateRecord(@PathVariable Long id, @Valid @RequestBody FinancialRecord record) {
        return ResponseEntity.ok(service.updateRecord(id, record));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        service.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
