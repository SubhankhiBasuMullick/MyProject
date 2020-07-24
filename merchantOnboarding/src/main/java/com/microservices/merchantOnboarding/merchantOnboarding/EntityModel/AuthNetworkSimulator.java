package com.microservices.merchantOnboarding.merchantOnboarding.EntityModel;

import javax.persistence.*;

@Entity
@Table(name = "AuthNetworkSimulator")
public class AuthNetworkSimulator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authNetworkSimulatorId;
    @Column(name="AuthTransactionId")
    private long authTransactionId;

    @Column(name="Status")
    private String status;
    @Column(name="Reason")
    private String reason;

    public AuthNetworkSimulator() {
    }

    public AuthNetworkSimulator(Long authNetworkSimulatorId,long authTransactionId, String status, String reason) {
        this.authNetworkSimulatorId = authNetworkSimulatorId;
        this.authTransactionId=authTransactionId;
        this.status = status;
        this.reason = reason;
    }

    public Long getAuthNetworkSimulatorId() {
        return authNetworkSimulatorId;
    }

    public void setAuthNetworkSimulatorId(Long authNetworkSimulatorId) {
        this.authNetworkSimulatorId = authNetworkSimulatorId;
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

    public long getAuthTransactionId() {
        return authTransactionId;
    }

    public void setAuthTransactionId(long authTransactionId) {
        this.authTransactionId = authTransactionId;
    }
}
