package com.logistic.resources.imp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaResourceTest {
	
	private static final String API_VERSION = "/api/v1/";
	private static final String SERVICE = "categorias/";
	
	/*
	 * Melhorar este teste, verificar o conteúdo do body
	 * https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/html/spring-boot-features.html#boot-features-testing
	 * 
	 * 	Se você quer se concentrar apenas na camada web e não começar uma completa ApplicationContext, considere usando @WebMvcTestvez .
	 * https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-mvc-tests
	 */
	@Test
    void getTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get(API_VERSION+SERVICE)).andExpect(status().isOk()).andReturn();
    }

}
