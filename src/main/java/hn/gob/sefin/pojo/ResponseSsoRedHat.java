package hn.gob.sefin.pojo;

import java.util.List;

/*Proyecto: api-info-token-sso*/

/**
 * HISTORIA DE CAMBIOS
 * Fecha                    Autor                  Descripcion
 * 08/08/2022               Ronald Ortiz           Clase con objeto json a retornar con la respuesta obtenida de Red Hat Single Sign On
 * 
 */

public class ResponseSsoRedHat<T> {

	String state;
	List<T> data;
	List<String> messages;
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public List<T> getData() {
		return data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	

}
