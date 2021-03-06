package fall2018.csc2017.game_center.slidingtiles;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.Scoreable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class TileBoardManager implements Serializable, Scoreable {

    /**
     * Constant for infinity undo moves allowed
     */
    static final int INFINITY_UNDO = -1;

    /**
     * Serial number for serialization use.
     */
    private static final long serialVersionUID = 3L;

    /**
     * Constant for default max score (per complexity)
     */
    private static final int MAX_SCORE = 50;

    /**
     * Constant for default min score
     */
    private static final int MIN_SCORE = 10;

    /**
     * ID of the blank tile.
     */
    private final int blankId;

    /**
     * Number of rows and columns on the board
     */
    private final int numRowCol;

    /**
     * The board being managed.
     */
    private TileBoard board;

    /**
     * Number of undo moves allowed
     */
    private int numUndo;

    /**
     * Double ended queue of the positions of undo moves stored
     */
    private Deque<TilePosition> undoQueue;

    /**
     * Number of moves currently made
     */
    private int numMoves;

    /**
     * Manage a new shuffled board.
     *
     * @param numRowCol number of rows and columns for board
     * @param numUndo   number of undo moves allowed
     */
    TileBoardManager(int numRowCol, int numUndo) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = numRowCol * numRowCol;
        for (int tileNum = 0; tileNum != numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(numTiles, R.drawable.tile_25));

        Collections.shuffle(tiles);
        while (!isSolvable(tiles, numRowCol)) {
            Collections.shuffle(tiles);
        }

        this.board = new TileBoard(tiles, numRowCol);
        undoQueue = new ArrayDeque<>();
        this.numUndo = numUndo;
        this.numRowCol = numRowCol;
        blankId = numTiles;
        numMoves = 0;
    }

    /**
     * Manage a new shuffled board with manually rows and columns
     *
     * @param numRowCol number of rows and columns for board
     */
    TileBoardManager(int numRowCol) {
        this(numRowCol, INFINITY_UNDO);
    }

    /**
     * Manage a preset board with manual rows and columns
     */
    public TileBoardManager(TileBoard board, int numRowCol, int numUndo){
        final int numTiles = numRowCol * numRowCol;

        this.board = board;
        undoQueue = new ArrayDeque<>();
        this.numUndo = numUndo;
        this.numRowCol = numRowCol;
        blankId = numTiles;
        numMoves = 0;
    }
    /**
     * Manage a new shuffled board with default rows and columns
     */
    public TileBoardManager() {
        this(TileBoard.DEFAULT_ROW_COL);
    }

    /**
     * Return the current board.
     */
    TileBoard getBoard() {
        return board;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        for (int i = 0; i < board.numTiles(); i++) {
            TilePosition tile = new TilePosition(i);

            if (board.getTile(tile.getRow(), tile.getCol()).getId() != i + 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / board.getNumRowCol();
        int col = position % board.getNumRowCol();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == board.getNumRowCol() - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == board.getNumRowCol() - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Check to see if a board is solvable.
     *
     * @param tiles     the list of the tiles.
     * @param numRowCol the size of the row of the board.
     * @return if the board is solvable or not.
     * @see <a href="https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html">
     *     Solvability of the Tiles Game</a>
     */
    boolean isSolvable(List<Tile> tiles, int numRowCol) {
        boolean solvable = false;

        if (numRowCol % 2 == 1) {
            if (getInversion(tiles) % 2 == 0)
                solvable = true;
        } else {
            if ((getBlankRow(tiles, numRowCol) % 2 == 0) && (getInversion(tiles) % 2 == 0))
                solvable = true;
            else if ((getBlankRow(tiles, numRowCol) % 2 == 1) && (getInversion(tiles) % 2 == 1))
                solvable = true;
        }

        return solvable;
    }

    /**
     * Returns the total number of inversions in a board
     *
     * @param tiles the list of the tiles.
     * @return the total number of inversions
     */
    private int getInversion(List<Tile> tiles) {
        int i = 0;
        int inversionI;
        int inversionT = 0;

        while (i != tiles.size()) {
            inversionI = tiles.get(i).getId() - 1;

            for (int a = 0; a < i; a++) {
                if (tiles.get(a).getId() < tiles.get(i).getId())
                    inversionI--;
            }
            inversionT += inversionI;
            i++;
        }
        return inversionT;
    }

    /**
     * Determines the row of the blank tile in the board.
     *
     * @param tiles     the list of tiles,
     * @param numRowCol the size of the row of the board.
     * @return the row of the blank tile
     */
    private int getBlankRow(List<Tile> tiles, int numRowCol) {
        int numTiles = numRowCol * numRowCol;
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).getId() == numTiles)
                return (i+1) / numRowCol;
        }
        return numTiles / numRowCol;
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {
        if (isValidTap(position)) {
            TilePosition tile = new TilePosition(position);
            addUndoPosition(tile);
            numMoves++;
            board.swapTiles(tile.getRow(), tile.getCol(), tile.getSwapRow(), tile.getSwapCol());
        }
    }

    /**
     * Deletes the oldest move if the undo queue is full and then adds the most recent move
     *
     * @param tile move to be added
     */
    private void addUndoPosition(TilePosition tile) {
        if (numUndo > 0 || numUndo == INFINITY_UNDO) {
            if (undoQueue.size() == numUndo) {
                undoQueue.removeLast();
            }
            undoQueue.addFirst(tile);
        }
    }

    /**
     * Undo the last move
     */
    void undo() {
        TilePosition tile = undoQueue.pop();
        board.swapTiles(tile.getRow(), tile.getCol(), tile.getSwapRow(), tile.getSwapCol());
    }

    /**
     * Return whether the undo queue contains elements (i.e. there are moves to undo)
     *
     * @return a move can be undo'd
     */
    boolean hasUndo() {
        return undoQueue.size() > 0;
    }

    @Override
    public int getScore() {
        if (puzzleSolved()) {
            return ((MAX_SCORE * numRowCol) - numMoves) > 0 ? ((MAX_SCORE * numRowCol) - numMoves)
                    : MIN_SCORE;
        }
        return 0;
    }

    /**
     * Stores the row and col of a tile & the neighboring blank tile that may or may not exist,
     * reduces code duplication.
     */
    private final class TilePosition implements Serializable {

        private final int row;
        private final int col;
        private final int swapRow;
        private final int swapCol;

        /**
         * Store a tile position
         *
         * @param position the tile position to store
         */
        TilePosition(int position) {
            row = position / board.getNumRowCol();
            col = position % board.getNumRowCol();
            swapRow = setSwapRow();
            swapCol = setSwapCol();
        }

        /**
         * Return the row of the tile to be swapped.
         * Precondition: tile tapped neighbors the blank tile
         *
         * @return the row of the blank tile
         */
        private int setSwapRow() {
            if (row > 0 && board.getTile(row - 1, col).getId() == blankId) {
                return row - 1;
            } else if (row < board.getNumRowCol() - 1 &&
                    board.getTile(row + 1, col).getId() == blankId) {
                return row + 1;
            }
            return row;
        }

        /**
         * Return the column of the tile to be swapped.
         * Precondition: tile tapped neighbors the blank tile
         *
         * @return the column of the blank tile
         */
        private int setSwapCol() {
            if (col < board.getNumRowCol() - 1 && board.getTile(row, col + 1).getId() == blankId) {
                return col + 1;
            } else if (col > 0 && board.getTile(row, col - 1).getId() == blankId) {
                return col - 1;
            }
            return col;
        }

        /**
         * Return row of tile position
         *
         * @return row of tile position
         */
        int getRow() {
            return row;
        }

        /**
         * Return column of tile position
         *
         * @return column of tile position
         */
        int getCol() {
            return col;
        }

        /**
         * Return row of tile's swap position
         *
         * @return row of tile's swap position
         */
        int getSwapRow() {
            return swapRow;
        }

        /**
         * Return column of tile's swap position
         *
         * @return column of tile's swap position
         */
        int getSwapCol() {
            return swapCol;
        }

    }

}
