package de.metaphoriker.pathetic;

import de.metaphoriker.pathetic.bukkit.listeners.ChunkInvalidateListener;
import de.metaphoriker.pathetic.platform.Initializer;
import de.metaphoriker.pathetic.platform.Listener;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import de.metaphoriker.pathetic.util.ErrorLogger;

@UtilityClass
@Slf4j
public class Pathetic {

  private static final String PROPERTIES_FILE = "pathetic.properties";

  private static final Set<Runnable> SHUTDOWN_LISTENERS = new HashSet<>();

  private static Initializer instance;
  @Getter private static String modelVersion;

  /**
   * @throws IllegalStateException If an attempt is made to initialize more than 1 time
   */
  public static void initialize(Initializer javaPlugin) {

    if (instance != null) throw ErrorLogger.logFatalError("Can't be initialized twice");

    instance = javaPlugin;

    loadModelVersion();

    if (checkMinecraftVersion(16, 0, javaPlugin.majorMinecraftVersion(), javaPlugin.minorMinecraftVersion())) {
      log.warn(
          "Pathetic is currently running in a version older than or equal to 1.16. "
              + "Some functionalities might not be accessible, such as accessing the BlockState of blocks.");
    }

    log.debug("Pathetic v{} initialized", modelVersion);
  }

  public static void shutdown() {
    SHUTDOWN_LISTENERS.forEach(Runnable::run);
    SHUTDOWN_LISTENERS.clear();

    instance = null;
    log.debug("Pathetic shutdown");
  }

  public static boolean isInitialized() {
    return instance != null;
  }

  public static Initializer getPluginInstance() {
    return instance;
  }

  public static void addShutdownListener(Runnable listener) {
    SHUTDOWN_LISTENERS.add(listener);
  }

  private static void loadModelVersion() {
    try (InputStream inputStream =
        Pathetic.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
      Properties properties = new Properties();
      properties.load(inputStream);

      modelVersion = properties.getProperty("model.version");
    } catch (IOException e) {
      throw ErrorLogger.logFatalError("Error loading model version", e);
    }
  }

  private boolean checkMinecraftVersion(int minimumMajor, int minimumMinor, int major, int minor) {

    if (major < minimumMajor) return false;
    return minor >= minimumMinor;

  }

  public static Listener getListener() {

    return new ChunkInvalidateListener();

  }
}
