package utils;





import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation testannotation, Class testClass,
                          Constructor testConstructor, Method testMethod)    {
        Class<? extends IRetryAnalyzer> retryClass = testannotation.getRetryAnalyzerClass();
        if (retryClass == null)    {
            testannotation.setRetryAnalyzer(FailRetry.class);//pass the class name created in Step-1
        }

    }
}
