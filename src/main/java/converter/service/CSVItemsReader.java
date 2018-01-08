package converter.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import converter.beans.InputItem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CSVItemsReader {

    public List<InputItem> loadCSVItems(String path) {
        List<InputItem> items = null;

        try {

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "UTF-8");

            CsvToBean csvToBean = new CsvToBeanBuilder(inputStreamReader)
                    .withType(InputItem.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .withQuoteChar('"')
                    .withEscapeChar('\\')
                    .withSkipLines(1)
                    .build();

            items = csvToBean.parse();

        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }
}
