package demoqa.utility;

import com.github.javafaker.Faker;
import demoqa.entity.Account;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DataTest {
    private static ThreadLocal<String> browser = new ThreadLocal<>();
    private static JSONObject environment, account;
    private static Faker faker = new Faker();


    public synchronized static void init() {
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "account.json");
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            account = new JSONObject(content);

            file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "environment.json");
            content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            environment = new JSONObject(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static JSONObject getConfig() {
        return environment;
    }

    public static Account getAdminAccount() {
        return new Account.Builder()
                .username(account.getString("username"))
                .password(account.getString("password"))
                .build();
    }

    public static Account getRandomAccount() {
        return new Account.Builder()
                .username(faker.name().username())
                .password(faker.internet().password())
                .build();
    }

    public static String getHomeURL() {
        return environment.getString("homeUrl");
    }

}
