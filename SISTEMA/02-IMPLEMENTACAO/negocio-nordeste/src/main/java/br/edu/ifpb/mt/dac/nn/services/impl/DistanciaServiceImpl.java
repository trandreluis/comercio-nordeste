package br.edu.ifpb.mt.dac.nn.services.impl;

import java.util.List;

import javax.inject.Inject;

import com.google.maps.model.DistanceMatrix;

import br.edu.ifpb.mt.dac.nn.dao.impl.AnuncioDaoImpl;
import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.googlewebservice.GoogleWebServicesResponse;
import br.edu.ifpb.mt.dac.nn.model.Anuncio;
import br.edu.ifpb.mt.dac.nn.model.Localizacao;
import br.edu.ifpb.mt.dac.nn.services.AnuncioService;

public class DistanciaServiceImpl {

	private static final long serialVersionUID = 1865483278732134577L;

	private GoogleWebServicesResponse googleWebServicesResponse = new GoogleWebServicesResponse();

	public List<Anuncio> buscaOtimizadaPelaDistancia(Localizacao localizacaoBuscador,
			List<Anuncio> anunciosNaoOtimizados) throws NegocioNordesteException {

		List<Anuncio> anunciosOtimizados = null;
		long menorDistacia = 0;

		if (anunciosNaoOtimizados != null) {
			String[] localizacaoAnuncios = new String[anunciosNaoOtimizados.size()];

			for (int i = 0; i < anunciosNaoOtimizados.size(); i++) {
				localizacaoAnuncios[i] = anunciosNaoOtimizados.get(i).getLocalizacao().getCidade() + ","
						+ anunciosNaoOtimizados.get(i).getLocalizacao().getEstado();
			}

			try {
				DistanceMatrix matrix = googleWebServicesResponse.consultaDistancia(
						localizacaoBuscador.getEstado() + "+" + localizacaoBuscador.getCidade(), localizacaoAnuncios);

				for (int i = 0; i <= matrix.destinationAddresses.length-1; i++) {
					System.out.println("Cidade:" + matrix.destinationAddresses[i] + "," + " Distancia: "
							+ matrix.rows[0].elements[i].distance.inMeters);

//					
//					if (menorDistacia > matrix.rows[0].elements[i].distance.inMeters || menorDistacia == 0) {
//						menorDistacia = matrix.rows[0].elements[i].distance.inMeters;
//						anunciosOtimizados = anunciosNaoOtimizados.get(i);
//					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return anunciosOtimizados;
	}

}
