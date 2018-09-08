package com.water.user.springboot.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.water.user.springboot.document.NotificationData;
import com.water.user.springboot.document.NotificationRequestModel;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.lang.reflect.Type;


public class NotificationUtil {
	
	public static void sendNotification() {
		
		try {
	
	DefaultHttpClient httpClient = new DefaultHttpClient();
    HttpPost postRequest = new HttpPost("https://fcm.googleapis.com/fcm/send");

    NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
    NotificationData notificationData = new NotificationData();

    notificationData.setDetail("Your sign up process has been authorized. You can complete the registration process now");
    notificationData.setTitle("Wahter - Sign up authorized");
    notificationData.setChannel_id("MY_CHANNEL_ID_NEW");
    notificationData.setTimestamp("2018-12-01 12:12:05");
    notificationRequestModel.setData(notificationData);
    notificationRequestModel.setTo("cqhwOURWZZU:APA91bEaWyp-4Sejo8YaKfvSbi4aJ0p14wNyFxY_sEG4_cyDpcyLjnz3jvKVu2IdvxKOpcZFF7prEhPbH4DXWJ240m1fAwuco7FtT13wZ1UK8vE121FSQweR3-1B9o9zn59U7xymby0N");
    
    Gson gson = new Gson();
    Type type = new TypeToken<NotificationRequestModel>() {
    }.getType();

    String json = gson.toJson(notificationRequestModel, type);

    StringEntity input = new StringEntity(json);
    input.setContentType("application/json");

    // server key of your firebase project goes here in header field.
    // You can get it from firebase console.

    postRequest.addHeader("Authorization", "key=AAAAiHq7BUE:APA91bHVe2rcytVysPF7TW_275ykkbQnhZU881b1vC07fEY8N_qhgrVLDfqd7Qc23eC2MwI-cs4ZH2klFxX52VL0NISQ92MTn3sjG_Cib8hbfpsopF6OLiGg35OAI1T3ou44ZjOhY7Kg");
    postRequest.addHeader("Content-Type", "application/json");
    postRequest.setEntity(input);

    System.out.println("reques:" + json);

    HttpResponse response = httpClient.execute(postRequest);

    if (response.getStatusLine().getStatusCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
                + response.getStatusLine().getStatusCode());
    } else if (response.getStatusLine().getStatusCode() == 200) {

        System.out.println("response:" + EntityUtils.toString(response.getEntity()));
       
    }
		}catch (Exception e) {
			e.printStackTrace();
		}
}


}
