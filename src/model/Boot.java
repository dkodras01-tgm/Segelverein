package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kodras.Connect;

public class Boot implements Updateable {

	private int id = -1, personen;
	private String name;
	private double tiefgang;

	public Boot() {
		this(0, "Silver Turtle Express", 6, 8.0);
	}

	public Boot(ResultSet ress) throws SQLException {
		this.id = ress.getInt("id");
		this.name = ress.getString("name");
		this.personen = ress.getInt("personen");
		this.tiefgang = ress.getDouble("tiefgang");
	}

	public Boot(ResultSet ress, int id) throws SQLException {
		ress.first();
		do {
			if (ress.getInt("id") == id) {
				this.id = ress.getInt("id");
				this.name = ress.getString("name");
				this.personen = ress.getInt("personen");
				this.tiefgang = ress.getDouble("tiefgang");
				break;
			}
		} while (ress.next() == true);
		if (this.id == -1) {
			throw new SQLException("Boot nicht vorhanden");
		}
	}

	public Boot(int id, String name, int personen, double tiefgang) {
		super();
		this.id = id;
		this.name = name;
		this.personen = personen;
		this.tiefgang = tiefgang;
	}

	public String toString() {
		return "Boot [id=" + id + ", name=" + name + ", personen=" + personen
				+ ", tiefgang=" + tiefgang + "]";
	}
	
	/**
	 * Aendert den Datensatz, mit den gesetzten Parametern
	 * 
	 * @return true wenn transaktion erfolgreich durchgefuehrt wurde
	 */
	@Override
	public boolean update() throws SQLException {
		String queue = "UPDATE boot SET id='" + id + "', name='" + name
				+ "', personen='" + personen + "', tiefgang='" + tiefgang
				+ "' WHERE id=" + id + ";";
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
						.println("Boot updaten gescheitert. Transaktion abgebrochen.");
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
		if (id == 0)
			id = Connect.getNextID();
		String queue = "INSERT INTO boot (id, name, personen, tiefgang) VALUES ("
				+ id + ", '"
				+ name + "','"
				+ personen + "', '"
				+ tiefgang
				+ "');";
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
//				e.printStackTrace();
				System.out
						.println("Boot einfuegen gescheitert. Transaktion abgebrochen.");
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
		String queue = "DELETE FROM boot WHERE id=" + id + ";";
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
						.println("Boot loeschen gescheitert. Transaktion abgebrochen.");
				e.printStackTrace();
				Connect.conn.rollback();
			}
			return temp;
		}
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersonen() {
		return personen;
	}

	public void setPersonen(int personen) {
		this.personen = personen;
	}

	public double getTiefgang() {
		return tiefgang;
	}

	public void setTiefgang(double tiefgang) {
		this.tiefgang = tiefgang;
	}
}