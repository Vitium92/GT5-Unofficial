package gregtech.api.util;

/**
 * Constants used by the Minetweaker/GTNH recipe API.
 * <p>
 * These constants are referenced by the Minetweaker-Gregtech-5-Addon
 * for recipe construction and fuel registration.
 */
public class GTRecipeConstants {

    /**
     * Metadata key for fuel value (EU per millibucket).
     */
    public static final String FUEL_VALUE = "fuel_value";

    /**
     * Metadata key for fuel type (generator type index).
     */
    public static final String FUEL_TYPE = "fuel_type";

    /**
     * Recipe map name for diesel generator fuels.
     * In GT5U, fuels are in separate maps. This constant points to
     * the diesel fuel map as the primary fuel map.
     */
    public static final String Fuel = "gt.recipe.dieselgeneratorfuel";

    /**
     * Metadata key for cleanroom requirement.
     */
    public static final String CLEANROOM = "cleanroom";

    /**
     * Metadata key for coil tier in blast furnace.
     */
    public static final String COIL_TIER = "coil_tier";
}
