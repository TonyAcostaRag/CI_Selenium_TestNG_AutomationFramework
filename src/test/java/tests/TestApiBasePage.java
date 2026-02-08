package tests;

import org.testng.annotations.BeforeClass;

import api.clients.EmployeeApiClient;
import framework.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class TestApiBasePage {

    protected RequestSpecification requestSpec;
    protected EmployeeApiClient employeeApi;

    @BeforeClass
    public void setup() {
        
        String token = ConfigReader.get("token");

        employeeApi = new EmployeeApiClient();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("apiHost"))
                .addHeader("Authorization", token)
                .setContentType("application/json")
                .build();

        RestAssured.requestSpecification = requestSpec;
    }
    
}
