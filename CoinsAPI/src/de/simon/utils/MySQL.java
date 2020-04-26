package de.simon.utils;

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.simon.coins.Coins;

public class MySQL {

	//MySQL Daten
	private String HOST = Coins.host;
	private String DATABASE = Coins.Database;
	private String USER = Coins.user;
	private String PASSWORD = Coins.password;

	private Connection connection;

	public MySQL(String host, String database, String user, String password) {
		this.HOST = host;
		this.DATABASE = database;
		this.USER = user;
		this.PASSWORD = password;

		connect();
	}

	public void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
			System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
		} catch (SQLException e) {
			System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
			}
		} catch (SQLException e) {
			System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
		}
	}

	public void update(String qry) {
		try {
			if (connection.isClosed()) {
				connect();
			}
			Statement st = connection.createStatement();
			st.executeUpdate(qry);
			st.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public ResultSet query(String qry) {
		ResultSet rs = null;

		try {
			if (connection.isClosed()) {
				connect();
			}
			Statement st = connection.createStatement();
			st.executeQuery(qry);
			rs = st.getResultSet();
			return rs;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return rs;
	}
}
