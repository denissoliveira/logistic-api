package com.logistic.services.imp;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.logistic.domain.PagamentoComBoleto;
import com.logistic.services.IBoletoService;

@Service
public class BoletoService implements IBoletoService {

	@Override
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
