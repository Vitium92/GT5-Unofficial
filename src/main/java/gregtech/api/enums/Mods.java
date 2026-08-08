package gregtech.api.enums;

import cpw.mods.fml.common.Loader;

public enum Mods {

    GregTech("GregTech", "GT", "gt"),
    IndustrialCraft2("IC2", "IC2", "IC2"),
    ThermalExpansion("ThermalExpansion", "TE", "CoFH|ThermalExpansion"),
    AppliedEnergistics2("appliedenergistics2", "AE2", "AE"),
    BuildCraftCore("BuildCraft|Core", "BC", "BC"),
    BuildCraftTransport("BuildCraft|Transport", "BCT", "BC"),
    BuildCraftFactory("BuildCraft|Factory", "BCF", "BC"),
    BuildCraftEnergy("BuildCraft|Energy", "BCE", "BC"),
    BuildCraftBuilders("BuildCraft|Builders", "BCB", "BC"),
    BuildCraftSilicon("BuildCraft|Silicon", "BCS", "BC"),
    Forestry("Forestry", "Forestry", "F"),
    Thaumcraft("Thaumcraft", "TC", "Thaumcraft"),
    TwilightForest("TwilightForest", "TF", "TF"),
    Railcraft("Railcraft", "RC", "RC"),
    Harvestcraft("harvestcraft", "HC", "HC"),
    GalcticraftCore("GalacticraftCore", "GC", "GC"),
    GalcticraftMars("GalacticraftMars", "GCM", "GC"),
    GalcticraftPlanets("GalacticraftPlanets", "GCP", "GC"),
    TaintedMagic("TaintedMagic", "TM", "TM"),
    Avaritia("Avaritia", "Avaritia", "Avaritia"),
    BloodMagic("AWWayofTime", "BM", "BM"),
    Botania("Botania", "Botania", "Botania"),
    Mariculture("Mariculture", "Mariculture", "Mariculture"),
    Natura("Natura", "Natura", "Natura"),
    BiomesOPlenty("BiomesOPlenty", "BoP", "BoP"),
    OpenComputers("OpenComputers", "OC", "OC"),
    ComputerCraft("ComputerCraft", "CC", "CC"),
    DraconicEvolution("DraconicEvolution", "DE", "DE"),
    ExtraUtilities("ExtraUtilities", "EU", "EU"),
    EnderIO("EnderIO", "EIO", "EIO"),
    Chisel2("chisel", "Chisel", "Chisel"),
    PamHarvestCraft("harvestcraft", "PamHC", "HC"),
    NetherOres("NetherOres", "NO", "NO"),
    ImmersiveEngineering("ImmersiveEngineering", "IE", "IE"),
    MetallurgyCore("MetallurgyCore", "MetCore", "Met"),
    Metallurgy4("Metallurgy", "Met4", "Met"),
    TinkersConstruct("TConstruct", "TiC", "TiC"),
    MineTweaker3("MineTweaker3", "MT3", "MT"),
    CraftTweaker("CraftTweaker", "CT", "CT"),
    GTPlusPlus("GTPlusPlus", "GT++", "GT++"),
    BartWorks("bartworks", "BW", "BW"),
    NewHorizonsCoreMod("NewHorizonsCoreMod", "NHCore", "NH"),
    Galacticgreg("galacticgreg", "GG", "GG"),
    Kekztech("Kekztech", "Kekz", "Kekz"),
    TecTech("tectech", "TT", "TT"),
    TecTechGTNH("GTNH-TecTech", "TTGTNH", "TT"),
    EnderZoo("EnderZoo", "EZ", "EZ"),
    ActuallyAdditions("actuallyadditions", "AA", "AA"),
    EnderCrop("endercrop", "EC", "EC"),
    StorageDrawers("StorageDrawers", "SD", "SD"),
    StorageDrawersExtra("StorageDrawersExtra", "SDE", "SDE"),
    RefinedStorage("refinedstorage", "RS", "RS"),
    ExtraCells2("extracells", "EC2", "EC2"),
    AppliedEnergistics2Fluids("ae2fluids-rvc", "AE2F", "AE2"),
    DeadRail("deadrail", "DR", "DR"),
    WAILA("Waila", "WAILA", "WAILA"),
    HWYLA("Hwyla", "HWYLA", "HWYLA"),
    NEI("NotEnoughItems", "NEI", "NEI"),
    JourneyMap("JourneyMap", "JM", "JM"),
    VoxelMap("voxelmap", "VM", "VM"),
    XaerosMinimap("XaerosMinimap", "XM", "XM"),
    Baubles("Baubles", "Baubles", "Baubles");

    public final String modID;
    public final String shortName;
    public final String alternativeModID;

    Mods(String modID, String shortName, String alternativeModID) {
        this.modID = modID;
        this.shortName = shortName;
        this.alternativeModID = alternativeModID;
    }

    public boolean isModLoaded() {
        return Loader.isModLoaded(modID);
    }

    public static boolean isModLoaded(String modID) {
        return Loader.isModLoaded(modID);
    }
}
