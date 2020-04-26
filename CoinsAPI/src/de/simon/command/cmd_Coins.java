package de.simon.command;

//Imports
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import de.simon.coins.Coins;
import de.simon.utils.Coins_SQL;

public class cmd_Coins implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				int coins = Coins_SQL.getCoins(p.getUniqueId().toString());
				p.sendMessage(Coins.prefix + "Deine Coins §8» §e" + coins);
			}else if (args.length == 1 && p.isOp()) {
				Player pl = (Player) Bukkit.getOfflinePlayer(args[1]);
				int coins = Coins_SQL.getCoins(pl.getUniqueId().toString());
				p.sendMessage(Coins.prefix + pl.getName()+" Coins §8» §e" + coins);
			}else {
				p.sendMessage(Coins.prefix + "/coins");
				if(p.isOp()) {
				p.sendMessage(Coins.prefix + "/coins <Name>");
				}
			}
		}else {
			sender.sendMessage(Coins.prefix+ "§cDu bist die Konsole!");
		}
		
		return false;
	}

}
