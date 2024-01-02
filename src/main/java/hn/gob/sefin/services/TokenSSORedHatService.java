package hn.gob.sefin.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.gob.sefin.interfaces.TokenSsoInterface;
import hn.gob.sefin.pojo.JsonResponseSso;
import hn.gob.sefin.pojo.ParamSsoRedHat;
import hn.gob.sefin.pojo.ResponseSsoRedHat;


/*Proyecto: api-info-token-sso*/

/**
 * HISTORIA DE CAMBIOS
 * Fecha                    Autor                  Descripcion
 * 08/08/2022               Ronald Ortiz           Clase que contiene la logica de negocio a implementar en la obtencion de token por medio de red hat single sign on 
 * 
 */

@Service
public class TokenSSORedHatService implements TokenSsoInterface {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${scope.servicesAccount}")
	public String scopeServicesAccount;
	
	@Value("${grantType.servicesAccount}")
	public String grantTypeServicesAccount;
	
	@Value("${mensaje.exitoso}")
	public String mensajeExitoso;
	
	@Value("${mensaje.error}")
	public String mensajeError;
	
	@Value("${mensaje.sinparametros}")
	public String mensajeSinParametros;
	
	@Value("${mid.path.sso}")
	public String midPathSso;
	
	@Value("${final.path.sso}")
	public String finalPathSso;
	
	public static final String CONTEN_TYPE_CONST   = "Content-Type";

	public static final String CLIENT_ID_CONST     = "client_id";
	
	public static final String CLIENT_SECRET_CONST = "client_secret";
	
	public static final String GRANT_TYPE          = "grant_type";
	
	public static final String ACCEPT_CONST        = "Accept";
	
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Servicio para obtener el token y se permite que desde el origen definan el
	 * grant type a utilizar para generar el token
	 * 
	 * @param requestFE Se espera un objeto de tipo ParamSsoRedHat, esta clase la puede
	 *           encontrar en el parquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *           clase representa una propiedad dentro del json a recibir en cada
	 *           solicitu
	 * @return Se retorna un objeto de tipo ResponseSSORedHat, esta clase la puede
	 *         encontrar en el paquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *         clase representa una propiedad dentro del json a retornar
	 * @see Constantes#RESPONSE_OBTENERTOKEN  EjemploRespuesta
	 */
	@Override
	public ResponseSsoRedHat<JsonResponseSso> getToken(ParamSsoRedHat requestFE) {

		ResponseSsoRedHat<JsonResponseSso> responseGT = new ResponseSsoRedHat<>();    
		
		try {
			HttpHeaders headersGT = new HttpHeaders();
			headersGT.add(CONTEN_TYPE_CONST, MediaType.APPLICATION_FORM_URLENCODED.toString());
			headersGT.add(ACCEPT_CONST, MediaType.APPLICATION_JSON.toString());
			headersGT.setBasicAuth(requestFE.getClientId(), requestFE.getClientSecret());
			
			MultiValueMap<String, String> requestBodyGT = new LinkedMultiValueMap<>();
			requestBodyGT.add(CLIENT_ID_CONST, requestFE.getClientId());
			requestBodyGT.add(GRANT_TYPE, requestFE.getGrantType());
			requestBodyGT.add("scope", requestFE.getScope());
			requestBodyGT.add("username", requestFE.getUsername());
			requestBodyGT.add("password", requestFE.getPassword());
			requestBodyGT.add(CLIENT_SECRET_CONST, requestFE.getClientSecret());
			
			HttpEntity<MultiValueMap<String, String>> formEntityGT = new HttpEntity<>(requestBodyGT, headersGT);
			
			String urlSSOGT = "https://"+requestFE.getHost()+midPathSso+requestFE.getSecurityRealm()+finalPathSso;
	      
			ResponseEntity<JsonResponseSso> responseSSO = restTemplate.exchange(urlSSOGT, HttpMethod.POST, formEntityGT, JsonResponseSso.class);
			
			responseGT.setState(mensajeExitoso);
			responseGT.setData(Arrays.asList(responseSSO.getBody()));
			responseGT.setMessages(Arrays.asList(""));
			
		}catch(Exception exGT) {
			responseGT.setState(mensajeError);
			responseGT.setData(Arrays.asList(new JsonResponseSso()));
			responseGT.setMessages(Arrays.asList(exGT.getMessage()));
		}
		
		return responseGT;
	}

