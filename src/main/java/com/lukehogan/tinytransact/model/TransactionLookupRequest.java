package com.lukehogan.tinytransact.model;

import java.time.OffsetDateTime;

public class TransactionLookupRequest {
	
	private OffsetDateTime beginDate;
	private OffsetDateTime endDate;
	private Integer accountNum;
	private Integer cardNum;
	
	//getters
	public OffsetDateTime getBeginDate() {
		return beginDate;
	}
	public OffsetDateTime getEndDate() {
		return endDate;
	}
	public Integer getAccountNum() {
		return accountNum;
	}
	public Integer getCardNum() {
		return cardNum;
	}
	public TransactionLookupRequest(OffsetDateTime beginDate, OffsetDateTime endDate, Integer accountNum,
			Integer cardNum) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.accountNum = accountNum;
		this.cardNum = cardNum;
	}
	
	
	//Constructor to handle date range and account request
	

	

	
	
	
}
