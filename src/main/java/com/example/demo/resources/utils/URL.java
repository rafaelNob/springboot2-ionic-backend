package com.example.demo.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String nome) {

		try {
			return URLDecoder.decode(nome, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			return "";
		}

	}

	public static List<Integer> decodeIntList(String s) {
		// DOIS MODOS DE FAZER A MESMA COISA COM E SEM LAMBDA
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}

		return list;
		// return Arrays.asList(s.split(",")).stream().map(x ->
		// Integer.parseInt(x)).collect(Collectors.toList());
	}
}
