package storeTestcase;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Listenerclass implements ITestListener,ISuiteListener {

	static ExtentTest test;
	static ExtentReports report;
	
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		report = new ExtentReports("D:\\Mywork\\FrameworkSwagger\\test-output\\ExtentReportResults1.html");
		
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		report.flush();
		report.close();
	}

	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub
		test = report.startTest(result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.FAIL, "Test case -> "+result.getName()+" Status is Failed");
		report.endTest(test);
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, "Test case -> "+result.getName()+" Status is Skipped");
		report.endTest(test);

	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.PASS, "Test case -> "+result.getName()+" Status is Passed");
		report.endTest(test);
	
	}

}
