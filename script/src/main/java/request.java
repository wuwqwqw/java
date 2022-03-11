import okhttp3.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class request {
    public static void main(String[] args) throws IOException {
        String pathname = "D:\\process.txt";
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\r\n    \"q\": \""+line+"\",\r\n    \"bid\": \"5\",\r\n    \"hid\": \"REV01\",\r\n    \"lat\": \"39.98598833333333\",\r\n    \"lon\": \"116.3041375\",\r\n    \"uid\": \"80099\",\r\n    \"vid\": \"-1\",\r\n    \"vin\": \"L1NSPGHBXLA000423\",\r\n    \"city\": \"北京市\",\r\n    \"sign\": \"70541c60c28b8dfdb65609240c15ac55\",\r\n    \"app_v\": \"V9.9.9.999\",\r\n    \"model\": \"1\",\r\n    \"msgId\": \"e6f596e90b7a3c5e\",\r\n    \"app_id\": \"xmart:appid:002\",\r\n    \"params\": \"{\\\"remainderRange\\\":\\\"618\\\"}\",\r\n    \"status\": \"wait\",\r\n    \"msgType\": \"req\",\r\n    \"pre_cmd\": \"\",\r\n    \"recordId\": \"c51ce947-b1c7-4d88-9690-11f7700ae65e:da9244d3d3cb4e0998d1e1021011e0ea\",\r\n    \"sceneIds\": [\"com.xiaopeng.musicradio-V1.4.0_20210420070954-SearchResultSong\"],\r\n    \"speaking\": \"false\",\r\n    \"speech_v\": \"2.8.0\",\r\n    \"timestamp\": \"1619507086205\",\r\n    \"active_app\": \"com.xiaopeng.musicradio\",\r\n    \"pre_intent\": \"music_search_play\",\r\n    \"active_page\": \"main\",\r\n    \"switch_data\": \"{\\\"sound_location\\\":\\\"1\\\",\\\"dialogStartReason\\\":\\\"wakeup.major\\\",\\\"soundZoneMode\\\":\\\"0\\\"}\",\r\n    \"original_text\": \"打开空调\",\r\n    \"hardwareId\":\"123123\",\r\n    \"continueStatus\":\"start\"\r\n  }");
                Request request = new Request.Builder()
                        .url("http://127.0.0.1:18081/process")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
