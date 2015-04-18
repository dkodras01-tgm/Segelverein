package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import kodras.Connect;

public class Person implements Updateable {

	private int key = -1;
	private String name;
	private Date geburtsdatum;

	@SuppressWarnings("deprecation")
	public Person() {
		this(0, "Max Mustermann", new Date(70, 1, 1));
	}

	public Person(ResultSet ress) throws SQLException {
		this.key = ress.getInt("key");
		this.name = ress.getString("nname");
		this.geburtsdatum = ress.getDate("geburtsdatum");
	}

	public Person(ResultSet ress, int key) throws SQLException {
		ress.first();
		do {
			if (ress.getInt("key") == key) {
				this.key = ress.getInt("key");
				this.name = ress.getString("name");
				this.geburtsdatum = ress.getDate("geburtsdatum");
				break;
			}
		} while (ress.next() == true);
		if (this.key == -1) {
			throw new SQLException("Person nicht vorhanden");
		}
	}

	public Person(int key, String name, Date geburtsdatum) {
		super();
		this.key = key;
		this.name = name;
		this.geburtsdatum = geburtsdatum;
	}

	public String toString() {
		return "Person [key=" + key + ", name=" + name + ", geburtsdatum="
				+ geburtsdatum + "]";
	}

	/**
	 * Aendert den Datensatz, mit den gesetzten Parametern
	 * 
	 * @return true wenn transaktion erfolgreich durchgefuehrt wurde
	 */
	@Override
	public boolean update() throws SQLException {
		String queue = "UPDATE person SET key='" + key + "', name='" + name
				+ "', geburtsdatum='"
				+ new java.sql.Date(geburtsdatum.getTime()).toString()
				+ "' WHERE key=" + key + ";";
		System.out.println(queue);
		if (Connect.conn == null) {
			return false;
		} else {
			boolean temp = false;
			try {
				Statement sm = Connect.conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				temp = sm.execute(queue);
				sm.close();
				Connect.conn.commit();
			} catch (SQLException e) {
				System.out
						.println("Person updaten gescheitert. Transaktion abgebrochen.");
				Connect.conn.rollback();
			}
			return temp;
		}
	}

	/**
	 * Fuegt den Datensatz, mit den gesetzten Parametern ein
	 * 
	 * @return true wenn transaktion erfolgreich durchgefuehrt wurde
	 */
	@Override
	public boolean insert() throws SQLException {
		if (key == 0)
			key = Connect.getNextID();
		String queue = "INSERT INTO person (key, name, geschlecht,"
				+ " geburtsdatum, gehalt, position, von, bis) VALUES (" + key
				+ ", '" + name + "','"
				+ new java.sql.Date(geburtsdatum.getTime()).toString() + "');";
		if (Connect.conn == null) {
			return false;
		} else {
			boolean temp = false;
			try {
				Statement sm = Connect.conn.createStatement(
						ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				temp = sm.execute(queue);
				Connect.conn.commit();
				sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out
						.println("Person einfuegen gescheitert. Transaktion abgebrochen.");
				Connect.conn.rollback();
			}
			return temp;
		}
	}

	/**
	 * Loescht den Datensatz, mit der gesetzten ID
	 * 
	 * @return true wenn transaktion erfolgreich durchgefuehrt wurde
	 */
	@Override
	public boolean delete() throws SQLException {
		String queue = "DELETE FROM person WHERE key=" + key + ";";
		if (Connect.conn == null) {
			return false;
		} else {
			boolean temp = false;
			try {
				Statement sm = Connect.conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				temp = sm.execute(queue);
				Connect.conn.commit();
				sm.close();
			} catch (SQLException e) {
				System.out
						.println("Person loeschen gescheitert. Transaktion abgebrochen.");
				e.printStackTrace();
				Connect.conn.rollback();
			}
			return temp;
		}
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
}