package org.casadocodigo.loja.model;

import java.math.BigDecimal;

public class PaymentData {

	private BigDecimal value;
	
	public PaymentData() {
	}

	public PaymentData(BigDecimal value) {
		this.value = value;
		// TODO Auto-generated constructor stub
	}
	
	public BigDecimal getValue() {
		return value;
	}

}
