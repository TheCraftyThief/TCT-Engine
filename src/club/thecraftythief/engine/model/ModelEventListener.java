package club.thecraftythief.engine.model;

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
            if(ModelMgr.getInstance().isModel(stand)) {
                String modelName = ModelMgr.getInstance().getModelName(stand);

                ModelData modelData = ModelMgr.getInstance().getModel(modelName);

                ModelInteractEvent modelEvent = new ModelInteractEvent(modelData, stand, player);
                Bukkit.getPluginManager().callEvent(modelEvent);
                e.setCancelled(true);
            }
        }
    }
}
