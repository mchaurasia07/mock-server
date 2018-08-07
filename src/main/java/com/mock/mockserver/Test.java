package com.mock.mockserver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayank.chaurasia on 02-08-2018.
 */
public class Test {

  public static void main1 (String args[]){
    String jsonString = "{\"id\":2245222,\"from\":{\"id\":284145,\"email\":\"akshay.mandke@globant.com\"," +
        "\"fullName\":\"Akshay Mandke\",\"identification\":\"akshay.mandke@globant.com\",\"profileImageId\":204851," +
        "\"profileImageCode\":\"jqvhjchsrycuybqnashw_jpg\",\"uid\":\"7bf7f53037227a0efaf8b712662dba66\"}," +
        "\"to\":{\"id\":313853,\"email\":\"madhurima.soni@globant.com\",\"fullName\":\"Madhurima Soni\"," +
        "\"identification\":\"madhurima.soni@globant.com\",\"profileImageId\":159529," +
        "\"profileImageCode\":\"wvzlaglqvvwdyiwjunim\",\"uid\":\"2ec5f19582bb3cca981184ea17b8e69a\"}," +
        "\"starMeUpOrganizationStar\":{\"id\":33,\"name\":\"Excellence in your Work\",\"imageId\":207548," +
        "\"imageCode\":\"kzsgbisyuqvfbofdmygz_png\",\"backgroundColor\":\"#00D0F5\"},\"date\":\"Aug 02, 2018 05:59:24" +
        " AM\",\"lastUpdate\":\"Aug 02, 2018 05:59:24 AM\",\"notes\":\"Excellent work in testing..Keep up the Awesome" +
        " work ..\",\"enabled\":true,\"typeStar\":\"C\",\"comments\":[{\"id\":3031598,\"type\":\"LIKE\"," +
        "\"dateComment\":\"Aug 02, 2018 06:00:12 AM\",\"enabled\":true,\"imageCode\":\"\"}],\"hashtagDTOs\":[]," +
        "\"isEditable\":false,\"isShareable\":false,\"quantityUsersKudos\":0,\"quantityNotShowed\":0}";

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      List<Model> modelList = new ArrayList();
      modelList.add(new Model(10001, 20001));
      modelList.add(new Model(10001, 20002));
      modelList.add(new Model(10001, 20003));
      modelList.add(new Model(10001, 20004));
      modelList.add(new Model(10001, 20005));
      modelList.add(new Model(10001, 20006));
      modelList.add(new Model(10001, 20007));
      modelList.add(new Model(10001, 20008));
      modelList.add(new Model(10001, 20009));
      modelList.add(new Model(10001, 20010));

      modelList.add(new Model(10002, 20001));
      modelList.add(new Model(10002, 20002));
      modelList.add(new Model(10002, 20003));
      modelList.add(new Model(10002, 20004));
      modelList.add(new Model(10002, 20005));
      modelList.add(new Model(10002, 20006));
      modelList.add(new Model(10002, 20007));
      modelList.add(new Model(10002, 20008));
      modelList.add(new Model(10002, 20009));
      modelList.add(new Model(10002, 20010));

      modelList.add(new Model(10003, 20001));
      modelList.add(new Model(10003, 20002));
      modelList.add(new Model(10003, 20003));
      modelList.add(new Model(10003, 20004));
      modelList.add(new Model(10003, 20005));

      modelList.add(new Model(10004, 20006));
      modelList.add(new Model(10004, 20007));
      modelList.add(new Model(10004, 20008));
      modelList.add(new Model(10004, 20009));
      modelList.add(new Model(10004, 20010));

      modelList.add(new Model(20001, 10001));
      modelList.add(new Model(20001, 10002));
      modelList.add(new Model(20001, 10003));
      modelList.add(new Model(20001, 10004));
      modelList.add(new Model(20001, 10005));

      modelList.add(new Model(20002, 10001));
      modelList.add(new Model(20002, 10002));
      modelList.add(new Model(20002, 10003));
      modelList.add(new Model(20002, 10004));
      modelList.add(new Model(20002, 10005));

      modelList.add(new Model(20003, 10001));
      modelList.add(new Model(20003, 10002));
      modelList.add(new Model(20003, 10003));
      modelList.add(new Model(20003, 10004));
      modelList.add(new Model(20003, 10005));
      modelList.add(new Model(20003, 10006));
      modelList.add(new Model(20003, 10007));
      modelList.add(new Model(20003, 10008));
      modelList.add(new Model(20003, 10009));
      modelList.add(new Model(20003, 10010));

      modelList.add(new Model(20004, 10001));
      modelList.add(new Model(20004, 10002));
      modelList.add(new Model(20004, 10003));
      modelList.add(new Model(20004, 10004));
      modelList.add(new Model(20004, 10005));
      modelList.add(new Model(20004, 10006));
      modelList.add(new Model(20004, 10007));
      modelList.add(new Model(20004, 10008));
      modelList.add(new Model(20004, 10009));
      modelList.add(new Model(20004, 10010));

      JsonNode jsonNode = objectMapper.readTree(jsonString);
      long id = 2245222;
      for (Model model : modelList) {
        id = ++id;
        ((ObjectNode) jsonNode).put("id", id);
        ObjectNode from = (ObjectNode) ((ObjectNode) jsonNode).get("from");
        from.put("id", model.getFromId());
        from.put("email", model.getFromEmail());
        from.put("fullName", model.getFromFullName());

        ObjectNode to = (ObjectNode) ((ObjectNode) jsonNode).get("to");
        to.put("id", model.getToId());
        to.put("email", model.getToEmail());
        to.put("fullName", model.getToFullName());
        System.out.println(jsonNode.toString());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
