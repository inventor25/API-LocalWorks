package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {

   public Map<String, String> reqresUsersSetUp(String name,String job) {

        Map<String,String> reqresUsers = new HashMap<>();

        if (name!=null) {
            reqresUsers.put("name",name);
        }
        if (job!=null) {
            reqresUsers.put("job",job);
        }
        return reqresUsers;
    }

}
