package hn.gob.sefin.utils;

/**
 * HISTORIA DE CAMBIOS
 * Fecha                    Autor                  	Descripcion
 * 26/08/2022               Bryan Nuñez 			Clase que contiene las Cadenas de texto a utilizar en la API*/


public class Constantes {
	
	private Constantes()
		{
				//Constructors
		}
	
	public static final String RESPONSE_OBTENERTOKENBYAPLICACION = "{\n    \"state\": \"success\",\n    \"data\": [\n        {\n            \"access_token\": \"eyJhbGciKQ...\",\n            \"refresh_token\": \"eyJhbGciO...\",\n            \"expires_in\": 1500,\n            \"refresh_expires_in\": 1800\n        }\n    ],\n    \"messages\": [\n        \"\"\n    ]\n}\n";
	public static final String RESPONSE_OBTENERTOKEN = "{\n    \"state\": \"success\",\n    \"data\": [\n        {\n            \"access_token\": \"eyJhbG...\",\n            \"refresh_token\": \"eyJhbGc...\",\n            \"expires_in\": 1500,\n            \"refresh_expires_in\": 1800\n        }\n    ],\n    \"messages\": [\n        \"\"\n    ]\n}\n";
	public static final String REQUEST_JSONREQUEST = "{\n    \"clientId\": \"52b3ss1211e\",\n    \"grantType\": \"client_credentials\",\n    \"clientSecret\": \"abababa21ba1b1ab1ab1ab1\",\n    \"username\": \"\",\n    \"password\": \"\",\n    \"scope\": \"openid\",\n    \"securityRealm\": \"reino-sso\",\n    \"host\": \"rhsso.sefin.gob.hn\",\n    \"audience\": \"\",\n    \"subjectToken\": \"\"\n}\n";
}
