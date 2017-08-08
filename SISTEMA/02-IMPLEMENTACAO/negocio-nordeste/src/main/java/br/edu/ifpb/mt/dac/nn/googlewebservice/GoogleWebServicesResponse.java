package br.edu.ifpb.mt.dac.nn.googlewebservice;

import java.io.Serializable;
import org.joda.time.DateTime;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

public class GoogleWebServicesResponse implements Serializable {

	private static final long serialVersionUID = 1766L;

	private GeoApiContext apiContext;

	public GoogleWebServicesResponse() {
		apiContext =  new GeoApiContext().setApiKey("AIzaSyCpNv0NWReekBltiw80xAtXeQ5o9o0B7DM");
	}

	public DistanceMatrix consultaDistancia(String origem, String[] destinations) throws Exception {

		DistanceMatrix matrix = DistanceMatrixApi.newRequest(apiContext)
		        .origins(origem)
		        .destinations(destinations)
		        .mode(TravelMode.DRIVING)
		        .language("pt-BR")
		        .avoid(RouteRestriction.TOLLS)
		        .await();
		
		return matrix;
	
	}

}