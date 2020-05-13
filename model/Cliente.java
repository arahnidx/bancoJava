package br.com.poo.sysfi.model;

public class Cliente extends Entity {
	private static final long serialVersionUID = 1L;
	public static final String NOME = "cliente_nome";
	public static final String CPF = "cliente_cpf";
	public static final String ENDERECO = "cliente_endereco";
	public static final String NUMEROCONTA = "numero_conta";

	private String nome;
	private String cpf;
	private String endereco;
	private String NumeroConta;
	private Conta conta; /* Não usado após implementação do banco de dados, o código ainda precisa ser refinado */

	public Cliente() {
		this.nome = NOME;
		this.cpf = CPF;
		this.endereco = ENDERECO;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(String NumeroConta) {
		this.NumeroConta = NumeroConta;
	}

	public void setContaNumber(Conta conta) {
		NumeroConta = Integer.toString(conta.getNumeroConta()); //Transforma o numero de conta em uma String
	}

	public String getContaNumber() {
		return NumeroConta;
	}

	/* Trata dados recebidos da DB, pois são retornados em String e as vezes é preciso usar como int */
	public void setNumeroConta(String NumeroConta) {
		this.NumeroConta = NumeroConta;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String Cpf) {
		this.cpf = Cpf;
	}

}
