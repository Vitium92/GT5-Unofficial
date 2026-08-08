package gregtech.api.enums;

import gregtech.api.interfaces.internal.IGT_Mod;
import gregtech.api.interfaces.internal.IGT_RecipeAdder;
import gregtech.api.net.IGT_NetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.Iterator;

public class GTValues {

    public static final String E = GT_Values.E;
    public static final int[] B = GT_Values.B;
    public static final long M = GT_Values.M;
    public static final long L = GT_Values.L;
    public static final short W = GT_Values.W;

    public static final long[] V = GT_Values.V;

    public static final long[] VP = new long[V.length];
    static {
        for (int i = 0; i < V.length; i++) {
            VP[i] = V[i] * 30 / 32;
        }
    }

    public static final long[] AatV = new long[V.length];
    static {
        for (int i = 0; i < V.length; i++) {
            AatV[i] = Integer.MAX_VALUE / V[i];
        }
    }

    public static final String[] VN = GT_Values.VN;
    public static final String[] VOLTAGE_NAMES = GT_Values.VOLTAGE_NAMES;

    public static final ItemStack NI = GT_Values.NI;
    public static final FluidStack NF = GT_Values.NF;

    public static final IGT_Mod GT = GT_Values.GT;
    public static final IGT_RecipeAdder RA = GT_Values.RA;
    public static final IGT_NetworkHandler NW = GT_Values.NW;

    public static boolean D1 = GT_Values.D1;
    public static boolean D2 = GT_Values.D2;
    public static World DW = GT_Values.DW;

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T>[] emptyIteratorArray() {
        return new Iterator[0];
    }
}
