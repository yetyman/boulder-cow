package bouldercow.flow.effects;

public interface IHoldsResources {
    public ResourceEntry allResources();

    //used to check modification to resource pools. returns string for informative messages
    public String canModifyResource(ResourceEntry resource);
    /**
     * used to modify resource pools. returns null because some operations are illegal, such as resulting in negative resources
     * @param resource a resource representing the modification to this resourceEntry
     * @return a resource entry representing what was retrieved from the resource, empty entry if nothing was taken
     */
    public ResourceEntry modifyResource(ResourceEntry resource);
}
