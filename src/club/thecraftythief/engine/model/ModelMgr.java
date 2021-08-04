package club.thecraftythief.engine.model;

import club.thecraftythief.engine.model.impl.GoldBarModel;

import java.util.ArrayList;
import java.util.List;

public class ModelMgr {

    private final List<ModelData> models;

    public ModelMgr() {
        models = new ArrayList<ModelData>();

        registerModel(new GoldBarModel());
    }

    private void registerModel(ModelData newModel) {
        models.add(newModel);
    }
}
