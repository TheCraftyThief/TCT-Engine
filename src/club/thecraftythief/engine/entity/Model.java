package club.thecraftythief.engine.entity;

import com.destroystokyo.paper.block.TargetBlockInfo;
import com.destroystokyo.paper.entity.TargetEntityInfo;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Model implements ArmorStand {

    private ArmorStand model;
    public Model(ArmorStand model) {
        this.model = model;
    }

    @Override
    public @NotNull ItemStack getItemInHand() {
        return model.getItemInHand();
    }

    @Override
    public void setItemInHand(@Nullable ItemStack item) {
        model.setItemInHand(item);
    }

    @Override
    public @NotNull ItemStack getBoots() {
        return model.getBoots();
    }

    @Override
    public void setBoots(@Nullable ItemStack item) {
        model.setBoots(item);
    }

    @Override
    public @NotNull ItemStack getLeggings() {
        return model.getLeggings();
    }

    @Override
    public void setLeggings(@Nullable ItemStack item) {
        model.setLeggings(item);
    }

    @Override
    public @NotNull ItemStack getChestplate() {
        return model.getChestplate();
    }

    @Override
    public void setChestplate(@Nullable ItemStack item) {
        model.setChestplate(item);
    }

    @Override
    public @NotNull ItemStack getHelmet() {
        return model.getHelmet();
    }

    @Override
    public void setHelmet(@Nullable ItemStack item) {
        model.setHelmet(item);
    }

    @Override
    public @NotNull EulerAngle getBodyPose() {
        return model.getBodyPose();
    }

    @Override
    public void setBodyPose(@NotNull EulerAngle pose) {
        model.setBodyPose(pose);
    }

    @Override
    public @NotNull EulerAngle getLeftArmPose() {
        return model.getLeftArmPose();
    }

    @Override
    public void setLeftArmPose(@NotNull EulerAngle pose) {
        model.setLeftArmPose(pose);
    }

    @Override
    public @NotNull EulerAngle getRightArmPose() {
        return model.getRightArmPose();
    }

    @Override
    public void setRightArmPose(@NotNull EulerAngle pose) {
        model.setRightArmPose(pose);
    }

    @Override
    public @NotNull EulerAngle getLeftLegPose() {
        return model.getLeftLegPose();
    }

    @Override
    public void setLeftLegPose(@NotNull EulerAngle pose) {
        model.setLeftLegPose(pose);
    }

    @Override
    public @NotNull EulerAngle getRightLegPose() {
        return model.getRightLegPose();
    }

    @Override
    public void setRightLegPose(@NotNull EulerAngle pose) {
        model.setRightLegPose(pose);
    }

    @Override
    public @NotNull EulerAngle getHeadPose() {
        return model.getHeadPose();
    }

    @Override
    public void setHeadPose(@NotNull EulerAngle pose) {
        model.setHeadPose(pose);
    }

    @Override
    public boolean hasBasePlate() {
        return model.hasBasePlate();
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        model.setBasePlate(basePlate);
    }

    @Override
    public boolean isVisible() {
        return model.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        model.setVisible(visible);
    }

    @Override
    public boolean hasArms() {
        return model.hasArms();
    }

    @Override
    public void setArms(boolean arms) {
        model.setArms(arms);
    }

    @Override
    public boolean isSmall() {
        return model.isSmall();
    }

    @Override
    public void setSmall(boolean small) {
        model.setSmall(small);
    }

    @Override
    public boolean isMarker() {
        return model.isMarker();
    }

    @Override
    public void setMarker(boolean marker) {
        model.setMarker(marker);
    }

    @Override
    public void addEquipmentLock(@NotNull EquipmentSlot slot, @NotNull LockType lockType) {
        model.addEquipmentLock(slot, lockType);
    }

    @Override
    public void removeEquipmentLock(@NotNull EquipmentSlot slot, @NotNull LockType lockType) {
        model.removeEquipmentLock(slot, lockType);
    }

    @Override
    public boolean hasEquipmentLock(@NotNull EquipmentSlot slot, @NotNull LockType lockType) {
        return model.hasEquipmentLock(slot, lockType);
    }

    @Override
    public boolean canMove() {
        return model.canMove();
    }

    @Override
    public void setCanMove(boolean move) {
        model.setCanMove(move);
    }

    @Override
    public boolean canTick() {
        return model.canTick();
    }

    @Override
    public void setCanTick(boolean tick) {
        model.setCanTick(tick);
    }

    @Override
    public @NotNull ItemStack getItem(@NotNull EquipmentSlot slot) {
        return model.getItem(slot);
    }

    @Override
    public void setItem(@NotNull EquipmentSlot slot, @Nullable ItemStack item) {
        model.setItem(slot, item);
    }

    @Override
    public @NotNull Set<EquipmentSlot> getDisabledSlots() {
        return model.getDisabledSlots();
    }

    @Override
    public void setDisabledSlots(@NotNull EquipmentSlot... slots) {
        model.setDisabledSlots(slots);
    }

    @Override
    public void addDisabledSlots(@NotNull EquipmentSlot... slots) {
        model.addDisabledSlots(slots);
    }

    @Override
    public void removeDisabledSlots(@NotNull EquipmentSlot... slots) {
        model.removeDisabledSlots(slots);
    }

    @Override
    public boolean isSlotDisabled(@NotNull EquipmentSlot slot) {
        return model.isSlotDisabled(slot);
    }

    @Override
    public double getEyeHeight() {
        return model.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return model.getEyeHeight(ignorePose);
    }

    @Override
    public @NotNull Location getEyeLocation() {
        return model.getEyeLocation();
    }

    @Override
    public @NotNull List<Block> getLineOfSight(@Nullable Set<Material> transparent, int maxDistance) {
        return model.getLineOfSight(transparent, maxDistance);
    }

    @Override
    public @NotNull Block getTargetBlock(@Nullable Set<Material> transparent, int maxDistance) {
        return model.getTargetBlock(transparent, maxDistance);
    }

    @Override
    public @Nullable Block getTargetBlock(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return model.getTargetBlock(maxDistance, fluidMode);
    }

    @Override
    public @Nullable BlockFace getTargetBlockFace(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return model.getTargetBlockFace(maxDistance, fluidMode);
    }

    @Override
    public @Nullable TargetBlockInfo getTargetBlockInfo(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return model.getTargetBlockInfo(maxDistance, fluidMode);
    }

    @Override
    public @Nullable Entity getTargetEntity(int maxDistance, boolean ignoreBlocks) {
        return model.getTargetEntity(maxDistance, ignoreBlocks);
    }

    @Override
    public @Nullable TargetEntityInfo getTargetEntityInfo(int maxDistance, boolean ignoreBlocks) {
        return model.getTargetEntityInfo(maxDistance, ignoreBlocks);
    }

    @Override
    public @NotNull List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> transparent, int maxDistance) {
        return model.getLastTwoTargetBlocks(transparent, maxDistance);
    }

    @Override
    public @Nullable Block getTargetBlockExact(int maxDistance) {
        return model.getTargetBlockExact(maxDistance);
    }

    @Override
    public @Nullable Block getTargetBlockExact(int maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        return model.getTargetBlockExact(maxDistance, fluidCollisionMode);
    }

    @Override
    public @Nullable RayTraceResult rayTraceBlocks(double maxDistance) {
        return model.rayTraceBlocks(maxDistance);
    }

    @Override
    public @Nullable RayTraceResult rayTraceBlocks(double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        return model.rayTraceBlocks(maxDistance, fluidCollisionMode);
    }

    @Override
    public int getRemainingAir() {
        return model.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int ticks) {
        model.setRemainingAir(ticks);
    }

    @Override
    public int getMaximumAir() {
        return model.getMaximumAir();
    }


    //TODO: PVP PLEASE FINISH IMPLEMENTINg THIS!!! <33 -ASM
    @Override
    public void setMaximumAir(int ticks) {
        model.setMaximumAir(ticks);
    }

    @Override
    public int getArrowCooldown() {
        return model.getArrowCooldown();
    }

    @Override
    public void setArrowCooldown(int ticks) {
        model.setArrowCooldown(ticks);
    }

    @Override
    public int getArrowsInBody() {
        return model.getArrowsInBody();
    }

    @Override
    public void setArrowsInBody(int count) {
        model.setArrowsInBody(count);
    }

    @Override
    public int getBeeStingerCooldown() {
         return model.getBeeStingerCooldown();
    }

    @Override
    public void setBeeStingerCooldown(int ticks) {
        model.setBeeStingerCooldown(ticks);
    }

    @Override
    public int getBeeStingersInBody() {
        return model.getBeeStingersInBody();
    }

    @Override
    public void setBeeStingersInBody(int count) {
        model.setBeeStingersInBody(count);
    }

    @Override
    public int getMaximumNoDamageTicks() {return model.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        model.setMaximumNoDamageTicks(ticks);
    }

    @Override
    public double getLastDamage() {
        return model.getLastDamage();
    }

    @Override
    public void setLastDamage(double damage) {
        model.setLastDamage(damage);
    }

    @Override
    public int getNoDamageTicks() {
        return model.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        model.setNoDamageTicks(ticks);
    }

    @Override
    public @Nullable Player getKiller() {
        return model.getKiller();
    }

    @Override
    public void setKiller(@Nullable Player killer) {
        model.setKiller(killer);
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect effect) {
        return model.addPotionEffect(effect);
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect effect, boolean force) {
        return model.addPotionEffect(effect, force);
    }

    @Override
    public boolean addPotionEffects(@NotNull Collection<PotionEffect> effects) {
        return model.addPotionEffects(effects);
    }

    @Override
    public boolean hasPotionEffect(@NotNull PotionEffectType type) {
        return model.hasPotionEffect(type);
    }

    @Override
    public @Nullable PotionEffect getPotionEffect(@NotNull PotionEffectType type) {
        return model.getPotionEffect(type);
    }

    @Override
    public void removePotionEffect(@NotNull PotionEffectType type) {
        model.removePotionEffect(type);
    }

    @Override
    public @NotNull Collection<PotionEffect> getActivePotionEffects() {
        return model.getActivePotionEffects();
    }

    @Override
    public boolean hasLineOfSight(@NotNull Entity other) {
        return model.hasLineOfSight(other);
    }

    @Override
    public boolean hasLineOfSight(@NotNull Location location) {
        return model.hasLineOfSight(location);
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return model.getRemoveWhenFarAway();
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        model.setRemoveWhenFarAway(remove);
    }

    @Override
    public @Nullable EntityEquipment getEquipment() {
        return model.getEquipment();
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        model.setCanPickupItems(pickup);
    }

    @Override
    public boolean getCanPickupItems() {
        return model.getCanPickupItems();
    }

    @Override
    public boolean isLeashed() {
        return model.isLeashed();
    }

    @Override
    public @NotNull Entity getLeashHolder() throws IllegalStateException {
        return model.getLeashHolder();
    }

    @Override
    public boolean setLeashHolder(@Nullable Entity holder) {
        return model.setLeashHolder(holder);
    }

    @Override
    public boolean isGliding() {
        return model.isGliding();
    }

    @Override
    public void setGliding(boolean gliding) {
        model.setGliding(gliding);
    }

    @Override
    public boolean isSwimming() {
        return model.isSwimming();
    }

    @Override
    public void setSwimming(boolean swimming) {
        model.setSwimming(swimming);
    }

    @Override
    public boolean isRiptiding() {
        return model.isRiptiding();
    }

    @Override
    public boolean isSleeping() {
        return model.isSleeping();
    }

    @Override
    public boolean isClimbing() {
        return model.isClimbing();
    }

    @Override
    public void setAI(boolean ai) {
        model.setAI(ai);
    }

    @Override
    public boolean hasAI() {
        return model.hasAI();
    }

    @Override
    public void attack(@NotNull Entity target) {
        model.attack(target);
    }

    @Override
    public void swingMainHand() {
        model.swingMainHand();
    }

    @Override
    public void swingOffHand() {
        swingOffHand();
    }

    @Override
    public void setCollidable(boolean collidable) {
        model.setCollidable(collidable);
    }

    @Override
    public boolean isCollidable() {
        return model.isCollidable();
    }

    @Override
    public @NotNull Set<UUID> getCollidableExemptions() {
        return model.getCollidableExemptions();
    }

    @Override
    public <T> @Nullable T getMemory(@NotNull MemoryKey<T> memoryKey) {
        return model.getMemory(memoryKey);
    }

    @Override
    public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T memoryValue) {
        model.setMemory(memoryKey, memoryValue);
    }

    @Override
    public @NotNull EntityCategory getCategory() {
        return model.getCategory();
    }

    @Override
    public void setInvisible(boolean invisible) {
        model.setInvisible(invisible);
    }

    @Override
    public boolean isInvisible() {
        return model.isInvisible();
    }

    @Override
    public int getArrowsStuck() {
        return model.getArrowsStuck();
    }

    @Override
    public void setArrowsStuck(int arrows) {
        model.setArrowsStuck(arrows);
    }

    @Override
    public int getShieldBlockingDelay() {
        return model.getShieldBlockingDelay();
    }

    @Override
    public void setShieldBlockingDelay(int delay) {
        model.setShieldBlockingDelay(delay);
    }

    @Override
    public @Nullable ItemStack getActiveItem() {
        return model.getActiveItem();
    }

    @Override
    public void clearActiveItem() {
        model.clearActiveItem();
    }

    @Override
    public int getItemUseRemainingTime() {
        return model.getItemUseRemainingTime();
    }

    @Override
    public int getHandRaisedTime() {
        return model.getHandRaisedTime();
    }

    @Override
    public boolean isHandRaised() {
        return model.isHandRaised();
    }

    @Override
    public @NotNull EquipmentSlot getHandRaised() {
        return model.getHandRaised();
    }

    @Override
    public boolean isJumping() {
        return model.isJumping();
    }

    @Override
    public void setJumping(boolean jumping) {
        model.setJumping(jumping);
    }

    @Override
    public void playPickupItemAnimation(@NotNull Item item, int quantity) {
        model.playPickupItemAnimation(item, quantity);
    }

    @Override
    public float getHurtDirection() {
        return model.getHurtDirection();
    }

    @Override
    public void setHurtDirection(float hurtDirection) {
        model.setHurtDirection(hurtDirection);
    }

    @Override
    public @Nullable AttributeInstance getAttribute(@NotNull Attribute attribute) {
        return model.getAttribute(attribute);
    }

    @Override
    public void registerAttribute(@NotNull Attribute attribute) {
        model.registerAttribute(attribute);
    }

    @Override
    public void damage(double amount) {
        model.damage(amount);
    }

    @Override
    public void damage(double amount, @Nullable Entity source) {
        model.damage(amount, source);
    }

    @Override
    public double getHealth() {return model.getHealth();
    }

    @Override
    public void setHealth(double health) {
        model.setHealth(health);
    }

    @Override
    public double getAbsorptionAmount() {
        return model.getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        model.setAbsorptionAmount(amount);
    }

    @Override
    public double getMaxHealth() {
        return model.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        model.setMaxHealth(health);
    }

    @Override
    public void resetMaxHealth() {
        model.resetMaxHealth();
    }

    @Override
    public @NotNull Location getLocation() {
        return model.getLocation();
    }

    @Override
    public @Nullable Location getLocation(@Nullable Location loc) {
        return model.getLocation(loc);
    }

    @Override
    public void setVelocity(@NotNull Vector velocity) {
        model.setVelocity(velocity);
    }

    @Override
    public @NotNull Vector getVelocity() {
        return model.getVelocity();
    }

    @Override
    public double getHeight() {
        return model.getHeight();
    }

    @Override
    public double getWidth() {
        return model.getWidth();
    }

    @Override
    public @NotNull BoundingBox getBoundingBox() {
        return model.getBoundingBox();
    }

    @Override
    public boolean isOnGround() {
        return model.isOnGround();
    }

    @Override
    public boolean isInWater() {
        return model.isInWater();
    }

    @Override
    public @NotNull World getWorld() {
        return model.getWorld();
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        model.setRotation(yaw, pitch);
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        return model.teleport(location);
    }

    @Override
    public boolean teleport(@NotNull Location location, PlayerTeleportEvent.@NotNull TeleportCause cause) {
        return model.teleport(location, cause);
    }

    @Override
    public boolean teleport(@NotNull Entity destination) {
        return model.teleport(destination);
    }

    @Override
    public boolean teleport(@NotNull Entity destination, PlayerTeleportEvent.@NotNull TeleportCause cause) {
        return model.teleport(destination, cause);
    }

    @Override
    public @NotNull List<Entity> getNearbyEntities(double x, double y, double z) {
        return model.getNearbyEntities(x, y, z);
    }

    @Override
    public int getEntityId() {
        return model.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return model.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return model.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        model.setFireTicks(ticks);
    }

    @Override
    public void setVisualFire(boolean fire) {
        model.setVisualFire(fire);
    }

    @Override
    public boolean isVisualFire() {
        return model.isVisualFire();
    }

    @Override
    public int getFreezeTicks() {
        return model.getFreezeTicks();
    }

    @Override
    public int getMaxFreezeTicks() {
        return model.getMaxFreezeTicks();
    }

    @Override
    public void setFreezeTicks(int ticks) {
        model.setFreezeTicks(ticks);
    }

    @Override
    public boolean isFrozen() {
        return model.isFrozen();
    }

    @Override
    public void remove() {
        model.remove();
    }

    @Override
    public boolean isDead() {
        return model.isDead();
    }

    @Override
    public boolean isValid() {
        return model.isValid();
    }

    @Override
    public void sendMessage(@NotNull String message) {
        model.sendMessage(message);
    }

    @Override
    public void sendMessage(@NotNull String... messages) {
        model.sendMessage(messages);
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {
        model.sendMessage(sender, message);
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String... messages) {
        model.sendMessage(sender, messages);
    }

    @Override
    public @NotNull Server getServer() {
        return model.getServer();
    }

    @Override
    public @NotNull String getName() {
        return model.getName();
    }

    @Override
    public boolean isPersistent() {
        return model.isPersistent();
    }

    @Override
    public void setPersistent(boolean persistent) {
        model.setPersistent(persistent);
    }

    @Override
    public @Nullable Entity getPassenger() {
        return model.getPassenger();
    }

    @Override
    public boolean setPassenger(@NotNull Entity passenger) {
        return model.setPassenger(passenger);
    }

    @Override
    public @NotNull List<Entity> getPassengers() {
        return model.getPassengers();
    }

    @Override
    public boolean addPassenger(@NotNull Entity passenger) {
        return model.addPassenger(passenger);
    }

    @Override
    public boolean removePassenger(@NotNull Entity passenger) {
        return model.removePassenger(passenger);
    }

    @Override
    public boolean isEmpty() {
        return model.isEmpty();
    }

    @Override
    public boolean eject() {
        return model.eject();
    }

    @Override
    public float getFallDistance() {
        return model.getFallDistance();
    }

    @Override
    public void setFallDistance(float distance) {
        model.setFallDistance(distance);
    }

    @Override
    public void setLastDamageCause(@Nullable EntityDamageEvent event) {
        model.setLastDamageCause(event);
    }

    @Override
    public @Nullable EntityDamageEvent getLastDamageCause() {
        return model.getLastDamageCause();
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return model.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return model.getTicksLived();
    }

    @Override
    public void setTicksLived(int value) {
        model.setTicksLived(value);
    }

    @Override
    public void playEffect(@NotNull EntityEffect type) {
        model.playEffect(type);
    }

    @Override
    public @NotNull EntityType getType() {
        return model.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return model.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return model.leaveVehicle();
    }

    @Override
    public @Nullable Entity getVehicle() {
        return model.getVehicle();
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        model.setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return model.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean flag) {
        model.setGlowing(flag);
    }

    @Override
    public boolean isGlowing() {
        return model.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        model.setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return model.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return model.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        model.setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return model.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        model.setGravity(gravity);
    }

    @Override
    public int getPortalCooldown() {
        return model.getPortalCooldown();
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        model.setPortalCooldown(cooldown);
    }

    @Override
    public @NotNull Set<String> getScoreboardTags() {
        return model.getScoreboardTags();
    }

    @Override
    public boolean addScoreboardTag(@NotNull String tag) {
        return model.addScoreboardTag(tag);
    }

    @Override
    public boolean removeScoreboardTag(@NotNull String tag) {
        return model.removeScoreboardTag(tag);
    }

    @Override
    public @NotNull PistonMoveReaction getPistonMoveReaction() {
        return model.getPistonMoveReaction();
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return model.getFacing();
    }

    @Override
    public @NotNull Pose getPose() {
        return model.getPose();
    }

    @Override
    public @NotNull Spigot spigot() {
        return model.spigot();
    }

    @Override
    public @Nullable Location getOrigin() {
        return model.getOrigin();
    }

    @Override
    public boolean fromMobSpawner() {
        return model.fromMobSpawner();
    }

    @Override
    public CreatureSpawnEvent.@NotNull SpawnReason getEntitySpawnReason() {
        return model.getEntitySpawnReason();
    }

    @Override
    public boolean isInRain() {
        return model.isInRain();
    }

    @Override
    public boolean isInBubbleColumn() {
        return model.isInBubbleColumn();
    }

    @Override
    public boolean isInWaterOrRain() {
        return model.isInWaterOrRain();
    }

    @Override
    public boolean isInWaterOrBubbleColumn() {
        return model.isInWaterOrBubbleColumn();
    }

    @Override
    public boolean isInWaterOrRainOrBubbleColumn() {
        return model.isInWaterOrRainOrBubbleColumn();
    }

    @Override
    public boolean isInLava() {
        return model.isInLava();
    }

    @Override
    public boolean isTicking() {
        return model.isTicking();
    }

    @Override
    public @Nullable Component customName() {
        return model.customName();
    }

    @Override
    public void customName(@Nullable Component customName) {
        model.customName(customName);
    }

    @Override
    public @Nullable String getCustomName() {
        return model.getCustomName();
    }

    @Override
    public void setCustomName(@Nullable String name) {
        model.setCustomName(name);
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        model.setMetadata(metadataKey, newMetadataValue);
    }

    @Override
    public @NotNull List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        return model.getMetadata(metadataKey);
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        return model.hasMetadata(metadataKey);
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        model.removeMetadata(metadataKey, owningPlugin);
    }

    @Override
    public boolean isPermissionSet(@NotNull String name) {
        return model.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm) {
        return model.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(@NotNull String name) {
        return model.hasPermission(name);
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        return model.hasPermission(perm);
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        return model.addAttachment(plugin, name, value);
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return model.addAttachment(plugin);
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        return model.addAttachment(plugin, name, value, ticks);
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        return model.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {
        model.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        model.recalculatePermissions();
    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return model.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return model.isOp();
    }

    @Override
    public void setOp(boolean value) {
        model.setOp(value);
    }

    @Override
    public @NotNull PersistentDataContainer getPersistentDataContainer() {
        return model.getPersistentDataContainer();
    }

    @Override
    public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile) {
        return model.launchProjectile(projectile);
    }

    @Override
    public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile, @Nullable Vector velocity) {
        return model.launchProjectile(projectile, velocity);
    }
}
