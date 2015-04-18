package model;

import java.sql.SQLException;

public interface Updateable {
	
	/**
	 * Aendert den Datensatz, mit den gesetzten Parametern
	 * 
	 * @return true wenn transaktion erfolgreich durchgefuehrt wurde
	 */
	public boolean update() throws SQLException;
	
	/**
	 * Fuegt den Datensatz, mit den gesetzten Parametern ein
	 * 
	 * @return true wenn transaktion erfolgreich durchgefuehrt wurde
	 */
	public boolean insert() throws SQLException;
	
	/**
	 * Loescht den Datensatz, mit der gesetzten ID
	 * 
	 * @return true wenn transaktion erfolgreich durchgefuehrt wurde
	 */
	public boolean delete() throws SQLException;
}