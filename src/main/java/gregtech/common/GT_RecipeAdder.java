package gregtech.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.internal.IGT_RecipeAdder;
import gregtech.api.objects.GT_FluidStack;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Recipe.GT_Recipe_AssemblyLine;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.GT_IntegratedCircuit_Item;
import mods.railcraft.common.blocks.aesthetics.cube.EnumCube;
import mods.railcraft.common.items.RailcraftToolItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class GT_RecipeAdder
        implements IGT_RecipeAdder {
	
	private boolean isAddingDeprecatedRecipes = false;
	
	@Deprecated
    public boolean addFusionReactorRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, int aStartEU) {
        return false;
    }

    @Override
    public boolean addFusionReactorRecipe(FluidStack aInput1, FluidStack aInput2, FluidStack aOutput1, int aDuration, int aEUt, int aStartEU) {
        if (aInput1 == null || aInput2 == null || aOutput1 == null || aDuration < 1 || aEUt < 1 || aStartEU < 1) {
            return false;
        }
        if ((aOutput1 != null) && ((aDuration = GregTech_API.sRecipeFile.get("fusion", aOutput1.getFluid().getName(), aDuration)) <= 0)) {
            return false;
        }
        RecipeMaps.fusionRecipes.add(
            GT_Values.RA.stdBuilder()
                .fluidInputs(aInput1, aInput2)
                .fluidOutputs(aOutput1)
                .duration(aDuration)
                .eut(aEUt)
                .specialValue(aStartEU)
        );
        return true;
    }

    public boolean addCentrifugeRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, int aDuration) {
        return addCentrifugeRecipe(aInput1, aInput2 < 0 ? ItemList.IC2_Fuel_Can_Empty.get(-aInput2, new Object[0]) : aInput2 > 0 ? ItemList.Cell_Empty.get(aInput2, new Object[0]) : null, null, null, aOutput1, aOutput2, aOutput3, aOutput4, aOutput5, aOutput6, null, aDuration, 5);
    }

    public boolean addCentrifugeRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, int[] aChances, int aDuration, int aEUt) {
        if (((aInput1 == null) && (aFluidInput == null)) || ((aOutput1 == null) && (aFluidOutput == null))) {
            return false;
        }
        if ((aInput1 != null) && ((aDuration = GregTech_API.sRecipeFile.get("centrifuge", aInput1, aDuration)) <= 0)) {
            return false;
        }
        if ((aFluidInput != null) && ((aDuration = GregTech_API.sRecipeFile.get("centrifuge", aFluidInput.getFluid().getName(), aDuration)) <= 0)) {
            return false;
        }
        RecipeMaps.centrifugeRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, aInput2)
                .itemOutputs(aOutput1, aOutput2, aOutput3, aOutput4, aOutput5, aOutput6)
                .outputChances(aChances)
                .fluidInputs(aFluidInput)
                .fluidOutputs(aFluidOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addCompressorRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
        if ((aInput1 == null) || (aOutput1 == null)) {
                return false;
        }
        if ((aInput1 != null) && ((aDuration = GregTech_API.sRecipeFile.get("compressor", aInput1, aDuration)) <= 0)) {
                return false;
        }
        RecipeMaps.compressorRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1)
                .itemOutputs(aOutput1)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addElectrolyzerRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, int aDuration, int aEUt) {
        return addElectrolyzerRecipe(aInput1, aInput2 < 0 ? ItemList.IC2_Fuel_Can_Empty.get(-aInput2, new Object[0]) : aInput2 > 0 ? ItemList.Cell_Empty.get(aInput2, new Object[0]) : null, null, null, aOutput1, aOutput2, aOutput3, aOutput4, aOutput5, aOutput6, null, aDuration, aEUt);
    }

    public boolean addElectrolyzerRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, int[] aChances, int aDuration, int aEUt) {
        if (((aInput1 == null) && (aFluidInput == null)) || ((aOutput1 == null) && (aFluidOutput == null))) {
            return false;
        }
        if ((aInput1 != null) && ((aDuration = GregTech_API.sRecipeFile.get("electrolyzer", aInput1, aDuration)) <= 0)) {
            return false;
        }
        if ((aFluidInput != null) && ((aDuration = GregTech_API.sRecipeFile.get("electrolyzer", aFluidInput.getFluid().getName(), aDuration)) <= 0)) {
            return false;
        }
        RecipeMaps.electrolyzerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, aInput2)
                .itemOutputs(aOutput1, aOutput2, aOutput3, aOutput4, aOutput5, aOutput6)
                .outputChances(aChances)
                .fluidInputs(aFluidInput)
                .fluidOutputs(aFluidOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput, int aDuration) {
        return addChemicalRecipe(aInput1, aInput2, null, null, aOutput, aDuration);
    }

    public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, int aDuration) {
        return addChemicalRecipe(aInput1, aInput2, aFluidInput, aFluidOutput, aOutput, aDuration, 30);
    }

    public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, ItemStack aOutput2, int aDuration) {
        return addChemicalRecipe(aInput1, aInput2, aFluidInput, aFluidOutput, aOutput, aOutput2, aDuration, 30);
    }

    public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, int aDuration, int aEUTick) {
        return addChemicalRecipe(aInput1, aInput2, aFluidInput, aFluidOutput, aOutput, GT_Values.NI, aDuration, aEUTick);
    }

    public boolean addChemicalRecipeForBasicMachineOnly(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, ItemStack aOutput2, int aDuration, int aEUtick) {
        if (((aInput1 == null) && (aFluidInput == null)) || ((aOutput == null) && (aOutput2 == null) && (aFluidOutput == null))) {
            return false;
        }
        if ((aOutput != null || aOutput2 != null) && ((aDuration = GregTech_API.sRecipeFile.get("chemicalreactor", aOutput, aDuration)) <= 0)) {
            return false;
        }
        if ((aFluidOutput != null) && ((aDuration = GregTech_API.sRecipeFile.get("chemicalreactor", aFluidOutput.getFluid().getName(), aDuration)) <= 0)) {
            return false;
        }
        if (aEUtick <= 0) {
            return false;
        }
        RecipeMaps.chemicalReactorRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, aInput2)
                .itemOutputs(aOutput, aOutput2)
                .fluidInputs(aFluidInput)
                .fluidOutputs(aFluidOutput)
                .duration(aDuration)
                .eut(aEUtick)
                .specialValue(isAddingDeprecatedRecipes ? -300 : 0)
        );
