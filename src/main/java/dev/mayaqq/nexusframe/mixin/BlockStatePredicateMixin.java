package dev.mayaqq.nexusframe.mixin;

import dev.mayaqq.nexusframe.utils.BlockStatePredicateExtension;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(BlockStatePredicate.class)
public class BlockStatePredicateMixin implements BlockStatePredicateExtension {
    @Final
    @Shadow
    private StateManager<Block, BlockState> manager;
    @Override
    public BlockState getFirstBlockState() {
        return manager.getStates().get(0);
    }
}
