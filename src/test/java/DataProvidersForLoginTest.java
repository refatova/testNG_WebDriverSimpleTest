import org.testng.annotations.DataProvider;

public class DataProvidersForLoginTest {

    @DataProvider(name = "invalidloginCred")
    public Object[][] getLoginCredentials() {
        return new Object[][]{
                {"invalid","pUhutet"},
                {"mngr177707","invalid"},
                {"invalid","invalid"}
        };

    }
}
