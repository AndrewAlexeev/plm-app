package com.mai.projects.plm.config;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//http://localhost:5050/swagger-ui.html

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.regex("/.*"))
				.build()
				.apiInfo(prepareApiInfo());
		//    .securityContexts(Lists.newArrayList(securityContext()))
		//  .securitySchemes(Lists.newArrayList(apiKey()));

	}

	private ApiKey apiKey() {
		return new ApiKey("AUTHORIZATION", "api_key", "header");
	}

	private ApiInfo prepareApiInfo() {
		return new ApiInfoBuilder().title("REST")
				.description("API for product")
				.termsOfServiceUrl("http://swagger.io/terms/")
				.build();
	}

	@Bean
	@Primary
	public HandlerMethodResolver fluxMethodResolver(TypeResolver resolver) {
		return new HandlerMethodResolver(resolver) {
			@Override
			public ResolvedType methodReturnType(HandlerMethod handlerMethod) {
				var retType = super.methodReturnType(handlerMethod);

//				while (retType.getErasedType() == Mono.class || retType.getErasedType() == Flux.class || retType.getErasedType() == ResponseEntity.class) {
//					retType = retType.getTypeBindings().getBoundType(0);
//				}

				return retType;
			}
		};
	}
}