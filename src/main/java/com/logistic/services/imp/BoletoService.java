package com.logistic.services.imp;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.logistic.domain.PagamentoComBoleto;
import com.logistic.services.IBoletoService;

@Service
public class BoletoService implements IBoletoService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		logger.debug("Simulado preenchimento de boleto");
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
