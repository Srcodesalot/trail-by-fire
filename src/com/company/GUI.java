package com.company;
import java.awt.*;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//NOW FUNCTIONING AS MAIN CLASS
//Cool color scheme we may want https://www.fabmood.com/inspiration/emerald-teal-and-terracotta-color-scheme/
// warmer more aggressive maybe for attack screen https://www.schemecolor.com/halloween-attack-color-palette.php
//implemented this color scheme on the titleScreen and the CharacterScreen as a test.
//you can also use a css file which would be cleaner for sure
public class GUI extends Application {

    Stage window;
    Scene titleScreen, characterScreen, mainScreen, battleScreen, decisionScreen;
    Label hpLabel,decisionHpLabel, battleHpLabel, nameLabel, battleNameLabel, decisionNameLabel, makingName;
    TextArea mainTextArea, battleTextArea, decisionTextArea;
    int  storyTracker = 0, enemyTracker = 0, choiceTracker = 0;
    Player player;
    Story story;
    TextField playerNameInput;
    Battle curBattle;
    HBox choiceButtons;

    public void start(Stage primaryStage) throws Exception{
        //TODO:initial window: Style this screen
        window = primaryStage;
        window.setTitle("Trial by Fire");
        //intro one
        Label titleNameLabel = new Label("Trial by Fire");
        titleNameLabel.fontProperty().setValue(Font.font(50));
        titleNameLabel.setTextFill(Color.valueOf("#d2513f"));

        Button startButton = new Button("Begin");
        startButton.setStyle("-fx-background-color: #d2513f");
        startButton.setTextFill(Color.valueOf("#01181e"));
        startButton.setMinSize(100,35);
        startButton.setOnAction(e-> window.setScene(characterScreen));

        VBox titleLayout = new VBox(80);
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.getChildren().addAll(titleNameLabel ,startButton);
        titleLayout.setStyle("-fx-background-color: #01181e");

        titleScreen = new Scene(titleLayout,900,600);

        //TODO:Character builder: Style this screen
        makingName = new Label("What do they call you?");
        makingName.setFont(Font.font(40));
        makingName.setTextFill(Color.valueOf("#d2513f"));

        playerNameInput = new TextField();
        playerNameInput.setMaxSize(600,10);
        playerNameInput.setStyle("-fx-background-color: #01181e; -fx-text-inner-color: #3d7889;");
        playerNameInput.setFont(Font.font(25));
        playerNameInput.setAlignment(Pos.CENTER);
        playerNameInput.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER){
                String name = playerNameInput.getText();
                initPlayer(name);
                progress();
                window.setScene(mainScreen);
            }
                }
        );

        VBox characterLayout = new VBox(20);
        characterLayout.setAlignment(Pos.CENTER);
        characterLayout.getChildren().addAll(makingName ,playerNameInput);
        characterLayout.setStyle("-fx-background-color: #01181e");

        characterScreen = new Scene(characterLayout,900,600);

        //TODO:GAME SCREEN: Style this screen
        hpLabel = new Label("HP: " );

        nameLabel = new Label("Name: ");

        HBox playerPanel = new HBox(characterScreen.getWidth()/2);
        playerPanel.setAlignment(Pos.CENTER);
        playerPanel.getChildren().addAll(hpLabel, nameLabel);

        mainTextArea = new TextArea();
        mainTextArea.isWrapText();

        VBox mainLayout = new VBox(0);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(playerPanel, mainTextArea);

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e-> progress());
        continueButton.setAlignment(Pos.CENTER_RIGHT);

        VBox fullLayout = new VBox(120);
        fullLayout.setAlignment(Pos.TOP_CENTER);
        fullLayout.getChildren().addAll(mainLayout, continueButton);

        mainScreen = new Scene(fullLayout,900,600);

        //TODO:BATTLE SCREEN: Style this screen
        battleHpLabel = new Label("HP: " );

        battleNameLabel = new Label("Name: ");

        HBox battlePlayerPanel = new HBox(characterScreen.getWidth()/2);
        battlePlayerPanel.setAlignment(Pos.CENTER);
        battlePlayerPanel.getChildren().addAll(battleHpLabel, battleNameLabel);

        battleTextArea = new TextArea();
        battleTextArea.isWrapText();

        VBox battleMainLayout = new VBox(0);
        battleMainLayout.setAlignment(Pos.CENTER);
        battleMainLayout.getChildren().addAll(battlePlayerPanel, battleTextArea);

        Button quick = new Button("Quick Attack");
        quick.setOnAction(e-> battleAction(1));

        Button strong = new Button("Heavy Attack");
        strong.setOnAction(e-> battleAction(2));

        Button dodge = new Button("Dodge");
        dodge.setOnAction(e-> battleAction(3));

        Button block = new Button("Block");
        block.setOnAction(e-> battleAction(4));

        HBox attackButtons = new HBox(10);
        attackButtons.setAlignment(Pos.CENTER);
        attackButtons.getChildren().addAll(quick,strong);

        HBox defenseButtons = new HBox(10);
        defenseButtons.setAlignment(Pos.CENTER);
        defenseButtons.getChildren().addAll(dodge,block);

        VBox battleButtons = new VBox(10);
        battleButtons.getChildren().addAll(attackButtons,defenseButtons);

        VBox battleLayout = new VBox(120);
        battleLayout.setAlignment(Pos.TOP_CENTER);
        battleLayout.getChildren().addAll(battleMainLayout,battleButtons);

        battleScreen = new Scene(battleLayout,900,600);

        window.setScene(titleScreen);
        window.show();

        //TODO: DESICION SCREEN: Style this screen
        decisionHpLabel = new Label("HP: " );

        decisionNameLabel = new Label("Name: ");

        HBox decisionPlayerPanel = new HBox(characterScreen.getWidth()/2);
        decisionPlayerPanel.setAlignment(Pos.CENTER);
        decisionPlayerPanel.getChildren().addAll(decisionHpLabel, decisionNameLabel);

        decisionTextArea = new TextArea();
        decisionTextArea.isWrapText();
        decisionTextArea.setText("What will you do?");

        VBox decisionMainLayout = new VBox(0);
        decisionMainLayout.setAlignment(Pos.CENTER);
        decisionMainLayout.getChildren().addAll(decisionPlayerPanel, decisionTextArea);

        //actual decision buttons are made in decision init meathod
        choiceButtons = new HBox(10);
        choiceButtons.getChildren().addAll();
        choiceButtons.setAlignment(Pos.CENTER);

        VBox decisionLayout = new VBox(120);
        decisionLayout.setAlignment(Pos.TOP_CENTER);
        decisionLayout.getChildren().addAll(decisionMainLayout,choiceButtons);

        decisionScreen = new Scene(decisionLayout,900,600);

        window.setScene(titleScreen);
        window.show();

    }

    //initialization meathods
    private void initPlayer(String name){
        player = new Player(name, 1);
        nameLabel.setText("Name: " + name);
        hpLabel.setText("HP: " + player.getCurHp());
        battleNameLabel.setText("Name: " + name);
        battleHpLabel.setText("HP: " + player.getCurHp());
        decisionNameLabel.setText("Name: " + name);
        decisionHpLabel.setText("HP: " + player.getCurHp());

        story = new Story(player);
    }

    private void initBattle(){
        Player enemy = story.getEnemy(enemyTracker);
        enemy.Restore();
        Battle battle = new Battle(player, enemy);
        curBattle = battle;
    }

    private void initDecision(){
        choiceButtons.getChildren().clear();
        String choice  = "choice" + choiceTracker;
        Choices thisChoice = new Choices();
        String [] labels = thisChoice.getChoices(choice);
        int x = 0;

        for (String label: labels) {
            System.out.println(label);
            final int picked = x;
            Button button = new Button(label);
            button.setOnAction(e-> choose(picked));

            choiceButtons.getChildren().add(button);

            x++;
        }
    }

    private void progress(){
        String storyLine = story.getStory(storyTracker);
        System.out.println(storyLine);
        storyTracker++;
        if (storyLine.contains("BATTLE")) {
            initBattle();
            battleTextArea.setText("Quick, the battle has begun, Whats your move?! ");
            window.setScene(battleScreen);
        }
        if (storyLine.contains("DECISION TREE")) {
            initDecision();
            window.setScene(decisionScreen);
            mainTextArea.setText("SEARCH!!!!");
        }
        if(storyLine.contains("LEVEL UP!")){
            player.levelUp();
            player.Restore();
            updatePlayerPanel();
        }
        mainTextArea.setText(storyLine);
    }

    //User Actions
    private void battleAction(int x){
        String result = curBattle.battle(x);
        if (result == "PASS") {
            mainTextArea.setText("Victory!");
            updatePlayerPanel();
            window.setScene(mainScreen);
        }
        if (result == "FAIL") {
            mainTextArea.setText("You Lost.");
            //restore in retry screen
            player.Restore();
            storyTracker--;
            window.setScene(mainScreen);
        }
        battleTextArea.setText(result);
        updatePlayerPanel();
    }

    private void choose(int x){
        Choices thisChoice = new Choices();
        String result = thisChoice.Results(choiceTracker, x);
        mainTextArea.setText(result);
        System.out.println(result);
        if(result.contains("LEVEL UP!")){
            player.levelUp();
        }
        window.setScene(mainScreen);
    }

    //Mutators
    private void updatePlayerPanel(){
        hpLabel.setText("HP: " + player.getCurHp());
        battleHpLabel.setText("HP: " + player.getCurHp());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}