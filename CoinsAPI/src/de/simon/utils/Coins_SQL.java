package de.simon.utils;

//Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import de.simon.coins.Coins;

public class Coins_SQL {

	public static boolean playerExists(String uuid) {

		try {
			ResultSet rs = Coins.mysql.query("SELECT * FROM Coins WHERE UUID= '" + uuid + "'");

			if (rs.next()) {

				return rs.getString("UUID") != null;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void createPlayer(String uuid) {

		if (!(playerExists(uuid))) {
			Coins.mysql.update("INSERT INTO Coins(UUID, coins) VALUES ('" + uuid + "', '0');");
		}

	}

	public static Integer getCoins(String uuid) {
		Integer i = 0;

		if (playerExists(uuid)) {

			try {
				ResultSet rs = Coins.mysql.query("SELECT coins FROM Coins WHERE UUID= '" + uuid + "'");
				if ((!rs.next()) || (Integer.valueOf(rs.getInt("coins")) == null))
					;

				i = rs.getInt("coins");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			createPlayer(uuid);
			getCoins(uuid);
		}

		return i;
	}

	public static void setCoins(String uuid, Integer coins) {
		if (playerExists(uuid)) {
			Coins.mysql.update("UPDATE Coins SET coins= '" + coins + "'WHERE UUID= '" + uuid + "';");

		} else {
			createPlayer(uuid);
			setCoins(uuid, coins);
		}

	}

	public static void addCoins(String uuid, Integer coins) {
		if (playerExists(uuid)) {
			setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() + coins.intValue()));

		} else {
			createPlayer(uuid);
			addCoins(uuid, coins);
		}
	}

	public static void removeCoins(String uuid, Integer coins) {
		if (playerExists(uuid)) {
			setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() - coins.intValue()));

		} else {
			createPlayer(uuid);
			removeCoins(uuid, coins);
		}
	}


}
