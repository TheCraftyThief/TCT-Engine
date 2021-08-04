package club.thecraftythief.engine.model;

import java.util.ArrayList;
import java.util.List;

public class ModelMgr {

    private static ModelMgr instance;
    private final List<ModelData> models;

    public ModelMgr() {
        instance = this;
        models = new ArrayList<ModelData>();
    }

    public static ModelMgr getInstance() {
        return instance;
    }

    public void registerModel(ModelData newModel) {
        models.add(newModel);
    }

    public ModelData getModel(String modelName) {
        for (ModelData model : models) {
            if (model.getModelName().equalsIgnoreCase(modelName)) {
                return model;
            }
        }
        return null;
    }

    public ModelData getModelByID(int modelId) {
        for (ModelData model : models) {
            if (model.getModelId() == modelId) {
                return model;
            }
        }
        return null;
    }
}
