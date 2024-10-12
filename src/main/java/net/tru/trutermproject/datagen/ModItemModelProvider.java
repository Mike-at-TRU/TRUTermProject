package net.tru.trutermproject.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.tru.trutermproject.TRUTermProject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TRUTermProject.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //basicItem()
    }
}
