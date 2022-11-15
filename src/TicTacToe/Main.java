import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static final char DOT_EMPTY = '#';
    public static final char DOT_O = 'O';
    public static final char DOT_X = 'X';
    char[][] table;
    public static Scanner inp = new Scanner(System.in);
    public static Random rand = new Random();

    int countCellX = 0; int countCellO = 0; int countFreeCell = 0; 
    int countLine = 0; int countColumn = 0; int running = 0; 

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    //  игровое поле
    TicTacToe(){
        table = new char[5][5];

        this.initTable();
    }

    // игра 
    void game(){
        while (true) {
            this.humanTurn();

            if (checkWin(DOT_X)) {
                System.out.println("Победа");
                break;
            }

            if (this.isFullTable()){
                System.out.println("Ничья с ботом");
                break;
            }

            this.aiTurn();
            this.printTable();

            if (checkWin(DOT_O)){
                System.out.println("Вы проиграли");
                break;
            }

            if (this.isFullTable()){
                System.out.println("Ничья с ботом");
                break;
            }

        }

        System.out.println("Игра закончена");
        this.printTable();
    }

    //табличка символами "#"
    void initTable(){
        for (int line = 0; line < 5; line++){
            for (int column = 0; column < 5; column++){
                this.table[line][column] = DOT_EMPTY;
            }
        }
    }

    //вывод игрового поля
    void printTable(){
        for (int line = 0; line < 5; line++){
            for (int column = 0; column < 5; column++){
                System.out.print(this.table[line][column] + " ");
            }
            System.out.print("\n");
        }
    }

    //проверка  таблички, для того, чтобы узнать когда ничья
    boolean isFullTable(){
        for (int line = 0; line < 5; line++){
            for (int column = 0; column < 5; column++){
                if (this.table[line][column] == DOT_EMPTY) return false;
            }
        }

        return true;
    }

    //метод, содержащий цикл для чтения с клавиатуры
    int getNum(){
        int trying;

        while (true){
            if (inp.hasNext()){
                trying = inp.nextInt();
                break;
            }
        }
        return trying;
    }
    //ход человека
    void humanTurn(){
        int x, y;
        do{
            System.out.println("Введите число из диапазона между отчанием и надеждой (1..5)");
            x = getNum() - 1;
            y = getNum() - 1;
        }while(!isCellValidHuman(x, y));

        table[y][x] = DOT_X;
    }

    //ход компьютера
    void aiTurn(){
        if (this.check1Dig()) return;

        if (this.check2Diagonal()) return;

        if (this.check3Diagonal()) return;

        if (this.check4Diagonal()) return;

        if (this.check5Diagonal()) return;

        if (this.check6Diagonal()) return;

        if (this.checkLines()) return;

        if (this.checkColumns()) return;

        this.randomWalk();
    }

    //смотрим дивпозон человека
    boolean isCellValidHuman(int x, int y){
        if (x < 0 || x > 4 || y < 0 || y > 4){
            System.out.println("Вы вышли из диапазона (1..5)");
            return false;
        }

        if (this.table[y][x] != DOT_EMPTY){
                System.out.println("Ячейка занята");
                return false;
        }

        return true;
    }

    //спотрим диапазон компьютора
    boolean isCellValidAI(int x, int y){
        if (x < 0 || x > 4 || y < 0 || y > 4) return false;

        return this.table[y][x] == DOT_EMPTY;
    }

    //считаем наши X, O и пустые клетки
    void count(int line, int column){
        if (this.table[line][column] == DOT_X) countCellX++;

        if (this.table[line][column] == DOT_O) countCellO++;

        if (this.table[line][column] == DOT_EMPTY) countFreeCell++;
    }

    //инициализируем  счетчики
    void initCount(){
        this.countCellX = 0;
        this.countCellO = 0;
        this.countFreeCell = 0;
    }

    //бот почти проиграл
    boolean isLosingForAI(){
        return (this.countFreeCell == 2 && this.countCellX == 3 || this.countFreeCell == 1 && this.countCellX == 3 ||
                this.countFreeCell == 3 && this.countCellX == 2);
    }

    //бот почти выиграл
    boolean isWining(){
        return (this.countFreeCell == 2 && this.countCellO == 3 || this.countFreeCell == 1 && this.countCellO == 3);
    }

    //проверка на победу
    boolean checkWin(char dot){
        for (int element_line = 0; element_line < 5; element_line++){
            for (int element_column = 0; element_column < 4; element_column++){
                if (table[element_line][element_column] == dot && table[element_line][element_column + 1] == dot)
                    countLine++;

                if (countLine >= 3){
                    return true;
                }
            }
            countLine = 0;
        }

        for (int element_column = 0; element_column < 5; element_column++){
            for (int element_line = 0; element_line < 4; element_line++){
                if (table[element_line][element_column] == dot && table[element_line + 1][element_column] == dot)
                    countColumn++;

                if (countColumn >= 3){
                    return true;
                }
            }
            countColumn = 0;
            running += 1;
        }

        return (table[0][1] == dot && table[1][2] == dot && table[2][3] == dot && table[3][4] == dot) ||
                (table[0][3] == dot && table[1][2] == dot && table[2][1] == dot && table[3][0] == dot) ||
                (table[1][0] == dot && table[2][1] == dot && table[3][2] == dot && table[4][3] == dot) ||
                (table[1][4] == dot && table[2][3] == dot && table[3][2] == dot && table[4][1] == dot) ||
                (table[0][0] == dot && table[1][1] == dot && table[2][2] == dot && table[3][3] == dot &&
                        table[4][4] == dot)||
                (table[0][0] == dot && table[1][1] == dot && table[2][2] == dot && table[3][3] == dot) ||
                (table[1][1] == dot && table[2][2] == dot && table[3][3] == dot && table[4][4] == dot) ||
                (table[0][4] == dot && table[1][3] == dot && table[2][2] == dot && table[3][1] == dot &&
                        table[4][0] == dot)||
                (table[0][4] == dot && table[1][3] == dot && table[2][2] == dot && table[3][1] == dot)||
                (table[1][3] == dot && table[2][2] == dot && table[3][1] == dot && table[4][0] == dot);
    }

    //рандомим ходьбу боту
    void randomWalk(){
        int x, y;
        do{
            x = rand.nextInt(5);
            y = rand.nextInt(5);
        }while (!isCellValidAI(x,y));

        this.table[y][x] = DOT_O;
    }

    //бот ставит O вместо "#"
    boolean putO(int line, int column){
        if (this.table[line][column] == DOT_EMPTY){
            this.table[line][column] = DOT_O;
            return true;
        }

        return false;
    }

    
    boolean check1Dig(){
        this.initCount();
        for (int line = 0; line < 5; line++){
            this.count(line, line);
        }

        if (this.isWining() || this.isLosingForAI()){
            for (int line = 0; line < 5; line++){
                if (this.putO(line, line)) return true;
            }
        }

        return false;
    }

   
    boolean check2Diagonal() {
        this.initCount();
        int column = 4;
        for (int line = 0; line < 5; line++) {
            this.count(line, column);
            column--;
        }

        if (this.isWining() || this.isLosingForAI()) {
            column = 4;
            for (int line = 0; line < 5; line++) {
                if (this.putO(line, column)) return true;
                column--;
            }
        }

        return false;
    }

    
    boolean check3Diagonal() {
        this.initCount();
        int column = 1;
        for (int line = 0; line < 4; line++) {
            this.count(line, column);
            column++;
        }

        if (this.isWining() || this.isLosingForAI()) {
            column = 1;
            for (int line = 0; line < 4; line++) {
                if (this.putO(line, column)) return true;
                column++;
            }
        }

        return false;
    }

    boolean check4Diagonal() {
        this.initCount();
        int column = 0;
        for (int line = 1; line < 5; line++) {
            this.count(line, column);
            column++;
        }

        if (this.isWining() || this.isLosingForAI()) {
            column = 0;
            for (int line = 1; line < 5; line++) {
                if (this.putO(line, column)) return true;
                column++;
            }
        }

        return false;
    }

    boolean check5Diagonal() {
        this.initCount();
        int column = 3;
        for (int line = 0; line < 4; line++) {
            this.count(line, column);
            column--;
        }

        if (this.isWining() || this.isLosingForAI()) {
            column = 3;
            for (int line = 0; line < 4; line++) {
                if (this.putO(line, column)) return true;
                column--;
            }
        }

        return false;
    }

    boolean check6Diagonal() {
        this.initCount();
        int column = 4;
        for (int line = 1; line < 5; line++) {
            this.count(line, column);
            column--;
        }

        if (this.isWining() || this.isLosingForAI()) {
            column = 4;
            for (int line = 1; line < 5; line++) {
                if (this.putO(line, column)) return true;
                column--;
            }
        }

        return false;
    }

    
    boolean checkLines() {
        for (int line = 0; line < 5; line++){
            this.initCount();
            for (int column = 0; column < 5; column++) {
                this.count(line, column);
            }

            if (this.isWining() || this.isLosingForAI()) {
                for (int column = 0; column < 4; column++) {
                    if ((this.table[line][column] == this.table[line][column + 1] && this.table[line][0] == DOT_X) ||
                            (this.table[line][column] == this.table[line][column + 1] && this.table[line][1] == DOT_X)){
                        if (this.putO(line, column)) return true;
                    } else if (((this.table[line][column] != this.table[line][column + 1])  ||
                            ((this.table[line][2] == DOT_X && this.table[line][3] == DOT_X) ||
                                    (this.table[line][3] == DOT_X && this.table[line][4] == DOT_X)))){
                        if (this.table[line][column] == DOT_X){
                            if (this.putO(line, column + 1)) return true;
                        }else if (this.putO(line, column)) return true;
                    }
                }
            }
        }
        return false;
    }

   
    boolean checkColumns() {
        for (int column = 0; column < 5; column++) {
            this.initCount();
            for (int line = 0; line < 5; line++) {
                this.count(line, column);
            }

            if (this.isWining() || this.isLosingForAI()) {
                for (int line = 0; line < 4; line++) {
                    if ((this.table[line][column] == this.table[line + 1][column] && this.table[0][column] == DOT_X) ||
                            (this.table[line][column] == this.table[line + 1][column] && this.table[1][column] == DOT_X)){
                        if (this.putO(line, column)) return true;
                    }else if ((this.table[line][column] != this.table[line + 1][column])  ||
                            ((this.table[2][column] == DOT_X && this.table[3][column] == DOT_X) ||
                                    (this.table[3][column] == DOT_X && this.table[4][column] == DOT_X))) {
                        if (this.table[line][column] == DOT_X) {
                            if (this.putO(line + 1, column)) return true;
                        } else if (this.putO(line, column)) return true;
                    }
                }
            }
        }
        return false;
    }
}
