package com.microservices.merchantOnboarding.merchantOnboarding.EntityModel;




import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "AuthTransaction")
public class AuthTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

	@Column(name="Username")
    private String username;

	@Column(name="Password")
    private String password;
	@Column(name="TransactionAmount")
	private double transactionAmount;
	@Column(name="TransactionDate")
	private Date transactionDate;

	@Column(name="Status")
    private String status;
	@Column(name="Reason")
    private String reason;


    public AuthTransaction() {
    }

	public AuthTransaction(Long transactionId,String username,String password, double transactionAmount,Date transactionDate,
						    String status,String reason) {
		super();
		this.transactionId = transactionId;
		this.username=username;
		this.password=password;
		this.transactionAmount=transactionAmount;
		this.transactionDate=transactionDate;
		this.status = status;
		this.reason=reason;


	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
}