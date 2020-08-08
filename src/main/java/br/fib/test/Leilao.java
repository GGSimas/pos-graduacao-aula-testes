package br.fib.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {
    private String descricao;
    private List<Lance> lances;

    public Leilao(String descricao) {
   	 this.descricao = descricao;
   	 this.lances = new ArrayList<Lance>();
    }

    public void propoe(Lance lance) {
    	if (lances.isEmpty() || PodeDarLance(lance.getUsuario()))
    	{
    		lances.add(lance);
    	}
    }	

    public String getDescricao() {
   	 	return descricao;
    }

    public List<Lance> getLances() {
   	 	return Collections.unmodifiableList(lances);
    }
    
    private Lance ultimoLanceDado() {
		return lances.get(lances.size() -1);
	}
    
    private boolean PodeDarLance(Usuario usuario) {
    	return !ultimoLanceDado().getUsuario().equals(usuario) &&
    			quantidadeLancesDados(usuario) < 5;
    }
    
    private int quantidadeLancesDados(Usuario usuario) {
    	int total = 0;
    	for (Lance lance: lances) {
    		if (lance.getUsuario().equals(usuario)) total ++;
    	}
    	
    	return total;
    }
}
