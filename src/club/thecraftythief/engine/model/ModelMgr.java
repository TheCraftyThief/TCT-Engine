package club.thecraftythief.engine.model;

import club.thecraftythief.engine.data.DataStore;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.List;

public class ModelMgr {

    public static final String MODEL_TAG = "model";

    private static ModelMgr instance;

    public static ModelMgr getInstance() {
        return instance;
    }

    private final List<ModelData> models;

    public ModelMgr() {
        instance = this;
        models = new ArrayList<ModelData>();
    }

    public void registerModel(ModelData newModel) {
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

    //Remember to catch the exception and handle it nicely!
    public void spawnModelByName(String modelName, Location targetLoc) throws Exception {
        ModelData model = this.getModel(modelName);
        if(model == null) {
            throw new Exception("Couldn't find model with name \""+modelName+"\"");
        }
        spawnModel(model, targetLoc);
    }
    public void spawnModel(ModelData model, Location targetLoc) throws Exception {
        World world = targetLoc.getWorld();
        targetLoc.add(model.getSpawnOffset());
        ArmorStand stand = (ArmorStand)world.spawnEntity(targetLoc, EntityType.ARMOR_STAND);
        stand.setInvisible(true);
        stand.setGravity(false);
        stand.setItem(EquipmentSlot.HEAD, model.getItemStack());
        DataStore.store(stand, MODEL_TAG, "true");
        DataStore.store(stand, "model_name", model.getModelName());
    }
}
