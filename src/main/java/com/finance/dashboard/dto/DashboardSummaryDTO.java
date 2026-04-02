package com.finance.dashboard.dto;

import java.math.BigDecimal;
import java.util.Map;

public class DashboardSummaryDTO {
    private BigDecimal totalIncome = BigDecimal.ZERO;
    private BigDecimal totalExpenses = BigDecimal.ZERO;
    private BigDecimal netBalance = BigDecimal.ZERO;
    private Map<String, BigDecimal> categoryWiseIncome;
    private Map<String, BigDecimal> categoryWiseExpenses;

    public DashboardSummaryDTO() {}

    public BigDecimal getTotalIncome() { return totalIncome; }
    public void setTotalIncome(BigDecimal totalIncome) { this.totalIncome = totalIncome; }

    public BigDecimal getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(BigDecimal totalExpenses) { this.totalExpenses = totalExpenses; }

    public BigDecimal getNetBalance() { return netBalance; }
    public void setNetBalance(BigDecimal netBalance) { this.netBalance = netBalance; }

    public Map<String, BigDecimal> getCategoryWiseIncome() { return categoryWiseIncome; }
    public void setCategoryWiseIncome(Map<String, BigDecimal> categoryWiseIncome) { this.categoryWiseIncome = categoryWiseIncome; }

    public Map<String, BigDecimal> getCategoryWiseExpenses() { return categoryWiseExpenses; }
    public void setCategoryWiseExpenses(Map<String, BigDecimal> categoryWiseExpenses) { this.categoryWiseExpenses = categoryWiseExpenses; }
}
