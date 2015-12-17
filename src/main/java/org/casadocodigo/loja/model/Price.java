package org.casadocodigo.loja.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price {

	@Column(scale = 2)
	private BigDecimal value;
	private BookType bookType;
	
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal valor) {
		this.value = valor;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType tipoLivro) {
		this.bookType = tipoLivro;
	}

	@Override
	public String toString() {
		return "Price [value=" + value + ", bookType=" + bookType + "]";
	}
	
	

}
