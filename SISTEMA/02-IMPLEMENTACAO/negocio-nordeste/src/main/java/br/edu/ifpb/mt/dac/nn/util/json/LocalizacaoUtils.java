package br.edu.ifpb.mt.dac.nn.util.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.edu.ifpb.mt.dac.nn.model.Cidade;
import br.edu.ifpb.mt.dac.nn.model.Estado;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

public class LocalizacaoUtils implements Serializable {

	private static final long serialVersionUID = 4309798536454874L;

	public static List<Cidade> buscarCidadePorCodigoDoEstado(String codigo) {

		Gson gson = new Gson();
		BufferedReader readerCidades = null;

		try {
			readerCidades = new BufferedReader(new FileReader("json/cidades-brasil.json"));
		} catch (Exception e) {
			MessageUtils.messageError("Erro ao buscar cidades (JSON)");
		}

		JsonParser parser = new JsonParser();
		JsonArray obj = parser.parse(readerCidades).getAsJsonArray();
		List<Cidade> cidadesDoEstado = new ArrayList<>();
		for (JsonElement jsonElement : obj) {
			Cidade cidade = gson.fromJson(jsonElement, Cidade.class);
			if (cidade.getEstado().equals(codigo)) {
				cidadesDoEstado.add(cidade);
			}
		}
		return cidadesDoEstado;
	}

	public static void main(String[] args) {

		LocalizacaoUtils e = new LocalizacaoUtils();
		System.out.println(e.buscarEstadosDoNordeste());

	}

	public List<Estado> buscarEstadosDoNordeste() {

		Gson gson = new Gson();
		BufferedReader readerEstados = null;

		try {
			URL s = getClass().getResource("/json/estados-nordeste.json");
			readerEstados = new BufferedReader(new FileReader(new File(s.toURI())));
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.messageError("Erro ao buscar estados (JSON)");
		}

		JsonParser parser = new JsonParser();
		JsonArray obj = parser.parse(readerEstados).getAsJsonArray();
		List<Estado> estados = new ArrayList<>();
		for (JsonElement jsonElement : obj) {
			Estado estado = gson.fromJson(jsonElement, Estado.class);
			estados.add(estado);
		}
		return estados;
	}

}