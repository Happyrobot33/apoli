package io.github.apace100.apoli.condition.type.block.meta;

import io.github.apace100.apoli.condition.BlockCondition;
import io.github.apace100.apoli.condition.ConditionConfiguration;
import io.github.apace100.apoli.condition.context.BlockContext;
import io.github.apace100.apoli.condition.type.BlockConditionType;
import io.github.apace100.apoli.condition.type.BlockConditionTypes;
import io.github.apace100.apoli.condition.type.meta.AllOfMetaConditionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class AllOfBlockConditionType extends BlockConditionType implements AllOfMetaConditionType<BlockContext, BlockCondition> {

	private final List<BlockCondition> conditions;

	public AllOfBlockConditionType(List<BlockCondition> conditions) {
		this.conditions = prepareConditions(this, conditions);
	}

	@Override
	public boolean test(World world, BlockPos pos) {
		return AllOfMetaConditionType.condition(new BlockContext(world, pos), conditions());
	}

	@Override
	public ConditionConfiguration<?> configuration() {
		return BlockConditionTypes.ALL_OF;
	}

	@Override
	public List<BlockCondition> conditions() {
		return conditions;
	}

}
