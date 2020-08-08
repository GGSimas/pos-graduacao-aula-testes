package br.fib.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class AvaliadorTest {

	@Test
	public void deveRetornarOMaiorLanceEOMenorLance() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Novo Playstation 3");
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloero = new Avaliador();
		leiloero.avalia(leilao);
		
		Assert.assertEquals(400,leiloero.getMaiorLance(), 0.00001);
		Assert.assertEquals(250,leiloero.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCresceteComOutrosValores() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Novo Playstation 3");
		leilao.propoe(new Lance(joao, 1000.0));
		leilao.propoe(new Lance(jose, 2000.0));
		leilao.propoe(new Lance(maria, 3000.0));
		
		Avaliador leiloero = new Avaliador();
		leiloero.avalia(leilao);
		
		Assert.assertEquals(3000,leiloero.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000,leiloero.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecresceteComOutrosValores() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Novo Playstation 3");
		leilao.propoe(new Lance(joao, 3000.0));
		leilao.propoe(new Lance(jose, 2000.0));
		leilao.propoe(new Lance(maria, 1000.0));
		
		Avaliador leiloero = new Avaliador();
		leiloero.avalia(leilao);
		
		Assert.assertEquals(3000,leiloero.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000,leiloero.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLancesSemOrdemComOutrosValores() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Novo Playstation 3");
		leilao.propoe(new Lance(joao, 2000.0));
		leilao.propoe(new Lance(jose, 3000.0));
		leilao.propoe(new Lance(maria, 1000.0));
		
		Avaliador leiloero = new Avaliador();
		leiloero.avalia(leilao);
		
		Assert.assertEquals(3000,leiloero.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000,leiloero.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLanceComApenasUmValor() {
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("Novo Playstation 3");
		leilao.propoe(new Lance(joao, 2000.0));
		
		Avaliador leiloero = new Avaliador();
		leiloero.avalia(leilao);
		
		Assert.assertEquals(2000,leiloero.getMaiorLance(), 0.00001);
		Assert.assertEquals(2000,leiloero.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Novo Playstation 3");
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 500.0));
		
		Avaliador leiloero = new Avaliador();
		leiloero.avalia(leilao);
		List<Lance> maiores = leiloero.getTresMaiores();
		
		Assert.assertEquals(3, maiores.size());
		
	}
}
