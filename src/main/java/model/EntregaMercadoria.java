package model;

public class EntregaMercadoria {
	private Entrega entrega;
	private Mercadoria mercadoria;
	private int quantidade;

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public Mercadoria getMercadoria() {
		return mercadoria;
	}

	public void setMercadoria(Mercadoria mercadoria) {
		this.mercadoria = mercadoria;
	}

	public int getQuantidade() {
		return quantidade;	
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}