package com.builder.exceptions;

import org.jetbrains.annotations.NonNls;

/**
 * @author marcinjaz
 */
public class BuilderException extends RuntimeException {
	public BuilderException(@NonNls String message) {
		super(message);
	}
}
