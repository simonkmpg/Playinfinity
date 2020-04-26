package de.simon.utils;

//Imports
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import de.simon.coins.Coins;

public class FileManager {

	File file = new File("plugins/" + Coins.main.getName(), "SQLDaten.yml");
	FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public void register() {
		cfg.options().copyDefaults(true);
		cfg.addDefault("host", Coins.host);
		cfg.addDefault("database", Coins.Database);
		cfg.addDefault("user", Coins.user);
		cfg.addDefault("password", Coins.password);
		saveCfg();
		Coins.host = cfg.getString("host");
		Coins.Database = cfg.getString("database");
		Coins.user = cfg.getString("user");
		Coins.password = cfg.getString("password");

	}

	public void saveCfg() {
		try {
			cfg.save(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
