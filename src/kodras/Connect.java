package kodras;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
 
public class Connect {
 
    // Diese Eintraege werden zum 
    // Verbindungsaufbau benoetigt. 
    String hostname;
    String port;
    String dbname;
    String user;
    String password;
    public static Connection conn = null;
 
    /**
     * @param hostname
     * @param dbname
     * @param user
     * @param password
     */
    public Connect(String hostname, String dbname, String user, String password) {
    	this(hostname, "5432", dbname, user, password);
    }
    
    /**
     * @param hostname
     * @param port
     * @param dbname
     * @param user
     * @param password
     */
    public Connect(String hostname, String port, String dbname, String user, String password) {
 
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            System.out.println("Treiber wurde geladen.");
        } catch (Exception e) {
            System.err.println("Treiber konnte nicht geladen werden.");
            JOptionPane.showMessageDialog(null, "Driver konnte nicht geladen werden.");
        }
 
        try {
            String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbname;
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            System.out.println("Es wurde sich erfolgreich an der Datenbank:" + url + " angemeldet.");
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Fehler beim Verbindungsaufbau.");
        }
    }
    
    /**
     * @return int, Gibt die naechst moegliche Personennummer zurueck
     */
    public static int getNextID() {
    	if(conn==null) {
    		return -1;
    	} else {
    		try {
				Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet res = s.executeQuery("SELECT * FROM v_n;");
				res.first();
				return res.getInt("n");
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
    	}
    }
    
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}