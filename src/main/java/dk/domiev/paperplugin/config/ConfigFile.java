package dk.domiev.paperplugin.config;

import org.bukkit.Bukkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.File;
import java.nio.file.Path;

/**
 * ConfigFile, use with an entity class with Configurate annotations.
 * https://github.com/SpongePowered/Configurate
 * @param <T>
 */
public class ConfigFile<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFile.class);
    private final File file;
    private final Class<T> type;
    private HoconConfigurationLoader loader;

    public ConfigFile(Class<T> type, File file) {
        this.type = type;
        this.file = file;
    }

    private void initConfig() {
        this.loader = HoconConfigurationLoader.builder()
                .file(file)
                .prettyPrinting(true)
                .build();

        saveConfig(loadConfig());
    }

    public void saveDefault() {
        saveConfig(loadConfig());
    }

    public T loadConfig() {
        try {
            CommentedConfigurationNode node = loader.load();
            return node.get(type);
        } catch (ConfigurateException e) {
            LOGGER.error("Failed to save configuration.", e);
            return null;
        }
    }

    public void saveConfig(T cfg) {
        try {
            CommentedConfigurationNode node = loader.load();
            node.set(type, cfg);
            loader.save(node);
        } catch (ConfigurateException e) {
            LOGGER.error("Failed to save configuration.", e);
        }
    }
}
