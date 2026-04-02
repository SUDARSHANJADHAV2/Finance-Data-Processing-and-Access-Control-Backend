package com.finance.dashboard.service;

import com.finance.dashboard.dto.DashboardSummaryDTO;
import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.RecordType;
import com.finance.dashboard.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FinancialRecordService {

    private final FinancialRecordRepository repository;

    public FinancialRecordService(FinancialRecordRepository repository) {
        this.repository = repository;
    }

    public FinancialRecord createRecord(FinancialRecord record) {
        return repository.save(record);
    }

    public FinancialRecord updateRecord(Long id, FinancialRecord updatedRecord) {
        return repository.findById(id).map(record -> {
            record.setAmount(updatedRecord.getAmount());
            record.setType(updatedRecord.getType());
            record.setCategory(updatedRecord.getCategory());
            record.setDate(updatedRecord.getDate());
            record.setNotes(updatedRecord.getNotes());
            return repository.save(record);
        }).orElseThrow(() -> new RuntimeException("Record not found with id " + id));
    }

    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }

    public FinancialRecord getRecordById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id " + id));
    }

    public List<FinancialRecord> getAllRecords() {
        return repository.findAll();
    }

    public DashboardSummaryDTO getDashboardSummary() {
        List<FinancialRecord> allRecords = repository.findAll();

        BigDecimal totalIncome = allRecords.stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .map(FinancialRecord::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = allRecords.stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .map(FinancialRecord::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal netBalance = totalIncome.subtract(totalExpenses);

        Map<String, BigDecimal> incomeByCategory = allRecords.stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .collect(Collectors.groupingBy(
                        FinancialRecord::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, FinancialRecord::getAmount, BigDecimal::add)
                ));

        Map<String, BigDecimal> expenseByCategory = allRecords.stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .collect(Collectors.groupingBy(
                        FinancialRecord::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, FinancialRecord::getAmount, BigDecimal::add)
                ));

        DashboardSummaryDTO summary = new DashboardSummaryDTO();
        summary.setTotalIncome(totalIncome);
        summary.setTotalExpenses(totalExpenses);
        summary.setNetBalance(netBalance);
        summary.setCategoryWiseIncome(incomeByCategory);
        summary.setCategoryWiseExpenses(expenseByCategory);

        return summary;
    }
}
