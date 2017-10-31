package com.sunil.demo.test.utils;

import android.content.Context;

/**
 * Created by Sunil Rana on 10/29/2017.
 */

public class Utils {


    public static String loadJSONFromAsset(Context context) {
        return  "[\n" +
                "        {\n" +
                "          \"id\": \"0\",\n" +
                "          \"name\": \"Product 1\",\n" +
                "          \"description\": \" 1 lorem ipsum detail\",\n" +
                "          \"regular_price\": \"20\",\n" +
                "          \"sale_price\": \"15\",\n" +
                "          \"product_photo\": \"\",\n" +
                "          \"color\": \"red\",\n" +
                "          \"store\": \"A\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"1\",\n" +
                "          \"name\": \"Product 2\",\n" +
                "          \"description\": \" 2 lorem ipsum detail\",\n" +
                "          \"regular_price\": \"50\",\n" +
                "          \"sale_price\": \"40\",\n" +
                "          \"product_photo\": \"\",\n" +
                "          \"color\": \"green\",\n" +
                "          \"store\": \"B\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"3\",\n" +
                "          \"name\": \"Product 3\",\n" +
                "          \"description\": \" 3 lorem ipsum detail\",\n" +
                "          \"regular_price\": \"80\",\n" +
                "          \"sale_price\": \"65\",\n" +
                "          \"product_photo\": \"\",\n" +
                "          \"color\": \"yellow\",\n" +
                "          \"store\": \"C\"\n" +
                "        }\n" +
                "      ";

    }
}
