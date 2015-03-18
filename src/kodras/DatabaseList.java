package kodras;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.Updateable;

public class DatabaseList<T extends Updateable> extends ArrayList<T>{

	private static final long serialVersionUID = 8725567662791779308L;

	public DatabaseList() {
		super();
	}

	public DatabaseList(Collection<? extends T> arg0) {
		super(arg0);
	}

	public DatabaseList(int arg0) {
		super(arg0);
	}

	@Override
	public void add(int arg0, T arg1) {
		try {
			arg1.insert();
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		super.add(arg0, arg1);
	}

	@Override
	public boolean add(T arg0) {
		return this.add(arg0, true);
	}
	
	public boolean add(T arg0, boolean insert) {
		if (insert) {
			try {
				arg0.insert();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return super.add(arg0);
	}

	@Override
	public T remove(int arg0) {
		try {
			get(arg0).delete();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.remove(arg0);
	}

	@Override
	public boolean remove(Object arg0) {
		try {
			((Updateable)arg0).delete();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.remove(arg0);
	}

	@Override
	public T set(int arg0, T arg1) {
		try {
			arg1.update();
	} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.set(arg0, arg1);
	}
}