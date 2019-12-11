package CapaDomini.ModelDomini;

public class ZigZag {
    private int x;
    private int y;
    private int initialx;
    private int initialy;
    boolean asc;
    private int [][] mat;

    public ZigZag(int[][] content, int startx, int starty) {
        mat = content;
        x = 0;
        y = 0;
        initialx = startx;
        initialy = starty;
        asc = true;
    }


    public boolean isEnd() {
        return x == 8 && y == 7;
    }

    private void nextPosition() {
        boolean lastFirstDiagonal = x == 7 && y == 0;
        if(x + y <= 7 && !lastFirstDiagonal) {
            if (asc) {
                x -= 1;
                y += 1;
            } else {
                x += 1;
                y -= 1;
            }
            if (x < 0 || y < 0) {
                if (asc) {
                    asc = false;
                    x += 1;
                } else {
                    asc = true;
                    y += 1;
                }
            }
        }
        else {
            if (asc) {
                x -= 1;
                y += 1;
            } else {
                x += 1;
                y -= 1;
            }
            if (x > 7 || y > 7) {
                if (asc) {
                    asc = false;
                    x += 2;
                    y -= 1;
                } else {
                    asc = true;
                    y += 2;
                    x -= 1;
                }
            }
        }
    }

    public int getNextNumber() {
        int num = mat[x+initialx][y+initialy];
        nextPosition();
        return num;
    }
    public void writeNum(int num) {
        mat[x+initialx][y+initialy] = num;
        nextPosition();
    }

    public int[][] getMatrix() {
        return mat.clone();
    }

    public void nextSquare() {
        x = 0;
        y = 0;
        asc = true;
        initialy += 8;
        if (initialy >= mat[0].length) {
            initialy = 0;
            initialx += 8;
        }
    }
}
