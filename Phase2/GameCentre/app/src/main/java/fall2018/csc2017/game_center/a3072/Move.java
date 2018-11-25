package fall2018.csc2017.game_center.a3072;

class Move {

    static boolean swipeLeft(Card[][] board){

        boolean move = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

                for (int x1 = x+1; x1 < 4; x1++) {
                    if (board[x1][y].getNum() > 0) {

                        if (board[x][y].getNum() <= 0) {
                            board[x][y].setNum(board[x1][y].getNum());
                            board[x1][y].setNum(0);

                            x--;
                            move = true;
                        } else if (board[x][y].equals(board[x1][y])) {
                            board[x][y].setNum(board[x][y].getNum()*2);
                            board[x1][y].setNum(0);

                            MainActivity.getMainActivity().addScore(board[x][y].getNum());
                            move = true;
                        }
                        break;

                    }
                }
            }
        }
        return move;
    }

    static boolean swipeRight(Card[][] board){

        boolean move = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {

                for (int x1 = x-1; x1 >= 0; x1--) {
                    if (board[x1][y].getNum() > 0) {

                        if (board[x][y].getNum() <= 0) {
                            board[x][y].setNum(board[x1][y].getNum());
                            board[x1][y].setNum(0);

                            x++;
                            move = true;
                        } else if (board[x][y].equals(board[x1][y])) {
                            board[x][y].setNum(board[x][y].getNum()*2);
                            board[x1][y].setNum(0);

                            MainActivity.getMainActivity().addScore(board[x][y].getNum());
                            move = true;
                        }
                        break;
                    }
                }
            }
        }
        return move;
    }

    static boolean swipeUp(Card[][] board){


        boolean move = false;

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                for (int y1 = y+1; y1 < 4; y1++) {
                    if (board[x][y1].getNum() > 0) {

                        if (board[x][y].getNum() <= 0) {
                            board[x][y].setNum(board[x][y1].getNum());
                            board[x][y1].setNum(0);

                            y--;
                            move = true;
                        } else if (board[x][y].equals(board[x][y1])) {
                            board[x][y].setNum(board[x][y].getNum()*2);
                            board[x][y1].setNum(0);

                            MainActivity.getMainActivity().addScore(board[x][y].getNum());
                            move = true;
                        }
                        break;
                    }
                }
            }
        }
        return move;
    }

    static boolean swipeDown(Card[][] board){

        boolean move = false;

        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {

                for (int y1 = y-1; y1 >= 0; y1--) {
                    if (board[x][y1].getNum() > 0) {

                        if (board[x][y].getNum() <= 0) {
                            board[x][y].setNum(board[x][y1].getNum());
                            board[x][y1].setNum(0);

                            y++;
                            move = true;
                        } else if (board[x][y].equals(board[x][y1])) {
                            board[x][y].setNum(board[x][y].getNum()*2);
                            board[x][y1].setNum(0);

                            MainActivity.getMainActivity().addScore(board[x][y].getNum());
                            move = true;
                        }
                        break;
                    }
                }
            }
        }
        return move;
    }
}
