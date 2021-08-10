package club.thecraftythief.engine.model;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public abstract class ModelData {
    String modelName;
    int modelId;

    // TODO: Do javadocs for all methods like done in PartyMgr
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
        return new Vector(0, 0, 0);
    }

    public ItemStack getItemStack() {
        return getItemStack(modelName);
    }

    public ItemStack getItemStack(String itemName) {
        ItemStack stack = new ItemStack(Material.STICK);
        ItemMeta meta = stack.getItemMeta();
        meta.setCustomModelData(modelId);
        meta.setDisplayName(modelName);
        stack.setItemMeta(meta);
        return stack;
    }
}
