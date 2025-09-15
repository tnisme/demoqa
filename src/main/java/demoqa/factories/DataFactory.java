package demoqa.factories;

import com.github.javafaker.Faker;
import demoqa.entity.Account;
import demoqa.utility.JsonUtility;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataFactory {

    private static final Faker faker = new Faker();
    private static final Map<String, JSONObject> cache = new ConcurrentHashMap<>();

    private DataFactory() {
        // private constructor to prevent instantiation
    }

    private static JSONObject getJson(String fileName) {
        return cache.computeIfAbsent(fileName,
                f -> JsonUtility.readJson("data/" + f));
    }

    public static Account getAdminAccount() {
        JSONObject account = getJson("account.json");
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
        JSONObject environment = getJson("environment.json");
        return environment.getString("homeUrl");
    }
}
