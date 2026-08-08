package gregtech.api.recipe;

import gregtech.api.util.GT_Recipe;

import java.util.HashSet;

import static gregtech.api.enums.GT_Values.E;

/**
 * Fluent builder for creating new {@link RecipeMap} instances backed by {@link GT_Recipe.GT_Recipe_Map}.
 * <p>
 * Example usage:
 * <pre>
 *   RecipeMap myMap = RecipeMapBuilder.of("gtpp.recipe.cokeoven")
 *       .maxIO(2, 9, 1, 1)
 *       .minInputs(1, 0)
 *       .neiName("Coke Oven")
 *       .neiGUIPath("basicmachines/Default")
 *       .build();
 * </pre>
 * <p>
 * After building, you can add recipes:
 * <pre>
 *   myMap.addRecipe(true, inputs, outputs, null, chances, fluidIn, fluidOut, duration, euPerTick, 0);
 * </pre>
 */
public class RecipeMapBuilder {

    private String mName;
    private String mNEIName;
    private String mNEIPath;
    private int mMaxItemInputs = 1;
    private int mMaxItemOutputs = 1;
    private int mMaxFluidInputs = 0;
    private int mMaxFluidOutputs = 0;
    private int mMinItemInputs = 0;
    private int mMinFluidInputs = 0;
    private int mAmperage = 1;
    private String mNEISpecialValuePre = E;
    private int mNEISpecialValueMultiplier = 1;
    private String mNEISpecialValuePost = E;
    private boolean mShowVoltageAmperage = true;
    private boolean mNEIAllowed = true;
    private int mInitialCapacity = 100;

    private RecipeMapBuilder() {}

    /**
     * Start building a new recipe map.
     *
     * @param name the unique unlocalised name (e.g. "gtpp.recipe.cokeoven")
     */
    public static RecipeMapBuilder of(String name) {
        RecipeMapBuilder builder = new RecipeMapBuilder();
        builder.mName = name;
        return builder;
    }

    /**
     * Set max item inputs, item outputs, fluid inputs, and fluid outputs.
     */
    public RecipeMapBuilder maxIO(int itemInputs, int itemOutputs, int fluidInputs, int fluidOutputs) {
        this.mMaxItemInputs = itemInputs;
        this.mMaxItemOutputs = itemOutputs;
        this.mMaxFluidInputs = fluidInputs;
        this.mMaxFluidOutputs = fluidOutputs;
        return this;
    }

    /**
     * Set minimum required inputs for recipe search.
     *
     * @param items minimum item inputs
     * @param fluids minimum fluid inputs
     */
    public RecipeMapBuilder minInputs(int items, int fluids) {
        this.mMinItemInputs = items;
        this.mMinFluidInputs = fluids;
        return this;
    }

    /**
     * Set the NEI display name. If null, uses the unlocalised name.
     */
    public RecipeMapBuilder neiName(String neiName) {
        this.mNEIName = neiName;
        return this;
    }

    /**
     * Set the NEI GUI texture path.
     */
    public RecipeMapBuilder neiGUIPath(String path) {
        this.mNEIPath = path;
        return this;
    }

    /**
     * Set the amperage.
     */
    public RecipeMapBuilder amperage(int amperage) {
        this.mAmperage = amperage;
        return this;
    }

    /**
     * Set the NEI special value display.
     *
     * @param pre       prefix string (e.g. "Heat Capacity: ")
     * @param multiplier value multiplier before display
     * @param post      suffix string (e.g. " K")
     */
    public RecipeMapBuilder neiSpecialValue(String pre, int multiplier, String post) {
        this.mNEISpecialValuePre = pre;
        this.mNEISpecialValueMultiplier = multiplier;
        this.mNEISpecialValuePost = post;
        return this;
    }

    /**
     * Set whether to show voltage/amperage in NEI.
     */
    public RecipeMapBuilder showVoltageAmperage(boolean show) {
        this.mShowVoltageAmperage = show;
        return this;
    }

    /**
     * Set whether NEI is allowed for this map.
     */
    public RecipeMapBuilder neiAllowed(boolean allowed) {
        this.mNEIAllowed = allowed;
        return this;
    }

    /**
     * Set the initial capacity for the recipe hash set.
     */
    public RecipeMapBuilder initialCapacity(int capacity) {
        this.mInitialCapacity = capacity;
        return this;
    }

    /**
     * Build the recipe map. Registers it in GT_Recipe.GT_Recipe_Map.sMappings.
     *
     * @return the new RecipeMap wrapper
     * @throws IllegalArgumentException if name is null or empty
     */
    public RecipeMap build() {
        if (mName == null || mName.isEmpty()) {
            throw new IllegalArgumentException("RecipeMap name must not be null or empty");
        }

        String localName = mNEIName != null ? mNEIName : mName;
        String guiPath = mNEIPath != null ? mNEIPath : "basicmachines/Default";

        GT_Recipe.GT_Recipe_Map backend = new GT_Recipe.GT_Recipe_Map(
            new HashSet<GT_Recipe>(mInitialCapacity),
            mName,
            localName,
            mNEIName,
            guiPath,
            mMaxItemInputs,
            mMaxItemOutputs,
            mMinItemInputs,
            mMinFluidInputs,
            mAmperage,
            mNEISpecialValuePre,
            mNEISpecialValueMultiplier,
            mNEISpecialValuePost,
            mShowVoltageAmperage,
            mNEIAllowed
        );

        return new RecipeMap(backend);
    }
}
