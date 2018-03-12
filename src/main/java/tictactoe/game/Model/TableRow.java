package tictactoe.game.Model;

import java.util.ArrayList;

public class TableRow {

    public ArrayList<TableCell> row = new ArrayList<>();

    int id = 0;

    public TableRow(int id){
        row.add(new TableCell(0));
        row.add(new TableCell(1));
        row.add(new TableCell(2));
        this.id=id;
    }

    public ArrayList<TableCell> getRow() {
        return row;
    }

    public void setRow(ArrayList<TableCell> row) {
        this.row = row;
    }
}
