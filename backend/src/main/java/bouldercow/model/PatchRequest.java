package bouldercow.model;

import com.fasterxml.jackson.databind.JsonNode;

public class PatchRequest {
    private JsonNode patches;
    private long clientVersion;
    private long lastServerVersion;
    
    public JsonNode getPatches() { return patches; }
    public void setPatches(JsonNode patches) { this.patches = patches; }
    
    public long getClientVersion() { return clientVersion; }
    public void setClientVersion(long clientVersion) { this.clientVersion = clientVersion; }
    
    public long getLastServerVersion() { return lastServerVersion; }
    public void setLastServerVersion(long lastServerVersion) { this.lastServerVersion = lastServerVersion; }
}