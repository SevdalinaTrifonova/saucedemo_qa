package dataproviders;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Clients {
    @DataProvider ( name = "clientDataCsv" )
    public static Object[][] readClientDataFromCsv() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/clientData.csv"))) {
            List<String[]> csvData = csvReader.readAll();
            csvReader.close();
            Object[][] csvDataObject = new Object[csvData.size()][5];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;
        }
    }

}
