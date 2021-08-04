package club.thecraftythief.engine.model;

import club.thecraftythief.engine.data.DataStore;
import club.thecraftythief.engine.model.events.ModelInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ModelEventListener implements Listener {
    /* This handler only responds to right click! */
    @EventHandler
    public void onEntityClick(PlayerInteractAtEntityEvent e) throws Exception {
        Player player = e.getPlayer();
        Entity clicked = e.getRightClicked();
        if(clicked instanceof ArmorStand) {
            ArmorStand stand = (ArmorStand) clicked;
            String isModel = DataStore.read(stand, ModelMgr.MODEL_TAG);
            if(isModel != null) {
                if(isModel.equals("true")) {
                    String modelName = DataStore.read(stand, "model_name");
                    if(modelName == null) {
                        throw new Exception("ArmorStand \""+stand.getUniqueId().toString()+"\" is marked as a model, without model data??");
                    }
                    ModelData modelData = ModelMgr.getInstance().getModel(modelName);
                    if(modelData == null) {
                        throw new Exception("ArmorStand \""+stand.getUniqueId().toString()+"\" has proper model data, but the model cannot be found? Was it unloaded?");
                    }
                    ModelInteractEvent modelEvent = new ModelInteractEvent(modelData, stand, player);
                    Bukkit.getPluginManager().callEvent(modelEvent);
                    e.setCancelled(true);
                }
            }
        }
    }
}
