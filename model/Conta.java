package br.com.poo.sysfi.model;

import javax.swing.JOptionPane;

public class Conta {
	public static final String SENHA = "conta_senha";
	public static final String CPF = "conta_cpf";
	public static final String SALDO = "conta_saldo";
	public static final String CLIENTE = "conta_cliente";
	public static final String NUMEROCONTA = "conta_numero";
	public static final String TIPOCONTA = "conta_tipo";
	public static final String CLIENTENOME = "conta_cliente_nome";

	protected int numeroConta;
	private static int numeroDeContas = 1000000;
	protected String cpf;
	protected String senha = "123";
	protected double saldo = 0;
	protected String saldoString;
	private String TipoConta;
	protected Cliente cliente; /* Não usado a partir da implementação do MongoDB*/
	private String clienteNome;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public Conta() {
		this.numeroConta = numeroDeContas++;

	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	/* Faz o tratamento do numero da conta recebido da DB */
	public void setNumeroContaString(String s) {
		this.numeroConta = Integer.parseInt(s);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double d) {
		this.saldo = d;
	}
	
	
	public void setSaldoString(String d) { //Transforma saldo recebido das JText em double
		this.saldo = Double.parseDouble(d);
	}

	public String getTipoConta() {
		return TipoConta;
	}

	
	public void setTipoConta(String tipoConta) {
		TipoConta = tipoConta;
	}

	public void deposito(double deposito) {
		if (deposito <= 0) { //rejeita valores negativos
			JOptionPane.showMessageDialog(null, "Eh preciso inserir um numero em numero de valor");
			return;
		}
		this.saldo += deposito;

	}

	public void saque(double saque) {
		if (saldo <= 0 || saque > saldo) { // rejeita saques quando não tem saldo suficiente
			JOptionPane.showMessageDialog(null, "Dinheiro insuficiente na conta para saque");
			return;
		} else {
			this.saldo -= saque;

		}
	}

}
