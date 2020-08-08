package br.fib.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario maria;
	private Usuario jose;
	private Leilao leilao;
	
	@Before
	public void iniciandoValoresComuns() { 
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("joão");
		this.maria = new Usuario("maria");
		this.jose = new Usuario("josé");
	}
	@Test
	public void deveRetornarOMaiorLanceEOMenorLance() {
		
		Leilao leilao = new CriadorLeiloes().para("Computador Quantico")
				.lance(joao, 250.0)
				.lance(jose, 300.0)
			    .lance(maria, 400.0)
			    .constroi();
	
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(400,leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(250,leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCresceteComOutrosValores() {
		Leilao leilao = new CriadorLeiloes().para("Playstation 3 novo")
				.lance(joao, 1000.0)
				.lance(jose, 2000.0)
			    .lance(maria, 3000.0)
			    .constroi();
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(3000,leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000,leiloeiro.getMenorLance(), 0.00001);		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecresceteComOutrosValores() {
		Leilao leilao = new CriadorLeiloes().para("Playstation 3 novo")
				.lance(joao, 3000.0)
				.lance(jose, 2000.0)
			    .lance(maria, 1000.0)
			    .constroi();
		
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(3000,leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000,leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLancesSemOrdemComOutrosValores() {
		Leilao leilao = new CriadorLeiloes().para("Playstation 3 novo")
				.lance(joao, 2000.0)
				.lance(jose, 1000.0)
			    .lance(maria, 3000.0)
			    .constroi();
			
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(3000,leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000,leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLanceComApenasUmValor() {
		Leilao leilao = new CriadorLeiloes().para("Playstation 3 novo")
				.lance(joao, 2000.0)
			    .constroi();
		
		leilao.propoe(new Lance(joao, 2000.0));
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(2000,leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(2000,leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorLeiloes().para("Playstation 3 novo")
				.lance(joao, 200.0)
				.lance(maria, 300.0)
			    .lance(joao, 400.0)
			    .lance(maria, 500.00)
			    .constroi();
		
		leiloeiro.avalia(leilao);
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		Assert.assertEquals(3, maiores.size());
		Assert.assertEquals(500, maiores.get(0).getValor(), 0.00001);
		Assert.assertEquals(400, maiores.get(1).getValor(), 0.00001);
		Assert.assertEquals(300, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new CriadorLeiloes().para("Playstation 3 novo")
				.lance(joao, 2000.0)
				.lance(joao, 3000.0)
			    .constroi();
		
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQueCincoLancesDoMesmoUsuario() {
		
		Leilao leilao = new CriadorLeiloes().para("Playstation 3 novo")
				.lance(joao, 2000.0)
				.lance(maria, 3000.0)
			    .lance(joao, 4000.0)
			    .lance(maria, 5000.0)
			    .lance(joao, 6000.0)
			    .lance(maria, 7000.0)
			    .lance(joao, 8000.0)
			    .lance(maria, 9000.0)
			    .lance(joao, 10000.0)
			    .lance(maria, 11000.0)
			    .lance(joao, 12000.0)
			    .constroi();
		
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		
		Assert.assertEquals(11000, ultimoLance.getValor(), 0.00001);
	}
}
