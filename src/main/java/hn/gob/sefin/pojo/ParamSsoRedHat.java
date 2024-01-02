package hn.gob.sefin.pojo;


/*Proyecto: api-info-token-sso*/

/**
 * HISTORIA DE CAMBIOS
 * Fecha                    Autor                  Descripcion
 * 08/08/2022               Ronald Ortiz           Clase con objeto json a recibir desde frontend para obtener el token que se utilizara en las peticiones a enviar a traves de 3scale 
 * 
 */

public class ParamSsoRedHat {

	String grantType;
	String clientId;
	String clientSecret;
	String username;
	String password;
	String scope;
	String securityRealm;
    String host;
	String audience;
	String subjectToken;
	
	public String getGrantType() {
		return grantType;
	}
	
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClientSecret() {
		return clientSecret;
	}
	
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getScope() {
		return scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getSecurityRealm() {
		return securityRealm;
	}
	
	public void setSecurityRealm(String securityRealm) {
		this.securityRealm = securityRealm;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getAudience() {
		return audience;
	}
	
	public void setAudience(String audience) {
		this.audience = audience;
	}
	
	public String getSubjectToken() {
		return subjectToken;
	}
	
	public void setSubjectToken(String subjectToken) {
		this.subjectToken = subjectToken;
	}
	
}
