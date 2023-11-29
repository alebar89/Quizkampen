package Quizkampen.Client;

import Quizkampen.Config.Question;
import Quizkampen.Server.QuizSettingsHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.List;




public class QuizkampenClient implements ActionListener {

    private JFrame frame = new JFrame("QuizkampenClient");
    //Panel which is used for the game rounds
    private JPanel questionDisplayPanel = new JPanel();
    private JPanel quizGamePanel = new JPanel();
    public JTextArea quizTextArea = new JTextArea("");
    private JTextField categoryField = new JTextField();
    private JButton answerButton1 = new JButton();
    private JButton answerButton2 = new JButton();
    private JButton answerButton3 = new JButton();
    private JButton answerButton4 = new JButton();
    private JPanel cardPanel;
    private CardLayout cardLayout;

    List<Question> questionList;

    int finalScore;
    int round = 1;

    //Panel which is shown in between rounds while waiting for your turn
    JPanel newRound = new JPanel();
    JPanel playersPanel = new JPanel();
    JPanel firstRoundPanel = new JPanel();
    JPanel secondRoundPanel = new JPanel();
    JPanel thirdRoundPanel = new JPanel();
    JPanel resultPanel = new JPanel();
    JLabel playerNameLabel1 = new JLabel();
    JLabel playerNameLabel2 = new JLabel();
    JLabel roundOneLabel = new JLabel("Runda 1");
    JLabel roundTwoLabel = new JLabel("Runda 2");
    JLabel roundThreeLabel = new JLabel("Runda 3");
    JLabel resultLabel = new JLabel("Resultat");
    JButton NextRoundButton = new JButton("");
    JTextField resultPlayer1Round1 = new JTextField();
    JTextField resultPlayer2Round1 = new JTextField();
    JTextField resultPlayer1Round2 = new JTextField();
    JTextField resultPlayer2Round2 = new JTextField();
    JTextField resultPlayer1Round3 = new JTextField();
    JTextField resultPlayer2Round3 = new JTextField();
    JTextField finalResultPlayer1 = new JTextField();
    JTextField finalResultPlayer2 = new JTextField();




    JPanel changeColorPanel = new JPanel();



    private int numberOfCorrectAnswers;
    private int currentQuestionIndexRound1 = 0;
    private int currentQuestionIndexRound2 = 5;
    private int currentQuestionIndexRound3 = 10;

    private int totalRounds;
    private int totalQuestions;

    private static int Port = 44444;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    String playerID = "";
    String opponentPlayerID = "";

    //Variables to keep track of each players score client side
    int player1Round1Score;
    int player2Round1Score;
    int player1Round2Score;
    int player2Round2Score;
    int player1Round3Score;
    int player2Round3Score;

