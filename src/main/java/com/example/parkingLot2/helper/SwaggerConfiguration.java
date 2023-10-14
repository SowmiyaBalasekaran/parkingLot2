package com.example.parkingLot2.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
//@AllArgsConstructor
public class SwaggerConfiguration {
//  private static final String STOREID = "storeId";
//  private static final String CHANNELID = "channelId";
//  private static final String CLIENTID = "clientId";
//  private static final String REQUESTID = "requestId";
//  private static final String USERNAME = "username";
//
//  private static final List<String> mandatoryParams =
//      Arrays.asList(STOREID, CHANNELID, CLIENTID, REQUESTID, USERNAME);
//
//  @Value("${application.version}")
//  private String version;
//
//
//  public SwaggerConfiguration() {
//  }
//
//  public OperationCustomizer customize() {
//    return (operation, handlerMethod) -> {
//      operation = createMandatoryParamIfNotExist(operation);
//      operation.getParameters().replaceAll(this::setParam);
//      return operation;
//    };
//  }
//
//  public Operation createMandatoryParamIfNotExist(Operation operation) {
//    if (operation.getParameters() == null) {
//      operation.setParameters(new ArrayList<>());
//    }
//    List<String> params = mandatoryParams.stream().filter(
//        name -> !operation.getParameters().stream().map(Parameter::getName)
//            .collect(Collectors.toList()).contains(name)).collect(Collectors.toList());
//    params.forEach(s -> operation.getParameters()
//        .add(new Parameter().name(s).in(ParameterIn.QUERY.toString()).required(Boolean.TRUE)));
//    return operation;
//  }
//
//  public Parameter setParam(Parameter parameter) {
//    switch (parameter.getName()) {
//      case STOREID:
//        parameter.schema(new StringSchema()).description(STOREID).example("10001");
//        break;
//      case CHANNELID:
//        parameter.schema(new StringSchema()).description(CHANNELID).example("practice");
//        break;
//      case CLIENTID:
//        parameter.schema(new StringSchema()).description(CLIENTID).example("practice");
//        break;
//      case REQUESTID:
//        parameter.schema(new StringSchema()).description(REQUESTID)
//            .example(UUID.randomUUID().toString());
//        break;
//      case USERNAME:
//        parameter.schema(new StringSchema()).description(USERNAME).example(USERNAME);
//        break;
//      default:
//        break;
//
//    }
//    return parameter;
//  }
//
//  @Bean
//  public GroupedOpenApi groupedOpenApi() {
//    SpringDocUtils.getConfig().replaceWithClass(DeferredResult.class, ResponseEntity.class);
//    return GroupedOpenApi.builder().group("v1")
//        .addOpenApiCustomiser(openApi -> openApi.info(getInfo()))
//        .addOperationCustomizer(customize()).packagesToScan("com.example.practice.web.controller").build();
//  }
//
//  @Bean
//  public Info getInfo() {
//    try {
//      Properties properties = new Properties();
//      properties.load(this.getClass().getClassLoader().getResourceAsStream("swagger.properties"));
//      String title = properties.getProperty("application.api.title");
//      String description = properties.getProperty("application.api.description");
//      String licenseUrl = properties.getProperty("application.api.licenseLocation");
//      return new Info().contact(new io.swagger.v3.oas.models.info.Contact())
//          .license(new License().url(licenseUrl)).title(title).description(description)
//          .version(version);
//    } catch (Exception var7) {
//      log.warn("Failed to load swagger.properties", var7);
//      return new Info();
//    }
//  }
}
