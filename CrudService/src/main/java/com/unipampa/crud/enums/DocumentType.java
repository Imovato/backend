package com.unipampa.crud.enums;

public enum DocumentType {
	
	RENT('R'),
	SPEND('S');
	
	private Character key;

	private DocumentType(Character key) {
		this.key = key;
	}

	public Character getKey() {
		return key;
	}
}
