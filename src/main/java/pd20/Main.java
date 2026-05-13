package pd20;

import pd20.Client.RestCountriesClient;
import pd20.model.UserReport;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApiGateway apiGateway = new ApiGateway();
        JsonWriter jsonWriter = new JsonWriter();
        RestCountriesClient restCountriesClient = new RestCountriesClient();

        UserReport report = apiGateway.getUserReport(3L);
        jsonWriter.writeToJson(report);

        System.out.println(restCountriesClient.getCountryInfo("poland"));
    }
}
