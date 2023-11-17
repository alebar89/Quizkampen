package Quizkampen;

public class quizkampenProtocol {

        private static final int WAITING = 0;

        private static final int ANSWER = 2;

        private static final int ALTERNATIVES = 1;

        private static final int Questions = 4;

        private int state = WAITING;

        private int currentQuestion = 0;
        private int currentAlternatives= 0;


        private String[] Question = {"Vilka vann herrarnas fotbolls-VM 2022?","VIlket lag vann SM-guld i ishockey för herrar 2007?"};
        private String[][] answers = {
                {"Spanien", "Argentina", "Tyskland", "Italien"},
                {"Sverige", "USA","Canada","Modo"}
                };

                public String processInput(String theInput){
                    String theOutput = null;

                    if (state == WAITING){
                        theOutput = Question[currentQuestion];
                          state = ALTERNATIVES;
                    } else if (state == ALTERNATIVES){
                        theOutput = "Spanien"+ "Argentina"+ "Tyskland"+ "Italien"; //Skriver inte ut
                        if(state == ANSWER) {
                        } else if(theInput.equalsIgnoreCase("Argentina")){
                            theOutput = "Du har rätt";
                        }else {
                            theOutput = "Du svara fel";
                        }

                    }
                    return theOutput;
                }

}
