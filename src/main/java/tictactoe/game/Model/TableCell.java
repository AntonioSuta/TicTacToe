package tictactoe.game.Model;

public class TableCell {

    public String cellValue;
    public int id = 0;

    public TableCell(int id) {
        cellValue = "";
        this.id = id;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    public boolean hasValue(){
        if(cellValue.equals("")){
            return false;
        }else{
            return true;
        }
    }
}
