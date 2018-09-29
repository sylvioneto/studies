package br.com.spedroza.jms.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class FundTransfer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fromAcc;
	private String toAcc;
	private BigDecimal amount;
	
	public String getFromAcc() {
		return fromAcc;
	}
	public void setFromAcc(String fromAcc) {
		this.fromAcc = fromAcc;
	}
	public String getToAcc() {
		return toAcc;
	}
	public void setToAcc(String toAcc) {
		this.toAcc = toAcc;
	}

	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((fromAcc == null) ? 0 : fromAcc.hashCode());
		result = prime * result + ((toAcc == null) ? 0 : toAcc.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "FundTransfer [fromAcc=" + fromAcc + ", toAcc=" + toAcc + ", amount=" + amount + "]";
	}
		
	
}
