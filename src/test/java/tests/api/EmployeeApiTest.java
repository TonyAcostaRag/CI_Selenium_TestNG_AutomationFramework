package tests.api;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import tests.TestApiBasePage;
import api.models.Employee;
import listeners.TestLog;

public class EmployeeApiTest extends TestApiBasePage {

    @Test
    void createEmployeeByObject() {
        TestLog.info("Calling API to create an employee by object");
        Employee emp = new Employee();
        emp.setUsername("object");
        emp.setFirstName("nameObject");
        emp.setLastName("DoeObject");

        Response response = employeeApi.createEmployee(emp);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);
        TestLog.pass("Create Employee by Object test passed");
    }

    @Test
    void createEmployeeByFields() {
        TestLog.info("Calling API to create an employee by payload");

        String payload = 
                    "{\r\n" + //
                "          \"username\": \"jdoe\",\r\n" + //
                "          \"firstName\": \"John\",\r\n" + //
                "          \"lastName\": \"Doe\",\r\n" + //
                "          \"dependants\": 2\r\n" + //
                "        }";

        Response response = employeeApi.createEmployee(payload);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);
        TestLog.pass("Create Employee by Payload test passed");
    }


    @Test
    void failWhenRequiredFieldsMissing() {
        TestLog.info("Calling API to try to create an employee with missing username and lastname");

        Employee emp = new Employee();
        emp.setFirstName("John");

        Response response = employeeApi.createEmployee(emp);
        
        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 405);
        TestLog.pass("Failed creation of Employee due username and lastname are missing.");
    }

    @Test 
    void rejectAdditionalProperties() {
        TestLog.info("Calling API to try to create an employee with additional properties");

        String payload = 
                        "{\r\n" + //
                    "          \"username\": \"hacker\",\r\n" + //
                    "          \"firstName\": \"Bad\",\r\n" + //
                    "          \"lastName\": \"Actor\",\r\n" + //
                    "          \"unknownField\": \"shouldFail\"\r\n" + //
                    "        }";

        Response response = employeeApi.createEmployee(payload);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 400);
        TestLog.pass("Rejected creation of Employee due additional properties passed in payload.");
    }

    @Test
    void failwhenDependantsOutOfRange() {
        TestLog.info("Calling API to try to create an employee with dependants out of range (33)");

        Employee emp = new Employee();

        emp.setUsername("rangeFail");
        emp.setFirstName("Test");
        emp.setLastName("User");
        emp.setDependants(33);
        emp.setSalary(52000);

        Response response = employeeApi.createEmployee(emp);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 405);
        TestLog.pass("Failed creation of Employee due dependant is out of range");
    }


    @Test
    public void getAllEmployees() {
        TestLog.info("Calling API to get all the employees");
        Response response = employeeApi.getEmployees();

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);
        TestLog.pass("Successful retrieval of all Employees");
    }

    
    @Test
    public void updateEmployee() {
        TestLog.info("Calling API to update an employee data");

        Employee emp = new Employee();
        emp.setId("d6c39a42-2333-4cf2-a2f9-66b5b578df7d");
        emp.setUsername("api");
        emp.setFirstName("apiEdited");
        emp.setLastName("apiEdited");
        emp.setDependants(10);
        emp.setSalary(53000.0f);

        Response response = employeeApi.updateFullEmployee(emp);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);
        TestLog.pass("Successful update of specified employee id and edited fields");
    }

    
    @Test
    public void getEmployeeById() {
        TestLog.info("Calling API to get an employee by their id");

        String id = "bc68963e-467a-4ab4-a08e-b65e3092b02c";

        Response response = employeeApi.getEmployeeById(id);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);
        TestLog.pass("Successful retrieval of specified Employee");
    }
    

    @Test
    public void failForInvalidUUID() {
        TestLog.info("Calling API to try to get an employee with an non-uuid");

        String id = "not-a-uuid";

        Response response = employeeApi.getEmployeeById(id);

        TestLog.info(response.getBody().asString());
        assertEquals(response.getStatusCode(), 400);
        TestLog.pass("Failed retrieval of employees due invalid uuid");
    }


    @Test
    public void deleteEmployee() {
        TestLog.info("Calling API to delte an employee by their id");

        String id = "899bf614-c181-4a35-ad77-72f7fd6ff9ad";

        Response response = employeeApi.deleteEmployee(id);

        TestLog.info(response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);
        TestLog.pass("Successful retrieval of all Employees");

    }
    
}
