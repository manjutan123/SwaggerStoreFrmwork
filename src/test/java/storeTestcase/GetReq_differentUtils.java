package storeTestcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import common.RestUtlities;
import constants.Endpoints;
import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GetReq_differentUtils {

	static RequestSpecification reqspec;
	static RequestSpecBuilder reqbuild;
    static ResponseSpecBuilder resbuild;
    static ResponseSpecification resspec;
    Response response;
    
    @BeforeClass
    public void setup()
    {
    	reqspec=RestUtlities.getrequest();
    	
    	
    	reqspec.contentType("application/json");
    	resspec=RestUtlities.getResponsespec();
    }
    
    
    @Test(priority=0)
    public void getstatus1()
    {
    	reqspec.basePath(Paths.base_petpath);

    	RestUtlities.setEndpoint(Endpoints.status_get);
    	
    	Response res=RestUtlities.getResponse(RestUtlities.queryParamset(reqspec, "status", "pending"), "get");
    	
    	String restr=res.asString();
    	
    	System.out.println(res.prettyPrint());
    	
    	JsonPath pat=new JsonPath(restr);
    	
    	ArrayList<String> status=pat.get("status");
    	
    	System.out.println(status);
    	
    	Assert.assertTrue(status.contains("pending"));
    		    		
    }
    
    
    @Test(priority=1)
    public void getstatus2()
    {
    	//RestUtlities.resetBase();
    	reqspec.basePath(Paths.base_petpath);

    	given()
    	       .spec(RestUtlities.queryParamset(reqspec, "status", "sold"))

    	.when()
    	    .get(Endpoints.status_get)
    	.then()
    	 // .log().all() 
    	.spec(resspec);

}
}