//      GT_Recipe.GT_Recipe_Map.sMultiblockChemicalRecipes.addRecipe(false, new ItemStack[]{aInput1, aInput2}, new ItemStack[]{aOutput, aOutput2}, null, null, new FluidStack[]{aFluidInput}, new FluidStack[]{aFluidOutput}, aDuration, aEUtick, 0);
        return true;
    }

    public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, ItemStack aOutput2, int aDuration, int aEUtick) {
        if (((aInput1 == null) && (aFluidInput == null)) || ((aOutput == null) && (aOutput2 == null) && (aFluidOutput == null))) {
            return false;
        }
        if ((aOutput != null || aOutput2 != null) && ((aDuration = GregTech_API.sRecipeFile.get("chemicalreactor", aOutput, aDuration)) <= 0)) {
            return false;
        }
        if ((aFluidOutput != null) && ((aDuration = GregTech_API.sRecipeFile.get("chemicalreactor", aFluidOutput.getFluid().getName(), aDuration)) <= 0)) {
            return false;
        }
        if (aEUtick <= 0) {
            return false;
        }
        RecipeMaps.chemicalReactorRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, aInput2)
                .itemOutputs(aOutput, aOutput2)
                .fluidInputs(aFluidInput)
                .fluidOutputs(aFluidOutput)
                .duration(aDuration)
                .eut(aEUtick)
                .specialValue(isAddingDeprecatedRecipes ? -300 : 0)
        );
        if (!(aInput1 != null && aInput1.getItem() instanceof GT_IntegratedCircuit_Item && aInput1.getItemDamage() >= 10)
        		&& !(aInput2 != null && aInput2.getItem() instanceof GT_IntegratedCircuit_Item && aInput2.getItemDamage() >= 10)) {
            RecipeMaps.largeChemicalReactorRecipes.add(
                GT_Values.RA.stdBuilder().optimize(false)
                    .itemInputs(aInput1, aInput2)
                    .itemOutputs(aOutput, aOutput2)
                    .fluidInputs(aFluidInput)
                    .fluidOutputs(aFluidOutput)
                    .duration(aDuration)
                    .eut(aEUtick)
                    .specialValue(isAddingDeprecatedRecipes ? -300 : 0)
            );
        }
        return true;
    }

    public boolean addMultiblockChemicalRecipe(ItemStack[] aInputs, FluidStack[] aFluidInputs, FluidStack[] aFluidOutputs, ItemStack[] aOutputs, int aDuration, int aEUtick){
    	if (areItemsAndFluidsBothNull(aInputs, aFluidInputs) || areItemsAndFluidsBothNull(aOutputs, aFluidOutputs)) {
    		return false;
    	}
    	if (aEUtick <= 0) {
    		return false;
    	}
        RecipeMaps.largeChemicalReactorRecipes.add(
            GT_Values.RA.stdBuilder().optimize(false)
                .itemInputs(aInputs)
                .itemOutputs(aOutputs)
                .fluidInputs(aFluidInputs)
                .fluidOutputs(aFluidOutputs)
                .duration(aDuration)
                .eut(aEUtick)
        );
    	return true;
    }
    
    @Override
    public void addDefaultPolymerizationRecipes(Fluid aBasicMaterial, ItemStack aBasicMaterialCell, Fluid aPolymer){
    	//Oxygen/Titaniumtetrafluoride -> +50% Output each
        addChemicalRecipe(ItemList.Cell_Air.get(1, new Object[0]), GT_Utility.getIntegratedCircuit(1), new GT_FluidStack(aBasicMaterial, 288), new GT_FluidStack(aPolymer, 288),  Materials.Empty.getCells(1), 320);
        addChemicalRecipe(Materials.Oxygen.getCells(1),            GT_Utility.getIntegratedCircuit(1), new GT_FluidStack(aBasicMaterial, 144), new GT_FluidStack(aPolymer, 216),  Materials.Empty.getCells(1), 160);
        addChemicalRecipe(aBasicMaterialCell,                      GT_Utility.getIntegratedCircuit(1), Materials.Air.getGas(14000),            new GT_FluidStack(aPolymer, 1000), Materials.Empty.getCells(1), 1120);
        addChemicalRecipe(aBasicMaterialCell,  	                   GT_Utility.getIntegratedCircuit(1), Materials.Oxygen.getGas(7000),          new GT_FluidStack(aPolymer, 1500), Materials.Empty.getCells(1), 1120);
        addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(2)}, 
        		new FluidStack[]{new GT_FluidStack(aBasicMaterial, 2160), Materials.Air.getGas(15000),   Materials.Titaniumtetrachloride.getFluid(100)}, 
        		new FluidStack[]{new GT_FluidStack(aPolymer, 3240)}, null, 800, 30);
        addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(2)}, 
        		new FluidStack[]{new GT_FluidStack(aBasicMaterial, 2160), Materials.Oxygen.getGas(7500), Materials.Titaniumtetrachloride.getFluid(100)}, 
        		new FluidStack[]{new GT_FluidStack(aPolymer, 4320)}, null, 800, 30);
    }

	public boolean addBlastRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
        return addBlastRecipe(aInput1, aInput2, null, null, aOutput1, aOutput2, aDuration, aEUt, aLevel);
    }

    public boolean addBlastRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("blastfurnace", aInput1, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.blastFurnaceRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, aInput2)
                .itemOutputs(aOutput1, aOutput2)
                .fluidInputs(aFluidInput)
                .fluidOutputs(aFluidOutput)
                .duration(aDuration)
                .eut(aEUt)
                .specialValue(aLevel)
        );
        return true;
    }

    public boolean addPrimitiveBlastRecipe(ItemStack aInput1, ItemStack aInput2, int aCoalAmount, ItemStack aOutput1, ItemStack aOutput2, int aDuration) {
        if ((aInput1 == null && aInput2 == null) || (aOutput1 == null && aOutput2 == null)) {
            return false;
        }
        if (aCoalAmount <= 0) {
        	return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("primitiveblastfurnace", aInput1, aDuration)) <= 0) {
            return false;
        }
        Materials[] coals = new Materials[]{Materials.Coal, Materials.Charcoal};
        for (Materials coal : coals) {
        	RecipeMaps.primitiveBlastFurnaceRecipes.add(
                GT_Values.RA.stdBuilder()
                    .itemInputs(aInput1, aInput2, coal.getGems(aCoalAmount))
                    .itemOutputs(aOutput1, aOutput2, Materials.DarkAsh.getDustTiny(aCoalAmount))
                    .duration(aDuration)
                    .eut(0)
            );
        	RecipeMaps.primitiveBlastFurnaceRecipes.add(
                GT_Values.RA.stdBuilder()
                    .itemInputs(aInput1, aInput2, coal.getDust(aCoalAmount))
                    .itemOutputs(aOutput1, aOutput2, Materials.DarkAsh.getDustTiny(aCoalAmount))
                    .duration(aDuration)
                    .eut(0)
            );
        }        
        if (Loader.isModLoaded("Railcraft")) { 
        	RecipeMaps.primitiveBlastFurnaceRecipes.add(
                GT_Values.RA.stdBuilder()
                    .itemInputs(aInput1, aInput2, RailcraftToolItems.getCoalCoke(aCoalAmount / 2))
                    .itemOutputs(aOutput1, aOutput2, Materials.Ash.getDustTiny(aCoalAmount / 2))
                    .duration(aDuration * 2 / 3)
                    .eut(0)
            );
        }
        if ((aInput1 == null || aInput1.stackSize <= 6 ) && (aInput2 == null || aInput2.stackSize <= 6 ) && 
        		(aOutput1 == null || aOutput1.stackSize <= 6 ) && (aOutput2 == null || aOutput2.stackSize <= 6 )) {
        	aInput1 =  aInput1  == null ? null : GT_Utility.copyAmount(aInput1.stackSize  * 10, aInput1);
        	aInput2 =  aInput2  == null ? null : GT_Utility.copyAmount(aInput2.stackSize  * 10, aInput2);
        	aOutput1 = aOutput1 == null ? null : GT_Utility.copyAmount(aOutput1.stackSize * 10, aOutput1);
        	aOutput2 = aOutput2 == null ? null : GT_Utility.copyAmount(aOutput2.stackSize * 10, aOutput2);
            for (Materials coal : coals) {
            	RecipeMaps.primitiveBlastFurnaceRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aInput1, aInput2, coal.getBlocks(aCoalAmount))
                        .itemOutputs(aOutput1, aOutput2, Materials.DarkAsh.getDust(aCoalAmount))
                        .duration(aDuration * 10)
                        .eut(0)
                );
            	RecipeMaps.primitiveBlastFurnaceRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aInput1, aInput2, coal.getBlocks(aCoalAmount))
                        .itemOutputs(aOutput1, aOutput2, Materials.DarkAsh.getDust(aCoalAmount))
                        .duration(aDuration * 10)
                        .eut(0)
                );
            }
            if (Loader.isModLoaded("Railcraft")) { 
            	RecipeMaps.primitiveBlastFurnaceRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aInput1, aInput2, EnumCube.COKE_BLOCK.getItem(aCoalAmount / 2))
                        .itemOutputs(aOutput1, aOutput2, Materials.Ash.getDust(aCoalAmount / 2))
                        .duration(aDuration * 20 / 3)
                        .eut(0)
                );
            }
        }
        return true;
    }

    public boolean addCannerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("canning", aInput1, aDuration)) <= 0) {
            return false;
        }
        new GT_Recipe(aInput1, aEUt, aInput2, aDuration, aOutput1, aOutput2);
        return true;
    }
    
	@Override
	public boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
		return addAlloySmelterRecipe(aInput1, aInput2, aOutput1, aDuration, aEUt, false);
	}

    public boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, boolean hidden) {
        if ((aInput1 == null) || (aOutput1 == null || Materials.Graphite.contains(aInput1))) {
            return false;
        }
        if ((aInput2 == null) && ((OrePrefixes.ingot.contains(aInput1)) || (OrePrefixes.dust.contains(aInput1)) || (OrePrefixes.gem.contains(aInput1)))) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("alloysmelting", aInput2 == null ? aInput1 : aOutput1, aDuration)) <= 0) {
            return false;
        }
        GT_Recipe tRecipe =new GT_Recipe(aInput1, aInput2, aEUt, aDuration, aOutput1);
         if ((hidden) && (tRecipe != null)) {
            tRecipe.mHidden = true;
        }
        return true;
    }

    public boolean addCNCRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if ((GregTech_API.sRecipeFile.get("cnc", aOutput1, aDuration)) <= 0) {
            return false;
        }
        return true;
    }

    public boolean addLatheRecipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("lathe", aInput1, aDuration)) <= 0) {
            return false;
        }
        new GT_Recipe(aInput1, aOutput1, aOutput2, aDuration, aEUt);
        return true;
    }

    public boolean addCutterRecipe(ItemStack aInput, FluidStack aLubricant, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
        if ((aInput == null) || (aLubricant == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("cutting", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.cuttingMachineRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput1, aOutput2)
                .fluidInputs(aLubricant)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addCutterRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("cutting", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.cuttingMachineRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput1, aOutput2)
                .fluidInputs(new FluidStack[]{Materials.Water.getFluid(Math.max(4, Math.min(1000, aDuration * aEUt / 320)))})
                .duration(aDuration * 2)
                .eut(aEUt)
        );
        RecipeMaps.cuttingMachineRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput1, aOutput2)
                .fluidInputs(new FluidStack[]{GT_ModHandler.getDistilledWater(Math.max(3, Math.min(750, aDuration * aEUt / 426)))})
                .duration(aDuration * 2)
                .eut(aEUt)
        );
        RecipeMaps.cuttingMachineRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput1, aOutput2)
                .fluidInputs(new FluidStack[]{Materials.Lubricant.getFluid(Math.max(1, Math.min(250, aDuration * aEUt / 1280)))})
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }
    
    public boolean addAssemblerRecipe(ItemStack aInput1, Object aOreDict, int aAmount, FluidStack aFluidInput, ItemStack aOutput1, int aDuration, int aEUt){
    	for(ItemStack tStack : GT_OreDictUnificator.getOres(aOreDict)){
    		if(GT_Utility.isStackValid(tStack))
    		addAssemblerRecipe(aInput1, GT_Utility.copyAmount(aAmount, tStack), aFluidInput, aOutput1, aDuration, aEUt);
    	}
    	return true;
    }

    public boolean addAssemblerRecipe(ItemStack[] aInputs, Object aOreDict, int aAmount, FluidStack aFluidInput, ItemStack aOutput1, int aDuration, int aEUt){
    	for(ItemStack tStack : GT_OreDictUnificator.getOres(aOreDict)){
    		if(GT_Utility.isStackValid(tStack)) {
    			ItemStack[] extendedInputs = new ItemStack[aInputs.length + 1];
    			System.arraycopy(aInputs, 0, extendedInputs, 0, aInputs.length);
    			extendedInputs[aInputs.length] = GT_Utility.copyAmount(aAmount, tStack);
    			addAssemblerRecipe(extendedInputs, aFluidInput, aOutput1, aDuration, aEUt);
    		}
    	}
    	return true;
    }

    public boolean addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("assembling", aOutput1, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.assemblerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, aInput2 == null ? aInput1 : aInput2)
                .itemOutputs(aOutput1)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, ItemStack aOutput1, int aDuration, int aEUt) {
    	return addAssemblerRecipe(new ItemStack[]{aInput1, aInput2}, aFluidInput, aOutput1, aDuration, aEUt);
    }

    public boolean addAssemblerRecipe(ItemStack[] aInputs, FluidStack aFluidInput, ItemStack aOutput1, int aDuration, int aEUt) {
    	if (areItemsAndFluidsBothNull(aInputs, new FluidStack[]{aFluidInput})) {
    		return false;
    	}
    	if (aOutput1 == null) {
    		return false;
    	}
    	if ((aDuration = GregTech_API.sRecipeFile.get("assembling", aOutput1, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.assemblerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInputs)
                .itemOutputs(aOutput1)
                .fluidInputs(aFluidInput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addWiremillRecipe(ItemStack aInput, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("wiremill", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.wiremillRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addPolarizerRecipe(ItemStack aInput, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("polarizer", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.polarizerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addBenderRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("bender", aInput1, aDuration)) <= 0) {
            return false;
        }
        new GT_Recipe(aEUt, aDuration, aInput1, aOutput1);
        return true;
    }

    public boolean addExtruderRecipe(ItemStack aInput, ItemStack aShape, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aInput == null) || (aShape == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("extruder", aOutput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.extruderRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput, aShape)
                .itemOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addSlicerRecipe(ItemStack aInput, ItemStack aShape, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aInput == null) || (aShape == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("slicer", aOutput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.slicerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput, aShape)
                .itemOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addOreWasherRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, FluidStack aFluidInput, int aDuration, int aEUt) {
        return addOreWasherRecipe(aInput, aOutput1, aOutput2, aOutput3, aFluidInput, null, aDuration, aEUt);
    }

    public boolean addOreWasherRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, FluidStack aFluidInput, FluidStack aFluidOutput, int aDuration, int aEUt) {
        if ((aInput == null) || (aFluidInput == null) || ((aOutput1 == null) || (aOutput2 == null) || (aOutput3 == null))) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("orewasher", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.oreWasherRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput1, aOutput2, aOutput3)
                .fluidInputs(aFluidInput)
                .fluidOutputs(aFluidOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addImplosionRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aInput2 = GregTech_API.sRecipeFile.get("implosion", aInput1, aInput2)) <= 0) {
            return false;
        }
        int tExplosives = aInput2 > 0 ? aInput2 < 64 ? aInput2 : 64 : 1;
        int tGunpowder = tExplosives * 2;
        int tDynamite = Math.max(1, tExplosives / 2);
        int tTNT = Math.max(1, tExplosives/2);
        int tITNT = Math.max(1, tExplosives/4);
        //new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2);
        if(tGunpowder<65){
        	RecipeMaps.implosionCompressorRecipes.add(
                GT_Values.RA.stdBuilder()
                    .itemInputs(aInput1, ItemList.Block_Powderbarrel.get(tGunpowder, new Object[0]))
                    .itemOutputs(aOutput1, aOutput2)
                    .duration(20)
                    .eut(30)
            );
        }
        if(tDynamite<17){
        	RecipeMaps.implosionCompressorRecipes.add(
                GT_Values.RA.stdBuilder()
                    .itemInputs(aInput1, GT_ModHandler.getIC2Item("dynamite", tDynamite, null))
                    .itemOutputs(aOutput1, aOutput2)
                    .duration(20)
                    .eut(30)
            );
        }
        RecipeMaps.implosionCompressorRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, new ItemStack(Blocks.tnt,tTNT))
                .itemOutputs(aOutput1, aOutput2)
                .duration(20)
                .eut(30)
        );
        RecipeMaps.implosionCompressorRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, GT_ModHandler.getIC2Item("industrialTnt", tITNT, null))
                .itemOutputs(aOutput1, aOutput2)
                .duration(20)
                .eut(30)
        );
        
        return true;
    }

    @Deprecated
    public boolean addDistillationRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
