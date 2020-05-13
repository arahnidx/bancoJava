package br.com.poo.sysfi.model;

/* Unidades são criadas aqui, antes disso é só o banco que é criado apenas uma vez durante a abertura do programa */
public class Unidade extends Banco {

	public static final String NOMEUNIDADE = "unidade_nome";
	public static final String SENHA = "unidade_senha";

	private String NomeUnidade;
	private String Senha;

	public Unidade() {
	}

	public String getNomeUnidade() {
		return NomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		NomeUnidade = nomeUnidade;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

}
