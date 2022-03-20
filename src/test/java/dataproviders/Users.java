package dataproviders;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Users {
    @DataProvider ( name = "usersCsv" )
    public static Object[][] readUsersFromCsv(Method method) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/loginData.csv"))) {
            List<String[]> csvData = csvReader.readAll();
            csvReader.close();
            List<String[]> filteredData = new ArrayList<>();
            if (!method.getName().equals("loginTest")) {

                for (String[] el :
                        csvData) {
                    if (el[2].equals("true")) {
                        filteredData.add(el);
                    }
                }

            } else {
                filteredData = csvData;
            }

            Object[][] csvDataObject = new Object[filteredData.size()][3];
            for (int i = 0; i < filteredData.size(); i++) {
                csvDataObject[i] = filteredData.get(i);
            }
            return csvDataObject;
        }
    }
}
