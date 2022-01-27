package com.macy.consumer;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

@EnableRabbit
public class MacysOrderMessageConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacysOrderMessageConsumerApplication.class, args);
	}
	
//	private ApiInfo getApiInfo() {
//		return new ApiInfo(
//				"MacyOrderMessageConsumer REST API Documentation",
//				 "Stock REST APIs released by Zensar LTD.",
//				 "2.5",
//				 "http://zensar.com/termsoservice",
//				 new Contact("Preeti","http://preeti.com","preeti.rani@zensar.com"),
//				 "GPL",
//			      "http://gpl.com",
//			      new ArrayList<VendorExtension>());
//	}
//	
//	
//	@Bean
//	public Docket getCustomizedDocket() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(getApiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.macy.consumer"))
//				.paths(PathSelectors.any())
//				.build();
//	}
	
	
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
