package tictactoe.game.Repositories;

import tictactoe.game.Model.TableCell;
import tictactoe.game.Model.TableRow;

import java.util.ArrayList;

public class TableArrayListRepository implements ITableRepository {


    public ArrayList<TableRow> table = new ArrayList<>();


    public TableArrayListRepository() {
        table.add(new TableRow(0));
        table.add(new TableRow(1));
        table.add(new TableRow(2));


    }

    @Override
    public ArrayList<TableRow> readAll() {
        return table;
    }

}