//    if ((aInput1 == null) || (aOutput1 == null)) {
//      return false;
//    }
//    if ((aDuration = GregTech_API.sRecipeFile.get("distillation", aInput1, aDuration)) <= 0) {
//      return false;
//    }
//    new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt);
//    return true;
        return false;
    }

    @Override
    public boolean addUniversalDistillationRecipe(FluidStack aInput, FluidStack[] aOutputs, ItemStack aOutput2, int aDuration, int aEUt) {
    	for (int i = 0; i < Math.min(aOutputs.length, 11); i++) {
    		addDistilleryRecipe(i + 1, aInput, aOutputs[i], aOutput2, aDuration * 2, aEUt / 4, false);
    	}
        return addDistillationTowerRecipe(aInput, aOutputs, aOutput2, aDuration, aEUt);
    }

    public boolean addDistillationTowerRecipe(FluidStack aInput, FluidStack[] aOutputs, ItemStack aOutput2, int aDuration, int aEUt) {
        if (aInput == null || aOutputs == null || aOutputs.length < 1 || aOutputs.length > 11) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("distillation", aInput.getUnlocalizedName(), aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.distillationTowerRecipes.add(
            GT_Values.RA.stdBuilder().optimize(false)
                .itemOutputs(aOutput2)
                .fluidInputs(aInput)
                .fluidOutputs(aOutputs)
                .duration(Math.max(1, aDuration))
                .eut(Math.max(1, aEUt))
        );
        return false;
    }

    public boolean addVacuumFreezerRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("vacuumfreezer", aInput1, aDuration)) <= 0) {
            return false;
        }
        new GT_Recipe(aInput1, aOutput1, aDuration);
        return true;
    }

    public boolean addGrinderRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
        return false;
    }

    public boolean addFuel(ItemStack aInput1, ItemStack aOutput1, int aEU, int aType) {
        if (aInput1 == null) {
            return false;
        }
        new GT_Recipe(aInput1, aOutput1, GregTech_API.sRecipeFile.get("fuel_" + aType, aInput1, aEU), aType);
        return true;
    }

    public boolean addSonictronSound(ItemStack aItemStack, String aSoundName) {
        if ((aItemStack == null) || (aSoundName == null) || (aSoundName.equals(""))) {
            return false;
        }
        GT_Mod.gregtechproxy.mSoundItems.add(aItemStack);
        GT_Mod.gregtechproxy.mSoundNames.add(aSoundName);
        if (aSoundName.startsWith("note.")) {
            GT_Mod.gregtechproxy.mSoundCounts.add(Integer.valueOf(25));
        } else {
            GT_Mod.gregtechproxy.mSoundCounts.add(Integer.valueOf(1));
        }
        return true;
    }

    public boolean addForgeHammerRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
        if ((aInput1 == null) || (aOutput1 == null)) {
            return false;
        }
        if (!GregTech_API.sRecipeFile.get("forgehammer", aOutput1, true)) {
            return false;
        }
        RecipeMaps.hammerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1)
                .itemOutputs(aOutput1)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addBoxingRecipe(ItemStack aContainedItem, ItemStack aEmptyBox, ItemStack aFullBox, int aDuration, int aEUt) {
        if ((aContainedItem == null) || (aFullBox == null)) {
            return false;
        }
        if (!GregTech_API.sRecipeFile.get("boxing", aFullBox, true)) {
            return false;
        }
        RecipeMaps.packagerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aContainedItem, aEmptyBox)
                .itemOutputs(aFullBox)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addUnboxingRecipe(ItemStack aFullBox, ItemStack aContainedItem, ItemStack aEmptyBox, int aDuration, int aEUt) {
        if ((aFullBox == null) || (aContainedItem == null)) {
            return false;
        }
        if (!GregTech_API.sRecipeFile.get("unboxing", aFullBox, true)) {
            return false;
        }
        RecipeMaps.unpackagerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aFullBox)
                .itemOutputs(aContainedItem, aEmptyBox)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addThermalCentrifugeRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutput1 == null)) {
            return false;
        }
        if (!GregTech_API.sRecipeFile.get("thermalcentrifuge", aInput, true)) {
            return false;
        }
        RecipeMaps.thermalCentrifugeRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput1, aOutput2, aOutput3)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addAmplifier(ItemStack aAmplifierItem, int aDuration, int aAmplifierAmountOutputted) {
        if ((aAmplifierItem == null) || (aAmplifierAmountOutputted <= 0)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("amplifier", aAmplifierItem, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.uuAmplifierRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aAmplifierItem)
                .fluidOutputs(Materials.UUAmplifier.getFluid(aAmplifierAmountOutputted))
                .duration(aDuration)
                .eut(32)
        );
        return true;
    }

    public boolean addBrewingRecipe(ItemStack aIngredient, Fluid aInput, Fluid aOutput, boolean aHidden) {
        if ((aIngredient == null) || (aInput == null) || (aOutput == null)) {
            return false;
        }
        if (!GregTech_API.sRecipeFile.get("brewing", aOutput.getUnlocalizedName(), true)) {
            return false;
        }
        GT_Recipe tRecipe = RecipeMaps.brewingRecipes.add(
            GT_Values.RA.stdBuilder().optimize(false)
                .itemInputs(aIngredient)
                .fluidInputs(new FluidStack(aInput, 750))
                .fluidOutputs(new FluidStack(aOutput, 750))
                .duration(128)
                .eut(4)
        );
        if ((aHidden) && (tRecipe != null)) {
            tRecipe.mHidden = true;
        }
        return true;
    }

    public boolean addFermentingRecipe(FluidStack aInput, FluidStack aOutput, int aDuration, boolean aHidden) {
        if ((aInput == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("fermenting", aOutput.getFluid().getUnlocalizedName(), aDuration)) <= 0) {
            return false;
        }
        GT_Recipe tRecipe = RecipeMaps.fermentingRecipes.add(
            GT_Values.RA.stdBuilder().optimize(false)
                .fluidInputs(aInput)
                .fluidOutputs(aOutput)
                .duration(aDuration)
                .eut(2)
        );
        if ((aHidden) && (tRecipe != null)) {
            tRecipe.mHidden = true;
        }
        return true;
    }

    public boolean addDistilleryRecipe(ItemStack aCircuit, FluidStack aInput, FluidStack aOutput, ItemStack aSolidOutput, int aDuration, int aEUt, boolean aHidden) {
        if ((aInput == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("distillery", aOutput.getFluid().getUnlocalizedName(), aDuration)) <= 0) {
            return false;
        }
        //reduce the batch size if fluid amount is exceeding 
        int tScale = (Math.max(aInput.amount, aOutput.amount) + 999) / 1000;
        if (tScale <= 0) tScale = 1;
        if (tScale > 1){
        	//trying to find whether there is a better factor
        	for (int i = tScale; i <= 5; i++) {
        		if (aInput.amount % i == 0 && aDuration % i == 0) {
        			tScale = i;
        			break;
        		}
        	}
        	for (int i = tScale; i <= 5; i++) {
        		if (aInput.amount % i == 0 && aDuration % i == 0 && aOutput.amount % i == 0) {
        			tScale = i;
        			break;
        		}
        	}
        	aInput = new FluidStack(aInput.getFluid(), (aInput.amount + tScale - 1) / tScale);
        	aOutput = new FluidStack(aOutput.getFluid(), aOutput.amount / tScale);
        	if (aSolidOutput != null) {
        		ItemData tData = GT_OreDictUnificator.getItemData(aSolidOutput);
        		if (tData != null && (tData.mPrefix == OrePrefixes.dust || OrePrefixes.dust.mFamiliarPrefixes.contains(tData.mPrefix)))
        			aSolidOutput = GT_OreDictUnificator.getDust(tData.mMaterial.mMaterial, tData.mMaterial.mAmount * aSolidOutput.stackSize / tScale);
        		else {
        			if (aSolidOutput.stackSize / tScale == 0) aSolidOutput = GT_Values.NI;
            		else aSolidOutput = new ItemStack(aSolidOutput.getItem(), aSolidOutput.stackSize / tScale);
        		}
        	}
        	aDuration = (aDuration + tScale - 1) / tScale;
        }
        GT_Recipe tRecipe = RecipeMaps.distilleryRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aCircuit)
                .itemOutputs(aSolidOutput)
                .fluidInputs(aInput)
                .fluidOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        if ((aHidden) && (tRecipe != null)) {
            tRecipe.mHidden = true;
        }
        return true;
    }

    public boolean addDistilleryRecipe(ItemStack aCircuit, FluidStack aInput, FluidStack aOutput, int aDuration, int aEUt, boolean aHidden) {
    	return addDistilleryRecipe(aCircuit, aInput, aOutput, null, aDuration, aEUt, aHidden);
    }

    public boolean addDistilleryRecipe(int circuitConfig, FluidStack aInput, FluidStack aOutput, ItemStack aSolidOutput, int aDuration, int aEUt, boolean aHidden) {
    	return addDistilleryRecipe(GT_Utility.getIntegratedCircuit(circuitConfig), aInput, aOutput, aSolidOutput, aDuration, aEUt, aHidden);
    }

    public boolean addDistilleryRecipe(int circuitConfig, FluidStack aInput, FluidStack aOutput, int aDuration, int aEUt, boolean aHidden) {
    	return addDistilleryRecipe(GT_Utility.getIntegratedCircuit(circuitConfig), aInput, aOutput, aDuration, aEUt, aHidden);
    }
    
    public boolean addFluidSolidifierRecipe(ItemStack aMold, FluidStack aInput, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aMold == null) || (aInput == null) || (aOutput == null)) {
            return false;
        }
        if (aInput.isFluidEqual(Materials.PhasedGold.getMolten(144))) {
            aInput = Materials.VibrantAlloy.getMolten(aInput.amount);
        }
        if (aInput.isFluidEqual(Materials.PhasedIron.getMolten(144))) {
            aInput = Materials.PulsatingIron.getMolten(aInput.amount);
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("fluidsolidifier", aOutput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.fluidSolidificationRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aMold)
                .itemOutputs(aOutput)
                .fluidInputs(aInput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }
    
	@Override
	public boolean addFluidSmelterRecipe(ItemStack aInput, ItemStack aRemains, FluidStack aOutput, int aChance, int aDuration, int aEUt) {
		return addFluidSmelterRecipe(aInput, aRemains, aOutput, aChance, aDuration, aEUt, false);
	}

    public boolean addFluidSmelterRecipe(ItemStack aInput, ItemStack aRemains, FluidStack aOutput, int aChance, int aDuration, int aEUt, boolean hidden) {
        if ((aInput == null) || (aOutput == null)) {
            return false;
        }
        if (aOutput.isFluidEqual(Materials.PhasedGold.getMolten(1))) {
            aOutput = Materials.VibrantAlloy.getMolten(aOutput.amount);
        }
        if (aOutput.isFluidEqual(Materials.PhasedIron.getMolten(1))) {
            aOutput = Materials.PulsatingIron.getMolten(aOutput.amount);
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("fluidsmelter", aInput, aDuration)) <= 0) {
            return false;
        }
        GT_Recipe tRecipe = RecipeMaps.fluidExtractionRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aRemains)
                .outputChances(new int[]{aChance})
                .fluidOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        if ((hidden) && (tRecipe != null)) {
           tRecipe.mHidden = true;
        }
        return true;
    }

    public boolean addFluidExtractionRecipe(ItemStack aInput, ItemStack aRemains, FluidStack aOutput, int aChance, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutput == null)) {
            return false;
        }
        if (aOutput.isFluidEqual(Materials.PhasedGold.getMolten(1))) {
            aOutput = Materials.VibrantAlloy.getMolten(aOutput.amount);
        }
        if (aOutput.isFluidEqual(Materials.PhasedIron.getMolten(1))) {
            aOutput = Materials.PulsatingIron.getMolten(aOutput.amount);
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("fluidextractor", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.fluidExtractionRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aRemains)
                .outputChances(new int[]{aChance})
                .fluidOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addFluidCannerRecipe(ItemStack aInput, ItemStack aOutput, FluidStack aFluidInput, FluidStack aFluidOutput) {
        if ((aInput != null) && (aOutput != null)) {
            if ((aFluidInput == null ? 1 : 0) != (aFluidOutput == null ? 1 : 0)) {
            }
        } else {
            return false;
        }
        if (!GregTech_API.sRecipeFile.get("fluidcanner", aOutput, true)) {
            return false;
        }
        RecipeMaps.fluidCannerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput)
                .fluidInputs(aFluidInput == null ? null : aFluidInput)
                .fluidOutputs(aFluidOutput == null ? null : aFluidOutput)
                .duration(aFluidOutput == null ? aFluidInput.amount / 62 : aFluidOutput.amount / 62)
                .eut(1)
        );
        return true;
    }

    public boolean addChemicalBathRecipe(ItemStack aInput, FluidStack aBathingFluid, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, int[] aChances, int aDuration, int aEUt) {
        if ((aInput == null) || (aBathingFluid == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("chemicalbath", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.chemicalBathRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput1, aOutput2, aOutput3)
                .outputChances(aChances)
                .fluidInputs(aBathingFluid)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addElectromagneticSeparatorRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, int[] aChances, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutput1 == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("electromagneticseparator", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.electromagneticSeparatorRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput1, aOutput2, aOutput3)
                .outputChances(aChances)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addExtractorRecipe(ItemStack aInput, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("extractor", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.extractorRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addPrinterRecipe(ItemStack aInput, FluidStack aFluid, ItemStack aSpecialSlot, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aInput == null) || (aFluid == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("printer", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.printerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput)
                .special(aSpecialSlot)
                .fluidInputs(aFluid)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }
    
    public boolean addAutoclaveRecipe(ItemStack aInput, FluidStack aFluid, ItemStack aOutput, int aChance, int aDuration, int aEUt) {
    	return addAutoclaveRecipe(aInput, aFluid, aOutput, aChance, aDuration, aEUt, false);
    }

    public boolean addAutoclaveRecipe(ItemStack aInput, FluidStack aFluid, ItemStack aOutput, int aChance, int aDuration, int aEUt, boolean aCleanroom) {
        if ((aInput == null) || (aFluid == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("autoclave", aInput, aDuration)) <= 0) {
            return false;
        }
		if (!GT_Mod.gregtechproxy.mEnableCleanroom){
			aCleanroom = false;
		}
        RecipeMaps.autoclaveRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput)
                .itemOutputs(aOutput)
                .outputChances(new int[]{aChance})
                .fluidInputs(aFluid)
                .duration(aDuration)
                .eut(aEUt)
                .specialValue(aCleanroom ? -100 : 0)
        );
        return true;
    }

    public boolean addMixerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aInput3, ItemStack aInput4, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, int aDuration, int aEUt) {
        if (((aInput1 == null) && (aFluidInput == null)) || ((aOutput == null) && (aFluidOutput == null))) {
            return false;
        }
        if ((aOutput != null) && ((aDuration = GregTech_API.sRecipeFile.get("mixer", aOutput, aDuration)) <= 0)) {
            return false;
        }
        if ((aFluidOutput != null) && ((aDuration = GregTech_API.sRecipeFile.get("mixer", aFluidOutput.getFluid().getName(), aDuration)) <= 0)) {
            return false;
        }
        RecipeMaps.mixerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInput1, aInput2, aInput3, aInput4)
                .itemOutputs(aOutput)
                .fluidInputs(aFluidInput)
                .fluidOutputs(aFluidOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }
    
    public boolean addLaserEngraverRecipe(ItemStack aItemToEngrave, ItemStack aLens, ItemStack aEngravedItem, int aDuration, int aEUt) {
    	return addLaserEngraverRecipe( aItemToEngrave, aLens, aEngravedItem, aDuration, aEUt, false);
    }

    public boolean addLaserEngraverRecipe(ItemStack aItemToEngrave, ItemStack aLens, ItemStack aEngravedItem, int aDuration, int aEUt, boolean aCleanroom) {
        if ((aItemToEngrave == null) || (aLens == null) || (aEngravedItem == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("laserengraving", aEngravedItem, aDuration)) <= 0) {
            return false;
        }
		if (!GT_Mod.gregtechproxy.mEnableCleanroom){
			aCleanroom = false;
		}
        RecipeMaps.laserEngraverRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aItemToEngrave, aLens)
                .itemOutputs(aEngravedItem)
                .duration(aDuration)
                .eut(aEUt)
                .specialValue(aCleanroom ? -200 : 0)
        );
        return true;
    }

    public boolean addFormingPressRecipe(ItemStack aItemToImprint, ItemStack aForm, ItemStack aImprintedItem, int aDuration, int aEUt) {
        if ((aItemToImprint == null) || (aForm == null) || (aImprintedItem == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("press", aImprintedItem, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.formingPressRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aItemToImprint, aForm)
                .itemOutputs(aImprintedItem)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addFluidHeaterRecipe(ItemStack aCircuit, FluidStack aInput, FluidStack aOutput, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("fluidheater", aOutput.getFluid().getUnlocalizedName(), aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.fluidHeaterRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aCircuit)
                .fluidInputs(aInput)
                .fluidOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    public boolean addSifterRecipe(ItemStack aItemToSift, ItemStack[] aSiftedItems, int[] aChances, int aDuration, int aEUt) {
        if ((aItemToSift == null) || (aSiftedItems == null)) {
            return false;
        }
        for (ItemStack tStack : aSiftedItems) {
            if (tStack != null) {
                if ((aDuration = GregTech_API.sRecipeFile.get("sifter", aItemToSift, aDuration)) <= 0) {
                    return false;
                }
                RecipeMaps.sifterRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aItemToSift)
                        .itemOutputs(aSiftedItems)
                        .outputChances(aChances)
                        .duration(aDuration)
                        .eut(aEUt)
                );
                return true;
            }
        }
        return false;
    }
    
	@Override
	public boolean addArcFurnaceRecipe(ItemStack aInput, ItemStack[] aOutputs, int[] aChances, int aDuration, int aEUt) {
		return addArcFurnaceRecipe(aInput, aOutputs, aChances, aDuration, aEUt,	false);
	}

    public boolean addArcFurnaceRecipe(ItemStack aInput, ItemStack[] aOutputs, int[] aChances, int aDuration, int aEUt,	boolean hidden) {
        if ((aInput == null) || (aOutputs == null)) {
            return false;
        }
        for (ItemStack tStack : aOutputs) {
            if (tStack != null) {
                if ((aDuration = GregTech_API.sRecipeFile.get("arcfurnace", aInput, aDuration)) <= 0) {
                    return false;
                }
                GT_Recipe sRecipe = RecipeMaps.arcFurnaceRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aInput)
                        .itemOutputs(aOutputs)
                        .outputChances(aChances)
                        .fluidInputs(Materials.Oxygen.getGas(aDuration))
                        .duration(Math.max(1, aDuration))
                        .eut(Math.max(1, aEUt))
                );
                if ((hidden) && (sRecipe != null)) {
                   sRecipe.mHidden = true;
                }
                for (Materials tMaterial : new Materials[]{Materials.Argon, Materials.Nitrogen}) {
                    if (tMaterial.mPlasma != null) {
                        int tPlasmaAmount = (int) Math.max(1L, aDuration / (tMaterial.getMass() * 16L));
                        GT_Recipe tRecipe = RecipeMaps.plasmaArcFurnaceRecipes.add(
                            GT_Values.RA.stdBuilder()
                                .itemInputs(aInput)
                                .itemOutputs(aOutputs)
                                .outputChances(aChances)
                                .fluidInputs(tMaterial.getPlasma(tPlasmaAmount))
                                .fluidOutputs(tMaterial.getGas(tPlasmaAmount))
                                .duration(Math.max(1, aDuration / 16))
                                .eut(Math.max(1, aEUt / 3))
                        );
                        if ((hidden) && (tRecipe != null)) {
                           tRecipe.mHidden = true;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean addSimpleArcFurnaceRecipe(ItemStack aInput, FluidStack aFluidInput, ItemStack[] aOutputs, int[] aChances, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutputs == null) || aFluidInput == null) {
            return false;
        }
        for (ItemStack tStack : aOutputs) {
            if (tStack != null) {
                if ((aDuration = GregTech_API.sRecipeFile.get("arcfurnace", aInput, aDuration)) <= 0) {
                    return false;
                }
                RecipeMaps.arcFurnaceRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aInput)
                        .itemOutputs(aOutputs)
                        .outputChances(aChances)
                        .fluidInputs(aFluidInput)
                        .duration(Math.max(1, aDuration))
                        .eut(Math.max(1, aEUt))
                );
                return true;
            }
        }
        return false;
    }

    public boolean addPlasmaArcFurnaceRecipe(ItemStack aInput, FluidStack aFluidInput, ItemStack[] aOutputs, int[] aChances, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutputs == null) || aFluidInput == null) {
            return false;
        }
        for (ItemStack tStack : aOutputs) {
            if (tStack != null) {
                if ((aDuration = GregTech_API.sRecipeFile.get("arcfurnace", aInput, aDuration)) <= 0) {
                    return false;
                }
                RecipeMaps.plasmaArcFurnaceRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aInput)
                        .itemOutputs(aOutputs)
                        .outputChances(aChances)
                        .fluidInputs(aFluidInput)
                        .duration(Math.max(1, aDuration))
                        .eut(Math.max(1, aEUt))
                );
                return true;
            }
        }
        return false;
    }

    public boolean addPlasmaArcFurnaceRecipe(ItemStack aInput, FluidStack aFluidInput, ItemStack[] aOutputs, FluidStack aFluidOutput, int[] aChances, int aDuration, int aEUt) {
        if ((aInput == null) || (aOutputs == null) || aFluidInput == null) {
            return false;
        }
        for (ItemStack tStack : aOutputs) {
            if (tStack != null) {
                if ((aDuration = GregTech_API.sRecipeFile.get("arcfurnace", aInput, aDuration)) <= 0) {
                    return false;
                }
                RecipeMaps.plasmaArcFurnaceRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aInput)
                        .itemOutputs(aOutputs)
                        .outputChances(aChances)
                        .fluidInputs(aFluidInput)
                        .fluidOutputs(aFluidOutput)
                        .duration(Math.max(1, aDuration))
                        .eut(Math.max(1, aEUt))
                );
                return true;
            }
        }
        return false;
    }
    
	@Override
	public boolean addPulveriserRecipe(ItemStack aInput, ItemStack[] aOutputs, int[] aChances, int aDuration, int aEUt) {
		return addPulveriserRecipe(aInput, aOutputs, aChances, aDuration, aEUt, false);
	}

    public boolean addPulveriserRecipe(ItemStack aInput, ItemStack[] aOutputs, int[] aChances, int aDuration, int aEUt, boolean hidden) {
        if ((aInput == null) || (aOutputs == null)) {
            return false;
        }
        for (ItemStack tStack : aOutputs) {
            if (tStack != null) {
                if ((aDuration = GregTech_API.sRecipeFile.get("pulveriser", aInput, aDuration)) <= 0) {
                    return false;
                }
                GT_Recipe tRecipe = RecipeMaps.maceratorRecipes.add(
                    GT_Values.RA.stdBuilder()
                        .itemInputs(aInput)
                        .itemOutputs(aOutputs)
                        .outputChances(aChances)
                        .duration(aDuration)
                        .eut(aEUt)
                );
                if ((hidden) && (tRecipe != null)) {
                   tRecipe.mHidden = true;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addPyrolyseRecipe(ItemStack aInput, FluidStack aFluidInput, int intCircuit, ItemStack aOutput, FluidStack aFluidOutput, int aDuration, int aEUt) {
        if (aInput == null) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("pyrolyse", aInput, aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.pyrolyseRecipes.add(
            GT_Values.RA.stdBuilder().optimize(false)
                .itemInputs(aInput, ItemList.Circuit_Integrated.getWithDamage(0L, intCircuit, new Object[0]))
                .itemOutputs(aOutput)
                .fluidInputs(aFluidInput)
                .fluidOutputs(aFluidOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    @Override
    @Deprecated
    public boolean addCrackingRecipe(FluidStack aInput, FluidStack aOutput, int aDuration, int aEUt) {
//        if ((aInput == null) || (aOutput == null)) {
//            return false;
//        }
//        if ((aDuration = GregTech_API.sRecipeFile.get("cracking", aInput.getUnlocalizedName(), aDuration)) <= 0) {
//            return false;
//        }
//        GT_Recipe.GT_Recipe_Map.sCrakingRecipes.addRecipe(true, null, null, null, null, new FluidStack[]{aInput}, new FluidStack[]{aOutput}, aDuration, aEUt, 0);
//        GT_Recipe.GT_Recipe_Map.sCrakingRecipes.addRecipe(true, null, null, null, null, new FluidStack[]{aInput, GT_ModHandler.getSteam(aInput.amount)}, new FluidStack[]{aOutput, Materials.Hydrogen.getGas(aInput.amount)}, aDuration, aEUt, 0);
//        GT_Recipe.GT_Recipe_Map.sCrakingRecipes.addRecipe(true, null, null, null, null, new FluidStack[]{aInput, Materials.Hydrogen.getGas(aInput.amount)}, new FluidStack[]{new FluidStack(aOutput.getFluid(), (int) (aOutput.amount * 1.3))}, aDuration, aEUt, 0);
//        return true;
    	return false;
    }

    @Override
    public boolean addCrackingRecipe(int circuitConfig, FluidStack aInput, FluidStack aInput2, FluidStack aOutput, int aDuration, int aEUt) {
        if ((aInput == null && aInput2 == null) || (aOutput == null)) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("cracking", aInput.getUnlocalizedName(), aDuration)) <= 0) {
            return false;
        }
        RecipeMaps.oilCrackerRecipes.add(
            GT_Values.RA.stdBuilder().optimize(false)
                .itemInputs(GT_Utility.getIntegratedCircuit(circuitConfig))
                .fluidInputs(aInput, aInput2)
                .fluidOutputs(aOutput)
                .duration(aDuration)
                .eut(aEUt)
        );
        return true;
    }

    @Override
	public boolean addAssemblylineRecipe(ItemStack aResearchItem, int aResearchTime, ItemStack[] aInputs, FluidStack[] aFluidInputs, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aResearchItem==null)||(aResearchTime<=0)||(aInputs == null) || (aOutput == null) || aInputs.length>15 || aInputs.length<4) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("assemblingline", aOutput, aDuration)) <= 0) {
            return false;
        } 
        for(ItemStack tItem : aInputs){
            if(tItem==null){
                System.out.println("addAssemblingLineRecipe "+aResearchItem.getDisplayName()+" --> "+aOutput.getUnlocalizedName()+" there is some null item in that recipe");
            }
        }
        GT_Recipe.GT_Recipe_Map.sScannerFakeRecipes.addFakeRecipe(false, new ItemStack[]{aResearchItem}, new ItemStack[]{aOutput}, new ItemStack[]{ItemList.Tool_DataStick.getWithName(1L, "Writes Research result", new Object[0])}, null, null, aResearchTime, 30, -201);
        GT_Recipe.GT_Recipe_Map.sAssemblylineVisualRecipes.addFakeRecipe(false,aInputs,new ItemStack[]{aOutput},new ItemStack[]{ItemList.Tool_DataStick.getWithName(1L, "Reads Research result", new Object[0])},aFluidInputs,null,aDuration,aEUt,0,true);
        GT_Recipe.GT_Recipe_AssemblyLine.sAssemblylineRecipes.add(new GT_Recipe_AssemblyLine( aResearchItem, aResearchTime, aInputs, aFluidInputs, aOutput, aDuration, aEUt));
        return true;
	}

    @Override
	public boolean addAssemblylineRecipe(ItemStack aResearchItem, int aResearchTime, Object[] aInputs, FluidStack[] aFluidInputs, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aResearchItem==null)||(aResearchTime<=0)||(aInputs == null) || (aOutput == null) || aInputs.length>15 || aInputs.length<4) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("assemblingline", aOutput, aDuration)) <= 0) {
            return false;
        } 
        ItemStack[] tInputs = new ItemStack[aInputs.length];
        ItemStack[][] tAlts = new ItemStack[aInputs.length][];
        for(int i = 0; i < aInputs.length; i++){
        	Object obj = aInputs[i];
        	if (obj instanceof ItemStack) {
        		tInputs[i] = (ItemStack) obj;
        		tAlts[i] = null;
        		continue;
        	} else if (obj instanceof ItemStack[]) {
        		ItemStack[] aStacks = (ItemStack[]) obj;
        		if (aStacks.length > 0) {
        			tInputs[i] = aStacks[0];
        			tAlts[i] = (ItemStack[]) Arrays.copyOf(aStacks, aStacks.length);
        			continue;
        		}
        	} else if (obj instanceof Object[]) {
        		Object[] objs = (Object[]) obj;
        		List<ItemStack> tList;
        		if (objs.length >= 2 && !(tList = GT_OreDictUnificator.getOres(objs[0])).isEmpty()) {
        			try {
        				int tAmount = (int) objs[1];
            			List<ItemStack> uList = new ArrayList<>();
            			for (ItemStack tStack : tList) {
            				ItemStack uStack = GT_Utility.copyAmount(tAmount, tStack); 
            				if (GT_Utility.isStackValid(uStack)) {
            					uList.add(uStack);
            					if (tInputs[i] == null) tInputs[i] = uStack;
            				}
            			}
            			tAlts[i] = uList.toArray(new ItemStack[uList.size()]);
            			continue;
        			} catch (Exception t) {}
        		}
        	}
        	System.out.println("addAssemblingLineRecipe "+aResearchItem.getDisplayName()+" --> "+aOutput.getUnlocalizedName()+" there is some null item in that recipe");
        }
        GT_Recipe.GT_Recipe_Map.sScannerFakeRecipes.addFakeRecipe(false, new ItemStack[]{aResearchItem}, new ItemStack[]{aOutput}, new ItemStack[]{ItemList.Tool_DataStick.getWithName(1L, "Writes Research result", new Object[0])}, null, null, aResearchTime, 30, -201);
        GT_Recipe.GT_Recipe_Map.sAssemblylineVisualRecipes.addFakeRecipe(false,tInputs,new ItemStack[]{aOutput},new ItemStack[]{ItemList.Tool_DataStick.getWithName(1L, "Reads Research result", new Object[0])},aFluidInputs,null,aDuration,aEUt,0,tAlts,true);
        GT_Recipe.GT_Recipe_AssemblyLine.sAssemblylineRecipes.add(new GT_Recipe_AssemblyLine( aResearchItem, aResearchTime, tInputs, aFluidInputs, aOutput, aDuration, aEUt, tAlts));
        return true;
	}

	@Override
	public boolean addCircuitAssemblerRecipe(ItemStack[] aInputs, FluidStack aFluidInput, ItemStack aOutput, int aDuration, int aEUt) {
        if ((aInputs == null) || (aOutput == null) || aInputs.length>6 || aInputs.length<1) {
            return false;
        }
        if ((aDuration = GregTech_API.sRecipeFile.get("circuitassembler", aOutput, aDuration)) <= 0) {
            return false;
        } 
        RecipeMaps.circuitAssemblerRecipes.add(
            GT_Values.RA.stdBuilder()
                .itemInputs(aInputs)
                .itemOutputs(aOutput)
                .fluidInputs(aFluidInput)
                .duration(aDuration)
                .eut(aEUt)
        );
		return true;    
	}

	private boolean areItemsAndFluidsBothNull(ItemStack[] items, FluidStack[] fluids){
    	boolean itemsNull = true;
    	if (items != null) {
    		for (ItemStack itemStack : items) {
    			if (itemStack != null) {
    				itemsNull = false;
    				break;
    			}
    		}
    	}
    	boolean fluidsNull = true;
    	if (fluids != null) {
    		for (FluidStack fluidStack : fluids) {
    			if (fluidStack != null) {
    				fluidsNull = false;
    				break;
    			}
    		}
    	}
    	return itemsNull && fluidsNull;

	}

	public boolean isAddingDeprecatedRecipes() {
		return isAddingDeprecatedRecipes;
	}

	public void setIsAddingDeprecatedRecipes(boolean isAddingDeprecatedRecipes) {
		this.isAddingDeprecatedRecipes = isAddingDeprecatedRecipes;
	}

	@Override
	public gregtech.api.util.GTRecipeBuilder stdBuilder() {
		return new gregtech.api.util.GTRecipeBuilder();
	}

}
