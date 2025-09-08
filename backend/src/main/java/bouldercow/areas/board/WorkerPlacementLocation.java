package bouldercow.areas.board;

public class WorkerPlacementLocation {
    public Integer playerId;
    public int requiredWorkerCount;
    
    public WorkerPlacementLocation(int workerCount) {
        this.requiredWorkerCount = workerCount;
    }
    public WorkerPlacementLocation() {
    }
}
