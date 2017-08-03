package br.edu.ifpb.mt.dac.nn.beans;

import java.util.Base64;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ImagemBean extends AbstractBean {

	private static final long serialVersionUID = -7437667367775973347L;

	public String getImageContent(byte[] imageContent) {
		return Base64.getEncoder().encodeToString(imageContent);
	}

}