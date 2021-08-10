package club.thecraftythief.engine.player;

import club.thecraftythief.engine.data.DataStore;
import club.thecraftythief.engine.entity.Model;
import club.thecraftythief.engine.model.ModelMgr;
import com.destroystokyo.paper.ClientOption;
import com.destroystokyo.paper.Title;
import com.destroystokyo.paper.block.TargetBlockInfo;
import com.destroystokyo.paper.entity.TargetEntityInfo;
import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;
import java.util.*;

public class PlayerWrapper implements Player {

    /* Custom methods and data */

    public Model getCarrying() {
        List<Model> modelStands = ModelMgr.getInstance().getSpawnedModels(player.getWorld());
        ArmorStand carryingStand = null;
        //Using this to iterate to avoid making copies
        for (int i = 0; i < modelStands.size(); i++) {
            ArmorStand current = modelStands.get(i);
            String uuidStr = DataStore.read(current, Model.BEING_CARRIED_KEY);
            if (uuidStr == null) {
                continue;
            }
            UUID carrierUUID = UUID.fromString(uuidStr);
            if (carrierUUID.equals(player.getUniqueId())) {
                //Dont change rotation
                return new Model(current);
            }
        }
        return null;
    }

    /* Constructors */
    private final Player player;
    public PlayerWrapper(Player player) {
        this.player = player;
    }
    public PlayerWrapper(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }

    /* Bukkit methods */
    @Override
    public @NotNull Component displayName() {
        return player.displayName();
    }

    @Override
    public void displayName(@Nullable Component displayName) {
        player.displayName(displayName);
    }

    @Override
    public @NotNull String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public void setDisplayName(@Nullable String name) {
        player.setDisplayName(name);
    }

    @Override
    public void playerListName(@Nullable Component name) {
        player.playerListName(name);
    }

    @Override
    public @NotNull Component playerListName() {
        return player.playerListName();
    }

    @Override
    public @Nullable Component playerListHeader() {
        return player.playerListHeader();
    }

    @Override
    public @Nullable Component playerListFooter() {
        return player.playerListFooter();
    }

    @Override
    public @NotNull String getPlayerListName() {
        return player.getPlayerListName();
    }

    @Override
    public void setPlayerListName(@Nullable String name) {
        player.setPlayerListName(name);
    }

    @Override
    public @Nullable String getPlayerListHeader() {
        return player.getPlayerListHeader();
    }

    @Override
    public void setPlayerListHeader(@Nullable String header) {
        player.setPlayerListHeader(header);
    }

    @Override
    public @Nullable String getPlayerListFooter() {
        return player.getPlayerListFooter();
    }

    @Override
    public void setPlayerListFooter(@Nullable String footer) {
        player.setPlayerListHeader(footer);
    }

    @Override
    public void setPlayerListHeaderFooter(@Nullable String header, @Nullable String footer) {
        player.setPlayerListHeaderFooter(header, footer);
    }

    @Override
    public @NotNull Location getCompassTarget() {
        return player.getCompassTarget();
    }

    @Override
    public void setCompassTarget(@NotNull Location loc) {
        player.setCompassTarget(loc);
    }

    @Override
    public @Nullable InetSocketAddress getAddress() {
        return player.getAddress();
    }

    @Override
    public int getProtocolVersion() {
        return player.getProtocolVersion();
    }

    @Override
    public @Nullable InetSocketAddress getVirtualHost() {
        return player.getVirtualHost();
    }

    @Override
    public boolean isConversing() {
        return player.isConversing();
    }

    @Override
    public void acceptConversationInput(@NotNull String input) {
        player.acceptConversationInput(input);
    }

    @Override
    public boolean beginConversation(@NotNull Conversation conversation) {
        return player.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation) {
        player.abandonConversation(conversation);
    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent details) {
        player.abandonConversation(conversation, details);
    }

    @Override
    public void sendRawMessage(@NotNull String message) {
        player.sendRawMessage(message);
    }

    @Override
    public void sendRawMessage(@Nullable UUID sender, @NotNull String message) {
        player.sendRawMessage(sender, message);
    }

    @Override
    public void kickPlayer(@Nullable String message) {
        player.kickPlayer(message);
    }

    @Override
    public void kick(@Nullable Component message) {
        player.kick(message);
    }

    @Override
    public void kick(@Nullable Component message, PlayerKickEvent.@NotNull Cause cause) {
        player.kick(message, cause);
    }

    @Override
    public void chat(@NotNull String msg) {
        player.chat(msg);
    }

    @Override
    public boolean performCommand(@NotNull String command) {
        return player.performCommand(command);
    }

    @Override
    public @NotNull Location getLocation() {
        return player.getLocation();
    }

    @Override
    public @Nullable Location getLocation(@Nullable Location loc) {
        return player.getLocation(loc);
    }

    @Override
    public @NotNull Vector getVelocity() {
        return player.getVelocity();
    }

    @Override
    public void setVelocity(@NotNull Vector velocity) {
        player.setVelocity(velocity);
    }

    @Override
    public double getHeight() {
        return player.getHeight();
    }

    @Override
    public double getWidth() {
        return player.getHeight();
    }

    @Override
    public @NotNull BoundingBox getBoundingBox() {
        return player.getBoundingBox();
    }

    @Override
    public boolean isOnGround() {
        return player.isOnGround();
    }

    @Override
    public boolean isInWater() {
        return player.isInWater();
    }

    @Override
    public @NotNull World getWorld() {
        return player.getWorld();
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        player.setRotation(yaw, pitch);
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        return player.teleport(location);
    }

    @Override
    public boolean teleport(@NotNull Location location, PlayerTeleportEvent.@NotNull TeleportCause cause) {
        return player.teleport(location, cause);
    }

    @Override
    public boolean teleport(@NotNull Entity destination) {
        return player.teleport(destination);
    }

