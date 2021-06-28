package com.unipampa.crud.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum DocumentType {

	RENT('R'), SPEND('S');

	private Character key;

	private DocumentType(Character key) {
		this.key = key;
	}
}
