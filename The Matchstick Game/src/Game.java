/*
    Aufgabe 1) Gameplay - NIM Spiel
*/

import codedraw.*;

import java.awt.*;

public class Game {


    public static final int canvasSize = 800;
    public static final int rowHeight = 150;
    public static final int firstRow = canvasSize - 4 * rowHeight;
    public static final int secondRow = canvasSize - 3 * rowHeight;
    public static final int thirdRow = canvasSize - 2 * rowHeight;
    public static final int fourthRow = canvasSize - rowHeight;
    private static boolean[] picked = {false, false, false, false};
    private static int[] matches = {1, 3, 5, 7};
    private static int selectedRow = -1;
    private static int currentPlayer = 1;
    private static int player1Matches = 0;
    private static int player2Matches = 0;
    // TODO: Implementieren Sie hier Ihre Methoden für das Spiel

    public static void drawGame(CodeDraw myDrawObj, int player) {
        myDrawObj.clear();
        myDrawObj.setColor(Palette.LIGHT_BLUE);
        myDrawObj.fillRectangle(0, 0, canvasSize, canvasSize);
        drawPlayerField(myDrawObj);
        drawPlayerField(myDrawObj);
        drawActionButtons(myDrawObj);
        drawMatches(myDrawObj);
        drawPickedMatchesNearPlayer(myDrawObj);
        myDrawObj.show();
    }

    private static void drawPlayerField(CodeDraw myDrawOj) {
        myDrawOj.setColor(Palette.LIGHT_GREEN);
        myDrawOj.fillRectangle(170, 50 - 30, 130, 50);
        myDrawOj.setColor(Palette.LIGHT_PINK);
        myDrawOj.fillRectangle(170, 140 - 30, 130, 50);
        myDrawOj.setColor(Color.BLACK);
        myDrawOj.drawText(235, 50, "Player" + " " + 1 + ":");
        myDrawOj.drawText(235, 140, "Player" + " " + 2 + ":");
        myDrawOj.drawLine(0, 200, 800, 200);
    }

    private static void drawMatches(CodeDraw myDrawObj) {
        int[] rowY = {firstRow, secondRow, thirdRow, fourthRow};
        int matchWidth = 10;
        int matchHeight = 70;
        int headHeight = 20;
        int matchSpacing = 50;

        for (int i = 0; i < matches.length; i++) {
            for (int j = 0; j < matches[i]; j++) {
                myDrawObj.setColor(Palette.BISQUE);
                if (i == 0) {
                    myDrawObj.fillRectangle(400, rowY[i] + 40, matchWidth, matchHeight);
                }
                if (i == 1) {
                    myDrawObj.fillRectangle(325 + j * 75, rowY[i] + 40, matchWidth, matchHeight);
                }
                if (i == 2) {
                    myDrawObj.fillRectangle(250 + j * 75, rowY[i] + 40, matchWidth, matchHeight);
                }
                if (i == 3) {
                    myDrawObj.fillRectangle(175 + j * 75, rowY[i] + 40, matchWidth, matchHeight);

                }
                myDrawObj.setColor(Color.RED);
                if (i == 0) {
                    myDrawObj.fillEllipse(404, rowY[i] + 40, 7, 15);

                }
                if (i == 1) {
                    myDrawObj.fillEllipse(329 + j * 75, rowY[i] + 40, 7, 15);
                }
                if (i == 2) {
                    myDrawObj.fillEllipse(254 + j * 75, rowY[i] + 40, 7, 15);
                }
                if (i == 3) {
                    myDrawObj.fillEllipse(179 + j * 75, rowY[i] + 40, 7, 15);

                }
            }
        }
    }


    private static void drawActionButtons(CodeDraw myDrawObj) {
        boolean somethingIsPicked=false;
        for (int i = 0; i < 4; i++) {
            somethingIsPicked |=picked[i];
        }
        if (!somethingIsPicked){
            drawDefaultActionButtons(myDrawObj);
            return;
        }
        for (int i = 0; i < 4; i++) {
            myDrawObj.setColor(Color.GRAY);
            myDrawObj.fillRectangle(canvasSize - 80, firstRow + i * rowHeight + 60, 60, 30);
            if (picked[i]) {
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawText(canvasSize - 50, firstRow + i * rowHeight + 75, picked[i] ? "Take" : "Pick");
            }
        }
        myDrawObj.setColor(Color.GRAY);
        myDrawObj.fillRectangle(50, 75, 100, 30);
        myDrawObj.setColor(Color.BLACK);
        myDrawObj.drawText(100, 90, "NEXT");
    }
    private static void drawDefaultActionButtons (CodeDraw myDrawObj){
        for (int i = 0; i < 4; i++) {
            myDrawObj.setColor(Color.GRAY);
            myDrawObj.fillRectangle(canvasSize - 80, firstRow + i * rowHeight + 60, 60, 30);
            myDrawObj.setColor(Color.BLACK);
            myDrawObj.drawText(canvasSize - 50, firstRow + i * rowHeight + 75, "Pick");
        }
        myDrawObj.setColor(Color.GRAY);
        myDrawObj.fillRectangle(50, 75, 100, 30);
        myDrawObj.setColor(Color.BLACK);
        myDrawObj.drawText(100, 90, "NEXT");
    }

