package com.microservices.merchantOnboarding.merchantOnboarding.EntityModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "DepositTransaction")
public class DepositTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long depotransactionId;
    @Column(name = "Username")
   // private long authTransactionId;
    private String username;
    //private String password;
   // @Min(value = 100,message = "Minimum deposit amount should be INR 100")
    @Column(name = "TransactionAmount")
    private double transactionAmount;
   // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   // @JsonFormat(pattern = "MM/dd/yyyy")
   // @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "TransactionDate")
    private Date transactionDate;

    @Column(name = "Status")
    private String status;
    @Column(name = "Reason")
    private String reason;

    public DepositTransaction() {
    }

    public DepositTransaction(Long depotransactionId,String username, double transactionAmount, Date transactionDate, String status, String reason) {
       this.username=username;
        this.depotransactionId = depotransactionId;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.status = status;
        this.reason = reason;
    }

    public Long getDepotransactionId() {
        return depotransactionId;
    }

    public void setDepotransactionId(Long depotransactionId) {
        this.depotransactionId = depotransactionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
