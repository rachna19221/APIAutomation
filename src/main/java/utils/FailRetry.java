package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailRetry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 1;

    @Override
    public boolean retry(ITestResult result) {
        System.out.println("Retrying test: " + result.getName() + " | Attempt " + (retryCount + 1));
        if (retryCount < maxRetryCount) {

            retryCount++;
            return true;
        }
        return false;
    }
}
