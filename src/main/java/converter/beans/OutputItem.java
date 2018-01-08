package converter.beans;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OutputItem {

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private String offerurl;

    @CsvBindByPosition(position = 2)
    private String price;

    @CsvBindByPosition(position = 3)
    private String published;

    @CsvBindByPosition(position = 4)
    private String description;
}
