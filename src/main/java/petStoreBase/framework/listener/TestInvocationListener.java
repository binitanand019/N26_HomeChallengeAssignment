package petStoreBase.framework.listener;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestInvocationListener implements IInvokedMethodListener {

    static Logger log = Logger.getLogger(TestInvocationListener.class);
    public static boolean toberetried = false;

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}
