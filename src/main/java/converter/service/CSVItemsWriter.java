package converter.service;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import converter.beans.OutputItem;
import converter.mapper.CustomMappingStrategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class CSVItemsWriter {

    public void writeDataToCSVFile(List<OutputItem> outputItemList, String savingPath) {

        try( OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(savingPath), "ISO-8859-1")) {

            CustomMappingStrategy<OutputItem> mappingStrategy = new CustomMappingStrategy<>();
            mappingStrategy.setType(converter.beans.OutputItem.class);

            StatefulBeanToCsv<OutputItem> beanToCsv = new StatefulBeanToCsvBuilder<OutputItem>(outputStreamWriter)
                    .withSeparator('|')
                    .withQuotechar('\'')
                    .withEscapechar('\\')
                    .withMappingStrategy(mappingStrategy)
                    .build();

            beanToCsv.write(outputItemList);
        }catch(IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvRequiredFieldEmptyException e) {
            System.out.println(e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            System.out.println(e.getDestinationClass());
        }
    }
}
