package hn.gob.sefin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.gob.sefin.interfaces.TokenSsoInterface;
import hn.gob.sefin.pojo.JsonResponseSso;
import hn.gob.sefin.pojo.ParamSsoRedHat;
import hn.gob.sefin.pojo.ResponseSsoRedHat;
import hn.gob.sefin.utils.Constantes;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@RestController
@RequestMapping("/sso")
public class ServicesEndPoint {

	@Autowired
	TokenSsoInterface tokenSsoService;

	/**
	 * Servicio para obtener el token y se permite que desde el origen definan el
	 * grant type a utilizar para generar el token
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
	@PostMapping(value = "/obtenerToken", consumes = { "application/json" }, produces = { "application/json" })
	@CrossOrigin(origins = { "*" })
	@ApiOperation(tags = "Token", value = "Obtener Token por Origen", notes = "Servicio para obtener el token y se permite que desde el origen definan el grant type a utilizar para generar el token")
	@ApiResponse(code = 200, message = "Respuesta del servicio.", response = ResponseSsoRedHat.class, examples = @Example(value = @ExampleProperty(value = Constantes.RESPONSE_OBTENERTOKENBYAPLICACION, mediaType = MediaType.APPLICATION_JSON_VALUE)))
	public ResponseSsoRedHat<JsonResponseSso> getToken(
			@RequestBody @ApiParam(name = "jsonRequest", value = "Objeto con los Valores necesario para obtener el token", example = Constantes.REQUEST_JSONREQUEST) ParamSsoRedHat jsonRequest) {
		return tokenSsoService.getToken(jsonRequest);
	}

	/**
	 * Servicio para obtener el token unicamente con los valores de client id y
	 * client secret previamente generados en red hat single sign on
	 * 
	 * @param jsonRequest Se espera un objeto de tipo ParamSSORedHat, esta clase la puede
	 *           encontrar en el parquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *           clase representa una propiedad dentro del json a recibir en cada
	 *           solicitud
	 * @return Se retorna un objeto de tipo ResponseSSORedHat, esta clase la puede
	 *         encontrar en el paquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *         clase representa una propiedad dentro del json a retornar
	 * @see Constantes#RESPONSE_OBTENERTOKENBYAPLICACION  EjemploRespuesta
	 */
	@PostMapping(value = "/obtenerToken/servicesAccount", consumes = { "application/json" }, produces = {
			"application/json" })
	@CrossOrigin(origins = { "*" })
	@ApiOperation(tags = "Token", value = "Obtener Token por Client y Secret", notes = "Servicio para obtener el token unicamente con los valores de client id y client secret previamente generados en red hat single sign on")
	@ApiResponse(code = 200, message = "Respuesta del servicio.", response = ResponseSsoRedHat.class, examples = @Example(value = @ExampleProperty(value = Constantes.RESPONSE_OBTENERTOKENBYAPLICACION, mediaType = MediaType.APPLICATION_JSON_VALUE)))
	public ResponseSsoRedHat<JsonResponseSso> obtenerTokenServicesAccount(
			@RequestBody @ApiParam(name = "jsonRequest", value = "Objeto con los Valores necesario para obtener el token", example = Constantes.REQUEST_JSONREQUEST) ParamSsoRedHat jsonRequest) {
		return tokenSsoService.getTokenServiceAccount(jsonRequest);
	}

	/**
	 * Servicio para obtener un nuevo token compartido entre diferentes client id a
	 * partir de un access token o refresh token de otro client id
	 * 
	 * @param jsonRequest Se espera un objeto de tipo ParamSSORedHat, esta clase la puede
	 *           encontrar en el parquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *           clase representa una propiedad dentro del json a recibir en cada
	 *           solicitud
	 * @return Se retorna un objeto de tipo ResponseSSORedHat, esta clase la puede
	 *         encontrar en el paquete: hn.gob.sefin.pojo. Cada atributo de esta
	 *         clase representa una propiedad dentro del json a retornar
	 * @see Constantes#RESPONSE_OBTENERTOKENBYAPLICACION  EjemploRespuesta
	 */	
	@PostMapping(value = "/obtenerToken/tokenExchange", consumes = { "application/json" }, produces = {
			"application/json" })
	@CrossOrigin(origins = { "*" })
	@ApiOperation(tags = "Token", value = "Obtener Intercambio de Token", notes = "Servicio para obtener un nuevo token compartido entre diferentes client id a partir de un access token o refresh token de otro client id")
	@ApiResponse(code = 200, message = "Respuesta del servicio.", response = ResponseSsoRedHat.class, examples = @Example(value = @ExampleProperty(value = Constantes.RESPONSE_OBTENERTOKENBYAPLICACION, mediaType = MediaType.APPLICATION_JSON_VALUE)))
	public ResponseSsoRedHat<JsonResponseSso> obtenerTokenExchange(
			@RequestBody @ApiParam(name = "jsonRequest", value = "Objeto con los Valores necesario para obtener el token", example = Constantes.REQUEST_JSONREQUEST)  ParamSsoRedHat jsonRequest) {
		return tokenSsoService.getTokenExChange(jsonRequest);
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
	 * @see Constantes#RESPONSE_OBTENERTOKENBYAPLICACION  EjemploRespuesta
	 */
	@GetMapping(value = "/obtenerTokenByAplicacion", produces = { "application/json" })
	@CrossOrigin(origins = { "*" })
	@ApiOperation(tags = "Token", value = "Obtener Token por Aplicaci�n", notes = "Servicio que sera utilizado por todos los frontend de sefin para obtener el token que les servira para interactuar con todos los servicios mapeados en 3scale acorde al grupo que pertenezca.")
	@ApiResponse(code = 200, message = "Respuesta del servicio.", response = ResponseSsoRedHat.class, examples = @Example(value = @ExampleProperty(value = Constantes.RESPONSE_OBTENERTOKENBYAPLICACION, mediaType = MediaType.APPLICATION_JSON_VALUE)))
	public ResponseSsoRedHat<JsonResponseSso> obtenerTokenByAplicacion(
			@RequestParam("idAplicacion") @ApiParam(name = "idAplicacion", value = "Nombre de la Aplicacion configurado en las variables de entorno", example = "GERH_APP") String idAplicacion) {
		return tokenSsoService.getTokenByAplication(idAplicacion);
	}
}
