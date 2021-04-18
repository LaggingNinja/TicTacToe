package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red
    int activePlayer = 0;  // set active player to 0, means player 1
    boolean gameIsActive = true;  // yes, the game is active
    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    // tic tac toe game, which cell is marked

    // what happen when the user click the image on the board
    public void dropIn(View view) {

        // here
        ImageView counter = (ImageView) view;
        // we need to know how many pictures click on the board, 9 pictures

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        // we need to know which picture click by the user on the board
        // look at the tag value for each image view object
        // tag 0 = first image to tag 8 9th image
        // we have 9 images here for 3  rows x 3 columns cell

        // if the user click the image within the board and
        // the game status is still active
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;

            // if the user is active, player turn 1 or 2
            counter.setTranslationY(-1000f);  // run animation

            //subtitle invisible once player start clicking on any box
            TextView test = (TextView) findViewById(R.id.subtitle);
            test.setVisibility(View.INVISIBLE);

            //good luck text visible once player start clicking on any box
            TextView test2 = (TextView) findViewById(R.id.good_luck);
            test2.setVisibility(View.VISIBLE);

            if (activePlayer == 0) {  // if player 1 turn
                counter.setImageResource(R.drawable.yellow);
                // get this image from res folder
                activePlayer = 1;
                // set active player to player 2 after player 1 finish
            } else {  // if player 2 turn
                counter.setImageResource(R.drawable.red);
                // get this image from res folder
                activePlayer = 0; // set active player to player 2 finish
            }


            // here
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            // run animation again

            // paste here
            // use for loop here to find out the winners by checking the array elements of the winning position
            // check if the user has clicked on the winning position or not
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won!, because they have click in the winning cells or positions
                    // written in the array
                    gameIsActive = false;  // set game status to false, not active anymore, cannot play already
                    String winner = "Player 2";  // set winner to red, player 2

                    if (gameState[winningPosition[0]] == 0) {  // if player 1 win the game
                        winner = "Player 1";  // if the winner is player 1
                    }

                    // here
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    // get the label from res folder, xml file
                    winnerMessage.setText(winner + " has won!");  // display the winner
                    GridLayout layout = (GridLayout) findViewById(R.id.playAgainLayout);
                    // get the layout manager from res folder, xml file
                    layout.setVisibility(View.VISIBLE);  // set the layout to visible to display all the components

                    //make good luck invisible
                    TextView aa = (TextView) findViewById(R.id.good_luck);
                    aa.setVisibility(View.INVISIBLE);
                    //if anyone wins, show congrats text
                    TextView test3 = (TextView) findViewById(R.id.congrats);
                    test3.setVisibility(View.VISIBLE);



                } else {
                    boolean gameIsOver = true;
                    // game is over, no winner or losers

                    for (int counterState : gameState) {
                        // for loop to check no winner or losers
                        if (counterState == 2) gameIsOver = false;
                    } // end of for loop for draw condition, no winner or loser

                    if (gameIsOver) {
                        // if game is over already with no winner or losers
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        // get the textview label from res, xml file
                        winnerMessage.setText("It's a draw");
                        // display message
                        GridLayout layout = (GridLayout) findViewById(R.id.playAgainLayout);
                        // get the layout manager from res, xml file
                        layout.setVisibility(View.VISIBLE);  // set it to visible to display all components

                        //make good luck invisible
                        TextView aa = (TextView) findViewById(R.id.good_luck);
                        aa.setVisibility(View.INVISIBLE);
                        //if draw, show nice try text
                        TextView test3 = (TextView) findViewById(R.id.nice_try);
                        test3.setVisibility(View.VISIBLE);

                    } // end if for game over

                }  // else if game over

            } // end of for loop for checking winner
        }  // end if for game is active


    }


    // when the user click play again button
    public void playAgain(View view) {

        gameIsActive = true;
        // change the status of the game to active again
        GridLayout layout = (GridLayout) findViewById(R.id.playAgainLayout);
        // get the linear layout manager from the res and xml file
        layout.setVisibility(View.INVISIBLE);

        //good luck text invisible once player click play again
        TextView test2 = (TextView) findViewById(R.id.good_luck);
        test2.setVisibility(View.INVISIBLE);

        //congrats text invisible once player click play again
        TextView test3 = (TextView) findViewById(R.id.congrats);
        test3.setVisibility(View.INVISIBLE);

        //nice try text invisible once player click play again
        TextView test4 = (TextView) findViewById(R.id.nice_try);
        test4.setVisibility(View.INVISIBLE);

        //subtitle visible once user lick play again
        TextView test = (TextView) findViewById(R.id.subtitle);
        test.setVisibility(View.VISIBLE);

        // turn it to become invisible again to hide all the components
        activePlayer = 0;
        // set player 1 as active, player 1 start the game first

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;  // why ? 3 cols and 3 rows
        }  // set all array elements value is 2, game is active, all 9 cells

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        // get the gridlayout from res folder, xml file

        // use for loops to run 9 times, get 9 pictures from the res folder,
        // display in the cell when the user click, i = 0 to 8
        for (int i = 0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}