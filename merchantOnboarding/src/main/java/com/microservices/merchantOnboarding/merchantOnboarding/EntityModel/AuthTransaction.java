package com.microservices.merchantOnboarding.merchantOnboarding.EntityModel;




import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

	@Column(name="Status")
    private String status;
	@Column(name="Reason")
    private String reason;


    public AuthTransaction() {
    }

	public AuthTransaction(Long transactionId,String username,String password,
						    String status,String reason) {
		super();
		this.transactionId = transactionId;
		this.username=username;
		this.password=password;
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
}