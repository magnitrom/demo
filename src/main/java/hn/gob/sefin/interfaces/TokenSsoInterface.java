package hn.gob.sefin.interfaces;

import hn.gob.sefin.pojo.JsonResponseSso;
import hn.gob.sefin.pojo.ParamSsoRedHat;
import hn.gob.sefin.pojo.ResponseSsoRedHat;
import hn.gob.sefin.utils.Constantes;

/*Proyecto: api-info-token-sso*/

/**
 * HISTORIA DE CAMBIOS
 * Fecha                    Autor                  Descripcion
 * 08/08/2022               Ronald Ortiz           Contrato para la logica de negocio a implementar para obtener los token por medio de red hat SSO 
 * 
 */

public interface TokenSsoInterface {

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
	public ResponseSsoRedHat<JsonResponseSso> getToken(ParamSsoRedHat requestFE);	

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
	public ResponseSsoRedHat<JsonResponseSso> getTokenServiceAccount(ParamSsoRedHat requestFE);
	
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
	public ResponseSsoRedHat<JsonResponseSso> getTokenExChange(ParamSsoRedHat requestFE);

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
	public ResponseSsoRedHat<JsonResponseSso> getTokenByAplication(String idAplicacion);
	
	
}
