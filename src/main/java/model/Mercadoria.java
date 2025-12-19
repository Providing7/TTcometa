package model;

public class Mercadoria {
	private String nome;
	private double peso;
	private double valor;
	private double volume;
	private int idMercadoria;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public int getIdMercadoria() {
		return idMercadoria;
	}

	public void setIdMercadoria(int idMercadoria) {
		this.idMercadoria = idMercadoria;
	}

}
