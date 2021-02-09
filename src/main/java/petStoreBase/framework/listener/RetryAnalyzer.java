package petStoreBase.framework.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount ;
    private static boolean isRerun = false;
    private static Logger log = LoggerFactory.getLogger(RetryAnalyzer.class);

    public boolean retry(ITestResult result) {
        if (retryCount < getMaxRetryCount()) {
            log.info("Retrying Test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
            result.setStatus(ITestResult.FAILURE);

            retryCount++;
            isRerun = true;
            return true;
        }
        else{
            retryCount = 0;
            isRerun = false;
        }

        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1) {
            resultName = "SUCCESS";
        }
        if (status == 2) {
            resultName = "FAILURE";
        }
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }

    public boolean isRerun() {
        return isRerun;
    }
    public boolean isRetryAvailable() {
        return retryCount < getMaxRetryCount();
    }
    public boolean isRetryAvailableForValidateSkipTest() {
        return retryCount <= getMaxRetryCount();
    }

    public int getMaxRetryCount(){
        if(System.getenv("MAX_RETRY_COUNT")!=null){
            maxRetryCount=Integer.parseInt(System.getenv("MAX_RETRY_COUNT"));
        }
        else{
            maxRetryCount=5;
        }
        return maxRetryCount;
    }

}

