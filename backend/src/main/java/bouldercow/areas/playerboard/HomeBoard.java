package bouldercow.areas.playerboard;




public class HomeBoard  {
    public HomeBGImage homeBGImage = new HomeBGImage();
    public HomeImage homeImage = new HomeImage();
    public BoulderPlacementLocation[][] boulderGrid = new BoulderPlacementLocation[5][12];
    public Boulder[][] boulders = new Boulder[5][2];
    public FeedingRequirement[] feedingRequirements = new FeedingRequirement[5];

    public HomeBoard(){
        

        

        
        //will move forward columns throughout game

        for (int i = 0; i < boulderGrid.length; i++) {//row
            for (int j = 0; j < boulderGrid[i].length; j++) {//column
                BoulderPlacementLocation boulderPlacementLocation = new BoulderPlacementLocation();
                boulderGrid[i][j] = boulderPlacementLocation;
                
            }
        }

        for (int i = 0; i < boulders.length; i++) {
            for (int j = 0; j < boulders[i].length; j++) {
                Boulder boulder = new Boulder();
                boulders[i][j] = boulder;
                boulder.row = i;
                boulder.col = j*2+3;
            }
        }

        for (int i = 0; i < feedingRequirements.length; i++) {
            FeedingRequirement feedingRequirement = new FeedingRequirement();
            feedingRequirements[i] = feedingRequirement;
            
        }
    }
}
