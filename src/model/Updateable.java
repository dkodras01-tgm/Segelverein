package model;

import java.sql.SQLException;

public interface Updateable {
	public boolean update() throws SQLException;
	public boolean insert() throws SQLException;
	public boolean delete() throws SQLException;
}