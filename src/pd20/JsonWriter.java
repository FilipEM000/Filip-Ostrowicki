package pd20;

import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.model.UserReport;

import java.io.File;
import java.io.IOException;

public class JsonWriter {
    ObjectMapper mapper = new ObjectMapper();

    public void writeToJson(UserReport report) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/pd20/raport.json"), report);
        } catch (IOException e) {
            System.err.println("Wystąpił błąd przy próbie zapisania raportu do JSON");
        }
    }
}
