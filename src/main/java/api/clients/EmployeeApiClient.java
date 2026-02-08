package api.clients;

import api.models.Employee;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class EmployeeApiClient {

    private static final String BASE_PATH = "/Prod/api/Employees";

    public Response createEmployee(Employee employee) {
        return given()
                .body(employee)
        .when().post(BASE_PATH)
        .then().extract().response();

    }

    public Response createEmployee(String payload) {
        return given()
                .body(payload)
        .when().post(BASE_PATH)
        .then().extract().response();
        
    }

    public Response updateFullEmployee(Employee employee) {
        return given()
                .body(employee)
        .when()
                .put(BASE_PATH)
        .then()
                .extract().response();
    }

    public Response deleteEmployee(String id) {
        return given()
                .pathParam("id", id)
        .when()
                .delete(BASE_PATH + "/{id}")
        .then()
                .extract().response();


    }

    public Response getEmployees() {
        return given()
                .when()
                .get(BASE_PATH)
                .then()
                .extract().response();
    }

    public Response getEmployeeById(String id) {
        return given()
                .pathParam("id", id)
        .when()
                .get(BASE_PATH + "/{id}")
        .then()
                .extract().response();
    }
    
}
