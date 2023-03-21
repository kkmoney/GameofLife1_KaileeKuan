public class Cell {
    private int x;
    private int y;
    static int size;
    private int row;
    private int column;
    private CellState cellState;
    private Rules rules;

    public Cell(int x, int y, int size, int row, int column, CellState cellState, Rules rules) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }


    /** if cell is alive or dead -> fill it with a different color
     * return the rect with the condtions
     *
      */
    public void display(){
        if(cellState == CellState.ALIVE){
            Main.app.fill(0);
        } else if(cellState == CellState.DEAD) {
            Main.app.fill(255);
        }
        Main.app.rect(x,y,size,size);
        }

    /** swtiching the dead and alive cells
     * for ex. cell is dead -> cell is alive
     *
      */
    public void handleClick(){
        if(cellState == CellState.DEAD){
            cellState = CellState.ALIVE;
        } else{
         cellState = CellState.DEAD;
        }
    }

    /** ew cell state is a function of the current state and quantity of live neighbors in the surrounding Moore neighborhood
     *
      * @param cells
     */

    public void applyRules(Cell[][] cells){
        int liveNeighbors = countLiveNeighbors(cells);
        cellState = rules.applyRules(cellState, liveNeighbors);
    }

    /**
     *inspects the eight surrounding neighbors, and returns how many are alive
      * @param cells
     * @return
     */
    public int countLiveNeighbors(Cell[][] cells){
        int count = 0; 
        for(int row = this.row - 1; row <= this.row + 1; row++){
            for(int column = this.column -1 ; column <= this.column + 1; column++){
                if(cells[row][column].cellState == CellState.ALIVE || cells[row][column].cellState == CellState.WILL_DIE){
                    count++; 
                } 
                if(this.row == row && this.column == column && cellState == CellState.ALIVE){
                    count--; 
                }
            }
            }
        return count;
    }


    /** completes the transition to the cell’s next state
     *
      */
    public void evolve(){
        if (cellState == CellState.WILL_REVIVE){
            cellState = CellState.ALIVE;
        } else if (cellState == CellState.WILL_DIE){
            cellState = CellState.DEAD;
        }
    }


}
