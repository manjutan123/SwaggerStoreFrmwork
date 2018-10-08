package storeTestcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import common.RestUtlities;
import constants.Endpoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Endtoendorder {
	
	static RequestSpecification reqspec1;
	static RequestSpecBuilder reqbuild1;
    static ResponseSpecBuilder resbuild1;
    static ResponseSpecification resspec1;
    
	@BeforeClass
	public void setuppre()
	{
		reqspec1=RestUtlities.getrequest();
		
		resspec1=RestUtlities.getResponsespec();
	}

	
	@Test
	public void setdata()
	{
		DataLoad l2;
		/*load1.setId(3);
		load1.setPetId(30);
		load1.setQuantity(31);
		load1.setShipDate("2018-09-25T12:33:22.905Z");
		load1.setStatus("hold");
		load1.setComplete(true);*/
		l2=DataLoad.setdata(6,22,22,"2018-09-25T12:33:22.905Z", "running", false);
				
		Response res=given()
		 .spec(reqspec1)
		 .body(l2)
		 .log().all()
		.when()
		  .post("/order")
		.then()
		 .spec(resspec1)
		 .extract().response();
		
		JsonPath pp=RestUtlities.getJson(res);
		System.out.println("return status is "+pp.get("status"));
		String st=pp.get("status");
		Assert.assertTrue("running".equalsIgnoreCase(st));
	}
	
	@Test(dependsOnMethods = "setdata")
	public void readData()
	{
		given()
		.spec(reqspec1)
		.log().all()
		.when()
		  .get(Endpoints.order_get)
		 .then()
		 .spec(resspec1);
	}
	
	@Test(dependsOnMethods = "readData")
	public void dropData()
	{
		given()
		.spec(reqspec1)
		.when()
		  .delete(Endpoints.order_get)
		 .then()
		 .spec(resspec1);
	}
}
