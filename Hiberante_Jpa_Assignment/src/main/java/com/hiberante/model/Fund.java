package com.hiberante.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "funds")
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "aum_amount")
    private BigDecimal aumAmount;

    @Column(name = "expense_ratio")
    private BigDecimal expenseRatio;

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getAumAmount() { return aumAmount; }
    public void setAumAmount(BigDecimal aumAmount) { this.aumAmount = aumAmount; }

    public BigDecimal getExpenseRatio() { return expenseRatio; }
    public void setExpenseRatio(BigDecimal expenseRatio) { this.expenseRatio = expenseRatio; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Manager getManager() { return manager; }
    public void setManager(Manager manager) { this.manager = manager; }

    @Override
    public String toString() {
        return "Fund{id=" + id +
               ", name='" + name + "'" +
               ", aumAmount=" + aumAmount +
               ", expenseRatio=" + expenseRatio +
               ", createdAt=" + createdAt +
               ", managerId=" + (manager != null ? manager.getId() : "null") +
               "}";
    }
}
