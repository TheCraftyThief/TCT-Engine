package club.thecraftythief.engine.model.events;

import club.thecraftythief.engine.model.ModelData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ModelInteractEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();
    private boolean cancel;

    private final ModelData model;
    private final ArmorStand entity;
    private final Player player;

    public ModelInteractEvent(ModelData model, ArmorStand entity, Player player) {
        this.model = model;
        this.entity = entity;
        this.player = player;
    }

    public ModelData getModel() {
        return model;
    }
    public ArmorStand getEntity() {
        return entity;
    }
    public Player getPlayer() {
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
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
