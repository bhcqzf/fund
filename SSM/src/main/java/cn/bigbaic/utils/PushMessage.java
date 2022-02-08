package cn.bigbaic.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.Properties;

public class PushMessage {
    public static String pushBark(String title,String message){
//        String url = "https:://api.day.app/FzzgGkRJntxwrZ2k3MSziD/" + title+"/"+ message;
        String url = getProperties() + "/" + title+"/"+ message;

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
    public static String getProperties() {
        String bark = null;
        Properties properties = new Properties();
//        InputStream in = PushMessage.class.getClassLoader().getResourceAsStream("conf/push.properties");
        String dir = System.getProperty("catalina.home") + File.separator + "config" + File.separator + "push.properties";
        try {
            FileInputStream fs = new FileInputStream(dir);
            InputStream in = new BufferedInputStream(fs);
            properties.load(in);
            String barkServer = properties.getProperty("barkServer");
            String barkSecert = properties.getProperty("barkSecert");
            bark = barkServer+ "/" + barkSecert;
//            System.out.println(barkServer);
//            System.out.println(barkSecert);
            System.out.println(bark);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bark;

    }
}
