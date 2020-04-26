package de.simon.coins;

//Imports
import java.io.IOException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.google.common.reflect.ClassPath;
import de.simon.command.cmd_Coins;
import de.simon.command.cmd_addCoins;
import de.simon.command.cmd_removeCoins;
import de.simon.command.cmd_setCoins;
import de.simon.utils.MySQL;
import de.simon.utils.Coins_SQL;
import de.simon.utils.FileManager;

public class Coins extends JavaPlugin {

	public static MySQL mysql;
	public static Coins_SQL coins;
	public static Coins main;
	public FileManager fm;

	public static String prefix = "§bPlayinfinity-Bewerbung §7» ";

	public static String host = "127.0.0.1";
	public static String Database = "Database";
	public static String user = "user";
	public static String password = "password";

	@Override
	public void onEnable() {

		coins = new Coins_SQL();
		main = this;
		mysql = new MySQL(host, Database, user, password);
		fm = new FileManager();
		fm.register();
		fm.saveCfg();
		register();
		ConnectMySQL();
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void register() {
        PluginManager pluginManager = getServer().getPluginManager();
        try {
            for (ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader()).getTopLevelClasses("de.simon.listener")) {
                Class clazz = Class.forName(classInfo.getName());

                if (Listener.class.isAssignableFrom(clazz)) {
                    pluginManager.registerEvents((Listener) clazz.newInstance(), this);
                }
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
        }
		getCommand("coins").setExecutor(new cmd_Coins());
		getCommand("setcoins").setExecutor(new cmd_setCoins());
		getCommand("addcoins").setExecutor(new cmd_addCoins());
		getCommand("removecoins").setExecutor(new cmd_removeCoins());
	}

	private void ConnectMySQL() {
		mysql = new MySQL(host, Database, user, password);
		mysql.update("CREATE TABLE IF NOT EXISTS Coins(UUID varchar(64), coins int);");
	}

}
