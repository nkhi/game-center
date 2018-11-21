package fall2018.csc2017.game_center.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;

import fall2018.csc2017.game_center.R;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    Tile(int id, int background) {
        this.id = id;
        this.background = background;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId The tile's background id to lookup
     */
    Tile(int backgroundId) {
        int[] tileImg = {
                R.drawable.tile_1,
                R.drawable.tile_2,
                R.drawable.tile_3,
                R.drawable.tile_4,
                R.drawable.tile_5,
                R.drawable.tile_6,
                R.drawable.tile_7,
                R.drawable.tile_8,
                R.drawable.tile_9,
                R.drawable.tile_10,
                R.drawable.tile_11,
                R.drawable.tile_12,
                R.drawable.tile_13,
                R.drawable.tile_14,
                R.drawable.tile_15,
                R.drawable.tile_16,
                R.drawable.tile_17,
                R.drawable.tile_18,
                R.drawable.tile_19,
                R.drawable.tile_20,
                R.drawable.tile_21,
                R.drawable.tile_22,
                R.drawable.tile_23,
                R.drawable.tile_24,
                R.drawable.tile_25
        };
        id = backgroundId + 1;
        background = tileImg[backgroundId];
    }

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }

}
