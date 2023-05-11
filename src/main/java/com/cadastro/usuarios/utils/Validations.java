package com.cadastro.usuarios.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

	public static boolean isValidEmail(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}

	public static boolean isValidNome(String nome) {
		boolean isValidNome = false;
		if (nome != null && nome.length() > 0) {
			String expression = "[a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(nome);
			if (matcher.matches()) {
				isValidNome = true;
			}
		}
		return isValidNome;
	}

	public static boolean isValidEndereco(String endereco) {
		boolean isValidEndereco = false;
		if (endereco != null && endereco.length() > 0) {
			String expression = "[a-zA-Z0-9,\\.\\-:áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(endereco);
			if (matcher.matches()) {
				isValidEndereco = true;
			}
		}
		return isValidEndereco;
	}

	public static boolean isValidNascimento(LocalDate nascimento) {

		LocalDate hoje = LocalDate.now();
		long anos = nascimento.until(hoje, ChronoUnit.YEARS);
		boolean isValidEndereco = false;
		if (anos >= 18) {
			isValidEndereco = true;
		}
		return isValidEndereco;
	}

}
