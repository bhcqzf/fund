package cn.bigbaic.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PushMessage {
    public static String pushBark(String title,String message){
        String url = "https://api.day.app/FzzgGkRJntxwrZ2k3MSziD/" + title+"/"+ message;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request.Builder builder = new Request.Builder()
                .url(url);
        Request request = builder.build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String resText = response.body().string();
            System.out.println(resText);
            return resText;
        } catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
