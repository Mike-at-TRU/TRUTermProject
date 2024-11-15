package net.tru.trutermproject.effects;

import net.minecraft.core.Holder;
import net.tru.trutermproject.TRUTermProject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = 
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TRUTermProject.MOD_ID);


        public static final Holder<MobEffect> PRETTY_EFFECT = MOB_EFFECTS.register("pretty",
            () -> new PrettyEffect(MobEffectCategory.NEUTRAL, 0x36ebab));


        public static void register(IEventBus eventBus) {
                MOB_EFFECTS.register(eventBus);
            }
}
