package br.com.poo.sysfi.model;

/* Classe que define se a conta � poupan�a */
public class ContaP extends Conta {

	private final String TipoConta = "Conta poupanca";

	public ContaP(String CPF, String senha) {//Construtor de conta poupan�a
		super();
		this.cpf = CPF;
		this.senha = senha;
	}

	public String getTipoConta() {
		return TipoConta;
	}

}
