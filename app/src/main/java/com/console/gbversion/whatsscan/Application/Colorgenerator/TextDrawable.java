package com.console.gbversion.whatsscan.Application.Colorgenerator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;

/* loaded from: classes.dex */
public class TextDrawable extends ShapeDrawable {
    private static final float SHADE_FACTOR = 0.9f;
    private final Paint borderPaint;
    private final int borderThickness;
    private final int color;
    private final int fontSize;
    private final int height;
    private final float radius;
    private final RectShape shape;
    private final String text;
    private final Paint textPaint;
    private final int width;

    /* loaded from: classes.dex */
    public interface IBuilder {
        TextDrawable build(String str, int i);
    }

    /* loaded from: classes.dex */
    public interface IConfigBuilder {
        IConfigBuilder bold();

        IShapeBuilder endConfig();

        IConfigBuilder fontSize(int i);

        IConfigBuilder height(int i);

        IConfigBuilder textColor(int i);

        IConfigBuilder toUpperCase();

        IConfigBuilder useFont(Typeface typeface);

        IConfigBuilder width(int i);

        IConfigBuilder withBorder(int i);
    }

    /* loaded from: classes.dex */
    public interface IShapeBuilder {
        IConfigBuilder beginConfig();

        TextDrawable buildRect(String str, int i);

        TextDrawable buildRound(String str, int i);

        TextDrawable buildRoundRect(String str, int i, int i2);

        IBuilder rect();

        IBuilder round();

        IBuilder roundRect(int i);
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    private TextDrawable(Builder builder) {
        super(builder.shape);
        String str;
        this.shape = builder.shape;
        this.height = builder.height;
        this.width = builder.width;
        this.radius = builder.radius;
        if (builder.toUpperCase) {
            str = builder.text.toUpperCase();
        } else {
            str = builder.text;
        }
        this.text = str;
        int i = builder.color;
        this.color = i;
        this.fontSize = builder.fontSize;
        Paint paint = new Paint();
        this.textPaint = paint;
        paint.setColor(builder.textColor);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(builder.isBold);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(builder.font);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth((float) builder.borderThickness);
        int i2 = builder.borderThickness;
        this.borderThickness = i2;
        Paint paint2 = new Paint();
        this.borderPaint = paint2;
        paint2.setColor(getDarkerShade(i));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth((float) i2);
        getPaint().setColor(i);
    }

    private int getDarkerShade(int i) {
        return Color.rgb((int) (((float) Color.red(i)) * SHADE_FACTOR), (int) (((float) Color.green(i)) * SHADE_FACTOR), (int) (((float) Color.blue(i)) * SHADE_FACTOR));
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect bounds = getBounds();
        if (this.borderThickness > 0) {
            drawBorder(canvas);
        }
        int save = canvas.save();
        canvas.translate((float) bounds.left, (float) bounds.top);
        int i = this.width;
        if (i < 0) {
            i = bounds.width();
        }
        int i2 = this.height;
        if (i2 < 0) {
            i2 = bounds.height();
        }
        int i3 = this.fontSize;
        if (i3 < 0) {
            i3 = Math.min(i, i2) / 2;
        }
        this.textPaint.setTextSize((float) i3);
        canvas.drawText(this.text, (float) (i / 2), ((float) (i2 / 2)) - ((this.textPaint.descent() + this.textPaint.ascent()) / 2.0f), this.textPaint);
        canvas.restoreToCount(save);
    }

    private void drawBorder(Canvas canvas) {
        RectF rectF = new RectF(getBounds());
        int i = this.borderThickness;
        rectF.inset((float) (i / 2), (float) (i / 2));
        RectShape rectShape = this.shape;
        if (rectShape instanceof OvalShape) {
            canvas.drawOval(rectF, this.borderPaint);
        } else if (rectShape instanceof RoundRectShape) {
            float f = this.radius;
            canvas.drawRoundRect(rectF, f, f, this.borderPaint);
        } else {
            canvas.drawRect(rectF, this.borderPaint);
        }
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.textPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.textPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.width;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.height;
    }

    public static IShapeBuilder builder() {
        return new Builder();
    }

    /* loaded from: classes.dex */
    public static class Builder implements IConfigBuilder, IShapeBuilder, IBuilder {
        private int borderThickness;
        private int color;
        private Typeface font;
        private int fontSize;
        private int height;
        private boolean isBold;
        public float radius;
        private RectShape shape;
        private String text;
        public int textColor;
        private boolean toUpperCase;
        private int width;

        @Override // com.amulyakhare.textdrawable.TextDrawable.IShapeBuilder
        public IConfigBuilder beginConfig() {
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IShapeBuilder endConfig() {
            return this;
        }

        private Builder() {
            this.text = "";
            this.color = -7829368;
            this.textColor = -1;
            this.borderThickness = 0;
            this.width = -1;
            this.height = -1;
            this.shape = new RectShape();
            this.font = Typeface.create("sans-serif-light", 0);
            this.fontSize = -1;
            this.isBold = false;
            this.toUpperCase = false;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IConfigBuilder width(int i) {
            this.width = i;
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IConfigBuilder height(int i) {
            this.height = i;
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IConfigBuilder textColor(int i) {
            this.textColor = i;
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IConfigBuilder withBorder(int i) {
            this.borderThickness = i;
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IConfigBuilder useFont(Typeface typeface) {
            this.font = typeface;
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IConfigBuilder fontSize(int i) {
            this.fontSize = i;
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IConfigBuilder bold() {
            this.isBold = true;
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IConfigBuilder
        public IConfigBuilder toUpperCase() {
            this.toUpperCase = true;
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IShapeBuilder
        public IBuilder rect() {
            this.shape = new RectShape();
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IShapeBuilder
        public IBuilder round() {
            this.shape = new OvalShape();
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IShapeBuilder
        public IBuilder roundRect(int i) {
            float f = (float) i;
            this.radius = f;
            this.shape = new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, null);
            return this;
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IShapeBuilder
        public TextDrawable buildRect(String str, int i) {
            rect();
            return build(str, i);
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IShapeBuilder
        public TextDrawable buildRoundRect(String str, int i, int i2) {
            roundRect(i2);
            return build(str, i);
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IShapeBuilder
        public TextDrawable buildRound(String str, int i) {
            round();
            return build(str, i);
        }

        @Override // com.amulyakhare.textdrawable.TextDrawable.IBuilder
        public TextDrawable build(String str, int i) {
            this.color = i;
            this.text = str;
            return new TextDrawable(this);
        }
    }
}
