package tictactoe.game.Repositories;

import tictactoe.game.Model.TableCell;
import tictactoe.game.Model.TableRow;

import java.util.ArrayList;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

public interface ITableRepository {




    ArrayList<TableRow> readAll();




}
