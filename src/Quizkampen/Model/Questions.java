package Quizkampen.Model;

public class Questions {

    //TODO innehåller frågorna i text.
    // arrayer för frågorna, svaren och alternativen.
    // metod för att avgöra om alternativet är det rätta svaret.
    public static final String SPORT_CATEGORY = "Sport";
    public static final String MOVIES_CATEGORY = "Movies";
    public static final String GEOGRAPHY_CATEGORY = "Geography";
    public static final String ANIMALS_CATEGORY = "Animals";

    private static String[] QuestionSport = {
            "Vilka vann herrarnas fotbolls-VM 2022?",
            "Vilket lag vann SM-guld i ishockey för herrar 2007?",
            "Hur många basketspelare får finnas på planen per lag?",
            "Vilken tennisspelare har vunnit flest Grand Slam titlar?",
    };
    private static String[] AnswersSport = {
            "Argentina",
            "Modo",
            "5",
            "Novak Djokovic",
    };
    private static String[][] AlternativesSport = {
            {"Spanien", "Argentina", "Tyskland", "Italien"},
            {"Djurgården", "Malmö", "Luleå", "Modo"},
            {"3", "4", "5", "6"},
            {"Novak Djokovic", "Rafael Nadal", "Roger Federer", "Pete Sampras"},
    };
    public String getQuestions(int index) {
        return QuestionSport[index];
    }

    public String getCorrectAnswer(int index) {
        return AnswersSport[index];
    }

    public String[] getAlternatives(int index) {
        return AlternativesSport[index];
    }

    public int getQuestionAmount() {
        return QuestionSport.length;
    }

    public static String[] getQuestionSport() {
        return QuestionSport;
    }

    public static String[] getAnswersSport() {
        return AnswersSport;
    }

    public static String[][] getAlternativesSport() {
        return AlternativesSport;
    }


    private static String[] QuestionMovies = {
            "Vilken film vann Oscar för Bästa Film 2019?",
            "Vilken skådespelare porträtterade Tony Stark/Iron Man i Marvel Cinematic Universe?",
            "I vilken film spelar Leonardo DiCaprio rollen som Dom Cobb, en extractor som stjäl hemligheter genom att infiltrera folks drömmar?",
            "Vilken animerad film handlar om en ung mexikansk pojke som reser till det lilla samhället De dödas land under Dia de los Muertos?"
    };
    private static String[] AnswersMovies = {
            "Parasite",
            "Robert Downey Jr",
            "Inception",
            "Coco",
    };
    private static String[][] AlternativesMovies = {
            {"La La Land", "1917", "The Shape of Water", "Parasite"},
            {"Chris Hemsworth", "Chris Evans", "Robert Downey Jr", "Mark Ruffalo"},
            {"Inception", "Shutter Island", "The Revenant", "The Wolf of Wall Street"},
            {"Moana", "Coco", "Zootopia", "Toy Story 4"},
    };
    public String getQuestionsMovies(int index) {
        return QuestionMovies[index];
    }

    public String getCorrectAnswerMovies(int index) {
        return AnswersMovies[index];
    }

    public String[] getAlternativesMovies(int index) {
        return AlternativesMovies[index];
    }

    public int getQuestionAmountMovies() {
        return QuestionMovies.length;
    }

    public static String[] getQuestionMovies() {
        return QuestionMovies;
    }

    public static String[] getAnswersMovies() {
        return AnswersMovies;
    }

    public static String[][] getAlternativesMovies() {
        return AlternativesMovies;
    }

    private static String[] QuestionGeography = {
            "Vilket land är världens största till ytan?",
            "Vad är huvudstaden i Australien?",
            "Vilket av följande länder gränsar inte till Medelhavet?",
            "Vilket land är känt som 'Landet av de uppgående solens land'?",
    };
    private static String[] AnswersGeography = {
            "Ryssland",
            "Canberra",
            "Ungern",
            "Japan",
    };
    private static String[][] AlternativesGeography = {
            {"Kina", "Indien", "Kanada", "USA"},
            {"Sydney", "Melbourne", "Canberra", "Brisbane"},
            {"Spanien", "Grekland", "Ungern", "Turkiet"},
            {"Kina", "Indien", "Japan", "Sydkorea"},
    };
    public String getQuestionsGeography(int index) {
        return QuestionGeography[index];
    }

    public String getCorrectAnswerGeography(int index) {
        return AnswersGeography[index];
    }

    public String[] getAlternativesGeography(int index) {
        return AlternativesGeography[index];
    }

    public int getQuestionAmountGeography() {
        return QuestionGeography.length;
    }

    public static String[] getQuestionGeography() {
        return QuestionGeography;
    }

    public static String[] getAnswersGeography() {
        return AnswersGeography;
    }

    public static String[][] getAlternativesGeography() {
        return AlternativesGeography;
    }

    private static String[] QuestionAnimals = {
            "Vilket av dessa djur är ett däggdjur och lägger ägg?",
            "Vilket djur har den längsta levnadslängden bland däggdjur?",
            "Vilken fågel är känd för sina färgglada fjädrar och lever främst i regnskogar i Sydamerika?",
            "Vilket djur anses vara världens snabbaste landlevande däggdjur?",
    };
    private static String[] AnswersAnimals = {
            "Platypus",
            "Galapagosjättesköldpadda",
            "Tukan",
            "Gepard",
    };
    private static String[][] AlternativesAnimals = {
            {"Koala", "Platypus", "Känguru", "Panda"},
            {"Elefant", "Blåval", "Galapagosjättesköldpadda", "Människa"},
            {"Pingvin", "Kakadua", "Tukan", "Kolibri"},
            {"Leopard", "Gepard", "Snöleopard", "Tiger"},
    };
    public String getQuestionsAnimals(int index) {
        return QuestionAnimals[index];
    }

    public String getCorrectAnswerAnimals(int index) {
        return AnswersAnimals[index];
    }

    public String[] getAlternativesAnimals(int index) {
        return AlternativesAnimals[index];
    }

    public int getQuestionAmountAnimals() {
        return QuestionAnimals.length;
    }

    public static String[] getQuestionAnimals() {
        return QuestionAnimals;
    }

    public static String[] getAnswersAnimals() {
        return AnswersAnimals;
    }

    public static String[][] getAlternativesAnimals() {
        return AlternativesAnimals;

    }
}