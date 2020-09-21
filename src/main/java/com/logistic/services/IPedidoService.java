package com.logistic.services;

import com.logistic.domain.Pedido;
import com.logistic.services.generic.IFindService;
import com.logistic.services.generic.IInsertService;

public interface IPedidoService extends IFindService<Pedido>, IInsertService<Pedido> {

}
