package tests.api;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import tests.TestApiBasePage;
import api.models.Employee;
import listeners.TestLog;

public class EmployeeApiTest extends TestApiBasePage {
/* 
    @Test
    void createEmployeeByObject() {
        // Failing test due creating with object
        Employee emp = new Employee();
        emp.setUsername("jdoe");
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setDependants(2);
        //emp.setSalary(52000);

        Response response = employeeApi.createEmployee(emp);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);

    }
 */

/*     String payload = 
                    "{\r\n" + //
                "          \"username\": \"jdoe\",\r\n" + //
                "          \"firstName\": \"John\",\r\n" + //
                "          \"lastName\": \"Doe\",\r\n" + //
                "          \"dependants\": 2,\r\n" + //
                "          \"salary\": 80000.0,\r\n" + //
                "          \"expiration\": \"2025-13-32T17:32:28Z\",\r\n" + //
                "          \"id\": \"0248cc10-e183-434c-b239-1770e825f8af\"\r\n" + //
                "        }";
 */
/* 
    @Test
    void createEmployeeByFields() {
        // Passing test
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

    }
 */

/* 
    @Test
    void failWhenRequiredFieldsMissing() {
        // "required": ["firstName", "lastName", "username"]

        Employee emp = new Employee();
        emp.setFirstName("John");

        Response response = employeeApi.createEmployee(emp);
        
        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 400);
    }

    @Test 
    void rejectAdditionalProperties() {
        // "additionalProperties": false

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

    }

    @Test
    void failwhenDependantsOutOfRange() {
        // "dependants": { "minimum": 0, "maximum": 32 }

        Employee emp = new Employee();

        emp.setUsername("rangeFail");
        emp.setFirstName("Test");
        emp.setLastName("User");
        emp.setDependants(33);
        emp.setSalary(52000);

        Response response = employeeApi.createEmployee(emp);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 400);
    }
*/

/*
    @Test
    public void getAllEmployees() {

        // Passing test
        Response response = employeeApi.getEmployees();

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);
    }
*/
    
/* 
    @Test
    public void updateEmployee() {
        // Passing test
        Employee emp = new Employee();
        emp.setId("d6c39a42-2333-4cf2-a2f9-66b5b578df7d");
        emp.setUsername("api");
        emp.setFirstName("apiEdite");
        emp.setLastName("apiEdite");
        emp.setId("cfd953d1-e007-458e-92cb-f29a053212f8"); // ---
        //emp.setDependants(10);
        emp.setSalary(53000.0f);

        Response response = employeeApi.updateFullEmployee(emp);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);
    }
   */

    
    @Test
    public void getEmployeeById() {

        String id = "bc68963e-467a-4ab4-a08e-b65e3092b02c";

        Response response = employeeApi.getEmployeeById(id);

        TestLog.info(response.getBody().asString());
        assertEquals(response.statusCode(), 200);
    }
    
/* 
    @Test
    public void failForInvalidUUID() {
        String id = "not-a-uuid";

        Response response = employeeApi.getEmployeeById(id);

        TestLog.info(response.getBody().asString());
        assertEquals(response.getStatusCode(), 400);
    }
*/
/* 
    @Test
    public void deleteEmployee() {
        String id = "e13233a1-db83-4215-9143-5f30c66076e9";

        Response response = employeeApi.deleteEmployee(id);

        TestLog.info(response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);

    }
      */
}
