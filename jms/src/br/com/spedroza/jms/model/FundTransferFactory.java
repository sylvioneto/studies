package br.com.spedroza.jms.model;

import java.math.BigDecimal;

public class FundTransferFactory {

	public FundTransfer getMockFundTransfer() {
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setFromAcc("1111");
		fundTransfer.setToAcc("2222");
		fundTransfer.setAmount(BigDecimal.valueOf(1000.00));
		return fundTransfer;
	}
}
