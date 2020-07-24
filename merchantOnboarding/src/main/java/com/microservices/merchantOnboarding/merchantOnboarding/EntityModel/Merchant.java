package com.microservices.merchantOnboarding.merchantOnboarding.EntityModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name= "Merchant" )
public class Merchant {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long merchantId;
	@NotEmpty(message = "Name is mandatory")
   @Column(name="Name")
    private String name;
	@NotEmpty(message = "Username is mandatory")
   @Column(name="UserName")
    private String username;
	@NotEmpty(message = "Password is mandatory")
   @Column(name="Password")
    private String password;
	@NotEmpty(message = "Address is mandatory")
   @Column(name="Address")
    private String address;
	@NotEmpty(message = "State is mandatory")
   @Column(name="State")
    private String state;
	@NotEmpty(message = "Country is mandatory")
   @Column(name="Country")
    private String country;
	@NotEmpty(message = "Contact number is mandatory")
	@Pattern(regexp="(^$|[0-9]{10})",message="Mobile number is invalid")
//	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
//			message="Mobile number is invalid")
   @Column(name="ContactNo")
    private String contactNo;
   @Column(name="Role")
   private String roles;

    public Merchant()
    {

    }

    public Merchant(Long merchantId, String name, String username, String password, String address, String state, String country, String contactNo,String roles ) {
        this.merchantId = merchantId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.state = state;
        this.country = country;
        this.contactNo = contactNo;
        this.roles=roles;
    }

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


}
