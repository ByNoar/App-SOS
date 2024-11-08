package produccion;

public class Board {

    private int squaresPerSide;

    private int turn;

    private char[][] grid;

    private int[] score;

    public int[] getScore() {
        return score;
    }

    public Board(){
        squaresPerSide = 3;
        grid = new char[squaresPerSide][squaresPerSide];
        for ( int row = 0; row < squaresPerSide; row++ ){
            for ( int column = 0; column < squaresPerSide; column++ ){
                grid[row][column] = '-';
            }
        }
        turn = 1;
        score = new int[2];
    }

    public Board(int squaresPerSide){
        this.squaresPerSide = squaresPerSide;
        grid = new char[squaresPerSide][squaresPerSide];
        for ( int row = 0; row < squaresPerSide; row++ ){
            for ( int column = 0; column < squaresPerSide; column++ ){
                grid[row][column] = '-';
            }
        }
        turn = 1;
        score = new int[2];
    }

    public int getSquaresPerSide() { return squaresPerSide; };

    public char getCell(int row, int column){
        if ( row >= 0 && row < squaresPerSide && column >= 0 && column < squaresPerSide ){
            return grid[row][column];
        }
        else return 'X';
    }

    public int getTurn(){
        return turn;
    }

    public void changeTurn(){
        turn = 3 - turn;
    }

    public void makePlay(int row, int column, char chosen){
        if ( row >= 0 && row < squaresPerSide && column >= 0 && column < squaresPerSide ){
            grid[row][column] = chosen;
        }
        else {
            throw new IllegalArgumentException("El argumento debe estar dentro de los limites del tablero");
        }
    }

    public void increaseScore(int player, int points){
        int index = player - 1;
        score[index] = score[index] + points;
    }

    public int[][] positionSOS(int row, int column, char chosen){
        int[][] around = new int[][]{ {-1,-1} , {0,-1} , {1,-1} , {-1,0} , {1,0} , {-1,1} , {0,1} , {1,1} } ;
        int [][] positions = new int[][]{};

        if ( chosen == 'S' ){
            for( int i = 0; i < 8; i++ ){
                if( getCell(row + around[i][0],column + around[i][1]) == 'O' && getCell(row + 2*around[i][0],column + 2*around[i][1]) == 'S' ){
                    positions[i][0] = row;
                    positions[i][1] = column;
                    positions[i][2] = row + 2*around[i][0];
                    positions[i][3] = column + 2*around[i][1];
                };
            }
        }

        else if ( chosen == 'O' ){
            for( int i = 0; i < 4; i++ ){
                if( getCell(row + around[i][0],column + around[i][1]) == 'S' && getCell(row - around[i][0],column - around[i][1]) == 'S' ){
                    positions[i][0] = row - around[i][0];
                    positions[i][1] = column - around[i][1];
                    positions[i][2] = row + around[i][0];
                    positions[i][3] = column + around[i][1];
                };
            }
        }
        return positions;
    }

    public void getWinner(){
        if ( score[0] > score[1] ) System.out.println("EL ganador " + 1 + " ha ganado con " + score[0] + " puntos. Felicidades!");
        else if ( score[0] < score[1] ) System.out.println("El ganador " + 2 + " ha ganado con " + score[1] + " puntos. Felicidades!");
        else System.out.println("Es un empate de " + score[1] + "puntos!");
    }

    public int getWinnerPlayer(){
        if( score[0] > score[1]){
            return 1;
        }
        else if( score[0] < score[1]){
            return 2;
        }
        else {
            return 3;
        }
    }

    public boolean isBoardFull(){
        for (int row = 0; row < squaresPerSide; row++){
            for (int column = 0; column < squaresPerSide; column++){
                if (getCell(row, column) == '-') return false;
            }
        }
        return true;
    }

    public int howManySOS(int row, int column, char chosen){
        int[][] around = new int[][]{ {-1,-1} , {0,-1} , {1,-1} , {-1,0} , {1,0} , {-1,1} , {0,1} , {1,1} } ;

        int points = 0;

        if ( chosen == 'S' ){
            for( int i = 0; i < 8; i++ ){
                if( getCell(row + around[i][0],column + around[i][1]) == 'O' && getCell(row + 2*around[i][0],column + 2*around[i][1]) == 'S' ) points++;
            }
        }

        else if ( chosen == 'O' ){
            for( int i = 0; i < 4; i++ ){
                if( getCell(row + around[i][0],column + around[i][1]) == 'S' && getCell(row - around[i][0],column - around[i][1]) == 'S' ) points++;
            }
        }
        return points;
    }

}
