package converter.mapper;

import converter.beans.InputItem;
import converter.beans.OutputItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ItemMapper {

    private final static String REGEX_DATE = "(\\d{1,2}.\\d{1,2}.\\d{4})";

    public List<OutputItem> mapIntoOutputItemList(List<InputItem> list) {
        return list.stream()
                .map(inputItem -> new converter.beans.OutputItem(
                        inputItem.getProductName(),
                        processUrl(inputItem),
                        checkPrice(inputItem.getSellingPrice()),
                        checkDate(inputItem.getDescription()),
                        inputItem.getDescription()
                )).collect(Collectors.toList());
    }

    private String processUrl(InputItem inputItem) {
        return inputItem.getLink() + "?sku=" + inputItem.getSku();
    }

    private String checkPrice(String price) {
        String convertedPrice = price.trim().replaceAll("[^0-9?!\\.,]", "");
        int comaIndex;
        int dotIndex;
        String priceAfterCorrection;

        if (convertedPrice.contains(",") && convertedPrice.contains(".")) {
            comaIndex = convertedPrice.indexOf(',');
            dotIndex = convertedPrice.indexOf('.');
            if (comaIndex > dotIndex) {
                String tmp = convertedPrice.replace(".", "");
                priceAfterCorrection = tmp.replace(",", ".");
            } else {
                priceAfterCorrection = convertedPrice.replace(",", "");
            }
        } else if (convertedPrice.contains(",")) {
            priceAfterCorrection = convertedPrice.replace(",", ".");
        } else {
            priceAfterCorrection = convertedPrice;
        }

        if(priceAfterCorrection == null) {
            priceAfterCorrection = "0";
        }

        return new BigDecimal(priceAfterCorrection).setScale(2, RoundingMode.UNNECESSARY).toString();
    }

    private String checkDate(String description) {
        Pattern pattern = Pattern.compile(REGEX_DATE);
        Matcher matcher = pattern.matcher(description);
        String finalDate = null;
        if (matcher.find()) {
            String foundDate = matcher.group().replace('-', '.');
            finalDate = convertDate(foundDate);
        }
        return finalDate;
    }

    private String convertDate(String foundDate) {
        String[] splitDate = foundDate.split("\\.");
        String finalDate;
        if(splitDate[1].length() == 2) {
            Integer checkMth = Integer.parseInt(splitDate[1]);
            if(checkMth > 12) {
                finalDate = convertAmericanDateToNormalDate(foundDate);
            }else {
                finalDate = correctNormalDate(foundDate);
            }
        }else {
            finalDate = splitDate[0] + "." + "0" + splitDate[1] + "." + splitDate[2];
        }
        return finalDate;
    }

    private String correctNormalDate(String foundDate) {
        String[] splitDate = foundDate.split("\\.");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<splitDate.length; i++) {
            if(splitDate[i].length() <2) {
                sb.append("0");
                sb.append(splitDate[i]);
                sb.append(".");
            }else {
                sb.append(splitDate[i]);
                if(i <= 1) {
                    sb.append(".");
                }
            }
        }
        return sb.toString();
    }

    private String convertAmericanDateToNormalDate(String foundDate) {
        String[] splitDate = foundDate.split("\\.");
        StringBuilder sb = new StringBuilder();
        sb.append(splitDate[1]);
        sb.append(".");
        if(splitDate[0].length()<1) {
            sb.append("0");
            sb.append(splitDate[0]);
            sb.append(".");
        }else {
            sb.append(splitDate[0]);
            sb.append(".");
        }
        sb.append(splitDate[2]);
        return sb.toString();
    }
}
