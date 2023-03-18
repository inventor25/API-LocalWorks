package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestAppTestData {
    /*


        {
            "meta": null,
                "data": {
            "id": 152944, ==>   Zaten id ile get() yaptığımız için bunu body'e eklemeye gerek yok yani assert etmeye gerek yok
                    "name": "Swami Dubashi",
                    "email": "dubashi_swami@swift.name",
                    "gender": "female",
                    "status": "inactive"
        }

     */
    public Map<String, String> dataMapMethod(String name,String email,String gender,String status) {
        Map<String,String > dataMap= new HashMap<>();
        dataMap.put("name",name);
        dataMap.put("email",email);
        dataMap.put("gender",gender);
        dataMap.put("status",status);

        return dataMap;

    }

    public Map<String, Object> expectedDataMapMethod(Object meta, Map<String,String> data) {
        Map<String,Object > expectedData= new HashMap<>();
        expectedData.put("meta",meta);
        expectedData.put("data",data);

        return expectedData;

    }
}
