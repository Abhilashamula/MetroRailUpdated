package com.arun.MetroEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Metroentity {
	@Id
	@Column(name="passangerid")
	private int id;
	private String fromstation;
	private String tostation;
	private String code;
	private boolean boaredornot;
	private double amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromstation() {
		return fromstation;
	}
	public void setFromstation(String fromstation) {
		this.fromstation = fromstation;
	}
	public String getTostation() {
		return tostation;
	}
	public void setTostation(String tostation) {
		this.tostation = tostation;
	}
	public Metroentity(int id, String fromstation, String tostation, String code, double amount, boolean bd) {
		super();
		this.id = id;
		this.fromstation = fromstation;
		this.tostation = tostation;
		this.code = code;
		this.amount = amount;
		this.boaredornot=bd;
	}
	public boolean isBoaredornot() {
		return boaredornot;
	}
	public void setBoaredornot(boolean boaredornot) {
		this.boaredornot = boaredornot;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Metroentity() {
		super();
	}
	@Override
	public String toString() {
		return "MetroModel [fromstation=" + fromstation + ", tostation=" + tostation + ", code=" + code + ", amount="
				+ amount + boaredornot + "]";
	}
	





}
