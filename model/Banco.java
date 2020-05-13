package br.com.poo.sysfi.model;

import java.util.ArrayList;

/* Principal Banco */
public class Banco {

	private String NomeUnidade;
	private String Senha;
	
	/* Arraylists usadas posteriormente */
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ArrayList<Conta> contas = new ArrayList<Conta>();
	ArrayList<Banco> unidades = new ArrayList<Banco>();

	public void addCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void addConta(Conta conta) {
		contas.add(conta);
	}

	public void addUnidade(Banco unidade) {
		unidades.add(unidade);
	}

	/* Bloco referente apenas à unidades */
	public void setNomeUnidade(String nomeUnidade) {
		NomeUnidade = nomeUnidade;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getNomeUnidade() {
		return NomeUnidade;
	}

	public String getSenha() {
		return Senha;
	}
	
	
	
	/* Retorna objetos através do índice */
	public Banco getUnidade(int unidade) {
		return unidades.get(unidade);
	}

	public Cliente getCliente(int cliente) {
		return clientes.get(cliente);
	}

	public Conta getConta(int conta) {
		return contas.get(conta);
	}

	public Conta removeConta(int conta) {
		contas.remove(conta);
		return contas.get(conta);

	}

	/* Retorna array lists */
	
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public ArrayList<Banco> getUnidades() {
		return unidades;
	}

}
