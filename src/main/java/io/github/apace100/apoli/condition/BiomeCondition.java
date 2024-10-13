package io.github.apace100.apoli.condition;

import io.github.apace100.apoli.condition.context.BiomeContext;
import io.github.apace100.apoli.condition.type.BiomeConditionType;
import io.github.apace100.apoli.condition.type.BiomeConditionTypes;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.calio.data.CompoundSerializableDataType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class BiomeCondition extends AbstractCondition<BiomeContext, BiomeConditionType> {

	public static final CompoundSerializableDataType<BiomeCondition> DATA_TYPE = ApoliDataTypes.condition("type", BiomeConditionTypes.DATA_TYPE, BiomeCondition::new);

	public BiomeCondition(BiomeConditionType conditionType, boolean inverted) {
		super(conditionType, inverted);
		conditionType.setCondition(this);
	}

	public BiomeCondition(BiomeConditionType conditionType) {
		this(conditionType, false);
	}

	public boolean test(BlockPos pos, RegistryEntry<Biome> biomeEntry) {
		return test(new BiomeContext(pos, biomeEntry));
	}

}
