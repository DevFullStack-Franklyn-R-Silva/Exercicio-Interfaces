package model.services;

public class ServicoFiscalBrasil implements ServicoFiscal{

	public double imposto(double montante) {
		if (montante <= 10.0) {
			return montante * 0.2;
		} else {
			return montante * 0.15;
		}
	}
}
