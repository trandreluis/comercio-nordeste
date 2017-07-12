package br.edu.ifpb.mt.dac.nn.dao;

import java.util.ArrayList;
import java.util.Date;

import br.edu.ifpb.mt.dac.nn.exceptions.NegocioNordesteException;
import br.edu.ifpb.mt.dac.nn.model.Anunciante;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.impl.AnuncianteServiceImpl;

public class DaoTest {

	public static void main(String[] args) {

		AnuncianteService service = new AnuncianteServiceImpl();

		Anunciante anunciante = new Anunciante();

		anunciante.setEmail("emaithbxdfhdfgndxrbgl@mail.com");
		anunciante.setSobrenome("lubgdrbis");
		anunciante.setDataNascimento(new Date());
		anunciante.setUsername("trandredddhthhtluis");
		anunciante.setSenha("123");

		try {
			service.salvar(anunciante);
		} catch (NegocioNordesteException e) {
			e.printStackTrace();
		}

		ArrayList<Anunciante> a = (ArrayList<Anunciante>) service.buscarTodos();

		for (Anunciante anunciante2 : a) {
			System.out.println(anunciante2);
		}

	}

}