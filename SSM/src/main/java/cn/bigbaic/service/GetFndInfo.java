package cn.bigbaic.service;

import cn.bigbaic.domain.Fund;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GetFndInfo {
    public Fund getFundInfo(String fundcode){
        Fund fund = new Fund();
        String url = "https://fundgz.1234567.com.cn/js/"+fundcode+".js";
        Map<String,String> map = new HashMap<>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request.Builder builder = new Request.Builder()
                .url(url);
        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
            String resText = response.body().string();
            //打印请求结果
//            System.out.println(resText);

            Pattern pattern = Pattern.compile("(jsonpgz\\()(.*?)(\\);)");

            Matcher matcher = pattern.matcher(resText);
            if (matcher.find()) {
                resText = matcher.group(2);
                //打印正则匹配结果
//                System.out.println(resText);
            }
            map = (Map<String, String>) JSONObject.parse(resText);
            fund.setFundcode(map.get("fundcode"));
            fund.setDwjz(Float.valueOf(map.get("dwjz")));
            fund.setGsz(Float.valueOf(map.get("gsz")));
            fund.setGszzl(Float.valueOf(map.get("gszzl")));
            fund.setJzrq(map.get("jzrq"));
            fund.setName(map.get("name"));
            if (map.get("gztime").split(" ").length > 1){
                fund.setGztime(map.get("gztime").split(" ")[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fund;
    }

    public void init(){

    }
}