    @Override
    public boolean teleport(@NotNull Entity destination, PlayerTeleportEvent.@NotNull TeleportCause cause) {
        return player.teleport(destination, cause);
    }

    @Override
    public @NotNull List<Entity> getNearbyEntities(double x, double y, double z) {
        return player.getNearbyEntities(x, y, z);
    }

    @Override
    public int getEntityId() {
        return player.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return player.getFireTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        player.setFireTicks(ticks);
    }

    @Override
    public int getMaxFireTicks() {
        return player.getMaxFireTicks();
    }

    @Override
    public boolean isVisualFire() {
        return player.isVisualFire();
    }

    @Override
    public void setVisualFire(boolean fire) {
        player.setVisualFire(fire);
    }

    @Override
    public int getFreezeTicks() {
        return player.getFreezeTicks();
    }

    @Override
    public void setFreezeTicks(int ticks) {
        player.setFreezeTicks(ticks);
    }

    @Override
    public int getMaxFreezeTicks() {
        return player.getMaxFreezeTicks();
    }

    @Override
    public boolean isFrozen() {
        return player.isFrozen();
    }

    @Override
    public void remove() {
        player.remove();
    }

    @Override
    public boolean isDead() {
        return player.isDead();
    }

    @Override
    public boolean isValid() {
        return player.isValid();
    }

    @Override
    public void sendMessage(@NotNull String message) {
        player.sendMessage(message);
    }

