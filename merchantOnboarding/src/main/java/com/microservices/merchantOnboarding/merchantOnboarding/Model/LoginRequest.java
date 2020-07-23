package com.microservices.merchantOnboarding.merchantOnboarding.Model;

import java.io.Serializable;

public class LoginRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;

  private String username;
    private String password;
    private String status;
    private String reason;


    public LoginRequest() {
        super();
    }

    public LoginRequest(String username, String password,String status,String reason) {
        this.setUsername(username);
        this.setPassword(password);
        this.setStatus(status);
        this.setReason(reason);

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
