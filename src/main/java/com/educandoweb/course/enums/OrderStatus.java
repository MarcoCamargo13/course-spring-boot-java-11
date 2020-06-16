package com.educandoweb.course.enums;

public enum OrderStatus {
	// Deixando as deckarações enums como WAITING_PAYMENT,PAID,etc, fica com valores
	// fixos 0 e 1
	// se houver uma manutençãoe trocar de poção os valores tb trocam
	WAITING_PAYMENT(0), PAID(1), SHIPPED(2), DELIVERED(3), CANCELED(4);

	private int code;

	private OrderStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OrderStatus valueOf(int code) {//aqui ele passa todos os valore para retorna o correto 
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Enum");
	}

}
