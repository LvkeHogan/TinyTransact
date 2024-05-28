package com.lukehogan.tinytransact.model;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

@Component
public class TransactionLookupRequest {
	
	private OffsetDateTime beginTimestamp;
	private OffsetDateTime endTimestamp;
	private Integer accountNum;
	private Long cardNum;
	
	//getters
	public OffsetDateTime getBeginTimestamp() {
		return beginTimestamp;
	}
	public OffsetDateTime getEndTimestamp() {
		return endTimestamp;
	}
	public Integer getAccountNum() {
		return accountNum;
	}
	public Long getCardNum() {
		return cardNum;
	}
	public TransactionLookupRequest(OffsetDateTime beginDate, OffsetDateTime endDate, Integer accountNum,
			Long cardNum) {
		super();
		this.beginTimestamp = beginDate;
		this.endTimestamp = endDate;
		this.accountNum = accountNum;
		this.cardNum = cardNum;
	}
	
}
