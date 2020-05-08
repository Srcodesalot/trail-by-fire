package com.company;
import java.util.HashMap;

public class Choices {

    private HashMap<String,String[]> choices = new HashMap();

    public Choices(){
        setChoices();
    }

    private void setChoices(){
        // to add choices follow the below format
        //each choice should be named choiceX with x being an incrementing integer
        String [] choice0 = {" Look for berrys by the stream."," Look for berries by in the forest "," Go home and rest"};
        choices.put("choice0",choice0);
    }

    public String [] getChoices(String choice){
        return choices.get(choice);
    }

    public String Results(int choiceSet, int selected){
        //place holder cases for testing please change
        switch (choiceSet) {
            case 0:
                return choice0(selected);
            case 1:
                return choice1(selected);
            case 2:
                return choice2(selected);
        }
        return null;
    }

    public String choice0(int selected){
        //place holder cases for testing please change
        switch (selected) {
            case 0:
                return "You see a few fish and bushes, but no berries";
            case 1:
                return "Congradulations You found the berries\n LEVEL UP! ";
            case 2:
                return "Youre right, lets just head back!";
        }
        return null;
    }

    public String choice1(int x){
        //place holder cases for testing please change
        switch (x) {
            case 0:
                return "Go Crazy ";
            case 1:
                return "Go Stupid";
            case 2:
                return "LEVEL UP!";
        }
        return null;
    }

    public String choice2(int x){
        //place holder cases for testing please change
        switch (x) {
            case 0:
                return " LEVEL UP!";
            case 1:
                return "You Suck!";
            case 2:
                return "I Suck!";
        }
        return null;
    }

}
