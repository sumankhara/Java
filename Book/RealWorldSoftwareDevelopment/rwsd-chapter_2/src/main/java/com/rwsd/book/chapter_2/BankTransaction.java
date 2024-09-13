package com.rwsd.book.chapter_2;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {

	private final LocalDate date;
	private final double amount;
	private final String description;
	
	public BankTransaction(LocalDate date, double amount, String description) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "BankTransaction [date=" + date + ", amount=" + amount + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, date, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankTransaction other = (BankTransaction) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(date, other.date) && Objects.equals(description, other.description);
	}
	
}
