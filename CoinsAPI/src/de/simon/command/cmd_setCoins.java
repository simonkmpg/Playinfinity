package de.simon.command;

//Imports
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.simon.coins.Coins;
import de.simon.utils.Coins_SQL;

public class cmd_setCoins implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
				if(args.length == 2) {
					Player pl = (Player) Bukkit.getOfflinePlayer(args[1]);
					int coins = Integer.parseInt(args[2]);
					Coins_SQL.setCoins(pl.getUniqueId().toString(), coins);
					p.sendMessage(Coins.prefix+"Du hast die §eCoins §von §c"+pl.getName()+" auf §e"+coins+" §7gesetzt.");
				}else {
					p.sendMessage(Coins.prefix+"/removecoins <Spieler> <Coins>");
				}
			}else {
				p.sendMessage(Coins.prefix+"§cDazu hast du keine Berechtigung.");
			}
		}else {
			sender.sendMessage(Coins.prefix+"Du bist die Konsole.");
		}
		return false;
	}

	
	
}
