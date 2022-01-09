package work.week02;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpDemo {
    public static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        String result = null;
        try {
            result = httpGetUrl("http://localhost:8801/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static String httpGetUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
