package club.thecraftythief.engine.model;

import org.bukkit.util.Vector;

public abstract class ModYModel extends ModelData {
    private final float yOffset;
    public ModYModel(String modelName, int modelId) {
        this(modelName, modelId, -1.38f);
    }
    public ModYModel(String modelName, int modelId, float yOffset) {
        super(modelName, modelId);
        this.yOffset = yOffset;
    }

    @Override
    public Vector getSpawnOffset() {
        Vector offset = super.getSpawnOffset();
        offset.setY(offset.getY()+yOffset);
        return offset;
    }
}
