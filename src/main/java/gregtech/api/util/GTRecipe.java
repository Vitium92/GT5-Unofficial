package gregtech.api.util;

public class GTRecipe {

    private final GT_Recipe mBackend;

    public GTRecipe(GT_Recipe backend) {
        this.mBackend = backend;
    }

    public GT_Recipe getBackend() {
        return mBackend;
    }
}
