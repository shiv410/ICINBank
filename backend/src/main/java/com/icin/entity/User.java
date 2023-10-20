package com.icin.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String fname;
	private String lname;
	private long phone;
	private String address;
	private String email;
	private String username;
	private String password;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dob;
	
	private String identityType;
	
	private String identity;

	@Column(columnDefinition = "boolean default false")
	private boolean status;
	
	@Column(columnDefinition = "boolean default false")
	private boolean authorizationStatus;
	
	@Column(columnDefinition = "integer default 3", nullable = false)
	private int featureStatus = 3;

	@OneToOne(targetEntity = Account.class, mappedBy = "user", orphanRemoval = false, fetch = FetchType.LAZY)
	private Account account;

	@OneToOne(targetEntity = Saccount.class, mappedBy = "user", orphanRemoval = false, fetch = FetchType.LAZY)
	private Saccount sAccount;

	public User(int id, String fname, String lname, long phone, String address, String email, String username,
			String password, Date dob, String identityType, String identity, boolean status,
			boolean authorizationStatus, int featureStatus, Account account, Saccount sAccount) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.identityType = identityType;
		this.identity = identity;
		this.status = status;
		this.authorizationStatus = authorizationStatus;
		this.featureStatus = featureStatus;
		this.account = account;
		this.sAccount = sAccount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isAuthorizationStatus() {
		return authorizationStatus;
	}

	public void setAuthorizationStatus(boolean authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}

	public int getFeatureStatus() {
		return featureStatus;
	}

	public void setFeatureStatus(int featureStatus) {
		this.featureStatus = featureStatus;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Saccount getsAccount() {
		return sAccount;
	}

	public void setsAccount(Saccount sAccount) {
		this.sAccount = sAccount;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
