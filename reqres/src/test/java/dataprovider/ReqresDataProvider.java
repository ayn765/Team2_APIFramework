package dataprovider;

import org.testng.annotations.DataProvider;

public class ReqresDataProvider {

    @DataProvider(name = "reqres_data")
    public Object[][] getData() {
        return new Object[][] {
                {7, "[Michael]"},
                {8, "[Lindsay]"},
                {9, "[Tobias]"},
                {10, "[Byron]"},
                {11, "[George]"},
                {12, "[Rachel]"},
        };
    }
}