	/**
	 * Servicio para obtener el token unicamente con los valores de client id y
	 * client secret previamente generados en red hat single sign on
	 * 
	 * @param Se espera un objeto de tipo ParamSSORedHat, esta clase la puede
	 *           encontrar en el parquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *           clase representa una propiedad dentro del json a recibir en cada
	 *           solicitu
	 * @return Se retorna un objeto de tipo ResponseSSORedHat, esta clase la puede
	 *         encontrar en el paquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *         clase representa una propiedad dentro del json a retornar
	 * @see Constantes#RESPONSE_OBTENERTOKEN  EjemploRespuesta
	 */
	@Override
	public ResponseSsoRedHat<JsonResponseSso> getTokenServiceAccount(ParamSsoRedHat requestFE) {

		   ResponseSsoRedHat<JsonResponseSso> responseGTSA = new ResponseSsoRedHat<>();    
		
		try {
			HttpHeaders headersGTSA = new HttpHeaders();
			headersGTSA.add(CONTEN_TYPE_CONST, MediaType.APPLICATION_FORM_URLENCODED.toString());
			headersGTSA.add(ACCEPT_CONST, MediaType.APPLICATION_JSON.toString());
			headersGTSA.setBasicAuth(requestFE.getClientId(), requestFE.getClientSecret());
			
			MultiValueMap<String, String> requestBodyGTSA = new LinkedMultiValueMap<>();
			requestBodyGTSA.add(CLIENT_ID_CONST, requestFE.getClientId());
			requestBodyGTSA.add(GRANT_TYPE, grantTypeServicesAccount);
			requestBodyGTSA.add("scope", scopeServicesAccount);
			requestBodyGTSA.add(CLIENT_SECRET_CONST, requestFE.getClientSecret());
			
			HttpEntity<MultiValueMap<String, String>> formEntityGTSA = new HttpEntity<>(requestBodyGTSA, headersGTSA);
			
			String urlSSOGTSA = "https://"+requestFE.getHost()+midPathSso+requestFE.getSecurityRealm()+finalPathSso;
	      		
			ResponseEntity<JsonResponseSso> responseSSOGTSA = restTemplate.exchange(urlSSOGTSA, HttpMethod.POST, formEntityGTSA, JsonResponseSso.class);
			
			responseGTSA.setState(mensajeExitoso);
			responseGTSA.setData(Arrays.asList(responseSSOGTSA.getBody()));
			responseGTSA.setMessages(Arrays.asList(""));
			
		}catch(Exception exGTSA) {
			responseGTSA.setState(mensajeError);
			responseGTSA.setData(Arrays.asList(new JsonResponseSso()));
			responseGTSA.setMessages(Arrays.asList(exGTSA.getMessage()));
		}
		
		return responseGTSA;
	}

