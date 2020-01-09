package CapaDomini.ModelDomini;


/**
 *
 * @author Pau
 */
public class ZigZag {
    private int x;
    private int y;
    private int initialx;
    private int initialy;
    boolean asc;
    private int[][] mat;

    /**
     * Constructora con una matriz de contenido, un punto de comienzo para x y para y
     * @param content
     * @param startx
     * @param starty
     */
    public ZigZag(int[][] content, int startx, int starty) {
        mat = content;
        x = 0;
        y = 0;
        initialx = startx;
        initialy = starty;
        asc = true;
    }
    
    public ZigZag(int sizeX, int sizeY, int startx, int starty) {
        mat = new int[sizeX][sizeY];
        x = 0;
        y = 0;
        initialx = startx;
        initialy = starty;
        asc = true;
    }

    /**
     * Funcion para ver si has llegado al final del recuadro de la matriz 8x8
     * @return bool
     */
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

    /**
     * Funcion para encontrar el siguiente numero
     * @return num(int)
     */
    public int getNextNumber() {
        int num = mat[x+initialx][y+initialy];
        nextPosition();
        return num;
    }

    /**
     * Funcion para escribir un numero en la matriz
     * @param num
     */
    public void writeNum(int num) {
        mat[x+initialx][y+initialy] = num;
        nextPosition();
    }

    /**
     * Funcion para obtener la matriz
     * @return mat(int[][])
     */
    public int[][] getMatrix() {
        return mat.clone();
    }

    /**
     * Funcion que pasa de un subcuadrado de 8x8 de la matriz al siguiente
     */
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
