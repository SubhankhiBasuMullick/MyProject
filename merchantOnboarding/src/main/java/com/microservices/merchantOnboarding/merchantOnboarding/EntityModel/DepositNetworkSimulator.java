package com.microservices.merchantOnboarding.merchantOnboarding.EntityModel;

import javax.persistence.*;



    @Entity
    @Table(name = "DepositNetworkSimulator")
    public class DepositNetworkSimulator {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long depoNetworkSimulatorId;
        @Column(name="DepoTransactionId")
        private long depositTransactionId;

        @Column(name="Status")
        private String status;
        @Column(name="Reason")
        private String reason;

        public DepositNetworkSimulator() {

        }

        public DepositNetworkSimulator(Long depoNetworkSimulatorId, long depositTransactionId, String status, String reason) {
            this.depoNetworkSimulatorId = depoNetworkSimulatorId;
            this.depositTransactionId = depositTransactionId;
            this.status = status;
            this.reason = reason;
        }

        public Long getDepoNetworkSimulatorId() {
            return depoNetworkSimulatorId;
        }

        public void setDepoNetworkSimulatorId(Long depoNetworkSimulatorId) {
            this.depoNetworkSimulatorId = depoNetworkSimulatorId;
        }

        public long getDepositTransactionId() {
            return depositTransactionId;
        }

        public void setDepositTransactionId(long depositTransactionId) {
            this.depositTransactionId = depositTransactionId;
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
    }
