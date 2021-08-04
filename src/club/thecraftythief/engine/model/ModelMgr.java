package club.thecraftythief.engine.model;

import club.thecraftythief.engine.data.DataStore;
import club.thecraftythief.engine.model.events.ModelInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.List;

public class ModelMgr {

    public static final String MODEL_TAG = "model";

    private static ModelMgr instance;
    private final List<ModelData> models;

    public ModelMgr() {
        instance = this;
        models = new ArrayList<ModelData>();
    }

    public static ModelMgr getInstance() {
        return instance;
    }
    //Register a new model
    public void registerModel(ModelData newModel) {
        models.add(newModel);
    }

    //Retrieve ModelData by name or ID
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
    //Get all registered ModelData
    public List<ModelData> getModels() {
        return models;
    }

    //Helper function to check if an ArmorStand is a model
    public boolean isModel(ArmorStand stand) {
        try {
            String isModel = DataStore.read(stand, ModelMgr.MODEL_TAG);
            if(isModel != null) {
                if(isModel.equals("true")) {
                    getModelName(stand);
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    //Helper function to read a model name from an armor stand
    public String getModelName(ArmorStand stand) throws Exception {
        String modelName = DataStore.read(stand, "model_name");
        if(modelName == null) {
            throw new Exception("ArmorStand \""+stand.getUniqueId().toString()+"\" is marked as a model, without model data??");
        }
        ModelData modelData = ModelMgr.getInstance().getModel(modelName);
        if(modelData == null) {
            throw new Exception("ArmorStand \""+stand.getUniqueId().toString()+"\" has proper model data, but the model cannot be found? Was it unloaded?");
        }
        return modelName;
    }

    //Spawn new models at a given location
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

    //All of the model entities in a given world
    public List<ArmorStand> getSpawnedModels(World world) {
        List<ArmorStand> models = new ArrayList<>();
        List<Entity> entities = world.getEntities();

        for (Entity ent : entities) {
            if(ent instanceof ArmorStand) {
                ArmorStand stand = (ArmorStand) ent;
                if(isModel(stand)) {
                    models.add(stand);
                }
            }
        }

        return models;
    }
}