    @Override
    public void sendMessage(@NotNull String... messages) {
        player.sendMessage(messages);
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {
        player.sendMessage(sender, message);
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String... messages) {
        player.sendMessage(sender, messages);
    }

    @Override
    public @NotNull Server getServer() {
        return player.getServer();
    }

    @Override
    public boolean isPersistent() {
        return player.isPersistent();
    }

    @Override
    public void setPersistent(boolean persistent) {
        player.setPersistent(persistent);
    }

    @Override
    public @Nullable Entity getPassenger() {
        return player.getPassenger();
    }

    @Override
    public boolean setPassenger(@NotNull Entity passenger) {
        return player.setPassenger(passenger);
    }

    @Override
    public @NotNull List<Entity> getPassengers() {
        return player.getPassengers();
    }

    @Override
    public boolean addPassenger(@NotNull Entity passenger) {
        return player.addPassenger(passenger);
    }

    @Override
    public boolean removePassenger(@NotNull Entity passenger) {
        return player.removePassenger(passenger);
    }

    @Override
    public boolean isEmpty() {
        return player.isEmpty();
    }

    @Override
    public boolean eject() {
        return player.eject();
    }

    @Override
    public float getFallDistance() {
        return player.getFallDistance();
    }

    @Override
    public void setFallDistance(float distance) {
        player.setFallDistance(distance);
    }

    @Override
    public @Nullable EntityDamageEvent getLastDamageCause() {
        return player.getLastDamageCause();
    }

    @Override
    public void setLastDamageCause(@Nullable EntityDamageEvent event) {
        player.setLastDamageCause(event);
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return player.getTicksLived();
    }

    @Override
    public void setTicksLived(int value) {
        player.setTicksLived(value);
    }

    @Override
    public void playEffect(@NotNull EntityEffect type) {
        player.playEffect(type);
    }

    @Override
    public @NotNull EntityType getType() {
        return player.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return player.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return player.leaveVehicle();
    }

    @Override
    public @Nullable Entity getVehicle() {
        return player.getVehicle();
    }

    @Override
    public boolean isCustomNameVisible() {
        return player.isCustomNameVisible();
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        player.setCustomNameVisible(flag);
    }

    @Override
    public boolean isGlowing() {
        return player.isGlowing();
    }

    @Override
    public void setGlowing(boolean flag) {
        player.setGlowing(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return player.isInvulnerable();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        player.setInvulnerable(flag);
    }

    @Override
    public boolean isSilent() {
        return player.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        player.setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return player.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        player.setGravity(gravity);
    }

    @Override
    public int getPortalCooldown() {
        return player.getPortalCooldown();
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        player.setPortalCooldown(cooldown);
    }

    @Override
    public @NotNull Set<String> getScoreboardTags() {
        return player.getScoreboardTags();
    }

    @Override
    public boolean addScoreboardTag(@NotNull String tag) {
        return player.addScoreboardTag(tag);
    }

    @Override
    public boolean removeScoreboardTag(@NotNull String tag) {
        return player.removeScoreboardTag(tag);
    }

    @Override
    public @NotNull PistonMoveReaction getPistonMoveReaction() {
        return player.getPistonMoveReaction();
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return player.getFacing();
    }

    @Override
    public @NotNull Pose getPose() {
        return player.getPose();
    }

    @Override
    public boolean isSneaking() {
        return player.isSneaking();
    }

    @Override
    public void setSneaking(boolean sneak) {
        player.setSneaking(sneak);
    }

    @Override
    public boolean isSprinting() {
        return player.isSprinting();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        player.setSprinting(sprinting);
    }

    @Override
    public void saveData() {
        player.saveData();
    }

    @Override
    public void loadData() {
        player.loadData();
    }

    @Override
    public boolean isSleepingIgnored() {
        return player.isSleepingIgnored();
    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {
        player.setSleepingIgnored(isSleeping);
    }

    @Override
    public boolean isOnline() {
        return player.isOnline();
    }

    @Override
    public boolean isBanned() {
        return player.isBanned();
    }

    @Override
    public boolean isWhitelisted() {
        return player.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean value) {
        player.setWhitelisted(value);
    }

    @Override
    public @Nullable Player getPlayer() {
        return player.getPlayer();
    }

    @Override
    public long getFirstPlayed() {
        return player.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return player.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return player.hasPlayedBefore();
    }

    @Override
    public @Nullable Location getBedSpawnLocation() {
        return player.getBedSpawnLocation();
    }

    @Override
    public void setBedSpawnLocation(@Nullable Location location) {
        player.setBedSpawnLocation(location);
    }

    @Override
    public long getLastLogin() {
        return player.getLastLogin();
    }

    @Override
    public long getLastSeen() {
        return player.getLastSeen();
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        player.incrementStatistic(statistic);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        player.decrementStatistic(statistic);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {
        player.incrementStatistic(statistic, amount);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {
        player.decrementStatistic(statistic, amount);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, int newValue) throws IllegalArgumentException {
        player.setStatistic(statistic, newValue);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        return player.getStatistic(statistic);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        player.incrementStatistic(statistic, material);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        player.decrementStatistic(statistic, material);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        return player.getStatistic(statistic, material);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount) throws IllegalArgumentException {
        player.incrementStatistic(statistic, material, amount);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount) throws IllegalArgumentException {
        player.decrementStatistic(statistic, material, amount);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int newValue) throws IllegalArgumentException {
        player.setStatistic(statistic, material, newValue);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        player.incrementStatistic(statistic, entityType);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        player.decrementStatistic(statistic, entityType);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        return player.getStatistic(statistic, entityType);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) throws IllegalArgumentException {
        player.incrementStatistic(statistic, entityType, amount);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) {
        player.decrementStatistic(statistic, entityType, amount);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int newValue) {
        player.setStatistic(statistic, entityType, newValue);
    }

    @Override
    public void setBedSpawnLocation(@Nullable Location location, boolean force) {
        player.setBedSpawnLocation(location, force);
    }

    @Override
    public void playNote(@NotNull Location loc, byte instrument, byte note) {
        player.playNote(loc, instrument, note);
    }

    @Override
    public void playNote(@NotNull Location loc, @NotNull Instrument instrument, @NotNull Note note) {
        player.playNote(loc, instrument, note);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, float volume, float pitch) {
        player.playSound(location, sound, volume, pitch);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String sound, float volume, float pitch) {
        player.playSound(location, sound, volume, pitch);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch) {
        player.playSound(location, sound, category, volume, pitch);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch) {
        player.playSound(location, sound, category, volume, pitch);
    }

    @Override
    public void stopSound(@NotNull Sound sound) {
        player.stopSound(sound);
    }

    @Override
    public void stopSound(@NotNull String sound) {
        player.stopSound(sound);
    }

    @Override
    public void stopSound(@NotNull Sound sound, @Nullable SoundCategory category) {
        player.stopSound(sound, category);
    }

    @Override
    public void stopSound(@NotNull String sound, @Nullable SoundCategory category) {
        player.stopSound(sound, category);
    }

    @Override
    public void playEffect(@NotNull Location loc, @NotNull Effect effect, int data) {
        player.playEffect(loc, effect, data);
    }

    @Override
    public <T> void playEffect(@NotNull Location loc, @NotNull Effect effect, @Nullable T data) {
        player.playEffect(loc, effect, data);
    }

    @Override
    public boolean breakBlock(@NotNull Block block) {
        return player.breakBlock(block);
    }

    @Override
    public void sendBlockChange(@NotNull Location loc, @NotNull Material material, byte data) {
        player.sendBlockChange(loc, material, data);
    }

    @Override
    public void sendBlockChange(@NotNull Location loc, @NotNull BlockData block) {
        player.sendBlockChange(loc, block);
    }

    @Override
    public void sendBlockDamage(@NotNull Location loc, float progress) {
        player.sendBlockDamage(loc, progress);
    }

    @Override
    public boolean sendChunkChange(@NotNull Location loc, int sx, int sy, int sz, @NotNull byte[] data) {
        return player.sendChunkChange(loc, sx, sy, sz, data);
    }

    @Override
    public void sendSignChange(@NotNull Location loc, @Nullable List<Component> lines) throws IllegalArgumentException {
        player.sendSignChange(loc, lines);
    }

    @Override
    public void sendSignChange(@NotNull Location loc, @Nullable List<Component> lines, @NotNull DyeColor dyeColor) throws IllegalArgumentException {
        player.sendSignChange(loc, lines, dyeColor);
    }

    @Override
    public void sendSignChange(@NotNull Location loc, @Nullable String[] lines) throws IllegalArgumentException {
        player.sendSignChange(loc, lines);
    }

    @Override
    public void sendSignChange(@NotNull Location loc, @Nullable String[] lines, @NotNull DyeColor dyeColor) throws IllegalArgumentException {
        player.sendSignChange(loc, lines, dyeColor);
    }

    @Override
    public void sendMap(@NotNull MapView map) {
        player.sendMap(map);
    }

    @Override
    public void sendActionBar(@NotNull String message) {
        player.sendActionBar(message);
    }

    @Override
    public void sendActionBar(char alternateChar, @NotNull String message) {
        player.sendActionBar(alternateChar, message);
    }

    @Override
    public void sendActionBar(@NotNull BaseComponent... message) {
        player.sendActionBar(message);
    }

    @Override
    public void setPlayerListHeaderFooter(@Nullable BaseComponent[] header, @Nullable BaseComponent[] footer) {
        player.setPlayerListHeaderFooter(header, footer);
    }

    @Override
    public void setPlayerListHeaderFooter(@Nullable BaseComponent header, @Nullable BaseComponent footer) {
        player.setPlayerListHeaderFooter(header, footer);
    }

    @Override
    public void setTitleTimes(int fadeInTicks, int stayTicks, int fadeOutTicks) {
        player.setTitleTimes(fadeInTicks, stayTicks, fadeOutTicks);
    }

    @Override
    public void setSubtitle(BaseComponent[] subtitle) {
        player.setSubtitle(subtitle);
    }

    @Override
    public void setSubtitle(BaseComponent subtitle) {
        player.setSubtitle(subtitle);
    }

    @Override
    public void showTitle(@Nullable BaseComponent[] title) {
        player.showTitle(title);
    }

    @Override
    public void showTitle(@Nullable BaseComponent title) {
        player.showTitle(title);
    }

    @Override
    public void showTitle(@Nullable BaseComponent[] title, @Nullable BaseComponent[] subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
        player.showTitle(title, subtitle, fadeInTicks, stayTicks, fadeOutTicks);
    }

    @Override
    public void showTitle(@Nullable BaseComponent title, @Nullable BaseComponent subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
        player.showTitle(title, subtitle, fadeInTicks, stayTicks, fadeOutTicks);
    }

    @Override
    public void sendTitle(@NotNull Title title) {
        player.sendTitle(title);
    }

    @Override
    public void updateTitle(@NotNull Title title) {
        player.updateTitle(title);
    }

    @Override
    public void hideTitle() {
        player.hideTitle();
    }

    @Override
    public void updateInventory() {
        player.updateInventory();
    }

    @Override
    public void setPlayerTime(long time, boolean relative) {
        player.setPlayerTime(time, relative);
    }

    @Override
    public long getPlayerTime() {
        return player.getPlayerTime();
    }

    @Override
    public long getPlayerTimeOffset() {
        return player.getPlayerTimeOffset();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return player.isPlayerTimeRelative();
    }

    @Override
    public void resetPlayerTime() {
        player.resetPlayerTime();
    }

    @Override
    public @Nullable WeatherType getPlayerWeather() {
        return player.getPlayerWeather();
    }

    @Override
    public void setPlayerWeather(@NotNull WeatherType type) {
        player.setPlayerWeather(type);
    }

    @Override
    public void resetPlayerWeather() {
        player.resetPlayerWeather();
    }

    @Override
    public void giveExp(int amount, boolean applyMending) {
        player.giveExp(amount, applyMending);
    }

    @Override
    public int applyMending(int amount) {
        return player.applyMending(amount);
    }

    @Override
    public void giveExpLevels(int amount) {
        player.giveExpLevels(amount);
    }

    @Override
    public float getExp() {
        return player.getExp();
    }

    @Override
    public void setExp(float exp) {
        player.setExp(exp);
    }

    @Override
    public int getLevel() {
        return player.getLevel();
    }

    @Override
    public void setLevel(int level) {
        player.setLevel(level);
    }

    @Override
    public int getTotalExperience() {
        return player.getTotalExperience();
    }

    @Override
    public void setTotalExperience(int exp) {
        player.setTotalExperience(exp);
    }

    @Override
    public void sendExperienceChange(float progress) {
        player.sendExperienceChange(progress);
    }

    @Override
    public void sendExperienceChange(float progress, int level) {
        player.sendExperienceChange(progress, level);
    }

    @Override
    public boolean getAllowFlight() {
        return player.getAllowFlight();
    }

    @Override
    public void setAllowFlight(boolean flight) {
        player.setAllowFlight(flight);
    }

    @Override
    public void hidePlayer(@NotNull Player player) {
        player.hidePlayer(player);
    }

    @Override
    public void hidePlayer(@NotNull Plugin plugin, @NotNull Player player) {
        player.hidePlayer(plugin, player);
    }

    @Override
    public void showPlayer(@NotNull Player player) {
        player.showPlayer(player);
    }

    @Override
    public void showPlayer(@NotNull Plugin plugin, @NotNull Player player) {
        player.showPlayer(plugin, player);
    }

    @Override
    public boolean canSee(@NotNull Player player) {
        return player.canSee(player);
    }

    @Override
    public boolean isFlying() {
        return player.isFlying();
    }

    @Override
    public void setFlying(boolean value) {
        player.setFlying(value);
    }

    @Override
    public float getFlySpeed() {
        return player.getFlySpeed();
    }

    @Override
    public void setFlySpeed(float value) throws IllegalArgumentException {
        player.setFlySpeed(value);
    }

    @Override
    public float getWalkSpeed() {
        return player.getWalkSpeed();
    }

    @Override
    public void setWalkSpeed(float value) throws IllegalArgumentException {
        player.setWalkSpeed(value);
    }

    @Override
    public void setTexturePack(@NotNull String url) {
        player.setTexturePack(url);
    }

    @Override
    public void setResourcePack(@NotNull String url) {
        player.setResourcePack(url);
    }

    @Override
    public void setResourcePack(@NotNull String url, @NotNull byte[] hash) {
        player.setResourcePack(url, hash);
    }

    @Override
    public @NotNull Scoreboard getScoreboard() {
        return player.getScoreboard();
    }

    @Override
    public void setScoreboard(@NotNull Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        player.setScoreboard(scoreboard);
    }

    @Override
    public boolean isHealthScaled() {
        return player.isHealthScaled();
    }

    @Override
    public void setHealthScaled(boolean scale) {
        player.setHealthScaled(scale);
    }

    @Override
    public double getHealthScale() {
        return player.getHealthScale();
    }

    @Override
    public void setHealthScale(double scale) throws IllegalArgumentException {
        player.setHealthScale(scale);
    }

    @Override
    public @Nullable Entity getSpectatorTarget() {
        return player.getSpectatorTarget();
    }

    @Override
    public void setSpectatorTarget(@Nullable Entity entity) {
        player.setSpectatorTarget(entity);
    }

    @Override
    public void sendTitle(@Nullable String title, @Nullable String subtitle) {
        player.sendTitle(title, subtitle);
    }

    @Override
    public void sendTitle(@Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    @Override
    public void resetTitle() {
        player.resetTitle();
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count) {
        player.spawnParticle(particle, location, count);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count) {
        player.spawnParticle(particle, x, y, z, count);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, @Nullable T data) {
        player.spawnParticle(particle, location, count, data);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, @Nullable T data) {
        player.spawnParticle(particle, x, y, z, count, data);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ) {
        player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        player.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data) {
        player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, data);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data) {

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {

    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {

    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {

    }

    @Override
    public @NotNull AdvancementProgress getAdvancementProgress(@NotNull Advancement advancement) {
        return player.getAdvancementProgress(advancement);
    }

    @Override
    public int getClientViewDistance() {
        return player.getClientViewDistance();
    }

    @Override
    public @NotNull Locale locale() {
        return player.locale();
    }

    @Override
    public int getPing() {
        return player.getPing();
    }

    @Override
    public @NotNull String getLocale() {
        return player.getLocale();
    }

    @Override
    public boolean getAffectsSpawning() {
        return player.getAffectsSpawning();
    }

    @Override
    public void setAffectsSpawning(boolean affects) {
        player.setAffectsSpawning(affects);
    }

    @Override
    public int getViewDistance() {
        return player.getViewDistance();
    }

    @Override
    public void setViewDistance(int viewDistance) {
        player.setViewDistance(viewDistance);
    }

    @Override
    public void updateCommands() {
        player.updateCommands();
    }

    @Override
    public void openBook(@NotNull ItemStack book) {
        player.openBook(book);
    }

    @Override
    public void setResourcePack(@NotNull String url, @NotNull String hash) {
        player.setResourcePack(url, hash);
    }

    @Override
    public void setResourcePack(@NotNull String url, @NotNull String hash, boolean required) {
        player.setResourcePack(url, hash, required);
    }

    @Override
    public void setResourcePack(@NotNull String url, @NotNull String hash, boolean required, @Nullable Component resourcePackPrompt) {
        player.setResourcePack(url, hash, required, resourcePackPrompt);
    }

    @Override
    public PlayerResourcePackStatusEvent.@Nullable Status getResourcePackStatus() {
        return player.getResourcePackStatus();
    }

    @Override
    public @Nullable String getResourcePackHash() {
        return player.getResourcePackHash();
    }

    @Override
    public boolean hasResourcePack() {
        return player.hasResourcePack();
    }

    @Override
    public @NotNull PlayerProfile getPlayerProfile() {
        return player.getPlayerProfile();
    }

    @Override
    public void setPlayerProfile(@NotNull PlayerProfile profile) {
        player.setPlayerProfile(profile);
    }

    @Override
    public float getCooldownPeriod() {
        return player.getCooldownPeriod();
    }

    @Override
    public float getCooledAttackStrength(float adjustTicks) {
        return player.getCooledAttackStrength(adjustTicks);
    }

    @Override
    public void resetCooldown() {
        player.resetCooldown();
    }

    @Override
    public <T> @NotNull T getClientOption(@NotNull ClientOption<T> option) {
        return player.getClientOption(option);
    }

    @Override
    public @Nullable Firework boostElytra(@NotNull ItemStack firework) {
        return player.boostElytra(firework);
    }

    @Override
    public void sendOpLevel(byte level) {
        player.sendOpLevel(level);
    }

    @Override
    public @NotNull Set<Player> getTrackedPlayers() {
        return player.getTrackedPlayers();
    }

    @Override
    public @Nullable String getClientBrandName() {
        return player.getClientBrandName();
    }

    @Override
    public @NotNull Spigot spigot() {
        return player.spigot();
    }

    @Override
    public @Nullable Location getOrigin() {
        return player.getOrigin();
    }

    @Override
    public boolean fromMobSpawner() {
        return player.fromMobSpawner();
    }

    @Override
    public CreatureSpawnEvent.@NotNull SpawnReason getEntitySpawnReason() {
        return player.getEntitySpawnReason();
    }

    @Override
    public boolean isInRain() {
        return player.isInRain();
    }

    @Override
    public boolean isInBubbleColumn() {
        return player.isInBubbleColumn();
    }

    @Override
    public boolean isInWaterOrRain() {
        return player.isInWaterOrRain();
    }

    @Override
    public boolean isInWaterOrBubbleColumn() {
        return player.isInWaterOrBubbleColumn();
    }

    @Override
    public boolean isInWaterOrRainOrBubbleColumn() {
        return player.isInWaterOrRainOrBubbleColumn();
    }

    @Override
    public boolean isInLava() {
        return player.isInLava();
    }

    @Override
    public boolean isTicking() {
        return player.isTicking();
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return player.serialize();
    }

    @Override
    public @NotNull String getName() {
        return player.getName();
    }

    @Override
    public @NotNull PlayerInventory getInventory() {
        return player.getInventory();
    }

    @Override
    public @NotNull Inventory getEnderChest() {
        return player.getEnderChest();
    }

    @Override
    public @NotNull MainHand getMainHand() {
        return player.getMainHand();
    }

    @Override
    public boolean setWindowProperty(InventoryView.@NotNull Property prop, int value) {
        return player.setWindowProperty(prop, value);
    }

    @Override
    public @NotNull InventoryView getOpenInventory() {
        return player.getOpenInventory();
    }

    @Override
    public @Nullable InventoryView openInventory(@NotNull Inventory inventory) {
        return player.openInventory(inventory);
    }

    @Override
    public @Nullable InventoryView openWorkbench(@Nullable Location location, boolean force) {
        return player.openWorkbench(location, force);
    }

    @Override
    public @Nullable InventoryView openEnchanting(@Nullable Location location, boolean force) {
        return player.openEnchanting(location, force);
    }

    @Override
    public void openInventory(@NotNull InventoryView inventory) {
        player.openInventory(inventory);
    }

    @Override
    public @Nullable InventoryView openMerchant(@NotNull Villager trader, boolean force) {
        return player.openMerchant(trader, force);
    }

    @Override
    public @Nullable InventoryView openMerchant(@NotNull Merchant merchant, boolean force) {
        return player.openMerchant(merchant, force);
    }

    @Override
    public @Nullable InventoryView openAnvil(@Nullable Location location, boolean force) {
        return player.openAnvil(location, force);
    }

    @Override
    public @Nullable InventoryView openCartographyTable(@Nullable Location location, boolean force) {
        return player.openCartographyTable(location, force);
    }

    @Override
    public @Nullable InventoryView openGrindstone(@Nullable Location location, boolean force) {
        return player.openGrindstone(location, force);
    }

    @Override
    public @Nullable InventoryView openLoom(@Nullable Location location, boolean force) {
        return player.openLoom(location, force);
    }

    @Override
    public @Nullable InventoryView openSmithingTable(@Nullable Location location, boolean force) {
        return player.openSmithingTable(location, force);
    }

    @Override
    public @Nullable InventoryView openStonecutter(@Nullable Location location, boolean force) {
        return player.openStonecutter(location, force);
    }

    @Override
    public void closeInventory() {
        player.closeInventory();
    }

    @Override
    public void closeInventory(InventoryCloseEvent.@NotNull Reason reason) {
        player.closeInventory(reason);
    }

    @Override
    public @NotNull ItemStack getItemInHand() {
        return player.getItemInHand();
    }

    @Override
    public void setItemInHand(@Nullable ItemStack item) {
        player.setItemInHand(item);
    }

    @Override
    public @NotNull ItemStack getItemOnCursor() {
        return player.getItemOnCursor();
    }

    @Override
    public void setItemOnCursor(@Nullable ItemStack item) {
        player.setItemOnCursor(item);
    }

    @Override
    public boolean hasCooldown(@NotNull Material material) {
        return player.hasCooldown(material);
    }

    @Override
    public int getCooldown(@NotNull Material material) {
        return player.getCooldown(material);
    }

    @Override
    public void setCooldown(@NotNull Material material, int ticks) {
        player.setCooldown(material, ticks);
    }

    @Override
    public boolean isDeeplySleeping() {
        return player.isDeeplySleeping();
    }

    @Override
    public int getSleepTicks() {
        return player.getSleepTicks();
    }

    @Override
    public @Nullable Location getPotentialBedLocation() {
        return player.getPotentialBedLocation();
    }

    @Override
    public boolean sleep(@NotNull Location location, boolean force) {
        return player.sleep(location, force);
    }

    @Override
    public void wakeup(boolean setSpawnLocation) {
        player.wakeup(setSpawnLocation);
    }

    @Override
    public @NotNull Location getBedLocation() {
        return player.getBedLocation();
    }

    @Override
    public @NotNull GameMode getGameMode() {
        return player.getGameMode();
    }

    @Override
    public void setGameMode(@NotNull GameMode mode) {
        player.setGameMode(mode);
    }

    @Override
    public boolean isBlocking() {
        return player.isBlocking();
    }

    @Override
    public double getEyeHeight() {
        return player.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return player.getEyeHeight(ignorePose);
    }

    @Override
    public @NotNull Location getEyeLocation() {
        return player.getEyeLocation();
    }

    @Override
    public @NotNull List<Block> getLineOfSight(@Nullable Set<Material> transparent, int maxDistance) {
        return player.getLineOfSight(transparent, maxDistance);
    }

    @Override
    public @NotNull Block getTargetBlock(@Nullable Set<Material> transparent, int maxDistance) {
        return player.getTargetBlock(transparent, maxDistance);
    }

    @Override
    public @Nullable Block getTargetBlock(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return player.getTargetBlock(maxDistance, fluidMode);
    }

    @Override
    public @Nullable BlockFace getTargetBlockFace(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return player.getTargetBlockFace(maxDistance, fluidMode);
    }

    @Override
    public @Nullable TargetBlockInfo getTargetBlockInfo(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return player.getTargetBlockInfo(maxDistance, fluidMode);
    }

    @Override
    public @Nullable Entity getTargetEntity(int maxDistance, boolean ignoreBlocks) {
        return player.getTargetEntity(maxDistance, ignoreBlocks);
    }

    @Override
    public @Nullable TargetEntityInfo getTargetEntityInfo(int maxDistance, boolean ignoreBlocks) {
        return player.getTargetEntityInfo(maxDistance, ignoreBlocks);
    }

    @Override
    public @NotNull List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> transparent, int maxDistance) {
        return player.getLastTwoTargetBlocks(transparent, maxDistance);
    }

    @Override
    public @Nullable Block getTargetBlockExact(int maxDistance) {
        return player.getTargetBlockExact(maxDistance);
    }

    @Override
    public @Nullable Block getTargetBlockExact(int maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        return player.getTargetBlockExact(maxDistance, fluidCollisionMode);
    }

    @Override
    public @Nullable RayTraceResult rayTraceBlocks(double maxDistance) {
        return player.rayTraceBlocks(maxDistance);
    }

    @Override
    public @Nullable RayTraceResult rayTraceBlocks(double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        return player.rayTraceBlocks(maxDistance, fluidCollisionMode);
    }

    @Override
    public int getRemainingAir() {
        return player.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int ticks) {
        player.setRemainingAir(ticks);
    }

    @Override
    public int getMaximumAir() {
        return player.getMaximumAir();
    }

    @Override
    public void setMaximumAir(int ticks) {
        player.setMaximumAir(ticks);
    }

    @Override
    public int getArrowCooldown() {
        return player.getArrowCooldown();
    }

    @Override
    public void setArrowCooldown(int ticks) {
        player.setArrowCooldown(ticks);
    }

    @Override
    public int getArrowsInBody() {
        return player.getArrowsInBody();
    }

    @Override
    public void setArrowsInBody(int count) {
        player.setArrowsInBody(count);
    }

    @Override
    public int getBeeStingerCooldown() {
        return player.getBeeStingerCooldown();
    }

    @Override
    public void setBeeStingerCooldown(int ticks) {
        player.setBeeStingerCooldown(ticks);
    }

    @Override
    public int getBeeStingersInBody() {
        return player.getBeeStingersInBody();
    }

    @Override
    public void setBeeStingersInBody(int count) {
        player.setBeeStingerCooldown(count);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return player.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        player.setMaximumNoDamageTicks(ticks);
    }

    @Override
    public double getLastDamage() {
        return player.getLastDamage();
    }

    @Override
    public void setLastDamage(double damage) {
        player.setLastDamage(damage);
    }

    @Override
    public int getNoDamageTicks() {
        return player.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        player.setNoDamageTicks(ticks);
    }

    @Override
    public @Nullable Player getKiller() {
        return player.getKiller();
    }

    @Override
    public void setKiller(@Nullable Player killer) {
        player.setKiller(killer);
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect effect) {
        return player.addPotionEffect(effect);
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect effect, boolean force) {
        return player.addPotionEffect(effect, force);
    }

    @Override
    public boolean addPotionEffects(@NotNull Collection<PotionEffect> effects) {
        return player.addPotionEffects(effects);
    }

    @Override
    public boolean hasPotionEffect(@NotNull PotionEffectType type) {
        return player.hasPotionEffect(type);
    }

    @Override
    public @Nullable PotionEffect getPotionEffect(@NotNull PotionEffectType type) {
        return player.getPotionEffect(type);
    }

    @Override
    public void removePotionEffect(@NotNull PotionEffectType type) {
        player.removePotionEffect(type);
    }

    @Override
    public @NotNull Collection<PotionEffect> getActivePotionEffects() {
        return player.getActivePotionEffects();
    }

    @Override
    public boolean hasLineOfSight(@NotNull Entity other) {
        return player.hasLineOfSight(other);
    }

    @Override
    public boolean hasLineOfSight(@NotNull Location location) {
        return player.hasLineOfSight(location);
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return player.getRemoveWhenFarAway();
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        player.setRemoveWhenFarAway(remove);
    }

    @Override
    public @Nullable EntityEquipment getEquipment() {
        return player.getEquipment();
    }

    @Override
    public boolean getCanPickupItems() {
        return player.getCanPickupItems();
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        player.setCanPickupItems(pickup);
    }

    @Override
    public boolean isLeashed() {
        return player.isLeashed();
    }

    @Override
    public @NotNull Entity getLeashHolder() throws IllegalStateException {
        return player.getLeashHolder();
    }

    @Override
    public boolean setLeashHolder(@Nullable Entity holder) {
        return player.setLeashHolder(holder);
    }

    @Override
    public boolean isGliding() {
        return player.isGliding();
    }

    @Override
    public void setGliding(boolean gliding) {
        player.setGliding(gliding);
    }

    @Override
    public boolean isSwimming() {
        return player.isSwimming();
    }

    @Override
    public void setSwimming(boolean swimming) {
        player.setSwimming(swimming);
    }

    @Override
    public boolean isRiptiding() {
        return player.isRiptiding();
    }

    @Override
    public boolean isSleeping() {
        return player.isSleeping();
    }

    @Override
    public boolean isClimbing() {
        return player.isClimbing();
    }

    @Override
    public void setAI(boolean ai) {
        player.setAI(ai);
    }

    @Override
    public boolean hasAI() {
        return player.hasAI();
    }

    @Override
    public void attack(@NotNull Entity target) {
        player.attack(target);
    }

    @Override
    public void swingMainHand() {
        player.swingMainHand();
    }

    @Override
    public void swingOffHand() {
        player.swingOffHand();
    }

    @Override
    public boolean isCollidable() {
        return player.isCollidable();
    }

    @Override
    public void setCollidable(boolean collidable) {
        player.setCollidable(collidable);
    }

    @Override
    public @NotNull Set<UUID> getCollidableExemptions() {
        return player.getCollidableExemptions();
    }

    @Override
    public <T> @Nullable T getMemory(@NotNull MemoryKey<T> memoryKey) {
        return player.getMemory(memoryKey);
    }

    @Override
    public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T memoryValue) {
        player.setMemory(memoryKey, memoryValue);
    }

    @Override
    public @NotNull EntityCategory getCategory() {
        return player.getCategory();
    }

    @Override
    public boolean isInvisible() {
        return player.isInvisible();
    }

    @Override
    public void setInvisible(boolean invisible) {
        player.setInvulnerable(invisible);
    }

    @Override
    public int getArrowsStuck() {
        return player.getArrowsStuck();
    }

    @Override
    public void setArrowsStuck(int arrows) {
        player.setArrowsStuck(arrows);
    }

    @Override
    public int getShieldBlockingDelay() {
        return player.getShieldBlockingDelay();
    }

    @Override
    public void setShieldBlockingDelay(int delay) {
        player.setShieldBlockingDelay(delay);
    }

    @Override
    public @Nullable ItemStack getActiveItem() {
        return player.getActiveItem();
    }

    @Override
    public void clearActiveItem() {
        player.clearActiveItem();
    }

    @Override
    public int getItemUseRemainingTime() {
        return player.getItemUseRemainingTime();
    }

    @Override
    public int getHandRaisedTime() {
        return player.getHandRaisedTime();
    }

    @Override
    public boolean isHandRaised() {
        return player.isHandRaised();
    }

    @Override
    public @NotNull EquipmentSlot getHandRaised() {
        return player.getHandRaised();
    }

    @Override
    public boolean isJumping() {
        return player.isJumping();
    }

    @Override
    public void setJumping(boolean jumping) {
        player.setJumping(jumping);
    }

    @Override
    public void playPickupItemAnimation(@NotNull Item item, int quantity) {
        player.playPickupItemAnimation(item, quantity);
    }

    @Override
    public float getHurtDirection() {
        return player.getHurtDirection();
    }

    @Override
    public void setHurtDirection(float hurtDirection) {
        player.setHurtDirection(hurtDirection);
    }

    @Override
    public @Nullable ItemStack getItemInUse() {
        return player.getItemInUse();
    }

    @Override
    public int getExpToLevel() {
        return player.getExpToLevel();
    }

    @Override
    public @Nullable Entity releaseLeftShoulderEntity() {
        return player.releaseLeftShoulderEntity();
    }

    @Override
    public @Nullable Entity releaseRightShoulderEntity() {
        return player.releaseRightShoulderEntity();
    }

    @Override
    public float getAttackCooldown() {
        return player.getAttackCooldown();
    }

    @Override
    public boolean discoverRecipe(@NotNull NamespacedKey recipe) {
        return player.discoverRecipe(recipe);
    }

    @Override
    public int discoverRecipes(@NotNull Collection<NamespacedKey> recipes) {
        return player.discoverRecipes(recipes);
    }

    @Override
    public boolean undiscoverRecipe(@NotNull NamespacedKey recipe) {
        return player.undiscoverRecipe(recipe);
    }

    @Override
    public int undiscoverRecipes(@NotNull Collection<NamespacedKey> recipes) {
        return player.undiscoverRecipes(recipes);
    }

    @Override
    public boolean hasDiscoveredRecipe(@NotNull NamespacedKey recipe) {
        return player.hasDiscoveredRecipe(recipe);
    }

    @Override
    public @NotNull Set<NamespacedKey> getDiscoveredRecipes() {
        return player.getDiscoveredRecipes();
    }

    @Override
    public @Nullable Entity getShoulderEntityLeft() {
        return player.getShoulderEntityLeft();
    }

    @Override
    public void setShoulderEntityLeft(@Nullable Entity entity) {
        player.setShoulderEntityLeft(entity);
    }

    @Override
    public @Nullable Entity getShoulderEntityRight() {
        return player.getShoulderEntityRight();
    }

    @Override
    public void setShoulderEntityRight(@Nullable Entity entity) {
        player.setShoulderEntityRight(entity);
    }

    @Override
    public void openSign(@NotNull Sign sign) {
        player.openSign(sign);
    }

    @Override
    public boolean dropItem(boolean dropAll) {
        return player.dropItem(dropAll);
    }

    @Override
    public float getExhaustion() {
        return player.getExhaustion();
    }

    @Override
    public void setExhaustion(float value) {
        player.setExhaustion(value);
    }

    @Override
    public float getSaturation() {
        return player.getSaturation();
    }

    @Override
    public void setSaturation(float value) {
        player.setSaturation(value);
    }

    @Override
    public int getFoodLevel() {
        return player.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int value) {
        player.setFoodLevel(value);
    }

    @Override
    public int getSaturatedRegenRate() {
        return player.getSaturatedRegenRate();
    }

    @Override
    public void setSaturatedRegenRate(int ticks) {
        player.setSaturatedRegenRate(ticks);
    }

    @Override
    public int getUnsaturatedRegenRate() {
        return player.getUnsaturatedRegenRate();
    }

    @Override
    public void setUnsaturatedRegenRate(int ticks) {
        player.setUnsaturatedRegenRate(ticks);
    }

    @Override
    public int getStarvationRate() {
        return player.getStarvationRate();
    }

    @Override
    public void setStarvationRate(int ticks) {
        player.setStarvationRate(ticks);
    }

    @Override
    public @Nullable AttributeInstance getAttribute(@NotNull Attribute attribute) {
        return player.getAttribute(attribute);
    }

    @Override
    public void registerAttribute(@NotNull Attribute attribute) {
        player.registerAttribute(attribute);
    }

    @Override
    public void damage(double amount) {
        player.damage(amount);
    }

    @Override
    public void damage(double amount, @Nullable Entity source) {
        player.damage(amount, source);
    }

    @Override
    public double getHealth() {
        return player.getHealth();
    }

    @Override
    public void setHealth(double health) {
        player.setHealth(health);
    }

    @Override
    public double getAbsorptionAmount() {
        return player.getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        player.setAbsorptionAmount(amount);
    }

    @Override
    public double getMaxHealth() {
        return player.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        player.setMaxHealth(health);
    }

    @Override
    public void resetMaxHealth() {
        player.resetMaxHealth();
    }

    @Override
    public @Nullable Component customName() {
        return player.customName();
    }

    @Override
    public void customName(@Nullable Component customName) {
        player.customName(customName);
    }

    @Override
    public @Nullable String getCustomName() {
        return player.getCustomName();
    }

    @Override
    public void setCustomName(@Nullable String name) {
        player.setCustomName(name);
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        player.setMetadata(metadataKey, newMetadataValue);
    }

    @Override
    public @NotNull List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        return player.getMetadata(metadataKey);
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        return player.hasMetadata(metadataKey);
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        player.removeMetadata(metadataKey, owningPlugin);
    }

    @Override
    public boolean isPermissionSet(@NotNull String name) {
        return player.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm) {
        return player.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(@NotNull String name) {
        return player.hasPermission(name);
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        return player.hasPermission(perm);
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        return player.addAttachment(plugin, name, value);
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return player.addAttachment(plugin);
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        return player.addAttachment(plugin, name, value, ticks);
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        return player.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {
        player.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        player.recalculatePermissions();
    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return player.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return player.isOp();
    }

    @Override
    public void setOp(boolean value) {
        player.setOp(value);
    }

    @Override
    public @NotNull PersistentDataContainer getPersistentDataContainer() {
        return player.getPersistentDataContainer();
    }

    @Override
    public void sendPluginMessage(@NotNull Plugin source, @NotNull String channel, @NotNull byte[] message) {
        player.sendPluginMessage(source, channel, message);
    }

    @Override
    public @NotNull Set<String> getListeningPluginChannels() {
        return player.getListeningPluginChannels();
    }

    @Override
    public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile) {
        return player.launchProjectile(projectile);
    }

    @Override
    public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile, @Nullable Vector velocity) {
        return player.launchProjectile(projectile, velocity);
    }
}
