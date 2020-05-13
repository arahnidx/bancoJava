package br.com.poo.sysfi.model;

/* Classe que define se a conta é corrente */
public class ContaC extends Conta {

	private final String TipoConta = "Conta corrente";

	public ContaC(String CPF, String senha) {//Construtor de conta corrente
		super();
		this.cpf = CPF;
		this.senha = senha;
	}

	public String getTipoConta() {
		return TipoConta;
	}

}
