package br.edu.ifpb.mt.dac.nn.util.webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleMapsUtils {

	// URL para consulta
	private static final String URL_MAPS_CONSULTA = "https://maps.googleapis.com/maps/api/distancematrix/";

	// Tipo de retorno
	private static final String TIPO_RETORNO = "json?";

	// Parâmetros
	private static String paramteros = "origins=Sertânia+Pernambuco&destinations=Monteiro+Paraíba&language=pt-BR&key=";

	// Key para serviço Google Matrix Distance
	private static final String KEY_MATRIX_DISTANCE = "AIzaSyCpNv0NWReekBltiw80xAtXeQ5o9o0B7DM";

	private static final String consultaSertaniaMonteiro = URL_MAPS_CONSULTA + TIPO_RETORNO + paramteros
			+ KEY_MATRIX_DISTANCE;

	public static void main(String[] args) {

		Gson gson = new Gson();

		try {
			System.out.println(readUrl(consultaSertaniaMonteiro).getAsJsonArray("rows").getAsJsonArray().get(0)
					.getAsJsonObject().getAsJsonArray("elements").get(0).getAsJsonObject().getAsJsonObject("distance")
					.get("text"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static JsonObject readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));

			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(reader).getAsJsonObject();

			return obj;

			// StringBuffer buffer = new StringBuffer();
			// int read;
			// char[] chars = new char[1024];
			// while ((read = reader.read(chars)) != -1)
			// buffer.append(chars, 0, read);
			//
			// return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}

	}

}