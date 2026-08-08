package gregtech.api.gui;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

/**
 * Draws a gauge pointer hand, rotating around the x&y coordinates.
 * Ported from GTNH CircularGaugeDrawable for vanilla MC 1.7.10 (Java 7).
 */
public class GT_CircularGaugeDrawable {

    private final float minAngle = (float) Math.toRadians(-235.0);
    private final float maxAngle = (float) Math.toRadians(45.0);
    private final int color;
    private float lastAngle = Float.NaN;
    private double progress = 0.0;

    public GT_CircularGaugeDrawable() {
        this(0xFF431d00);
    }

    public GT_CircularGaugeDrawable(int aColor) {
        this.color = aColor;
    }

    public void setProgress(double aProgress) {
        this.progress = aProgress;
    }

    public void draw(float x0, float y0, float width, float height) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glShadeModel(GL11.GL_SMOOTH);

        final Tessellator tessellator = Tessellator.instance;

        final float clampedProgress = MathHelper.clamp_float((float) progress, 0.0f, 1.0f);
        final float newDialAngle = minAngle + clampedProgress * (maxAngle - minAngle);
        if (Float.isNaN(lastAngle)) {
            lastAngle = newDialAngle;
        } else {
            lastAngle = (lastAngle + newDialAngle) / 2.0f;
        }
        final float angle = lastAngle;
        final float sinA = (float) Math.sin(-angle);
        final float cosA = (float) Math.cos(-angle);
        height /= 2.0f;

        tessellator.startDrawing(GL11.GL_TRIANGLE_STRIP);
        tessellator.setColorRGBA(
                (color >> 16) & 0xFF,
                (color >> 8) & 0xFF,
                color & 0xFF,
                (color >> 24) & 0xFF);
        tessellator.addVertex(x0 + width * cosA, y0 - width * sinA, 0.0f);
        tessellator.addVertex(x0 - height * sinA, y0 - height * cosA, 0.0f);
        tessellator.addVertex(x0 + height * sinA, y0 + height * cosA, 0.0f);
        tessellator.addVertex(x0 - height * cosA, y0 + height * sinA, 0.0f);
        tessellator.draw();

        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
