package com.udemy.lomonaco.rest.webservices.restful.swagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Lomanco", null, "fernando_lomonaco@outlook.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Beatiful API ", "Beatiful Api Description", "1.0", null,
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {

		List<ResponseMessage> responseMessages = Arrays.asList(
				new ResponseMessageBuilder().code(500).message("Erro Geral").responseModel(new ModelRef("Error")).build(),
				new ResponseMessageBuilder().code(404).message("Nada encontrado").responseModel(new ModelRef("Error"))
						.build());
		
		return new Docket(DocumentationType.SWAGGER_2).
				apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessages);
	}

}
