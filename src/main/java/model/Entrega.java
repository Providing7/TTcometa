package model;

public class Entrega {

	private int idEntrega;
	private Cliente remetente;
	private Cliente destinatario;
	private Endereco enderecoEntrega;
	private String status;

	private Mercadoria mercadoria;
	private int quantidade;

	public int getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(int idEntrega) {
		this.idEntrega = idEntrega;
	}

	public Cliente getRemetente() {
		return remetente;
	}

	public void setRemetente(Cliente remetente) {
		this.remetente = remetente;
	}

	public Cliente getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Cliente destinatario) {
		this.destinatario = destinatario;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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