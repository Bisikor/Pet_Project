package com.coder.currencyConverter.ParserTask;

import com.coder.currencyConverter.allURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

import static com.coder.currencyConverter.allURL.*;

@Component
public class courseParser {

    private static final String[]massUrl=readAllUrl();
    public static HashMap<String, Double> currencyInformation = new HashMap<>();
    public static String[] massName = new String[massUrl.length];

    // @Scheduled(fixedDelay = 5000)
    public static void parseNewCourse() {
        String[] massValue = new String[massUrl.length];
        int j = 0;
        int indexMass = 0;
        try {
            for (String s : massUrl) {
                Document parsedInfo = Jsoup.connect(s)
                        .userAgent("OperaGX")
                        .timeout(5000)
                        .referrer("https://google.com")
                        .get();
                Elements course = parsedInfo.getElementsByClass("text-nowrap text-narrow-screen-wrap result");
                Elements countrys = parsedInfo.getElementsByClass("title title-sm");
                int i = 0;
                String namedNimass = String.valueOf(countrys);
                int Idlast = namedNimass.lastIndexOf(')') + 1;
                namedNimass = namedNimass.substring(62, Idlast);
                massName[j] = namedNimass; //start 62
                j++;
                for (Element el : course) {
                    if (i == 0) {

                        int inde = el.ownText().indexOf(" ");
                        massValue[indexMass] = el.ownText().substring(0, inde);
                        indexMass++;
                    }
                    i++;
                    if (i == 4)
                        i = 0;
                }
            }
            for (int i = 0; i < massValue.length; i++) {
                currencyInformation.put(massName[i], Double.valueOf(massValue[i]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
