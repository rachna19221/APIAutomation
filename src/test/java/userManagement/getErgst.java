package userManagement;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.RetryListener;

@Listeners(RetryListener.class)
public class getErgst {
    public static int count=0;
    @Test(groups="SmokeSuite")
    public void myFailingTest() {
        System.out.println("Executing test..." + (count +1));
        Assert.fail("Forcing failure to trigger retry");
    }

}
