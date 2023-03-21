import processing.core.PApplet;

import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.Random;

public class Main extends PApplet {
    private static final int CELL_SIZE = 10;
    private static final int NUM_ROWS = 50;
    private static final int NUM_COLUMNS = 100;
    static Main app; //calls processing methods
    private Cell[][] cells;

    private boolean doEvolve;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    /**
     *
     */
    public Main() {
        super();
        app = this; //no arg constructor to PApplet constructor
        doEvolve = false;
    }

    /**
     *
     */
    public void settings() {
        //size(600, 500);
        size(NUM_COLUMNS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
    }

    /**
     * initializes cells to a 2D array of Cell
      */
    public void setup() {
        cells = new Cell[height / CELL_SIZE][width / CELL_SIZE]; //create a 2d array
        Rules rules = new MooreRules(new int[]{3}, new int[]{2, 3});
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                CellState s = CellState.DEAD;
                if (row != 0 && row != cells.length - 1 && col != 0 && col != cells[row].length - 1) {
                    s = random();
                }
                Cell c = new Cell(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, row, col, s, rules);
                cells[row][col] = c;
            }
        }
    }

    /**
     * call applyRules followed by evolve if doEvolve is true
     */
    public void draw() {
        if (doEvolve == true) {
            applyRules();
            evolve();
        }

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                cells[row][col].display();
            }
        }
    }

    /**uses mouseX and mouseY to calculate the row and column indices of the clicked cell
     * invoke that cell’s handleClick method
     *
     */
    public void mouseClicked() {
        cells[mouseY / CELL_SIZE][mouseX / CELL_SIZE].handleClick();
    }


    /**determining a cell’s state in the next generation (following evolution)
     * function of its current state and the state of its surrounding neighbors
     *
      */
    public void applyRules() {
        for (int row = 1; row < cells.length - 1; row++) {
            for (int col = 1; col < cells[row].length - 1; col++) {
                cells[row][col].applyRules(cells);
            }
        }
    }

    /**
     *
      */
    public void evolve() {
        for (int row = 1; row < cells.length - 1; row++) {
            for (int col = 1; col < cells[row].length - 1; col++) {
                cells[row][col].evolve();
            }
        }
    }

    /**
     *Randomly set cell state to dead or alive when the program begins running
     * Cells in the first and last rows and columns should remain dead
     * @return
     */
    public CellState random() {
        CellState s;
        int k = (Math.random() <= 0.5) ? 1 : 2;
        if (k == 1) {
            return CellState.ALIVE;
        }
        return CellState.DEAD;
    }

    /**
     *toggle doEvolve
      */
    public void keyPressed() {
        doEvolve = !doEvolve;
    }
}





