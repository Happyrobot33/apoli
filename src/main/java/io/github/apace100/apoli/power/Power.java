package io.github.apace100.apoli.power;

import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Power {

    protected LivingEntity entity;
    protected PowerType type;

    private boolean shouldTick = false;
    private boolean shouldTickWhenInactive = false;

    protected List<Predicate<Entity>> conditions;

    public Power(PowerType type, LivingEntity entity) {
        this.type = type;
        this.entity = entity;
        this.conditions = new LinkedList<>();
    }

    public Power addCondition(Predicate<Entity> condition) {
        this.conditions.add(condition);
        return this;
    }

    protected void setTicking() {
        this.setTicking(false);
    }

    protected void setTicking(boolean evenWhenInactive) {
        this.shouldTick = true;
        this.shouldTickWhenInactive = evenWhenInactive;
    }

    public boolean shouldTick() {
        return shouldTick;
    }

    public boolean shouldTickWhenInactive() {
        return shouldTickWhenInactive;
    }

    public void tick() {

    }

    public void onGained() {

    }

    public void onLost() {

    }

    public void onAdded() {

    }

    public void onAdded(boolean onSync) {

    }

    public void onRemoved() {

    }

    public void onRemoved(boolean onSync) {

    }

    public void onRespawn() {

    }

    public boolean isActive() {
        return conditions.stream().allMatch(condition -> condition.test(entity));
    }

    public NbtElement toTag(boolean onSync) {
        return this.toTag();
    }

    public NbtElement toTag() {
        return new NbtCompound();
    }

    public void fromTag(NbtElement tag, boolean onSync) {
        this.fromTag(tag);
    }

    public void fromTag(NbtElement tag) {

    }

    public PowerType getType() {
        return type;
    }

    public Identifier getId() {
        return this.getType().getId();
    }

    @Deprecated(forRemoval = true)
    public static PowerFactory createSimpleFactory(BiFunction<PowerType, LivingEntity, Power> powerConstructor, Identifier id) {
        return createSimpleFactory(id, powerConstructor);
    }

    public static PowerFactory createSimpleFactory(Identifier id, BiFunction<PowerType, LivingEntity, Power> powerConstructor) {
        return new PowerFactory<>(id, new SerializableData(), data -> powerConstructor).allowCondition();
    }

}
