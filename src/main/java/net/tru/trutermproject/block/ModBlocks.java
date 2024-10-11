package net.tru.trutermproject.block;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tru.trutermproject.TRUTermProject;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(TRUTermProject.MOD_ID);


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
