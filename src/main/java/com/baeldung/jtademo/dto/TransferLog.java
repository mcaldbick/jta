package com.baeldung.jtademo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;


@Entity
@Table(name="account")
public class TransferLog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	

	@Column(name="from_account")
    private String fromAccountId;
	@Column(name="to_account")
    private String toAccountId;
	@Column(name="ammount")
    private BigDecimal amount;
	@OrderBy(clause="FROM_ACCOUNT DESC")
	List <TransferLog> transferLogList =  new ArrayList();

    

	public TransferLog(String fromAccountId, String toAccountId, BigDecimal amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }
    public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	
    public String getToAccountId() {
        return toAccountId;
    }
    public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmmount(BigDecimal amount) {
    	this.amount= amount;
    }
    public List<TransferLog> getTransferLogList() {
		return transferLogList;
	}

	public void setTransferLogList(List<TransferLog> transferLogList) {
		this.transferLogList = transferLogList;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
