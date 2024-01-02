package hn.gob.sefin.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAutomationDevOps {

	@Autowired
	private Environment env;
	
	@Test
	public void validacionVariableConexionBaseDatos() {
		Assert.assertNotNull(env.getProperty("URL_DATABASE"));
	}
	
}
