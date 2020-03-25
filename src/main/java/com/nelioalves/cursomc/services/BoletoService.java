package com.nelioalves.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pg, Date instante) {
		Calendar cal = Calendar.getInstance();
		/**
		 * acrecenta 7 dias
		 */
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pg.setDataVencimento(cal.getTime());

	}

}