    /**
     * Constructs the client by connecting to the server and creates the GUI for the main game. Adds all other panels from other methods
     * to a cardLayout.
     */
    public QuizkampenClient(String serverAddress) {
        try {
            socket = new Socket(serverAddress, Port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Layout GUI
        frame.setLayout(new BorderLayout());
        //frame.getContentPane().setBackground(bgColor);

        cardPanel.add(quizGamePanel, "game");
        cardPanel.add(newRound, "newRound");
        cardPanel.add(changeColorPanel, "colorSwitch");
        cardPanel.setOpaque(false);

        quizGamePanel.setOpaque(false);
        quizGamePanel.setLayout(null);

        questionDisplayPanel.setBounds(25, 10, 425, 300);
        questionDisplayPanel.setLayout(null);
        questionDisplayPanel.add(categoryField);
        questionDisplayPanel.add(quizTextArea);

        quizTextArea.setBounds(50, 120, 325, 150);
        quizTextArea.setForeground(new Color(29, 39, 80));
        quizTextArea.setEditable(false);
        quizTextArea.setLineWrap(true);
        quizTextArea.setWrapStyleWord(true);
        quizTextArea.setFont(new Font("Dialog", Font.BOLD, 15));

        categoryField.setBounds(100, 0, 225, 30);
        categoryField.setEditable(false);
        categoryField.setFont(new Font("Dialog", Font.BOLD, 20));

        answerButton1.setBounds(25, 325, 200, 100);
        answerButton1.setFont(new Font("Dialog", Font.BOLD, 15));
        answerButton1.setFocusable(false);
        answerButton1.addActionListener(this);

        answerButton2.setBounds(250, 325, 200, 100);
        answerButton2.setFont(new Font("Dialog", Font.BOLD, 15));
        answerButton2.setFocusable(false);
        answerButton2.addActionListener(this);

        answerButton3.setBounds(25, 450, 200, 100);
        answerButton3.setFont(new Font("Dialog", Font.BOLD, 15));
        answerButton3.setFocusable(false);
        answerButton3.addActionListener(this);

        answerButton4.setBounds(250, 450, 200, 100);
        answerButton4.setFont(new Font("Dialog", Font.BOLD, 15));
        answerButton4.setFocusable(false);
        answerButton4.addActionListener(this);


        quizGamePanel.add(questionDisplayPanel);
        quizGamePanel.add(answerButton1);
        quizGamePanel.add(answerButton2);
        quizGamePanel.add(answerButton3);
        quizGamePanel.add(answerButton4);

        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
    }

    /**
     * The main mehtod where the game is played. Sets userID, amountOfRounds/Questions based on input from server.
     * Each client keeps track of what round is being played as well as the opponents scored with the help of the resulstList
     * in serverSideGame. The while loop breaks when the endGame method is called.
     * @throws Exception
     */
    public void play() throws Exception {
        cardLayout.show(cardPanel, "newRound");

        QuizSettingsHandler quizSettings = new QuizSettingsHandler();
        totalRounds = Integer.parseInt(quizSettings.getNumberOfRounds());
        totalQuestions = Integer.parseInt(quizSettings.getNumberOfQuestions());

        if (totalRounds == 2)
            thirdRoundPanel.setVisible(false);

        try {
            NextRoundButton.setEnabled(false);
            NextRoundButton.setText("Starta nästa runda");
            Object response;

            response = in.readObject();
            System.out.println(response);

            if (((String) response).startsWith("WELCOME")) {
                handleRoundTransition();
                playerID = ((String) response).substring(8);
                if (playerID.equals("Spelare 1")) {
                    opponentPlayerID = "Spelare 2";
                } else {
                    opponentPlayerID = "Spelare 1";
                }
                frame.setTitle("QuizkampenClient - Spelare " + playerID);
            }

            while (true) {
                response = in.readObject();
                if (response instanceof List<?>) {
                    initializeQuestions((List<Question>) response);
                } else if (response instanceof String) {

                    if (response == null)
                        break;

                    if (((String) response).startsWith("YOUR_TURN")) {
                        handleRoundTransition();
                        if (playerID.equals("Spelare 2")) {
                            NextRoundButton.setEnabled(false);
                            out.writeObject("ROUND_OVER 0");
                        } else
                            NextRoundButton.setEnabled(true);

                    } else if (((String) response).startsWith("RESULT")) {
                        out.writeObject("ENDROUND");
                        response = ((String) response).substring(6);
                        response = ((String) response).replace("[", "");
                        response = ((String) response).replace("]", "");

                        String[] resultList = ((String) response).split(",");
                        if (resultList.length > 6) {
                            NextRoundButton.setText("Slutspelat");
                            NextRoundButton.setEnabled(false);
                        }
                        if (resultList.length == 1) {
                            if (playerID.equals("Spelare 1")) {
                                NextRoundButton.setEnabled(true);
                            } else
                                NextRoundButton.setEnabled(false);
                        }
                        if (resultList.length == 2) {
                            if (playerID.equals("Spelare 2")) {
                                NextRoundButton.setEnabled(true);
                            } else NextRoundButton.setEnabled(false);
                        }
                        if (resultList.length == 3) {
                            player1Round1Score = Integer.parseInt(resultList[1].trim());
                            player2Round1Score = Integer.parseInt(resultList[2].trim());

                            if (numberOfCorrectAnswers == player1Round1Score) {
                                resultPlayer2Round1.setText(String.valueOf(player2Round1Score));
                            } else
                                resultPlayer2Round1.setText(String.valueOf(player1Round1Score));
                            if (playerID.equals("Spelare 1")) {
                                NextRoundButton.setEnabled(false);
                            }
                            if (playerID.equals("Spelare 2")) {
                                NextRoundButton.setEnabled(true);
                            }
                        }
                        if (resultList.length == 4) {
                            if (totalRounds == 2 && playerID.equals("Spelare 2")){
                                NextRoundButton.setText("Slutspelat");
                            }
                            if (playerID.equals("Spelare 1")) {
                                NextRoundButton.setEnabled(true);
                            }
                            if (playerID.equals("Spelare 2")) {
                                NextRoundButton.setEnabled(false);
                            }
                        }
                        if (resultList.length == 5) {
                            player1Round2Score = Integer.parseInt(resultList[4].trim());
                            player2Round2Score = Integer.parseInt(resultList[3].trim());
                            if (totalRounds == 2) {
                                NextRoundButton.setText("Slutspelat!");
                                NextRoundButton.setEnabled(false);
                                if (numberOfCorrectAnswers == player1Round2Score) {
                                    resultPlayer2Round2.setText(String.valueOf(player2Round2Score));
                                } else
                                    resultPlayer2Round2.setText(String.valueOf(player1Round2Score));
                                int endScore1 = player1Round1Score + player1Round2Score;
                                int endScore2 = player2Round1Score + player2Round2Score;
                                processGameResult(endScore1, endScore2);
                                break;
                            }
                            if (numberOfCorrectAnswers == player1Round2Score) {
                                resultPlayer2Round2.setText(String.valueOf(player2Round2Score));
                            } else
                                resultPlayer2Round2.setText(String.valueOf(player1Round2Score));
                            if (playerID.equals("Spelare 1")) {
                                NextRoundButton.setEnabled(true);
                            }
                            if (playerID.equals("Spelare 2")) {
                                NextRoundButton.setEnabled(false);
                            }
                        }
                        if (resultList.length == 6) {
                            if (playerID.equals("Spelare 2")) {
                                NextRoundButton.setEnabled(true);
                            } else NextRoundButton.setEnabled(false);
                        }
                        if (resultList.length == 7) {
                            NextRoundButton.setEnabled(false);
                            player1Round3Score = Integer.parseInt(resultList[5].trim());
                            player2Round3Score = Integer.parseInt(resultList[6].trim());
                            if (numberOfCorrectAnswers == player1Round2Score) {
                                resultPlayer2Round3.setText(String.valueOf(player2Round3Score));
                            } else
                                resultPlayer2Round3.setText(String.valueOf(player1Round3Score));
                            int endScore1 = player1Round1Score + player1Round2Score + player1Round3Score;
                            int endScore2 = player2Round1Score + player2Round2Score + player2Round3Score;
                            processGameResult(endScore1, endScore2);
                            break;
                        }
                    }
                }
            }
        } finally {
            socket.close();
        }
    }

    /**
     * endGame-method called when the match is finished. Checks whether the score is equal to the one that's stored locally.
     * Sets the score for the opponent on the GUI and prints out whethe you've won, lost or tied.
     * @param player1FinalScore score of Spelare 1
     * @param player1FinalScore score of Spelare 2
     */

    private void processGameResult(int player1FinalScore, int player2FinalScore) {
        if (player1FinalScore > player2FinalScore) {
            if (player1FinalScore == finalScore) {
                finalResultPlayer1.setText(String.valueOf(player1FinalScore));
                finalResultPlayer2.setText(String.valueOf(player2FinalScore));
                frame.setTitle("Vinst");
                NextRoundButton.setText("Du vann!");
            } else {
                finalResultPlayer1.setText(String.valueOf(player2FinalScore));
                finalResultPlayer2.setText(String.valueOf(player1FinalScore));
                frame.setTitle("Förlust");
                NextRoundButton.setText("Du förlora!");
            }
        } else if (player1FinalScore < player2FinalScore) {
            if (player1FinalScore == finalScore) {
                finalResultPlayer1.setText(String.valueOf(player1FinalScore));
                finalResultPlayer2.setText(String.valueOf(player2FinalScore));
                frame.setTitle("Förlust");
                NextRoundButton.setText("Du förlora!");
            } else {
                finalResultPlayer1.setText(String.valueOf(player2FinalScore));
                finalResultPlayer2.setText(String.valueOf(player1FinalScore));
                frame.setTitle("Vinst");
                NextRoundButton.setText("Du vann!");
            }
        } else {
            finalResultPlayer1.setText(String.valueOf(player1FinalScore));
            finalResultPlayer2.setText(String.valueOf(player2FinalScore));
            frame.setTitle("Oavgjort");
            NextRoundButton.setText("Det blev oavgjort!");
        }
    }


    /**
     * The screen which each player sees while they're inbetween rounds waiting for the other player to finish as well
     * as the starting screen and postgame screen. Starting newRound starts a new round when it's clicked.
     */
    private void handleRoundTransition() {
        cardLayout.show(cardPanel, "newRound");

        newRound.setLayout(null);
        newRound.setSize(500, 750);
        newRound.setOpaque(false);

        playersPanel.setBounds(0, 0, 500, 220);
        playersPanel.setOpaque(false);
        playersPanel.setLayout(null);


        playerID.equals("Spelare 1");
        playerNameLabel1.setBounds(35, 170, 150, 30);
        playerNameLabel1.setHorizontalAlignment(JLabel.CENTER);
        playerNameLabel1.setText(playerID);
        playerNameLabel1.setFont(new Font("Dialog", Font.BOLD, 20));




        playerID.equals("Spelare 1");
        playerNameLabel2.setBounds(300, 170, 150, 30);
        playerNameLabel2.setText(opponentPlayerID);
        playerNameLabel2.setHorizontalAlignment(JLabel.CENTER);
        playerNameLabel2.setFont(new Font("Dialog", Font.BOLD, 20));


        playersPanel.add(playerNameLabel1);
        playersPanel.add(playerNameLabel2);

        firstRoundPanel.setBounds(0, 220, 500, 100);
        configureRoundInterface(firstRoundPanel, roundOneLabel, resultPlayer1Round1, resultPlayer2Round1);
        firstRoundPanel.setOpaque(true);
        firstRoundPanel.add(roundOneLabel);
        roundOneLabel.setForeground(Color.BLACK);
        firstRoundPanel.add(resultPlayer1Round1);
        firstRoundPanel.add(resultPlayer2Round1);

        secondRoundPanel.setBounds(0, 300, 500, 100);
        configureRoundInterface(secondRoundPanel, roundTwoLabel, resultPlayer1Round2, resultPlayer2Round2);
        secondRoundPanel.add(roundTwoLabel);
        roundTwoLabel.setForeground(Color.BLACK);
        secondRoundPanel.add(resultPlayer1Round2);
        secondRoundPanel.add(resultPlayer2Round2);
        secondRoundPanel.setOpaque(false);

        thirdRoundPanel.setBounds(0, 380, 500, 100);
        configureRoundInterface(thirdRoundPanel, roundThreeLabel, resultPlayer1Round3, resultPlayer2Round3);
        thirdRoundPanel.add(roundThreeLabel);
        roundThreeLabel.setForeground(Color.BLACK);
        thirdRoundPanel.add(resultPlayer1Round3);
        thirdRoundPanel.add(resultPlayer2Round3);
        thirdRoundPanel.setOpaque(false);

        resultPanel.setBounds(0, 520, 500, 180);
        resultPanel.setLayout(null);
        resultPanel.setOpaque(false);

        NextRoundButton.setBounds(165, 80, 150, 50);
        NextRoundButton.setBackground(Color.WHITE);
        NextRoundButton.setEnabled(false);
        NextRoundButton.addActionListener(e -> {
            currentQuestionIndexRound1 = 0;
            currentQuestionIndexRound2 = 5;
            currentQuestionIndexRound3 = 10;
            numberOfCorrectAnswers = 0;
            try {
                nextQuestion();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        resultLabel.setBounds(185, 20, 115, 50);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setForeground(Color.BLACK);
        finalResultPlayer1.setBounds(35, 20, 150, 50);
        finalResultPlayer1.setEditable(false);
        finalResultPlayer1.setHorizontalAlignment(JTextField.CENTER);
        finalResultPlayer2.setBounds(300, 20, 150, 50);
        finalResultPlayer2.setEditable(false);
        finalResultPlayer2.setHorizontalAlignment(JTextField.CENTER);
        resultPanel.add(resultLabel);
        resultPanel.add(finalResultPlayer1);
        resultPanel.add(finalResultPlayer2);
        resultPanel.add(NextRoundButton);

        newRound.add(playersPanel);
        newRound.add(firstRoundPanel);
        newRound.add(secondRoundPanel);
        newRound.add(thirdRoundPanel);
        newRound.add(resultPanel);
    }

    private void configureRoundInterface(JPanel round1, JLabel r1, JTextField p1r1, JTextField p2r1) {
        round1.setLayout(null);
        r1.setBounds(200, 35, 100, 50);
        r1.setFont(new Font("Dialog", Font.BOLD, 20));
        r1.setForeground(Color.WHITE);
        p1r1.setBounds(35, 35, 150, 50);
        p1r1.setEditable(false);
        p1r1.setHorizontalAlignment(JTextField.CENTER);
        p2r1.setBounds(300, 35, 150, 50);
        p2r1.setEditable(false);
        p2r1.setHorizontalAlignment(JTextField.CENTER);
    }

    /**
     * Method to create questions
     * @param questionList
     */
    private void initializeQuestions(List<Question> questionList) {
        this.questionList = questionList;
    }

    /**
     * Method that shows the next question for each round. Shows a new question as long as the current questionIndex
     * isn't equal to the amountOfQuestions specified in the quizsettings.properties-file.
     * It also updates the GUI for the player client-side with the score from the current round.
     */
    public void nextQuestion() throws IOException {

        cardLayout.show(cardPanel, "game");

        if (currentQuestionIndexRound1 == totalQuestions) {
            out.writeObject("ROUND_OVER " + numberOfCorrectAnswers);
            NextRoundButton.setEnabled(false);
            if (round == 1) {
                finalScore = numberOfCorrectAnswers;
                resultPlayer1Round1.setText(String.valueOf(numberOfCorrectAnswers));
                round++;
                handleRoundTransition();
            } else if (round == 2) {
                finalScore += numberOfCorrectAnswers;
                resultPlayer1Round2.setText(String.valueOf(numberOfCorrectAnswers));
                round++;
                handleRoundTransition();
            } else if (round == 3) {
                NextRoundButton.setText("Slutspelat");
                NextRoundButton.setEnabled(false);
                finalScore += numberOfCorrectAnswers;
                resultPlayer1Round3.setText(String.valueOf(numberOfCorrectAnswers));
                round++;
                handleRoundTransition();
            } else if (round > 3) {
                finalResultPlayer1.setText(String.valueOf(finalScore));
            }
        }

        if (round == 1) {
            if (currentQuestionIndexRound1 < 5) {
                roundGUISetting(currentQuestionIndexRound1);
            }
        } else if (round == 2) {
            if (currentQuestionIndexRound2 < 10) {
                roundGUISetting(currentQuestionIndexRound2);
            }

        } else if (round == 3) {
            if (currentQuestionIndexRound3 < 15) {
                roundGUISetting(currentQuestionIndexRound3);
            }
        }
    }

    /**
     * Sets one to twenty category, question and answer based on current index
     * And adds background and forground color to four answer option buttons
     */

    private void roundGUISetting(int index) {
        categoryField.setText(questionList.get(index).getCategory());
        quizTextArea.setText(questionList.get(index).getQuestion());

        quizTextArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        categoryField.setHorizontalAlignment(JTextField.CENTER);

        answerButton1.setBackground(Color.DARK_GRAY);
        answerButton1.setForeground(Color.WHITE);
        answerButton2.setBackground(Color.DARK_GRAY);
        answerButton2.setForeground(Color.WHITE);
        answerButton3.setBackground(Color.DARK_GRAY);
        answerButton3.setForeground(Color.WHITE);
        answerButton4.setBackground(Color.DARK_GRAY);
        answerButton4.setForeground(Color.WHITE);

        answerButton1.setText(questionList.get(index).getAnswers().get(0));
        answerButton1.setEnabled(true);
        answerButton2.setText(questionList.get(index).getAnswers().get(1));
        answerButton2.setEnabled(true);
        answerButton3.setText(questionList.get(index).getAnswers().get(2));
        answerButton3.setEnabled(true);
        answerButton4.setText(questionList.get(index).getAnswers().get(3));
        answerButton4.setEnabled(true);
    }

    /**
     * Method that changes the values of red, green and blue depending on the postion of the JSliders in changeBackground.
     * Changes background of frame.
     */





    /**
     * Activates everytime the user clicks a button. Disables the buttons as well as
     * displays whether the answer was correct or not by changing the color of the button to green or red.
     * If the answer was correct it adds one point to correctGuesses.
     * The timer tells the program to wait(sleep) for a short while so that it doesnt instantly jump to the next question.
     * After a short delay it re-enables the buttons and starts nextQuestion-method.
     *
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        answerButton1.setEnabled(false);
        answerButton2.setEnabled(false);
        answerButton3.setEnabled(false);
        answerButton4.setEnabled(false);

        JButton src = (JButton) e.getSource();

        String svar = src.getText();

        if (svar.equals(questionList.get(currentQuestionIndexRound1).getCorrectAnswer()) || svar.equals(questionList.get(currentQuestionIndexRound2).getCorrectAnswer())
                || svar.equals(questionList.get(currentQuestionIndexRound3).getCorrectAnswer())) {
            src.setBackground(new Color(7, 255, 0));
            numberOfCorrectAnswers++;
        } else {
            src.setBackground(new Color(255, 0, 0));
        }
        //Waits for 300ms before loading next question
        Timer wait = new Timer(300, ae -> {

            //Enables buttons again for nextQ
            answerButton1.setEnabled(true);
            answerButton2.setEnabled(true);
            answerButton3.setEnabled(true);
            answerButton4.setEnabled(true);

            currentQuestionIndexRound1++;
            currentQuestionIndexRound2++;
            currentQuestionIndexRound3++;

            try {
                nextQuestion();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Makes it so that the timer doesn't try nextQ every 300ms, but rather just once
        wait.setRepeats(false);
        wait.start();
    }

    /**
     * Runs the client as an application.
     */
    public static void main(String[] args) throws Exception {

        String serverAddress = (args.length == 0) ? "localhost" : args[1];
        QuizkampenClient client = new QuizkampenClient(serverAddress);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(500, 750);
        client.frame.setVisible(true);
        client.frame.setResizable(false);
        client.play();
    }
}