package hn.gob.sefin.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/*Proyecto: api-info-token-sso*/

/**
 * HISTORIA DE CAMBIOS
 * Fecha                    Autor                  Descripcion
 * 08/08/2022               Ronald Ortiz           Clase con objeto json que detala las propiedades a utilizar de la respuesta enviada por la api de SSO 
 * 
 */

public class JsonResponseSso {

	@JsonProperty("access_token")
    public String accessToken;
	
	@JsonProperty("refresh_token")
    public String refreshToken;
	
	@JsonProperty("expires_in")
    public Long expiresIn;
	
	@JsonProperty("refresh_expires_in")
    public Long refreshExpiresIn;
      
}
