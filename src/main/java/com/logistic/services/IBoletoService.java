package com.logistic.services;

import java.util.Date;

import com.logistic.domain.PagamentoComBoleto;

public interface IBoletoService {
	
	void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido);

}
