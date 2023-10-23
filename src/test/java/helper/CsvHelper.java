package helper;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.CreateUser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
    public List<CreateUser> readUserCsv() {
        try (

                CSVReader reader = new CSVReaderBuilder(new FileReader("src/test/resources/testData/userData.csv")).withSkipLines(1).build()) {
            List<String[]> csvData = reader.readAll();
            List<CreateUser> users = new ArrayList<>();

            for (String[] row : csvData) {
                String status = row[0];
                String gender = row[1];
                String name = row[2];

                CreateUser user = new CreateUser();
                user.setEmail();
                user.setStatus(status);
                user.setGender(gender);
                user.setName(name);
                users.add(user);
            }
            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
