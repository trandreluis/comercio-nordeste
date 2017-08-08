package br.edu.ifpb.mt.dac.nn.services.impl;

import java.io.Serializable;
import java.util.List;

import com.google.maps.model.DistanceMatrix;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.googlewebservice.GoogleWebServicesResponse;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Localizacao;

public class DistanciaServiceImpl implements Serializable {

	private static final long serialVersionUID = 186548342134577L;

	private GoogleWebServicesResponse googleWebServicesResponse = new GoogleWebServicesResponse();

	public List<Anuncio> buscaOtimizadaPelaDistancia(Localizacao localizacaoBuscador,
			List<Anuncio> anunciosNaoOtimizados) throws NegocioNordesteException {
		List<Anuncio> anunciosOtimizados = anunciosNaoOtimizados;
		if (anunciosNaoOtimizados != null) {
			String[] localizacaoAnuncios = new String[anunciosNaoOtimizados.size()];

			for (int i = 0; i < anunciosNaoOtimizados.size(); i++) {
				localizacaoAnuncios[i] = anunciosNaoOtimizados.get(i).getLocalizacao().getCidade() + ","
						+ anunciosNaoOtimizados.get(i).getLocalizacao().getEstado();
			}
			try {
				DistanceMatrix matrix = googleWebServicesResponse.consultaDistancia(
						localizacaoBuscador.getEstado() + "+" + localizacaoBuscador.getCidade(), localizacaoAnuncios);

				long[] distancias = new long[matrix.destinationAddresses.length];

				for (int i = 0; i < matrix.destinationAddresses.length; i++) {
					distancias[i] = matrix.rows[0].elements[i].distance.inMeters;
				}

				for (int i = distancias.length - 1; i >= 1; i--) {
					for (int j = 0; j < i; j++) {
						long aux;
						Anuncio anuncio;
						if (distancias[j] > distancias[j + 1]) {
							aux = distancias[j];
							distancias[j] = distancias[j + 1];
							distancias[j + 1] = aux;

							anuncio = anunciosOtimizados.get(j + 1);
							anunciosOtimizados.set(j + 1, anunciosOtimizados.get(j));
							anunciosOtimizados.set(j, anuncio);
						}
					}
				}

			} catch (Exception e) {
				throw new NegocioNordesteException(
						"Erro ao conectar com webservice do Google. Verifique a conexão com a internet.");
			}
		}
		return anunciosOtimizados;
	}

}