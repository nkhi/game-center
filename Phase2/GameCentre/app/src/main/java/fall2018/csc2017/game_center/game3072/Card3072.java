package fall2018.csc2017.game_center.game3072;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card3072 extends FrameLayout {

    private static final int TEXT_SIZE = 32;
    private static final int LABEL_BACKGROUND_COL = 0x33ffffff;
    private static final int PLACEHOLDER_WIDTH_HEIGHT = -1;
    private static final int LABEL_MARGIN = 10;

    public static final int BASE_NUM = 3;

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

    protected int cardWidth = getCardWidth();
    private int num = 0;
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

    public int getNum() {
        return num;
    }

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

    public boolean equals(Card3072 c) {
        return getNum() == c.getNum();
    }

    private int getCardWidth() {
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();
        int cardWidth;
        cardWidth = displayMetrics.widthPixels;
        return (cardWidth - LABEL_MARGIN) / 4;
    }
}