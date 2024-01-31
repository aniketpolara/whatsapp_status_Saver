package com.console.gbversion.whatsscan.Application.Utils;

/* loaded from: classes2.dex */
public final class StoredData {
    public static final StoredData INSTANCE = new StoredData();
    private static final char[] mirror_small_capital_letters_and_digits = {592, 'q', 596, 'p', 477, 607, 595, 613, 7433, 383, 670, 'l', 623, 'u', 'o', 'd', 'b', 633, 's', 647, 'n', 652, 653, 'x', 654, 'z', 11375, 42221, 42203, 42231, 398, 42206, 42216, 'H', 'I', 383, 42200, 42230, 'W', 'N', 'O', 42194, 8184, 42212, 'S', 42197, 42229, 42213, 'M', 'X', 8516, 'Z', '0', 406, 1351, 400, 'h', 2796, '9', 'L', '8', '6'};
    private static final char[] simple_small_capital_letters_and_digits = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    static {
        new Art("▁ ▂ ▄ ▅ ▆ ▇ █", "█ ▇ ▆ ▅ ▄ ▂ ▁");
        new Art("°°°·.°·..·°¯°·._.·", "·._.·°¯°·.·° .·°°°");
        new Art("(¯`·.¸¸.·´¯`·.¸¸.->", "<-.¸¸.·´¯`·.¸¸.·´¯)");
        new Art("ıllıllı", "ıllıllı");
        new Art("¸,ø¤º°`°º¤ø,¸¸,ø¤º°", "°º¤ø,¸¸,ø¤º°`°º¤ø,¸");
        new Art("(¯´•._.•", "•._.•´¯)");
        new Art("꧁༒༻☬ད", "ཌ☬༺༒꧂");
        new Art("¸„.-•~¹°”ˆ˜¨", "¨˜ˆ”°¹~•-.„¸");
        new Art("˜”*°•.˜”*°•", "•°*”˜.•°*”˜");
        new Art("♥", "♥");
        new Art("♥♥♥", "♥♥♥");
        new Art("✎🐠", "🍓💝");
        new Art("(-_-)", "(-_-)");
        new Art("¤¸¸.•´¯`•¸¸.•..>>", "<<..•.¸¸•´¯`•.¸¸¤");
        new Art("*•.¸♡", "♡¸.•*");
        new Art("◥꧁ད ॐ卐", "卐ॐ ཌ꧂◤");
        new Art("🍑 ⋆ 🍎 🎀", "🎀 🍎 ⋆ 🍑");
        new Art("░▒▓█►─═ ♥", "♥ ═─◄█▓▒░");
        new Art(".•°¤*(¯`★´¯)*¤°", "°¤*(¯´★`¯)*¤°•.");
        new Art("🐣 🎀", "🎀 🐣");
        new Art("╚»★«╝", "╚»★«╝");
        new Art("∙∙·▫▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ", "ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫▫·∙∙");
        new Art("¯_( ͡° ͜ʖ ͡°)_/¯", "¯_( ͡° ͜ʖ ͡°)_/¯");
    }

    private StoredData() {
    }

    public final char[] getMirror_small_capital_letters_and_digits() {
        return mirror_small_capital_letters_and_digits;
    }

    public final char[] getSimple_small_capital_letters_and_digits() {
        return simple_small_capital_letters_and_digits;
    }
}
