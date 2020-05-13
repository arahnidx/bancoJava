package br.com.poo.sysfi.model;

import java.io.Serializable;

public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
