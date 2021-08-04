package club.thecraftythief.engine.model;

import club.thecraftythief.engine.model.impl.*;

import java.util.ArrayList;
import java.util.List;

public class ModelMgr {

    private static ModelMgr instance;

    public static ModelMgr getInstance() {
        return instance;
    }

    private final List<ModelData> models;

    public ModelMgr() {
        instance = this;
        models = new ArrayList<ModelData>();

        registerModel(new ComputerTowerModel());
        registerModel(new GoldBarModel());
        registerModel(new PhoneModel());
        registerModel(new RubiksCubeModel());
        registerModel(new CashModel());
        registerModel(new LaptopModel());
        registerModel(new TVModel());
    }

    private void registerModel(ModelData newModel) {
        models.add(newModel);
    }

    public ModelData getModel(String modelName) {
        for(int i = 0; i < models.size(); i++) {
            if(models.get(i).getModelName().equalsIgnoreCase(modelName)) {
                return models.get(i);
            }
        }
        return null;
    }
    public ModelData getModelByID(int modelId) {
        for(int i = 0; i < models.size(); i++) {
            if(models.get(i).getModelId() == modelId) {
                return models.get(i);
            }
        }
        return null;
    }
}
