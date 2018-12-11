package battleships.model;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Board {
    public GridPane Board;
    public  ArrayList<ArrayList<Field>> gameBoard;
    public ArrayList<Ship> ships;
    public int mode;
     
    public Board(GridPane board){
        this.Board = board;
        gameBoard = new ArrayList<ArrayList<Field>>(10);
        ships = new ArrayList<Ship>(15);
    }
    
    public abstract void createBoard();
    
    public void addShip(Ship ship) {
    	int x = ship.getX();
    	int y = ship.getY(); 
        this.gameBoard.get(x).get(y).setShip(ship);
    }
    
    
    void clearDisplayBoard() {
        GridPane board = this.Board;
        for (Node node : board.getChildren()) {
        	if(node instanceof Rectangle) {
            Integer x = GridPane.getColumnIndex(node);
            Integer y = GridPane.getRowIndex(node);
            if (x == null)
                GridPane.setColumnIndex(node, 0);
            if (y == null)
                GridPane.setRowIndex(node, 0); 
            Rectangle field = (Rectangle) node;
            field.setFill(Color.web("#b8bfc6"));
            }
        }
    }
    
    public Node getNode(int col, int row) {
        GridPane gridPane = this.Board;
        for (Node node : gridPane.getChildren()) {
            if (node == null) {
                return null;
            }
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    
    public void checkField(int col, int row) {
        ArrayList<ArrayList<Field>> board = this.gameBoard;
        Ship s = null;
        s = board.get(col).get(row).getShip();
        Node n = this.getNode(col, row);
        Rectangle r = (Rectangle) n;
        if (s != null) 
            r.setFill(Color.RED);
        else
            r.setFill(Color.WHITE);
        board.get(col).get(row).setAsClicked(true);
    }
    
    public void setFieldsState(boolean state) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gameBoard.get(x).get(y).setAsClicked(state);
            }
        }
    }
    
    public void resetHits() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gameBoard.get(x).get(y).setHit(false);
            }
        }
    }
    
    public void destroyAllShips() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
            		gameBoard.get(x).get(y).setShip(null);
            }
        }
    }

}
