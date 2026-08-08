package gregtech.api.recipe;

import gregtech.api.util.GTRecipe;
import gregtech.api.util.GT_Recipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper around GT_Recipe.GT_Recipe_Map providing a cleaner API.
 * <p>
 * This class is the recommended way to access recipe maps in new code. It provides
 * a cleaner, more descriptive API than directly using {@code GT_Recipe.GT_Recipe_Map.sXxxRecipes}.
 * <p>
 * Example usage:
 * <pre>
 *   RecipeMaps.maceratorRecipes.addRecipe(...);
 *   GT_Recipe recipe = RecipeMaps.alloySmelterRecipes.findRecipe(...);
 * </pre>
 */
public class RecipeMap<T extends GT_Recipe> {

    public static final Map<String, RecipeMap<? extends GT_Recipe>> ALL_RECIPE_MAPS = new HashMap<>();

    public final String unlocalizedName;
    private final GT_Recipe.GT_Recipe_Map mBackend;

    public RecipeMap(GT_Recipe.GT_Recipe_Map backend) {
        this.mBackend = backend;
        this.unlocalizedName = backend.mUnlocalizedName;
        ALL_RECIPE_MAPS.put(this.unlocalizedName, this);
    }

    public static RecipeMap<?> getByName(String name) {
        return ALL_RECIPE_MAPS.get(name);
    }

    /**
     * @return the underlying GT_Recipe_Map backend.
     */
    public GT_Recipe.GT_Recipe_Map getBackend() {
        return mBackend;
    }

    /**
     * Add a GTRecipe wrapper to this map (Minetweaker compatible).
     */
    public void add(GTRecipe recipe) {
        mBackend.addRecipe(recipe.getBackend());
    }

    /**
     * Build and add a recipe from a builder to this map (fluent API).
     */
    public GT_Recipe add(gregtech.api.util.GTRecipeBuilder builder) {
        GTRecipe recipe = builder.build().get();
        mBackend.addRecipe(recipe.getBackend());
        return recipe.getBackend();
    }

    /**
     * Remove a recipe from this map (for undo support).
     */
    public void removeRecipe(GT_Recipe recipe) {
        mBackend.mRecipeList.remove(recipe);
    }

    /**
     * Find a recipe matching the given inputs.
     */
    public GT_Recipe findRecipe(long aVoltage, net.minecraft.item.ItemStack[] aInputs, net.minecraftforge.fluids.FluidStack[] aFluidInputs) {
        return mBackend.findRecipe((gregtech.api.interfaces.tileentity.IHasWorldObjectAndCoords) null, false, aVoltage, aFluidInputs, aInputs);
    }

    /**
     * Add a recipe to this map.
     */
    public GT_Recipe addRecipe(boolean aOptimize, net.minecraft.item.ItemStack[] aInputs, net.minecraft.item.ItemStack[] aOutputs,
                               Object aSpecial, int[] aOutputChances, net.minecraftforge.fluids.FluidStack[] aFluidInputs,
                               net.minecraftforge.fluids.FluidStack[] aFluidOutputs, int aDuration, int aEUt, int aSpecialValue) {
        return mBackend.addRecipe(aOptimize, aInputs, aOutputs, aSpecial, aOutputChances, aFluidInputs, aFluidOutputs, aDuration, aEUt, aSpecialValue);
    }

    /**
     * Add a recipe without output chances (GT5U vanilla API compatible).
     */
    public GT_Recipe addRecipe(boolean aOptimize, net.minecraft.item.ItemStack[] aInputs, net.minecraft.item.ItemStack[] aOutputs,
                               Object aSpecial, net.minecraftforge.fluids.FluidStack[] aFluidInputs,
                               net.minecraftforge.fluids.FluidStack[] aFluidOutputs, int aDuration, int aEUt, int aSpecialValue) {
        return mBackend.addRecipe(aOptimize, aInputs, aOutputs, aSpecial, null, aFluidInputs, aFluidOutputs, aDuration, aEUt, aSpecialValue);
    }

    /**
     * Add a recipe with only fluid inputs/outputs and chances (fusion reactor compatible).
     */
    public GT_Recipe addRecipe(int[] aOutputChances, net.minecraftforge.fluids.FluidStack[] aFluidInputs,
                               net.minecraftforge.fluids.FluidStack[] aFluidOutputs, int aDuration, int aEUt, int aSpecialValue) {
        return mBackend.addRecipe(aOutputChances, aFluidInputs, aFluidOutputs, aDuration, aEUt, aSpecialValue);
    }

    /**
     * Add a recipe object directly.
     */
    public GT_Recipe addRecipe(GT_Recipe aRecipe) {
        return mBackend.addRecipe(aRecipe);
    }

    /**
     * @return all recipes in this map.
     */
    public Collection<GT_Recipe> getAllRecipes() {
        return mBackend.mRecipeList;
    }

    /**
     * @return the unlocalised name of this recipe map.
     */
    public String getUnlocalizedName() {
        return mBackend.mUnlocalizedName;
    }

    /**
     * @return the NEI display name.
     */
    public String getNEIName() {
        return mBackend.mNEIName;
    }

    /**
     * @return the usual input count.
     */
    public int getUsualInputCount() {
        return mBackend.mUsualInputCount;
    }

    /**
     * @return the usual output count.
     */
    public int getUsualOutputCount() {
        return mBackend.mUsualOutputCount;
    }

    /**
     * @return true if NEI is allowed for this map.
     */
    public boolean isNEIAllowed() {
        return mBackend.mNEIAllowed;
    }

    /**
     * @return the number of recipes in this map.
     */
    public int size() {
        return mBackend.mRecipeList.size();
    }

    /**
     * @return true if this map contains no recipes.
     */
    public boolean isEmpty() {
        return mBackend.mRecipeList.isEmpty();
    }

    public RecipeQuery findRecipeQuery() {
        return new RecipeQuery(this);
    }

    public static class RecipeQuery {
        private final RecipeMap<?> mMap;
        private net.minecraft.item.ItemStack[] mItems;
        private net.minecraftforge.fluids.FluidStack[] mFluids;
        private boolean mDontCheckStackSizes;

        RecipeQuery(RecipeMap<?> map) {
            this.mMap = map;
        }

        public RecipeQuery items(net.minecraft.item.ItemStack... items) {
            this.mItems = items;
            return this;
        }

        public RecipeQuery fluids(net.minecraftforge.fluids.FluidStack... fluids) {
            this.mFluids = fluids;
            return this;
        }

        public RecipeQuery dontCheckStackSizes() {
            this.mDontCheckStackSizes = true;
            return this;
        }

        public GT_Recipe find() {
            long voltage = 0;
            if (mItems != null) {
                for (net.minecraft.item.ItemStack stack : mItems) {
                    if (stack != null) {
                        voltage = Long.MAX_VALUE;
                        break;
                    }
                }
            }
            if (mFluids != null) {
                for (net.minecraftforge.fluids.FluidStack stack : mFluids) {
                    if (stack != null) {
                        voltage = Long.MAX_VALUE;
                        break;
                    }
                }
            }
            return mMap.findRecipe(voltage, mItems, mFluids);
        }
    }
}
