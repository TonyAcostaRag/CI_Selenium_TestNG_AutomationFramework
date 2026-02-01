package dataproviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {

        return new Object[][] {

            {"standard_user", "secret_sauce"},
            {"performance_glitch_user", "secret_sauce"}

        };
    }
    
}
