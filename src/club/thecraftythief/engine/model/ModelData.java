package club.thecraftythief.engine.model;

import org.bukkit.util.Vector;

public abstract class ModelData {
    String modelName;
    int modelId;

    public ModelData(String modelName, int modelId) {
        this.modelName = modelName;
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public Vector getSpawnOffset() {
        return new Vector(0,0,0);
    }
}
