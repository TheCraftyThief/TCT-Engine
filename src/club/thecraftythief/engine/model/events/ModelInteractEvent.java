package club.thecraftythief.engine.model.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import club.thecraftythief.engine.model.ModelData;
import club.thecraftythief.engine.entity.Model;
import club.thecraftythief.engine.player.PlayerWrapper;

public class ModelInteractEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();
    private final ModelData modelData;
    private final Model modelEntity;
    private final PlayerWrapper player;
    private boolean cancel;


    public ModelInteractEvent(ModelData modelData, ArmorStand modelEntity, Player player) {
        this(modelData, new Model(modelEntity), new PlayerWrapper(player));
    }
    public ModelInteractEvent(ModelData modelData, Model modelEntity, PlayerWrapper player) {
        this.modelData = modelData;
        this.modelEntity = modelEntity;
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public ModelData getModel() {
        return modelData;
    }

    public Model getEntity() {
        return modelEntity;
    }

    public PlayerWrapper getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
