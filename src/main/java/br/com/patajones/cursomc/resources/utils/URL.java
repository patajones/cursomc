package br.com.patajones.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	
	public static List<Integer> decodeIntList(String intList) {
		//os dois modos funcionam
		//return Pattern.compile(",").splitAsStream(intList).map(Integer::valueOf).collect(Collectors.toList());
		return Arrays.asList(intList.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
}
