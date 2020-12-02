package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelDeCarros;
import model.entities.Veiculo;
import model.services.ServicoDeAluguel;
import model.services.ServicoFiscalBrasil;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		System.out.println("Insira os dados de aluguel");
		System.out.println("Modelo do carro: ");
		String modelo = sc.nextLine();
		System.out.println("Retirada (dd/MM/aaaa hh:mm): ");
		Date inicio = sdf.parse(sc.nextLine());
		System.out.println("Retorno (dd/MM/aaaa hh:mm): ");
		Date fim = sdf.parse(sc.nextLine());

		AluguelDeCarros ac = new AluguelDeCarros(inicio, fim, new Veiculo(modelo));

		System.out.println("Insira o preço por hora: ");
		double precoPorHora = sc.nextDouble();
		System.out.println("Insira o preço por dia: ");
		double precoPorDia = sc.nextDouble();

		ServicoDeAluguel servicoDeAluguel = new ServicoDeAluguel(precoPorDia, precoPorHora, new ServicoFiscalBrasil());

		servicoDeAluguel.processarFatura(ac);

		System.out.println("FATURA: ");
		System.out.println("Pagamento básico: " + String.format("%.2f", ac.getFatura().getPagamentoBasico()));
		System.out.println("Imposto: " + String.format("%.2f", ac.getFatura().getImposto()));
		System.out.println("Pagamento total: " + String.format("%.2f", ac.getFatura().getPagamentoTotal()));

		sc.close();

	}

}
