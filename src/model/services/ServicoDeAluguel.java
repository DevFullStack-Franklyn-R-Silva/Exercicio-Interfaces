	package model.services;

import model.entities.AluguelDeCarros;
import model.entities.Fatura;

public class ServicoDeAluguel {
	private Double precoPorDia;
	private Double precoPorHora;

	private ServicoFiscal servicoFiscal;

	public ServicoDeAluguel() {
	}

	public ServicoDeAluguel(Double precoPorDia, Double precoPorHora, ServicoFiscal servicoFiscal) {
		super();
		this.precoPorDia = precoPorDia;
		this.precoPorHora = precoPorHora;
		this.servicoFiscal = servicoFiscal;
	}

	public Double getPrecoPorDia() {
		return precoPorDia;
	}

	public void setPrecoPorDia(Double precoPorDia) {
		this.precoPorDia = precoPorDia;
	}

	public Double getPrecoPorHora() {
		return precoPorHora;
	}

	public void setPrecoPorHora(Double precoPorHora) {
		this.precoPorHora = precoPorHora;
	}

	public ServicoFiscal getServicoFiscal() {
		return servicoFiscal;
	}

	public void setServicoFiscal(ServicoFiscal servicoFiscal) {
		this.servicoFiscal = servicoFiscal;
	}

	public void processarFatura(AluguelDeCarros aluguelDeCarros) {
		long t1 = aluguelDeCarros.getInicio().getTime();
		long t2 = aluguelDeCarros.getFim().getTime();
		double horas = (double) (t2 - t1) / 1000 / 60 / 60;

		double pagamentoBasico;
		if (horas <= 12.0) {
			pagamentoBasico = Math.ceil(horas) * precoPorHora;
		} else {
			pagamentoBasico = Math.ceil(horas / 24) * precoPorDia;
		}

		double imposto = servicoFiscal.imposto(pagamentoBasico);

		aluguelDeCarros.setFatura(new Fatura(pagamentoBasico, imposto));
	}

}
