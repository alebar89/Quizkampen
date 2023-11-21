package Quizkampen;

public class Questions {

    //TODO innehåller frågorna i text.
    // arrayer för frågorna, svaren och alternativen.
    // metod för att avgöra om alternativet är det rätta svaret.

    private String[] Question = {"Vilka vann herrarnas fotbolls-VM 2022?",
            "Vilket lag vann SM-guld i ishockey för herrar 2007?",
            "Hur många basketspelare får finnas på planen per lag?",
            "Vilken tennisspelare har vunnit flest Grand Slam titlar?"};

    private String[] Answers = {"Argentina",
            "Modo",
            "5",
            "Novak Djokovic"};
    private String[][] Alternatives = {
            {"Spanien", "Argentina", "Tyskland", "Italien"},
            {"Djurgården", "Malmö", "Luleå", "Modo"},
            {"3", "4", "5","6"},
            {"Novak Djokovic", "Rafael Nadal", "Roger Federer", "Pete Sampras"}};


    public String getQuestions(int index) {
        return Question[index];
    }

    public String getCorrectAnswer(int index) {
        return Answers[index];
    }

    public String[] getAlternatives(int index) {
        return Alternatives[index];
    }
    public int getQuestionAmount() {
        return Question.length;
    }



}
