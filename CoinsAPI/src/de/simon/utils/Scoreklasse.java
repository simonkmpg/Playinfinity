package de.simon.utils;

//Imports
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class Scoreklasse {
	
	  
	  public static void sendLobbyScoreboard(Player p)
	  { 
		
	    Scoreboard sb = new Scoreboard();
	    ScoreboardObjective obj = sb.registerObjective("§bPlayinfinity.net", IScoreboardCriteria.b);
	    
	    PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
	    PacketPlayOutScoreboardObjective deletePacket = new PacketPlayOutScoreboardObjective(obj, 1);
	    
	    PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
	    
	    obj.setDisplayName("§bPlayinfinity.net");
	    
	    ScoreboardScore a15 = new ScoreboardScore(sb, obj, "§0");
	    ScoreboardScore a14 = new ScoreboardScore(sb, obj, "§fCoins:");
	    ScoreboardScore a13 = new ScoreboardScore(sb, obj, "§7➥ §a"+Coins_SQL.getCoins(p.getUniqueId().toString()));
	    ScoreboardScore a12 = new ScoreboardScore(sb, obj, "§b");
	    
	    a12.setScore(4);
	    a13.setScore(3);
	    a14.setScore(2);
	    a15.setScore(1);
	    
	    PacketPlayOutScoreboardScore pa4 = new PacketPlayOutScoreboardScore(a12);
	    PacketPlayOutScoreboardScore pa3 = new PacketPlayOutScoreboardScore(a13);
	    PacketPlayOutScoreboardScore pa2 = new PacketPlayOutScoreboardScore(a14);
	    PacketPlayOutScoreboardScore pa1 = new PacketPlayOutScoreboardScore(a15);
	    
	    sendPacket(p, deletePacket);
	    sendPacket(p, createPacket);
	    sendPacket(p, display);
	    
	    sendPacket(p, pa4);
	    sendPacket(p, pa3);
	    sendPacket(p, pa2);
	    sendPacket(p, pa1);
	  }
	 
	  
	  @SuppressWarnings("rawtypes")
	private static void sendPacket(Player p, Packet packet)
	  {
	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	  }
	}
