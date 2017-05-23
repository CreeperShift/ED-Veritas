package info.creepershift.veritas.common.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * ED-Veritas
 * Created by Max on 5/24/2017.
 */
public class LineParser {



    /*
    Parses entire String and outputs it to the console. For DEBUG purposes.
     */
    public static void parseString(String jsonString) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonString);


            JSONObject jsonObject = (JSONObject) obj;

            System.out.println(jsonObject);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static String parseVersion(String jsonString, String key) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonString);
            JSONObject jsonObject = (JSONObject) obj;

            return (String) jsonObject.get(key);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    /*
        Reads out the material EVENT json line from a string.
        Returns both the name and the count inside a String Array.
         */
    public static String[] parseCollectedString(String jsonString) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonString);


            JSONObject jsonObject = (JSONObject) obj;
            String name = (String) jsonObject.get("Name");
            String count = String.valueOf(jsonObject.get("Count"));
            String type = (String) jsonObject.get("Category");
            String time = (String) jsonObject.get("timestamp");


            return new String[]{name, count, type, time};

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }






    public static String[] parseStartupString(String jsonString) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonString);


            JSONObject jsonObject = (JSONObject) obj;
            String name = (String) jsonObject.get("Commander");
            String credits = String.valueOf(jsonObject.get("Credits"));
            String ship = (String) jsonObject.get("Ship");
            String shipName = (String)jsonObject.get("ShipName");
            String time = (String) jsonObject.get("timestamp");


            return new String[]{name, credits, ship, shipName, time};

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String parseMissionString(String jsonString) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(jsonString);

            JSONObject jsonObject = (JSONObject) obj;

            return (String) jsonObject.get("timestamp");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String[][] parseMaterialDump(String jsonString, String type) {
        JSONParser parser = new JSONParser();


        try {
            Object obj = parser.parse(jsonString);
            JSONObject jsonObject = (JSONObject) obj;
            String time = (String) jsonObject.get("timestamp");
            JSONArray jarray = (JSONArray)jsonObject.get(type);


            String[][] returnString = new String[3][];
            returnString[0] = new String[jarray.size()];
            returnString[1] = new String[jarray.size()];
            returnString[2] = new String[]{time};

            for (int i = 0; i < jarray.size(); i++) {

                JSONObject jarrayobj = (JSONObject)jarray.get(i);

                String name = (String) jarrayobj.get("Name");
                String count = String.valueOf(jarrayobj.get("Count"));

                  System.out.println(name + ": " + count);
                //  System.out.println(count);
                returnString[0][i] = name;
                returnString[1][i] = count;

            }
            return returnString;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }



}
