package core;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class testAutomationAPI {

        public static void main(String[] args) throws ParseException {
            String json="[{\"name\":\"UBS\",\"addresses\":[\"London\"]},{\"name\":\"Bank of America\",\"addresses\":[\"Los Angeles\",\"New York\",\"Chicago\",\"San Francisco\"]},{\"name\":\"Citibank\",\"addresses\":[\"Los Angeles\",\"New York\"]},{\"name\":\"Deutsche Bank\",\"addresses\":[\"Berlin\",\"London\",\"Munich\"]}]";
            JSONParser parser=new JSONParser();
            JSONArray banks=(JSONArray) parser.parse(json);
            int maxCount=0;
            JSONObject finalObj=null;
            for(int i=0;i<banks.size();i++){
                JSONObject obj2=(JSONObject) banks.get(i);
                JSONArray address=(JSONArray) obj2.get("addresses");
                if(address.size()>maxCount){
                    finalObj=obj2;
                    maxCount=address.size();

                }
            }
            System.out.println(maxCount);
            System.out.println(finalObj.toString());
            int i = 5;
            System.out.println(i++ + ++i);


        }

    }

