package com.finance.dashboard.repository;

import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByType(RecordType type);
    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT r.category, SUM(r.amount) FROM FinancialRecord r WHERE r.type = :type GROUP BY r.category")
    List<Object[]> findCategoryWiseTotals(@Param("type") RecordType type);
}
