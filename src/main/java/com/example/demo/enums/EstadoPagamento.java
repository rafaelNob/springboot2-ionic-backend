package com.example.demo.enums;

public enum EstadoPagamento {
	
	PENDENTE(1,"Pendente"),QUITADO(2,"Pedido Quitado"),CANCELADO(3,"Cancelado");
	
	private int id;
	private String descricao;
	
	EstadoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento getEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException();
	}

}
