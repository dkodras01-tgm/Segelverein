package kodras;


import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.Boot;


public class Segelverein extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3995709385881974885L;
	
	private JFrame frame;
	private Connect connect;
	private DatabaseList<Boot> boot;
	private JTable table;
	private String spaltenName="";
	private boolean aufsteigend=true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Segelverein window = new Segelverein();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Segelverein() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		JMenuItem mntmNeueVerbindung = new JMenuItem("Neue Verbindung");
		mntmNeueVerbindung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DatabaseDialog((host, datenbank, benutzer, passwort) -> {
					connect = new Connect(host, datenbank, benutzer, passwort);
					reloadData();
				});
			}
		});
		mnDatei.add(mntmNeueVerbindung);
		
		JMenuItem mntmAktualisieren = new JMenuItem("Aktualisieren");
		mntmAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reloadData();
			}
		});
		mnDatei.add(mntmAktualisieren);
		
		JMenuItem mntmFilter = new JMenuItem("Filter");
		mntmFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FilterDialog fd = new FilterDialog();
				
				System.out.println(fd.getValue());
			}
		});
		mnDatei.add(mntmFilter);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenu mnHilfe = new JMenu("Hilfe");
		menuBar.add(mnHilfe);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHilfe.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showInfo();
			}
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		table = new JTable();
		tabbedPane.addTab("Boot", null, new JScrollPane(table), null);
		table.getTableHeader().addMouseListener(new TableHeaderMouseListener(table, this::changeFilter));
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
	}
	
	public void changeFilter(int column) {
		System.out.println(column);
		this.boot = new DatabaseList<>();
		String spaltenNameAlt = spaltenName;
		switch(column) {
			case 0:
				spaltenName = "id";
				break;
			case 1:
				spaltenName = "name";
				break;
			case 2:
				spaltenName = "personen";
				break;
			case 3:
				spaltenName = "tiefgang";
				break;
			default: return;
		}
		
		if(spaltenName.equals(spaltenNameAlt)) {
			aufsteigend = !aufsteigend;
		} else {
			aufsteigend = true;
		}
		
		try {
			Statement statement = Connect.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = statement.executeQuery("SELECT * FROM boot ORDER BY " + spaltenName + (aufsteigend?" ASC":" DESC"));
			rs.first();
			do {
				Boot boot = new Boot(rs);
				this.boot.add(boot, false);
			}
			while(rs.next()==true);
			rs.close();
		} catch (SQLException e) {
			
		}
		table.setModel(new TabelModel(boot));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	private void showInfo() {
		JOptionPane.showMessageDialog(this, "Created by Dominik Kodras\n4AHIT TGM");
	}
	
	private void reloadData () {
		if(connect==null) {
			JOptionPane.showMessageDialog(null, "Bitte zu erst Verbindung zur Datenbank herstellen.");
			return;
		} else {
			this.boot = new DatabaseList<>();
//			this.mannschaft = new DatabaseList<>();
//			this.einsaetze = new DatabaseList<>();
//			this.spiel = new DatabaseList<>();
//			this.spielen = new DatabaseList<>();
			try {
				Statement statement = Connect.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = statement.executeQuery("SELECT * FROM boot ORDER BY id ASC");
				rs.first();
				do {
					Boot boot = new Boot(rs);
					this.boot.add(boot, false);
				}
				while(rs.next()==true);
				rs.close();
//				rs=statement.executeQuery("SELECT * FROM mannschaft;");
//				rs.first();
//				do {
//					Mannschaft mannschaft = new Mannschaft(rs);
//					this.mannschaft.add(mannschaft);
//				}
//				while(rs.next()==true);
//				rs.close();
//				rs=statement.executeQuery("SELECT * FROM spielen;");
//				rs.first();
//				do {
//					Spielen spielen = new Spielen(rs);
//					this.spielen.add(spielen);
//				}
//				while(rs.next()==true);
//				rs.close();
//				rs=statement.executeQuery("SELECT * FROM spiel;");
//				rs.first();
//				do {
//					Spiel spiel = new Spiel(rs);
//					this.spiel.add(spiel);
//				}
//				while(rs.next()==true);
//				rs.close();
//				rs=statement.executeQuery("SELECT * FROM einsatz;");
//				rs.first();
//				do {
//					Einsatz einsatz = new Einsatz(rs);
//					this.einsaetze.add(einsatz);
//				}
//				while(rs.next()==true);
//				rs.close();
//				
//				statement.close();
				table.setModel(new TabelModel(boot));
//				TableColumn geschl_column = table.getColumnModel().getColumn(3);
//				JComboBox<String> cb = new JComboBox<>();
//				cb.addItem("Maennlich");
//				cb.addItem("Weiblich");
//				geschl_column.setCellEditor(new DefaultCellEditor(cb));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Fehler bei der SQL-Abfrage.");
				e.printStackTrace();
			}
		}
	}
}