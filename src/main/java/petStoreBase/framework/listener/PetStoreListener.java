package petStoreBase.framework.listener;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import petStoreBase.framework.Initialize.Initialize;
import petStoreBase.framework.launch.Initializer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

public class PetStoreListener extends TestListenerAdapter implements IRetryAnalyzer {

    static Logger log = Logger.getLogger(PetStoreListener.class);
    public static int passed = 0, failed = 0, skipped = 0, executed = 0,executiontrycount = 0, maxretrycount = 0,dataproviderSkip = 0;
    static ITestContext TestContext = null;
    private Timestamp starttime = null;
    private Timestamp endtime = null;
    public String ExecutionTime = null;
    String methodNameThis, methodNamePrev = "";
    public static ArrayList<String> testMethodsEntries = new ArrayList<>();
    public Initialize initialize;
    private String Environment;

    public PetStoreListener(){
        Initialize init = Initializer.getInitializer();
        System.out.println("init = " + init.EnvironmentDetails.setup.getEnvironment());
        Environment = init.EnvironmentDetails.setup.getEnvironment();
    }

    @Override
    public void onTestFailure(ITestResult tr)
    {
        TestInvocationListener invocationListener = new TestInvocationListener();


        failed++;
    }

    @Override
    public void onTestSkipped(ITestResult tr)
    {

        log("SKIPPED");
        skipped++;
        if(tr.getMethod().getRetryAnalyzer() != null  &&  tr.getMethod().getRetryAnalyzer() instanceof RetryAnalyzer){
            RetryAnalyzer retryAnalyzer = (RetryAnalyzer) tr.getMethod().getRetryAnalyzer();
            if(retryAnalyzer.isRetryAvailableForValidateSkipTest()){
                log.info("Retry available, not logging failed result to DB for Method " + tr.getMethod().getMethodName());
                if(tr.getParameters().length<=0) {
                    dataproviderSkip++;
                }
                return;
            }
        }
        methodNamePrev = tr.getMethod().getMethodName();
        testMethodsEntries.add(methodNamePrev);
        String inputParams = ArrayUtils.toString(tr.getParameters());


    }

    private void log(String string)
    {
        log.info(string);
    }

    @Override
    public void onTestSuccess(ITestResult tr)
    {
        executiontrycount = 0;
        String inputParams = ArrayUtils.toString(tr.getParameters());
        log("PASSED");
        log.info("Test Result while success :" + tr.getStatus());
        passed++;
        methodNamePrev = tr.getMethod().getMethodName();
        testMethodsEntries.add(methodNamePrev);

    }

    @Override
    public void onTestStart(ITestResult result)
    {
        methodNameThis = result.getMethod().getMethodName();
        System.out.println("'TEST EXECUTION STARTED ##TC_NAME##:: "
                + methodNameThis + "'");
        executed++;
    }

    @Override
    public void onStart(ITestContext testContext)
    {
        TestContext = testContext;
        starttime = new Timestamp(TestContext.getStartDate().getTime());

    }

    @Override
    public boolean retry(ITestResult iTestResult)
    {
        if (executiontrycount <= maxretrycount)
        {
            return true;
        }
        executiontrycount = 0;
        return false;
    }

    private int getExecutionRetriesCount(String methodName) {
        return Collections.frequency(testMethodsEntries, methodName);
    }
}

