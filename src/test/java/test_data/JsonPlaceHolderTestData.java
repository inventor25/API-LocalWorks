package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {


    //Api den Response olarak gelecek actual datamızı expected datamızla karşılaştırma yapmamız için
    //expectected datamızı oluşturmak için hazır bir method oluşturdum
    public Map<String, Object> expectedDataMethod(Integer userId, String title,Boolean completed) {


        Map<String,Object> expectedData = new HashMap<>();
        if (userId!=null) {
            expectedData.put("userId",userId);
        }
        if (title!=null) {
            expectedData.put("title",title);
        }
        if (completed!=null) {
            expectedData.put("completed",completed);
        }

        return expectedData;

    }

    /*
    Json'ı String'e çeviren reusable bir method olşuturduk yani expected data'yı string yapan method
     */

    public static String expectedDataInString(Integer userId, String title, Boolean completed){

        return "{ \"userId\": "+userId+", \"title\": \""+title+"\", \"completed\": "+completed+" }";

    }
}
