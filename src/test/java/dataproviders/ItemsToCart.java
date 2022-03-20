package dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ItemsToCart {
    @DataProvider ( name = "itemsToCartJson" )
    public Object[][] getData() {
        try {
            String filename = "src/test/resources/itemsToCart.json";
            File file = new File(filename);
            HashMap<String, Object> map = new ObjectMapper().readValue(file, HashMap.class);
            List<Object> casesList = (List<Object>) map.get("TestItems");
            Object[][] jsonDataObject = new Object[casesList.size()][1];
            for (int i = 0; i < casesList.size(); i++) jsonDataObject[i][0] = casesList.get(i);
            return jsonDataObject;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
