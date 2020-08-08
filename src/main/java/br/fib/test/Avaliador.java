package br.fib.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao) {
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			} 
			if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
		}
		
		pegaOsMaiores(leilao);
	}
	
	private void pegaOsMaiores(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {
			public int compare(Lance l1, Lance l2) {
				if (l1.getValor() < l2.getValor()) {
					return 1;
				}
				
				if (l1.getValor() > l2.getValor()) {
					return -1;
				}
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3: maiores.size());
	}

	public List<Lance> getTresMaiores() {
		return maiores;
	}
	public double getMaiorLance() {
		return maiorDeTodos;
	}

	public double getMenorLance() {
		return menorDeTodos;
	}
}
