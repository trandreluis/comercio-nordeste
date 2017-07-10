package br.edu.ifpb.mt.dac.nn.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.service.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.service.impl.AnuncianteServiceImpl;

public class DaoTest {
	
	public static void main(String[] args) {
		
		AnuncianteService service = new AnuncianteServiceImpl();
		
		Anunciante anunciante = new Anunciante();
		
		anunciante.setEmail("email@mail.com");
		anunciante.setNome("andre");
		anunciante.setSobrenome("luis");
		anunciante.setDataNascimento(new Date());
		anunciante.setNomeUsuario("trandreluis");
		anunciante.setSenha("123");
		
		service.salvar(anunciante);
		
		ArrayList<Anunciante> a = (ArrayList<Anunciante>) service.buscarTodos();
		
		for (Anunciante anunciante2 : a) {
			System.out.println(anunciante2);
		}
		
	}
	
}