    public static void playGame(CodeDraw myDrawObj, EventScanner myEventSC) {
        boolean finished = false;

        while (!finished) {
            if (myEventSC.hasMouseClickEvent()) {
                MouseClickEvent currentClick = myEventSC.nextMouseClickEvent();
                int mouseX = currentClick.getX();
                int mouseY = currentClick.getY();

                switch (mouseCoords(mouseX, mouseY)) {
                    case 0://case NEXT field was pressed
                        if (selectedRow != -1) {
                            currentPlayer = 3 - currentPlayer;
                            picked=new boolean[4];
                            drawGame(myDrawObj, currentPlayer);
                        }
                        break;
                    case 1://case PICK/TAKE field in ROW1 was pressed
                        // TODO: Implementieren Sie hier Ihren Code
                        pickOrTake(mouseCoords(mouseX, mouseY) - 1, myDrawObj);
                        break;
                    case 2://case PICK/TAKE field in ROW2 was pressed
                        // TODO: Implementieren Sie hier Ihren Code
                        pickOrTake(mouseCoords(mouseX, mouseY) - 1, myDrawObj);
                        break;
                    case 3://case PICK/TAKE field in ROW3 was pressed
                        // TODO: Implementieren Sie hier Ihren Code
                        pickOrTake(mouseCoords(mouseX, mouseY) - 1, myDrawObj);
                        break;
                    case 4://case PICK/TAKE field in ROW4 was pressed
                        pickOrTake(mouseCoords(mouseX, mouseY) - 1, myDrawObj);
                        if (TheGameIsFinished()) {
                            finished = true;
                            drawWinner(myDrawObj);
                        }
                        break;
                    default:
                        break;
                }
                myDrawObj.show();
            } else {
                myEventSC.nextEvent();
            }
            // TODO: Implementieren Sie hier Ihren Code
        }
    }

    private static boolean TheGameIsFinished() {
        for (int match : matches) {
            if (match > 0) {
                return false;
            }

        }
        return true;
    }

    private static void drawWinner(CodeDraw myDrawObj) {
        myDrawObj.setColor(Color.RED);
        String winnerText = "Player" + (currentPlayer == 1 ? 1 : 2) + "wins!";
        myDrawObj.drawText(canvasSize / 2, canvasSize / 2, winnerText);
        myDrawObj.show();
    }

    public static void pickOrTake(int row, CodeDraw myDrawObj) {
        boolean isCurrentPlayer1 = true;
        boolean isCurrentPlayer2 = false;
        if (picked[row] == true) {//falls eine Row schon ausgewählt wurde
            if (matches[row] > 0) {
                matches[row]--;//verringen anzahl an matches in entsprechender Row
                if (currentPlayer == 1) {
                    player1Matches++;
                } else {
                    player2Matches++;//zählen Anzahl von gesammelten Matches
                }

                drawGame(myDrawObj, currentPlayer);//zeichnet das Spiel neu und zeig aktuelle Zustand des Spiels
            }
        } else {//falls keine Row ausgewählt wurde
            for (int i = 0; i < picked.length; i++) {
                if(picked[i]) return;//iteriert durch alle Reihen. Wenn eine andere Reihe bereits ausgewählt wurde,darf man die andere Reihen nicht auswählen
            }
            picked[row] = true;
            selectedRow = row;
            drawGame(myDrawObj, currentPlayer);
        }
    }

    private static void drawPickedMatchesNearPlayer(CodeDraw myDrawObj) {
        int matchWidth = 10;
        int matchHeight = 70;
        int matchSpacing = 20;

        for (int j = 0; j < player1Matches; j++) {
            myDrawObj.setColor(Palette.BISQUE);
            myDrawObj.fillRectangle(400 + (j + 2) * matchSpacing, 20, matchWidth, matchHeight);
            myDrawObj.setColor(Color.RED);
            myDrawObj.fillEllipse(400 + (j + 2) * matchSpacing + 4, 20, 7, 15);
        }
        // Player 2 matches
        for (int j = 0; j < player2Matches; j++) {
            myDrawObj.setColor(Palette.BISQUE);
            myDrawObj.fillRectangle(400 + (j + 2) * matchSpacing, 110, matchWidth, matchHeight);
            myDrawObj.setColor(Color.RED);
            myDrawObj.fillEllipse(400 + (j + 2) * matchSpacing + 4, 110, 7, 15);
        }
    }

    public static int mouseCoords(int mouseX, int mouseY) {
        if (mouseX >= 50 && mouseX <= 150) {
            //Next Field
            if (mouseY >= 85 && mouseY <= 115) {
                System.out.println("NEXT");
                return 0;
            }
        }
        if (mouseX >= canvasSize - 80 && mouseX <= canvasSize - 20) {
            //Pick Match Input
            if (mouseY >= firstRow + 60 && mouseY <= firstRow + rowHeight - 60) {
                System.out.println("Row 1");
                return 1;
            }
            if (mouseY >= secondRow + 60 && mouseY <= secondRow + rowHeight - 60) {
                System.out.println("Row 2");
                return 2;
            }
            if (mouseY >= thirdRow + 60 && mouseY <= thirdRow + rowHeight - 60) {
                System.out.println("Row 3");
                return 3;
            }
            if (mouseY >= fourthRow + 60 && mouseY <= fourthRow + rowHeight - 60) {
                System.out.println("Row 4");
                return 4;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CodeDraw myDrawObj = new CodeDraw(canvasSize, canvasSize);
        myDrawObj.setColor(Palette.LIGHT_BLUE);
        myDrawObj.setTitle("Nim Spiel");

        EventScanner myEventSC = myDrawObj.getEventScanner();

        TextFormat format = myDrawObj.getTextFormat();
        format.setTextOrigin(TextOrigin.CENTER);

        drawGame(myDrawObj, 1);//drawing the whole game field
        playGame(myDrawObj, myEventSC);//starting the game
    }
}
