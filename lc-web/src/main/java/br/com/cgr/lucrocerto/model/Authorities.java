package br.com.cgr.lucrocerto.model;

public enum Authorities {
	USER("USER"),
	ADMIN("ADMIN");

	private String id;

	Authorities(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
