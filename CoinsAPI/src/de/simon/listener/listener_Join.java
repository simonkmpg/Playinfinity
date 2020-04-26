package de.simon.listener;

//Imports
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.simon.utils.Coins_SQL;
import de.simon.utils.Scoreklasse;

public class listener_Join implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Coins_SQL.createPlayer(p.getUniqueId().toString());
		Scoreklasse.sendLobbyScoreboard(p);
	}

}
