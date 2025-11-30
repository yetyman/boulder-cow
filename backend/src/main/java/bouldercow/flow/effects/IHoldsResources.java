package bouldercow.flow.effects;

public interface IHoldsResources {
    public ResourceEntry allResources();

    //used to check modification to resource pools. returns string for informative messages
    public String canModifyResource(ResourceEntry resource);
    //used to modify resource pools. returns boolean because some operations are illegal, such as resulting in negative resources
    public boolean modifyResource(ResourceEntry resource);
}
