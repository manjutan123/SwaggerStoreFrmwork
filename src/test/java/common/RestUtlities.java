package common;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import constants.Paths;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestUtlities {

	public static String ENDPOINT;
	public static RequestSpecBuilder REQUEST_BUILDER;
	public static RequestSpecification REQUEST_SPEC;
	public static ResponseSpecBuilder RESPONSE_BUILDER;
	public static ResponseSpecification RESPONSE_SPEC;
	
	public static RequestSpecification getrequest()
	{		
		REQUEST_BUILDER=new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(Paths.base_path);
		REQUEST_BUILDER.setBasePath(Paths.base_storepath);
		REQUEST_BUILDER.setContentType("application/json");
		REQUEST_SPEC=REQUEST_BUILDER.build();
		return REQUEST_SPEC;
    }

	public static void setEndpoint(String epoint)
	{
		ENDPOINT=epoint;
	}
	
	public static ResponseSpecification getResponsespec()
	{		
		RESPONSE_BUILDER=new ResponseSpecBuilder();
		RESPONSE_BUILDER.expectStatusCode(200);
		RESPONSE_BUILDER.expectResponseTime(lessThan(5L),TimeUnit.SECONDS);
		RESPONSE_SPEC=RESPONSE_BUILDER.build();
		return RESPONSE_SPEC;
    }
	
	public static RequestSpecification queryParamset(RequestSpecification rspec,String param,String val)
	{
		return rspec.queryParam(param, val);
	}

	public static RequestSpecification queryParamset(RequestSpecification rspec,Map<String,String>queryMap)
	{
		return rspec.queryParams(queryMap);
	}

	public static RequestSpecification pathParamSet(RequestSpecification rspec,String param,String val)
	{
		return rspec.pathParam(param, val);
	}
       
	public static Response getResponse()
	{
		return given().get(ENDPOINT);
	}
    
	public static Response getResponse(RequestSpecification rspec,String type)
	{
		REQUEST_SPEC.spec(rspec);
		Response response=null;
		if(type.equalsIgnoreCase("get"))
		{
			response=given().spec(REQUEST_SPEC).get(ENDPOINT);
		}
		else if(type.equalsIgnoreCase("post"))
		{
			response=given().spec(REQUEST_SPEC).post(ENDPOINT);
		}
		else if(type.equalsIgnoreCase("put"))
		{
			response=given().spec(REQUEST_SPEC).put(ENDPOINT);
		}
		else if(type.equalsIgnoreCase("delete"))
		{
			response=given().spec(REQUEST_SPEC).delete(ENDPOINT);
		}
		else
		{
			System.out.println("Type is not supported ");
		}
	//	response.then().log().all();
		response.then().spec(RESPONSE_SPEC);
		return response;
	}

	
	public static JsonPath getJson(Response res)
	{
		String path=res.asString();
		JsonPath jsonp=new JsonPath(path);
		return jsonp;
	}
	
	public static void resetBase()
	{
		RestAssured.basePath=null;
	}
	
	public static RequestSpecification setContenttype(RequestSpecification rspec,String type)
	{
		return rspec.contentType("type");
     }
}