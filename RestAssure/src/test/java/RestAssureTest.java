import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;



public class RestAssureTest {
    private int empId;

    @Before
    public void setUp()  {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4001;
        empId =1;
    }

    public Response getEmployeeList(){
        Response response = RestAssured.get("/employees/list");
        System.out.println(response.getBody());
        return response;
    }

    @Test
    public void onCallinggetEmployeeList_shouldReturnEmployeeList() {
        Response employeeList = getEmployeeList();
        System.out.println("String is "+employeeList.asString());

    }
    @Test
    public void givenEmployee_onPost_ShouldReturnAddedEmployee(){
        RestAssured.given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\":\"xyz\",\"salary\":\"8000\"}")
                .when()
                .post("/employees/create")
                .then()
                .body("id", Matchers.any(Integer.class))
                        .body("name",Matchers.is("xyz"));
    }
    @Test
    public void givenEmployee_WhenUpdate_ShouldReturnUpdatedEmployee(){
        RestAssured.given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\":\"abc\",\"salary\":\"500000\"}")
                .when()
                .put("/employees/update"+empId)
                .then()
                .body("id",Matchers.any(Integer.class))
                .body("name",Matchers.is("abc"))
                .body("salary",Matchers.is("500000"));
    }
}
