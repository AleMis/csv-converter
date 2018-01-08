package converter.mapper;

import com.opencsv.bean.ColumnPositionMappingStrategy;

public class CustomMappingStrategy  <T> extends ColumnPositionMappingStrategy<T> {
    private static final String[] HEADER = new String[]{"name", "offerurl", "price", "published", "description"};

    @Override
    public String[] generateHeader() {
        return HEADER;
    }
}
