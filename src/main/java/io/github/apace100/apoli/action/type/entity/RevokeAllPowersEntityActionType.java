package io.github.apace100.apoli.action.type.entity;

import io.github.apace100.apoli.action.ActionConfiguration;
import io.github.apace100.apoli.action.type.EntityActionType;
import io.github.apace100.apoli.action.type.EntityActionTypes;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import io.github.apace100.calio.registry.DataObjectFactory;
import io.github.apace100.calio.registry.SimpleDataObjectFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class RevokeAllPowersEntityActionType extends EntityActionType {

    public static final DataObjectFactory<RevokeAllPowersEntityActionType> DATA_FACTORY = new SimpleDataObjectFactory<>(
        new SerializableData()
            .add("source", SerializableDataTypes.IDENTIFIER),
        data -> new RevokeAllPowersEntityActionType(
            data.get("source")
        ),
        (actionType, serializableData) -> serializableData.instance()
            .set("source", actionType.source)
    );

    private final Identifier source;

    public RevokeAllPowersEntityActionType(Identifier source) {
        this.source = source;
    }

    @Override
    protected void execute(Entity entity) {
        PowerHolderComponent.revokeAllPowersFromSource(entity, source, true);
    }

    @Override
    public ActionConfiguration<?> configuration() {
        return EntityActionTypes.REVOKE_ALL_POWERS;
    }

}
