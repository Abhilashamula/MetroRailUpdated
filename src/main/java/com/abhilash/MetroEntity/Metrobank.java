package com.abhilash.MetroEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Metrobank {
@Id
private int id;
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

private double amount;

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

@Override
public String toString() {
	return "Metrobank [id=" + id + ", amount=" + amount + "]";
}

public Metrobank(int id, double amount) {
	super();
	this.id = id;
	this.amount = amount;
}

public Metrobank() {
	super();
}



}
