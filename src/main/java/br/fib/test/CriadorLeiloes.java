package br.fib.test;

public class CriadorLeiloes {
	private Leilao leilao;
	
	public CriadorLeiloes() {}
	
	public CriadorLeiloes para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;
	}
	
	public CriadorLeiloes lance(Usuario usuario, Double lance) {
		leilao.propoe(new Lance(usuario, lance));
		return this;
	}	
	
	public Leilao constroi() {
		return leilao;
	}
}
