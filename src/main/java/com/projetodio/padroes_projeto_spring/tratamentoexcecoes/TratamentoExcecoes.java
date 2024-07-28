package com.projetodio.padroes_projeto_spring.tratamentoexcecoes;

public class TratamentoExcecoes extends RuntimeException {
	public TratamentoExcecoes(String mensagem) {
		super(mensagem);
	}
	public TratamentoExcecoes(String mensagem, Object ... params) {
		super(String.format(mensagem, params));
	}
}