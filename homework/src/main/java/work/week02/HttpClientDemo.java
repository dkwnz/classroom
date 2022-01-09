package work.week02;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientDemo {
    public static void main(String[] args) {
        String result = httpGetUrl("http://localhost:8801/");
        System.out.println(result);
    }

    public static String httpGetUrl(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response1 = httpclient.execute(httpGet);) {
            HttpEntity httpEntity =  response1.getEntity();
            return EntityUtils.toString(httpEntity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
