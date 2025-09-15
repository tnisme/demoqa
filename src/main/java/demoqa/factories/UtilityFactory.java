package demoqa.factories;

import demoqa.utility.ActionUtility;
import demoqa.utility.AssertUtility;
import demoqa.utility.ReportUtility;
import demoqa.utility.WaitUtility;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;

public class UtilityFactory {

    private UtilityFactory() {
        // private constructor to prevent instantiation
    }

    private static final ThreadLocal<Map<Class<?>, Object>> UTILITIES =
            ThreadLocal.withInitial(HashMap::new);

    @SuppressWarnings("unchecked")
    private static <T> T getOrCreate(Class<T> clazz, Supplier<? extends T> creator) {
        Map<Class<?>, Object> map = UTILITIES.get();
        return (T) map.computeIfAbsent(clazz, k -> creator.get());
    }

    // alias methods
    public static WaitUtility waitUtil() {
        return getOrCreate(WaitUtility.class, () -> new WaitUtility(DriverManager.getDriver()));
    }

    public static ActionUtility actionUtil() {
        return getOrCreate(ActionUtility.class, () -> new ActionUtility(DriverManager.getDriver()));
    }

    public static void initReport(String suiteName) {
        getOrCreate(ReportUtility.class, () -> ReportUtility.create(suiteName));
    }

    public static ReportUtility reportUtil() {
        return (ReportUtility) UTILITIES.get().get(ReportUtility.class);
    }

    public static AssertUtility assertUtil() {
        return getOrCreate(AssertUtility.class, AssertUtility::new);
    }

    public static void clear() {
        Map<Class<?>, Object> map = UTILITIES.get();
        map.values().forEach(o -> {
            if (o instanceof AutoCloseable) {
                try { ((AutoCloseable)o).close(); } catch (Exception ignored) {}
            }
        });
        UTILITIES.remove();
    }
}

