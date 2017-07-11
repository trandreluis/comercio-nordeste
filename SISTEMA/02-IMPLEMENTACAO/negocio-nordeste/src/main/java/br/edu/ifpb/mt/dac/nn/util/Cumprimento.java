package br.edu.ifpb.mt.dac.nn.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Cumprimento {

	private String cumprimento;
	
	@PostConstruct
	public void mensagem() {
		
		LocalDateTime dataHora = LocalDateTime.now(ZoneId.of("Brazil/East"));
		
		int horas = dataHora.getHour();

		if (horas >= 18 && horas < 24) {
			this.cumprimento = "BOA NOITE";
		}
		else if (horas >= 12 && horas < 18) {
			this.cumprimento = "BOA TARDE";
		}
		else {
			this.cumprimento = "BOM DIA";
		}
	}
	
	public String getCumprimento() {
		return this.cumprimento;
	}
	
}