	/**
	 * Servicio para obtener un nuevo token compartido entre diferentes client id a
	 * partir de un access token o refresh token de otro client id
	 * 
	 * @param jsonRequest Se espera un objeto de tipo ParamSSORedHat, esta clase la puede
	 *           encontrar en el parquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *           clase representa una propiedad dentro del json a recibir en cada
	 *           solicitu
	 * @return Se retorna un objeto de tipo ResponseSSORedHat, esta clase la puede
	 *         encontrar en el paquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *         clase representa una propiedad dentro del json a retornar
	 * @see Constantes#RESPONSE_OBTENERTOKEN  EjemploRespuesta
	 */	
	@Override
	public ResponseSsoRedHat<JsonResponseSso> getTokenExChange(ParamSsoRedHat requestFE) {

		ResponseSsoRedHat<JsonResponseSso> responseGTEC = new ResponseSsoRedHat<>();    
		
		try {
			HttpHeaders headersGTEC = new HttpHeaders();
			headersGTEC.add(CONTEN_TYPE_CONST, MediaType.APPLICATION_FORM_URLENCODED.toString());
			headersGTEC.add(ACCEPT_CONST, MediaType.APPLICATION_JSON.toString());
			headersGTEC.setBasicAuth(requestFE.getClientId(), requestFE.getClientSecret());
			
			MultiValueMap<String, String> requestBodyGTEC = new LinkedMultiValueMap<>();
			requestBodyGTEC.add(CLIENT_ID_CONST, requestFE.getClientId());
			requestBodyGTEC.add(CLIENT_SECRET_CONST, requestFE.getClientSecret());
			requestBodyGTEC.add(GRANT_TYPE, "urn:ietf:params:oauth:grant-type:token-exchange");
			requestBodyGTEC.add("subject_token", requestFE.getSubjectToken());
			requestBodyGTEC.add("requested_token_type", "urn:ietf:params:oauth:token-type:refresh_token");
			requestBodyGTEC.add("audience", requestFE.getAudience());
		
			
			HttpEntity<MultiValueMap<String, String>> formEntityGTEC = new HttpEntity<>(requestBodyGTEC, headersGTEC);
			
			String urlSSOGTEC = "http://"+requestFE.getHost()+midPathSso+requestFE.getSecurityRealm()+finalPathSso;
	      		
			ResponseEntity<JsonResponseSso> responseSSOGTEC = restTemplate.exchange(urlSSOGTEC, HttpMethod.POST, formEntityGTEC, JsonResponseSso.class);
			
			responseGTEC.setState(mensajeExitoso);
			responseGTEC.setData(Arrays.asList(responseSSOGTEC.getBody()));
			responseGTEC.setMessages(Arrays.asList(""));
			
		}catch(Exception exGTEC) {
			responseGTEC.setState(mensajeError);
			responseGTEC.setData(Arrays.asList(new JsonResponseSso()));
			responseGTEC.setMessages(Arrays.asList(exGTEC.getMessage()));
		}
		
		return responseGTEC;
	}
	
	/**
	 * Servicio que sera utilizado por todos los frontend de sefin para obtener el
	 * token que les servira para interactuar con todos los servicios mapeados en
	 * 3scale acorde al grupo que pertenezca
	 * 
	 * @param Se espera un parametro de tipo String nombrado idAplicacion, este
	 *           valor hace referencia al nombre del aplicativo frontend que
	 *           contiene la variable de entorno configurada con los datos del
	 *           client id y client secret generados por 3scale para obtener el
	 *           token que se enviara en cada peticio
	 * @return Se retorna un objeto de tipo ResponseSSORedHat, esta clase la puede
	 *         encontrar en el paquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *         clase representa una propiedad dentro del json a retornar
	 * @see Constantes#RESPONSE_OBTENERTOKEN  EjemploRespuesta
	 */
	@Override
	public ResponseSsoRedHat<JsonResponseSso> getTokenByAplication(String idAplicacion) {
		ResponseSsoRedHat<JsonResponseSso> responseGTBA = new ResponseSsoRedHat<>();    
		
		if(System.getenv(idAplicacion) != null)
		{
			try {
				return getToken(mapper.readValue(System.getenv(idAplicacion), ParamSsoRedHat.class));
			} catch (Exception exGTBA) {
				responseGTBA.setState(mensajeError);
				responseGTBA.setData(Arrays.asList(new JsonResponseSso()));
				responseGTBA.setMessages(Arrays.asList(exGTBA.getMessage()));
			}
		}	
		else 
		{
			responseGTBA.setState(mensajeError);
			responseGTBA.setData(Arrays.asList(new JsonResponseSso()));
			responseGTBA.setMessages(Arrays.asList(mensajeSinParametros));
		}	
		
		
		return responseGTBA;
	}

}
