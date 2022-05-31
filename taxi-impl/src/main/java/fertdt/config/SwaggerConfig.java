package fertdt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder();
        parameterBuilder.name("Authorization")
                .in(ParameterType.HEADER)
                .query(q -> q.model(modelSpecificationBuilder -> modelSpecificationBuilder.scalarModel(ScalarType.STRING)))
                .description("JWT token")
                .required(false)
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("fertdt.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(List.of(parameterBuilder.build()));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TaxiProject")
                .version("1.0")
                .description("REST API for a simple taxi service project")
                .contact(new Contact("Alexey Kislovskiy", "", "kislovskijalexey34@gmail.com"))
                .build();
    }
}

