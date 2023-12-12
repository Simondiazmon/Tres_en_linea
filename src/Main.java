import java.util.Scanner;
public class Main {
    static Scanner cin = new Scanner(System.in);
    public static void main(String[] args) {
        char [][] tablero = new char[3][3];
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j ++){
                tablero[i][j] = ' ';
            }
        }

        System.out.println("Triqui - SDM\nEl primer jugador es X y el segundo jugador O");

        System.out.printf("  %1$2c  | %2$2c  |  %3$2c  \n", tablero[0][0], tablero[0][1], tablero[0][2]);
        System.out.printf(" __________________\n");
        System.out.printf("  %1$2c  | %2$2c  |  %3$2c  \n", tablero[1][0], tablero[1][1], tablero[1][2]);
        System.out.printf(" __________________\n");
        System.out.printf("  %1$2c  | %2$2c  |   %3$2c \n", tablero[2][0], tablero[2][1], tablero[2][2]);

        boolean partida = true;

        while(partida){

            boolean validoX, validoY;
            int filaX = 0, filaY = 0;
            int columnaX = 0, columnaY = 0;
            System.out.println(" Jugador 1 ingrese las coordenadas de la X (fila, columna):");
            filaX = cin.nextInt();
            cin.nextLine();
            columnaX = cin.nextInt();

            validoX = revisarCasilla(filaX,columnaX, tablero);
            if(validoX){
                System.out. println("Chao");
                actualizarTablr(filaX, columnaX, tablero, 'x');
                partida = revisarWin(tablero);

                if(!partida){
                    break;
                }

            }else{
                System.out. println("coordenada invalida");
            }

            System.out.println(" Jugador 2 ingrese las coordenadas de la O (fila, columna):");
            filaY = cin.nextInt();
            cin.nextLine();
            columnaY = cin.nextInt();

            validoY = revisarCasilla(filaY, columnaY, tablero);
            if(validoY){
                System.out. println("Chao");
                actualizarTablr(filaY, columnaY, tablero, 'o');
                partida = revisarWin(tablero);

                if(!partida){
                    break;
                }

            }else{
                System.out. println("coordenada invalida");
            }


        }
    }

    public static boolean revisarCasilla(int fila, int columna, char tablero[][]) {
        if(tablero[fila][columna] == 'X' || tablero[fila][columna] =='O' ){
            return false;
        }
        return true;
    }

    public static void actualizarTablr(int fila, int columna, char tablero[][], char jugador){
        //revisar si el jugador es x o c con if editar matriz en base a esto

        if(jugador == 'x'){
            tablero[fila][columna] = 'X';
        }else{
            tablero[fila][columna] = 'O';
        }

        System.out.printf("  %1$2c  | %2$2c  |  %3$2c  \n", tablero[0][0], tablero[0][1], tablero[0][2]);
        System.out.printf(" __________________\n");
        System.out.printf("  %1$2c  | %2$2c  |  %3$2c  \n", tablero[1][0], tablero[1][1], tablero[1][2]);
        System.out.printf(" __________________\n");
        System.out.printf("  %1$2c  | %2$2c  |   %3$2c \n", tablero[2][0], tablero[2][1], tablero[2][2]);
    }
    public static boolean revisarWin(char tablero[][]){
        int [] contadorX = new int[3], contadorColumnasX = new int[3], contadorDiagonalesX = new int[2];
        int [] contadorO = new int[3], contadorColumnasO = new int[3], contadorDiagonalesO = new int[2];
        // revisar columnas y filas
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                if(tablero[i][j] == 'X'){
                    contadorX[i] += 1;
                }else if (tablero[i][j] == 'O'){
                    contadorO[i] += 1;
                }

                if(tablero[j][i] == 'X'){
                    contadorColumnasX[i] +=1;
                }else if (tablero[j][i] == 'O'){
                    contadorColumnasO[i] += 1;
                }
            }
        }
        //revisar diagonales
        for(int i = 0; i < tablero.length; i++){
            if(tablero[i][i] == 'X'){
                contadorDiagonalesX[0] += 1;
            }
            if(tablero[i][i] == 'O'){
                contadorDiagonalesO[0] += 1;
            }
        }
        for(int i = tablero.length - 1; i >= 0; i--){
            for(int j = 0; j < tablero.length; j++){
                if((i + j) == (tablero.length - 1)){
                    if(tablero[i][j] == 'X'){
                        contadorDiagonalesX[1] += 1;
                    }
                    if(tablero[i][j] == 'O'){
                        contadorDiagonalesO[1] += 1;
                    }
                }
            }
        }
        //revisar columnas
        for(int i = 0; i < tablero.length; i++){
            System.out.println("numero de x en fila " + i + "= "+ contadorX[i]);
            System.out.println("numero de x en columna " + i + "= "+ contadorColumnasX[i]);
            System.out.println("numero de o en fila " + i + "= "+ contadorO[i]);
            System.out.println("numero de o en columna " + i + "= "+ contadorColumnasO[i]);
            if(contadorO[i] == 3 || contadorColumnasO[i] == 3){
                System.out.println("Jugador 2 ha ganado");
                return false;
            }else if(contadorX[i] == 3 || contadorColumnasX[i] == 3) {
                System.out.println("Jugador 1 ha ganado");
                return false;
            }
        }

        for(int i = 0; i < contadorDiagonalesX.length; i++){
            System.out.println("numero de x en la diagonal " + i + "= "+ contadorDiagonalesX[i]);
            System.out.println("numero de o en la diagonal " + i + "= "+ contadorDiagonalesO[i]);
            if(contadorDiagonalesX[i] == 3){
                System.out.println("Jugador 1 ha ganado");
                return false;
            }else if(contadorDiagonalesO[i] == 3){
                System.out.println("Jugador 2 ha ganado");
                return false;
            }
        }
        return true;
    }
}