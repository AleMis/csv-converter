package converter.beans;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class InputItem {

    @CsvBindByPosition(position = 0)
    private String productName;

    @CsvBindByPosition(position = 1)
    private String link;

    @CsvBindByPosition(position = 2)
    private String sku;

    @CsvBindByPosition(position = 3)
    private String sellingPrice;

    @CsvBindByPosition(position = 4)
    private String description;
}
