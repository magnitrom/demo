package hn.gob.sefin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/*Proyecto: api-info-token-sso*/

/**
 * HISTORIA DE CAMBIOS
 * Fecha                    Autor                  Descripcion
 * 08/08/2022               Ronald Ortiz           Clase que sera la encergada de renderizar el detalle swagger de los servicios implementados en el proyecto 
 */

@Configuration
public class SpringFoxConfig {
 
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        .select()                                       
        .apis(RequestHandlerSelectors.basePackage("hn.gob.sefin.rest"))
        .paths(PathSelectors.any())                     
        .build()   
        .apiInfo(apiInfo()); 
    }
	 public ApiInfo apiInfo() { 
	        return new ApiInfoBuilder()
	            .title("Token SSO API")
	            .description(
	                "Api encargada de obtener token de seguridad desde Redhat SSO  para utilizar en las peticiones a realizar a traves de 3scale.")
	            .version("1.0.0")
	            .build();                                        
	    }
	
}
