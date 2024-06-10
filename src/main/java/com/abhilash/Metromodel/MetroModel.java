package com.abhilash.Metromodel;



public class MetroModel {
     
	private int id;
	private String fromstation;
	private String tostation;
	private double code;
	private double amount;
	
	
	public void setTostation(String tostation) {
		this.tostation = tostation;
	}
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
	
	public double getCode() {
		return code;
	}
	public void setCode(double code) {
		this.code = code;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public MetroModel(int id, String fromstation, String tostation, double code, double amount) {
		super();
		this.id = id;
		this.fromstation = fromstation;
		this.tostation = tostation;
		this.code = code;
		this.amount = amount;
	}
	public MetroModel() {
		super();
	}
	@Override
	public String toString() {
		return "MetroModel [fromstation=" + fromstation + ", tostation=" + tostation + ", code=" + code + ", amount="
				+ amount + "]";
	}
	
	
	
}
