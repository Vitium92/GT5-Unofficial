package gregtech.common.tileentities.generators;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ConfigCategories;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_MetaTileEntity_SteamTurbine extends GT_MetaTileEntity_BasicGenerator {

    public int mEfficiency;
    private int mLastFluidAmount = 0;
    private int mSteamAccumulated = 0;
    private FluidStack mDistilledWater = null;

    public GT_MetaTileEntity_SteamTurbine(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier, new String[]{
                "Converts Steam into EU",
                "Base rate: 2L of Steam -> 1 EU"});
        onConfigLoad();
    }

    public GT_MetaTileEntity_SteamTurbine(String aName, int aTier, String aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
        onConfigLoad();
    }

    public GT_MetaTileEntity_SteamTurbine(String aName, int aTier, String[] aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
        onConfigLoad();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setInteger("mSteamAccumulated", this.mSteamAccumulated);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.mSteamAccumulated = aNBT.getInteger("mSteamAccumulated");
    }

    public boolean isOutputFacing(byte aSide) {
        return aSide == getBaseMetaTileEntity().getFrontFacing();
    }

    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_SteamTurbine(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    public GT_Recipe.GT_Recipe_Map getRecipes() {
        return null;
    }

    @Override
    public String[] getDescription() {
        String[] desc = new String[mDescriptionArray.length + 3];
        System.arraycopy(mDescriptionArray, 0, desc, 0, mDescriptionArray.length);
        desc[mDescriptionArray.length] = "Fuel Efficiency: " + (600 / getEfficiency()) + "%";
        desc[mDescriptionArray.length + 1] = String.format("Consumes up to %sL of Steam per second",
                (int) (4000 * (8 * Math.pow(4, mTier) + Math.pow(2, mTier)) / (600 / getEfficiency())));
        desc[mDescriptionArray.length + 2] = String.format("Outputs 1L of Distilled Water per %dL of Steam",
                getSteamToWaterRatio());
        return desc;
    }

    public int getCapacity() {
        return 24000 * this.mTier;
    }

    public void onConfigLoad() {
        this.mEfficiency = GregTech_API.sMachineFile.get(ConfigCategories.machineconfig, "SteamTurbine.efficiency.tier." + this.mTier, 6 + this.mTier);
    }

    public int getEfficiency() {
        return this.mEfficiency;
    }

    public int getSteamToWaterRatio() {
        return this.mTier == 1 ? 200 : this.mTier == 2 ? 180 : 160;
    }

    public int getFuelValue(FluidStack aLiquid) {
        if (aLiquid == null) return 0;
        String fluidName = aLiquid.getFluid().getUnlocalizedName(aLiquid);
        return GT_ModHandler.isSteam(aLiquid) || fluidName.equals("fluid.steam") || fluidName.equals("ic2.fluidSteam") || fluidName.equals("fluid.mfr.steam.still.name") ? 1 : 0;
    }

    public int consumedFluidPerOperation(FluidStack aLiquid) {
        return this.mEfficiency;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        mLastFluidAmount = (mFluid != null) ? mFluid.amount : 0;
        super.onPostTick(aBaseMetaTileEntity, aTick);

        if (aBaseMetaTileEntity.isServerSide() && mLastFluidAmount > 0 && mFluid != null) {
            int consumed = mLastFluidAmount - mFluid.amount;
            if (consumed > 0) {
                mSteamAccumulated += consumed;
                while (mSteamAccumulated >= getSteamToWaterRatio()) {
                    mSteamAccumulated -= getSteamToWaterRatio();
                    if (mDistilledWater == null) {
                        mDistilledWater = GT_ModHandler.getDistilledWater(1);
                    } else {
                        mDistilledWater.amount += 1;
                    }
                }
            }
        }

        if (aBaseMetaTileEntity.isServerSide() && mDistilledWater != null) {
            pushDistilledWater(aBaseMetaTileEntity);
        }
    }

    private void pushDistilledWater(IGregTechTileEntity aBaseMetaTileEntity) {
        boolean foundOutput = false;
        for (byte i = 1; mDistilledWater != null && i < 6; i++) {
            if (i != aBaseMetaTileEntity.getFrontFacing()) {
                IFluidHandler tTileEntity = aBaseMetaTileEntity.getITankContainerAtSide(i);
                if (tTileEntity != null) {
                    foundOutput = true;
                    FluidStack tDrained = aBaseMetaTileEntity.drain(
                            ForgeDirection.getOrientation(i),
                            Math.max(1, mDistilledWater.amount / 2), false);
                    if (tDrained != null) {
                        int tFilledAmount = tTileEntity.fill(
                                ForgeDirection.getOrientation(i).getOpposite(), tDrained, false);
                        if (tFilledAmount > 0) {
                            tTileEntity.fill(
                                    ForgeDirection.getOrientation(i).getOpposite(),
                                    aBaseMetaTileEntity.drain(
                                            ForgeDirection.getOrientation(i), tFilledAmount, true),
                                    true);
                        }
                    }
                }
            }
        }
        if (!foundOutput && mDistilledWater != null) {
            mDistilledWater = null;
        }
    }

    public ITexture[] getFront(byte aColor) {
        return new ITexture[]{super.getFront(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_FRONT),
                Textures.BlockIcons.OVERLAYS_ENERGY_OUT[this.mTier]};
    }

    public ITexture[] getBack(byte aColor) {
        return new ITexture[]{super.getBack(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_BACK)};
    }

    public ITexture[] getBottom(byte aColor) {
        return new ITexture[]{super.getBottom(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_BOTTOM)};
    }

    public ITexture[] getTop(byte aColor) {
        return new ITexture[]{super.getTop(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_TOP)};
    }

    public ITexture[] getSides(byte aColor) {
        return new ITexture[]{super.getSides(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_SIDE)};
    }

    public ITexture[] getFrontActive(byte aColor) {
        return new ITexture[]{super.getFrontActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_FRONT_ACTIVE),
                Textures.BlockIcons.OVERLAYS_ENERGY_OUT[this.mTier]};
    }

    public ITexture[] getBackActive(byte aColor) {
        return new ITexture[]{super.getBackActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_BACK_ACTIVE)};
    }

    public ITexture[] getBottomActive(byte aColor) {
        return new ITexture[]{super.getBottomActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_BOTTOM_ACTIVE)};
    }

    public ITexture[] getTopActive(byte aColor) {
        return new ITexture[]{super.getTopActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_TOP_ACTIVE)};
    }

    public ITexture[] getSidesActive(byte aColor) {
        return new ITexture[]{super.getSidesActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.STEAM_TURBINE_SIDE_ACTIVE)};
    }

    @Override
    public int getPollution() {
        return 0;
    }

    @Override
    public boolean isFluidInputAllowed(FluidStack aFluid) {
        if (aFluid.getFluid().getUnlocalizedName(aFluid).equals("ic2.fluidSuperheatedSteam")) {
            aFluid.amount = 0;
            aFluid = null;
            return false;
        }
        return super.isFluidInputAllowed(aFluid);
    }
}
