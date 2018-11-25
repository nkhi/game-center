package fall2018.csc2017.game_center.a3072;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

    public Card(Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33ffffff);

        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10,10,0,0);
        addView(label, lp);

        setNum(0);
    }

    private int num = 0;
    protected int cardWidth = GetCardWidth();

    public int getNum() {
        return num;
    }

    @SuppressLint("SetTextI18n")
    public void setNum(int num) {
        this.num = num;

        if (num<=0) {
            label.setText("");
        }else{
            label.setText(num+"");
        }

        switch (num) {
            case 0:
                label.setBackgroundColor(0x00000000);
                break;
            case 3:
                label.setBackgroundColor(0xffeee4da);
                break;
            case 6:
                label.setBackgroundColor(0xffede0c8);
                break;
            case 12:
                label.setBackgroundColor(0xfff2b179);
                break;
            case 24:
                label.setBackgroundColor(0xfff59563);
                break;
            case 48:
                label.setBackgroundColor(0xfff67c5f);
                break;
            case 96:
                label.setBackgroundColor(0xfff65e3b);
                break;
            case 192:
                label.setBackgroundColor(0xffedcf72);
                break;
            case 384:
                label.setBackgroundColor(0xffedcc61);
                break;
            case 768:
                label.setBackgroundColor(0xffedc850);
                break;
            case 1536:
                label.setBackgroundColor(0xffedc53f);
                break;
            case 3072:
                label.setBackgroundColor(0xffedc22e);
                break;
            case 6144:
                label.setBackgroundColor(0xff75a7f1);
                break;
            case 12288:
                label.setBackgroundColor(0xff4585f2);
                break;
            default:
                label.setBackgroundColor(0xff3c3a32);
                break;
        }
    }

    public boolean equals(Card c) {
        return getNum() == c.getNum();
    }

    private int GetCardWidth() {
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();
        int cardWidth;
        cardWidth = displayMetrics.widthPixels;
        return ( cardWidth - 10 ) / 4;
    }

    private TextView label;
}