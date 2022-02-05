package br.com.poo.sysfi.view;

import br.com.poo.sysfi.model.Banco;

public class AppMain {

	public static void main(String[] args) {
		String admin = "admin", senhaMestre = "123"; /* Login de admin, para caso n�o haja nenhuma unidade criada e para controle total */
		Banco Nubank = new Banco(); //Banco padr�o que tem como herdeiros as unidades
		new Login(admin, senhaMestre, Nubank);  //abrir login

	}

}
