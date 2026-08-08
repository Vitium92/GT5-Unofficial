package gregtech.api.util;

import gregtech.api.recipe.RecipeMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GTRecipeBuilder {

    public static final int SECONDS = 20;
    public static final int TICKS = 1;

    private ItemStack[] mItemInputs = new ItemStack[0];
    private ItemStack[] mItemOutputs = new ItemStack[0];
    private FluidStack[] mFluidInputs = new FluidStack[0];
    private FluidStack[] mFluidOutputs = new FluidStack[0];
    private int[] mOutputChances = new int[0];
    private int mDuration = 0;
    private int mEUt = 0;
    private int mSpecialValue = 0;
    private Object mSpecial = null;
    private boolean mOptimize = true;
    private final Map<String, Integer> mMetadata = new HashMap<>();

    public GTRecipeBuilder() {}

    public GTRecipeBuilder itemInputs(ItemStack... items) {
        this.mItemInputs = items;
        return this;
    }

    public GTRecipeBuilder itemOutputs(ItemStack... items) {
        this.mItemOutputs = items;
        return this;
    }

    public GTRecipeBuilder fluidInputs(FluidStack... fluids) {
        this.mFluidInputs = fluids;
        return this;
    }

    public GTRecipeBuilder fluidOutputs(FluidStack... fluids) {
        this.mFluidOutputs = fluids;
        return this;
    }

    public GTRecipeBuilder duration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public GTRecipeBuilder eut(int eut) {
        this.mEUt = eut;
        return this;
    }

    public GTRecipeBuilder outputChances(int... chances) {
        this.mOutputChances = chances;
        return this;
    }

    public GTRecipeBuilder specialValue(int special) {
        this.mSpecialValue = special;
        return this;
    }

    public GTRecipeBuilder special(ItemStack special) {
        this.mSpecial = special;
        return this;
    }

    public GTRecipeBuilder optimize(boolean optimize) {
        this.mOptimize = optimize;
        return this;
    }

    public GTRecipeBuilder metadata(String key, int value) {
        this.mMetadata.put(key, value);
        return this;
    }

    public GTRecipeBuilder metadata(String key, String value) {
        this.mMetadata.put(key, value.hashCode());
        return this;
    }

    public Map<String, Integer> getMetadata() {
        return mMetadata;
    }

    /**
     * Build and add this recipe to the given map (fluent API).
     */
    public GT_Recipe addTo(RecipeMap<?> map) {
        return map.add(this);
    }

    /**
     * Build the recipe without adding it to any map.
     * Used by Minetweaker's RA2Builder.addTo().
     */
    public Optional<GTRecipe> build() {
        GT_Recipe recipe = new GT_Recipe(
            mOptimize,
            mItemInputs != null ? mItemInputs : new ItemStack[0],
            mItemOutputs != null ? mItemOutputs : new ItemStack[0],
            mSpecial,
            mOutputChances != null && mOutputChances.length > 0 ? mOutputChances : null,
            mFluidInputs != null ? mFluidInputs : new FluidStack[0],
            mFluidOutputs != null ? mFluidOutputs : new FluidStack[0],
            mDuration,
            mEUt,
            mSpecialValue
        );
        return Optional.of(new GTRecipe(recipe));
    }
}
