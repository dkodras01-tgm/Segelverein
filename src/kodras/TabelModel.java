package kodras;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Boot;


public class TabelModel extends AbstractTableModel {

	private static final long serialVersionUID = -5360737563059338582L;

	private DatabaseList<Boot> boot;
	private static final String[] COLOUM_NAMES = new String[] {
			"ID", "Name", "Personen", "Tiefgang"
	};

	public TabelModel(DatabaseList<Boot> boot) {
		super();
		this.boot = boot;
	}

	@Override
	public int getColumnCount() {
		return COLOUM_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return boot.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Boot boot = this.boot.get(arg0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		switch (arg1) {
		case 0:
			return boot.getID();
		case 1:
			return boot.getName();
		case 2:
			return boot.getPersonen();
		case 3:
			return boot.getTiefgang();
		}
		return null;
	}

	@Override
	public String getColumnName(int arg0) {
		return COLOUM_NAMES[arg0];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return arg1 > 0;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		try {
			Boot boot = this.boot.get(arg1);
			String value = arg0.toString();
			switch (arg2) {
			case 1:
				boot.setName(value);
				break;
			case 2:
				boot.setPersonen(Integer.parseInt(value));
				break;
			case 3:
				boot.setTiefgang(Double.parseDouble(value));
				break;
			}
			boot.update();
		} catch (Exception e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Fehler bei eingabe!\n" + e.getMessage());
		}
	}

	/**
	 * Ueberprueft die Gueltigkeit eines Datums
	 * 
	 * @param value
	 * @return
	 */
	public static Date parseDate(String value) {
		SimpleDateFormat[] formats = new SimpleDateFormat[] {
				new SimpleDateFormat("dd.MM.yyyy"),
				new SimpleDateFormat("dd-MM-yyy"),
				new SimpleDateFormat("dd/MM/yyy"),
				new SimpleDateFormat("yyyy-MM-dd"),
				new SimpleDateFormat("yyyy/MM/dd")};
		for (SimpleDateFormat sdf : formats) {
			try {
				return sdf.parse(value);
			} catch (ParseException e) {
			}
		}
		throw new RuntimeException("Kein gueltiges Datumsformat!");
	}
}