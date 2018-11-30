package fall2018.csc2017.game_center.game3072;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Objects;

/**
 * A single "card" on the grid of the game - can be either empty or a number
 */
public class Card3072 extends FrameLayout {

    /**
     * Text size of number
     */
    private static final int TEXT_SIZE = 32;

    /**
     * Default background color of label
     */
    private static final int LABEL_BACKGROUND_COL = 0x33ffffff;

    /**
     * Placeholder width and height constant
     */
    private static final int PLACEHOLDER_WIDTH_HEIGHT = -1;

    /**
     * Margins for the text
     */
    private static final int LABEL_MARGIN = 10;

    /**
     * Base number of the game (2 for 2048, 3 for 3072...etc.)
     */
    public static final int BASE_NUM = 3;

    /*
     * Background colors of different numbers
     */
    private static final int EMPTY_BG = 0x00000000;
    private static final int NUM_1_BG = 0xffeee4da;
    private static final int NUM_2_BG = 0xffede0c8;
    private static final int NUM_3_BG = 0xfff2b179;
    private static final int NUM_4_BG = 0xfff59563;
    private static final int NUM_5_BG = 0xfff67c5f;
    private static final int NUM_6_BG = 0xfff65e3b;
    private static final int NUM_7_BG = 0xffedcf72;
    private static final int NUM_8_BG = 0xffedcc61;
    private static final int NUM_9_BG = 0xffedc850;
    private static final int NUM_10_BG = 0xffedc53f;
    private static final int NUM_11_BG = 0xffedc22e;
    private static final int NUM_12_BG = 0xff75a7f1;
    private static final int NUM_13_BG = 0xff4585f2;
    private static final int LARGE_NUM_BG = 0xff3c3a32;

    /**
     * Number displayed on the Card (0 for empty card)
     */
    private int num;

    /**
     * Label on the card displaying the text
     */
    private TextView label;

    public Card3072(Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(TEXT_SIZE);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(LABEL_BACKGROUND_COL);

        LayoutParams lp = new LayoutParams(PLACEHOLDER_WIDTH_HEIGHT, PLACEHOLDER_WIDTH_HEIGHT);
        lp.setMargins(LABEL_MARGIN, LABEL_MARGIN, 0, 0);
        addView(label, lp);

        setNum(0);
    }

    /**
     * Return the number of the card
     *
     * @return the number of the card
     */
    public int getNum() {
        return num;
    }

    /**
     * Sets the number, displays the updated card, and sets the background color
     * Suppresses String concatenation because text displayed with always be a number -
     * no need for translation
     *
     * @param num number to be set (0 for empty card)
     */
    @SuppressLint("SetTextI18n")
    public void setNum(int num) {
        this.num = num;

        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(num + "");
        }

        switch (num) {
            case 0:
                label.setBackgroundColor(EMPTY_BG);
                break;
            case BASE_NUM:
                label.setBackgroundColor(NUM_1_BG);
                break;
            case BASE_NUM * (1 << 1):
                label.setBackgroundColor(NUM_2_BG);
                break;
            case BASE_NUM * (1 << 2):
                label.setBackgroundColor(NUM_3_BG);
                break;
            case BASE_NUM * (1 << 3):
                label.setBackgroundColor(NUM_4_BG);
                break;
            case BASE_NUM * (1 << 4):
                label.setBackgroundColor(NUM_5_BG);
                break;
            case BASE_NUM * (1 << 5):
                label.setBackgroundColor(NUM_6_BG);
                break;
            case BASE_NUM * (1 << 6):
                label.setBackgroundColor(NUM_7_BG);
                break;
            case BASE_NUM * (1 << 7):
                label.setBackgroundColor(NUM_8_BG);
                break;
            case BASE_NUM * (1 << 8):
                label.setBackgroundColor(NUM_9_BG);
                break;
            case BASE_NUM * (1 << 9):
                label.setBackgroundColor(NUM_10_BG);
                break;
            case BASE_NUM * (1 << 10):
                label.setBackgroundColor(NUM_11_BG);
                break;
            case BASE_NUM * (1 << 11):
                label.setBackgroundColor(NUM_12_BG);
                break;
            case BASE_NUM * (1 << 12):
                label.setBackgroundColor(NUM_13_BG);
                break;
            default:
                label.setBackgroundColor(LARGE_NUM_BG);
                break;
        }
    }

    /**
     * Return the card's width
     *
     * @return width of the frame
     */
    int getCardWidth() {
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();
        int cardWidth;
        cardWidth = displayMetrics.widthPixels;
        return (cardWidth - LABEL_MARGIN) / 4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card3072 card3072 = (Card3072) o;
        return num == card3072.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}