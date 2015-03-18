package kodras;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class TableHeaderMouseListener extends MouseAdapter {
    
    private JTable table;
    private TableHeaderListener thlistener;
    
    public TableHeaderMouseListener(JTable table, TableHeaderListener thlistener) {
        this.table = table;
        this.thlistener = thlistener;
    }
     
    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        int column = table.columnAtPoint(point);
        if(thlistener!=null) {
        	thlistener.columnClicked(column);
        }
    }
}