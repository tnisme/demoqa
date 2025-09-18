package demoqa.factories;

import demoqa.utility.ActionUtility;
import demoqa.utility.AssertUtility;
import demoqa.utility.ReportUtility;
import demoqa.utility.WaitUtility;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Centralized factory for managing utility instances.
 * Uses ThreadLocal to ensure thread safety in parallel test execution.
 */
public class UtilityFactory {

    private UtilityFactory() {
        // private constructor to prevent instantiation
    }

    private static final ThreadLocal<Map<Class<?>, Object>> UTILITIES =
            ThreadLocal.withInitial(HashMap::new);

    /**
     * Generic method to retrieve or create a utility instance.
     * @param clazz
     * @param creator
     * @return
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    private static <T> T getOrCreate(Class<T> clazz, Supplier<? extends T> creator) {
        Map<Class<?>, Object> map = UTILITIES.get();
        return (T) map.computeIfAbsent(clazz, k -> creator.get());
    }

    /**
     * Retrieves or creates a WaitUtility instance.
     *
     * @return A WaitUtility instance for the current thread
     */
    public static WaitUtility waitUtil() {
        return getOrCreate(WaitUtility.class, () -> new WaitUtility(DriverManager.getDriver()));
    }

    /**
     * Retrieves or creates an ActionUtility instance.
     *
     * @return An ActionUtility instance for the current thread
     */
    public static ActionUtility actionUtil() {
        return getOrCreate(ActionUtility.class, () -> new ActionUtility(DriverManager.getDriver()));
    }

    /**
     * Initializes the ReportUtility with the given suite name.
     * Must be called before using reportUtil().
     *
     * @param suiteName The name of the test suite
     */
    public static void initReport(String suiteName) {
        getOrCreate(ReportUtility.class, () -> ReportUtility.create(suiteName));
    }

    /**
     * Retrieves the ReportUtility instance.
     * initReport() must be called before this method.
     *
     * @return The ReportUtility instance for the current thread
     */
    public static ReportUtility reportUtil() {
        return (ReportUtility) UTILITIES.get().get(ReportUtility.class);
    }

    /**
     * Retrieves or creates an AssertUtility instance.
     *
     * @return An AssertUtility instance for the current thread
     */
    public static AssertUtility assertUtil() {
        return getOrCreate(AssertUtility.class, AssertUtility::new);
    }

    /**
     * Clears all utility instances for the current thread.
     * Closes any AutoCloseable utilities if applicable.
     */
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

