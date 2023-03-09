public class MooreRules extends Rules {
    private static final int NUM_NEIGHBORS = 9;
    private boolean[] birthRules;
    private boolean[] survivalRules;

    public MooreRules(int[] birthNeighbors, int[] survivalNeighbors){
        super();
        birthRules = new boolean[NUM_NEIGHBORS];
        survivalRules = new boolean[NUM_NEIGHBORS];

        // use each value of neighbors as an index to toggle that rule element to true
        for (int neighbors: birthNeighbors){
            birthRules[neighbors] = true;
        }
        for (int neighbors: survivalNeighbors){
            survivalRules[neighbors] = true;
        }
    }

    public boolean shouldBeBorn(int liveNeighbors) {
        return birthRules[liveNeighbors];
    }

    public boolean shouldSurvive(int liveNeighbors) {
        return survivalRules[liveNeighbors];
    }

}

