package gregtech.api.recipe;

import gregtech.api.util.GT_Recipe;

/**
 * Provides named static references to all GT5U recipe maps using the {@link RecipeMap} wrapper.
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
public class RecipeMaps {

    // region Processing Machines

    public static final RecipeMap oreWasherRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sOreWasherRecipes);
    public static final RecipeMap thermalCentrifugeRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sThermalCentrifugeRecipes);
    public static final RecipeMap compressorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sCompressorRecipes);
    public static final RecipeMap extractorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sExtractorRecipes);
    public static final RecipeMap recyclerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sRecyclerRecipes);
    public static final RecipeMap furnaceRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sFurnaceRecipes);
    public static final RecipeMap microwaveRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sMicrowaveRecipes);
    public static final RecipeMap scannerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sScannerFakeRecipes);
    public static final RecipeMap rockBreakerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sRockBreakerFakeRecipes);
    public static final RecipeMap byProductList = new RecipeMap(GT_Recipe.GT_Recipe_Map.sByProductList);
    public static final RecipeMap replicatorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sReplicatorFakeRecipes);
    public static final RecipeMap organicReplicatorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sOrganicReplicatorFakeRecipes);
    public static final RecipeMap assemblylineVisualRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sAssemblylineVisualRecipes);
    public static final RecipeMap plasmaArcFurnaceRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sPlasmaArcFurnaceRecipes);
    public static final RecipeMap arcFurnaceRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sArcFurnaceRecipes);
    public static final RecipeMap printerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sPrinterRecipes);
    public static final RecipeMap sifterRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sSifterRecipes);
    public static final RecipeMap formingPressRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sPressRecipes);
    public static final RecipeMap laserEngraverRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes);
    public static final RecipeMap mixerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sMixerRecipes);
    public static final RecipeMap autoclaveRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sAutoclaveRecipes);
    public static final RecipeMap electromagneticSeparatorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sElectroMagneticSeparatorRecipes);
    public static final RecipeMap polarizerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sPolarizerRecipes);
    public static final RecipeMap maceratorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sMaceratorRecipes);
    public static final RecipeMap chemicalBathRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sChemicalBathRecipes);
    public static final RecipeMap fluidCannerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sFluidCannerRecipes);
    public static final RecipeMap brewingRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sBrewingRecipes);
    public static final RecipeMap fluidHeaterRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sFluidHeaterRecipes);
    public static final RecipeMap distilleryRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sDistilleryRecipes);
    public static final RecipeMap fermentingRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sFermentingRecipes);
    public static final RecipeMap fluidSolidificationRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sFluidSolidficationRecipes);
    public static final RecipeMap fluidExtractionRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sFluidExtractionRecipes);
    public static final RecipeMap packagerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sBoxinatorRecipes);
    public static final RecipeMap unpackagerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sUnboxinatorRecipes);
    public static final RecipeMap centrifugeRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes);
    public static final RecipeMap electrolyzerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes);
    public static final RecipeMap wiremillRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sWiremillRecipes);
    public static final RecipeMap metalBenderRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sBenderRecipes);
    public static final RecipeMap alloySmelterRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sAlloySmelterRecipes);
    public static final RecipeMap assemblerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sAssemblerRecipes);
    public static final RecipeMap circuitAssemblerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sCircuitAssemblerRecipes);
    public static final RecipeMap cannerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sCannerRecipes);
    public static final RecipeMap cncRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sCNCRecipes);
    public static final RecipeMap latheRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sLatheRecipes);
    public static final RecipeMap cuttingMachineRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sCutterRecipes);
    public static final RecipeMap slicerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sSlicerRecipes);
    public static final RecipeMap extruderRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sExtruderRecipes);
    public static final RecipeMap hammerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sHammerRecipes);
    public static final RecipeMap uuAmplifierRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sAmplifiers);
    public static final RecipeMap massFabricatorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sMassFabFakeRecipes);

    // endregion

    // region Multiblock Machines

    public static final RecipeMap fusionRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sFusionRecipes);
    public static final RecipeMap blastFurnaceRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sBlastRecipes);
    public static final RecipeMap primitiveBlastFurnaceRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sPrimitiveBlastRecipes);
    public static final RecipeMap implosionCompressorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sImplosionRecipes);
    public static final RecipeMap vacuumFreezerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sVacuumRecipes);
    public static final RecipeMap chemicalReactorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sChemicalRecipes);
    public static final RecipeMap largeChemicalReactorRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sMultiblockChemicalRecipes);
    public static final RecipeMap distillationTowerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sDistillationRecipes);
    public static final RecipeMap oilCrackerRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sCrakingRecipes);
    public static final RecipeMap pyrolyseRecipes = new RecipeMap(GT_Recipe.GT_Recipe_Map.sPyrolyseRecipes);

    // endregion

    // region Fuels

    public static final RecipeMap dieselGeneratorFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sDieselFuels);
    public static final RecipeMap gasTurbineFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sTurbineFuels);
    public static final RecipeMap thermalGeneratorFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sHotFuels);
    public static final RecipeMap semiFluidBoilerFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sDenseLiquidFuels);
    public static final RecipeMap plasmaGeneratorFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sPlasmaFuels);
    public static final RecipeMap magicFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sMagicFuels);
    public static final RecipeMap smallNaquadahReactorFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sSmallNaquadahReactorFuels);
    public static final RecipeMap largeNaquadahReactorFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sLargeNaquadahReactorFuels);
    public static final RecipeMap fluidNaquadahReactorFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sFluidNaquadahReactorFuels);
    public static final RecipeMap largeBoilerFakeFuels = new RecipeMap(GT_Recipe.GT_Recipe_Map.sLargeBoilerFakeFuels);

    // endregion
}
