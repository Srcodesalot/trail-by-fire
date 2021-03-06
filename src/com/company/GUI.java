package com.company;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import java.util.Timer;
import java.util.*;

//NOW FUNCTIONING AS MAIN CLASS
//Cool color scheme we may want https://www.fabmood.com/inspiration/emerald-teal-and-terracotta-color-scheme/
// warmer more aggressive maybe for attack screen https://www.schemecolor.com/halloween-attack-color-palette.php
//implemented this color scheme on the titleScreen and the CharacterScreen as a test.
//you can also use a css file which would be cleaner for sure
public class GUI extends Application {

    Stage window;
    Scene titleScreen, characterScreen, mainScreen, battleScreen, decisionScreen;
    Label hpLabel, decisionHpLabel, battleHpLabel, nameLabel, battleNameLabel, decisionNameLabel, makingName;
    Label mainTextArea, battleTextArea, decisionTextArea;
    int storyTracker = 0, enemyTracker = 0, choiceTracker = 0, themeIdx = 1;
    Player player;
    Story story;
    TextField playerNameInput;
    Battle curBattle;
    HBox choiceButtons;
    VBox mainLayout, fullLayout;
    Button continueButton;

    public void start(Stage primaryStage) throws Exception {
        String defaultTheme = "themes/dark-theme.css";

        //TODO:initial window: Style this screen
        window = primaryStage;
        window.setTitle("Trial by Fire");

        Button themeSwitcher = new Button("™");
        themeSwitcher.setOnAction(e -> themeSwap(themeIdx));
        themeSwitcher.getStyleClass().add("theme-manager");

        Label spacer = new Label(" ");

        HBox themeButton = new HBox(800);
        themeButton.getChildren().addAll(spacer,themeSwitcher);

        //intro one
        Label titleNameLabel = new Label("Trial by Fire");
        titleNameLabel.fontProperty().setValue(Font.font(100));

        Button startButton = new Button("Begin");
        startButton.setMinSize(100, 35);
        startButton.setOnAction(e -> window.setScene(characterScreen));

        VBox mainComponent  = new VBox(80);
        mainComponent.setAlignment(Pos.CENTER);
        mainComponent.getChildren().addAll(titleNameLabel, startButton);

        VBox titleLayout = new VBox(220);
        titleLayout.setAlignment(Pos.TOP_CENTER);
        titleLayout.getChildren().addAll(themeButton, mainComponent);

        titleScreen = new Scene(titleLayout, 900, 600);
        titleScreen.getStylesheets().add(defaultTheme);

        //TODO:Character builder: Style this screen
        makingName = new Label("What do they call you?");
        makingName.setFont(Font.font(40));

        playerNameInput = new TextField();
        playerNameInput.setMaxSize(600, 10);
        playerNameInput.getStyleClass().add("text-field");
        playerNameInput.setFont(Font.font(25));
        playerNameInput.setAlignment(Pos.CENTER);
        playerNameInput.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.ENTER) {
                        String name = playerNameInput.getText();
                        initPlayer(name);
                        progress();
                        window.setScene(mainScreen);
                    }
                }
        );

        VBox characterLayout = new VBox(20);
        characterLayout.setAlignment(Pos.CENTER);
        characterLayout.getChildren().addAll(makingName, playerNameInput);

        characterScreen = new Scene(characterLayout, 900, 600);
        characterScreen.getStylesheets().add(defaultTheme);

        //TODO:GAME SCREEN: Style this screen
        hpLabel = new Label("HP: ");
        hpLabel.getStyleClass().add("label-upTop");

        nameLabel = new Label("Name: ");
        nameLabel.getStyleClass().add("label-upTop");

        HBox hpName = new HBox(characterScreen.getWidth() / 2);
        hpName.setAlignment(Pos.CENTER);
        hpName.getChildren().addAll(hpLabel, nameLabel);

        Button gThemeSwitcher = new Button("™");
        gThemeSwitcher.setOnAction(e -> themeSwap(themeIdx));
        gThemeSwitcher.getStyleClass().add("theme-manager");

        HBox playerPanel = new HBox(55);
        playerPanel.setAlignment(Pos.BOTTOM_RIGHT);
        playerPanel.getChildren().addAll(hpName, gThemeSwitcher);

        mainTextArea = new Label();
        mainTextArea.isWrapText();
        mainTextArea.setMinWidth(50);
        mainTextArea.setMinHeight(50);

        HBox padding = new HBox(50);
        padding.setAlignment(Pos.CENTER);
        Label paddingleft = new Label("");
        Label paddingRight = new Label("");
        padding.getChildren().addAll(paddingleft, mainTextArea, paddingRight);

        mainLayout = new VBox(140);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(playerPanel, padding);

        continueButton = new Button("Continue");
        continueButton.setOnAction(e -> progress());
        continueButton.setAlignment(Pos.CENTER_RIGHT);

        fullLayout = new VBox(110);
        fullLayout.setAlignment(Pos.TOP_CENTER);
        fullLayout.getChildren().addAll(mainLayout, continueButton);

        mainScreen = new Scene(fullLayout, 900, 600);
        mainScreen.getStylesheets().add(defaultTheme);

        //TODO:BATTLE SCREEN: Style this screen
        battleHpLabel = new Label("HP: ");
        battleHpLabel.getStyleClass().add("label-upTop");

        battleNameLabel = new Label("Name: ");
        battleNameLabel.getStyleClass().add("label-upTop");

        HBox bHpName = new HBox(characterScreen.getWidth() / 2);
        bHpName.setAlignment(Pos.CENTER);
        bHpName.getChildren().addAll(battleHpLabel, battleNameLabel);

        Button bThemeSwitcher = new Button("™");
        bThemeSwitcher.setOnAction(e -> themeSwap(themeIdx));
        bThemeSwitcher.getStyleClass().add("theme-manager");

        HBox battlePlayerPanel = new HBox(55);
        battlePlayerPanel.setAlignment(Pos.BOTTOM_RIGHT);
        battlePlayerPanel.getChildren().addAll(bHpName, bThemeSwitcher);

        battleTextArea = new Label();
        battleTextArea.isWrapText();
        battleTextArea.prefWidth(50);

        VBox battleMainLayout = new VBox(140);
        battleMainLayout.setAlignment(Pos.CENTER);
        battleMainLayout.getChildren().addAll(battlePlayerPanel, battleTextArea);
        battleMainLayout.minHeight(600);

        Button quick = new Button("Quick Attack");
        quick.setOnAction(e -> battleAction(1));
        quick.setMinSize(200, 20);

        Button strong = new Button("Heavy Attack");
        strong.setOnAction(e -> battleAction(2));
        strong.setMinSize(200, 20);

        Button dodge = new Button("Dodge");
        dodge.setOnAction(e -> battleAction(3));
        dodge.setMinSize(200, 20);

        Button block = new Button("Block");
        block.setOnAction(e -> battleAction(4));
        block.setMinSize(200, 20);

        HBox attackButtons = new HBox(10);
        attackButtons.setAlignment(Pos.CENTER);
        attackButtons.getChildren().addAll(quick, strong);

        HBox defenseButtons = new HBox(10);
        defenseButtons.setAlignment(Pos.CENTER);
        defenseButtons.getChildren().addAll(dodge, block);

        VBox battleButtons = new VBox(10);
        battleButtons.getChildren().addAll(attackButtons, defenseButtons);

        VBox battleLayout = new VBox(110);
        battleLayout.setAlignment(Pos.TOP_CENTER);
        battleLayout.getChildren().addAll(battleMainLayout, battleButtons);

        battleScreen = new Scene(battleLayout, 900, 600);
        battleScreen.getStylesheets().add(defaultTheme);

        //TODO: DESICION SCREEN: Style this screen
        decisionHpLabel = new Label("HP: ");
        decisionHpLabel.getStyleClass().add("label-upTop");

        decisionNameLabel = new Label("Name: ");
        decisionNameLabel.getStyleClass().add("label-upTop");

        HBox dHpName = new HBox(characterScreen.getWidth() / 2);
        dHpName.setAlignment(Pos.CENTER);
        dHpName.getChildren().addAll(decisionHpLabel, decisionNameLabel);

        Button dThemeSwitcher = new Button("™");
        dThemeSwitcher.setOnAction(e -> themeSwap(themeIdx));
        dThemeSwitcher.getStyleClass().add("theme-manager");

        HBox decisionPlayerPanel = new HBox(55);
        decisionPlayerPanel.setAlignment(Pos.BOTTOM_RIGHT);
        decisionPlayerPanel.getChildren().addAll(dHpName, dThemeSwitcher);

        decisionTextArea = new Label();
        decisionTextArea.isWrapText();
        decisionTextArea.setText("What will you do?");

        VBox decisionMainLayout = new VBox(140);
        decisionMainLayout.setAlignment(Pos.CENTER);
        decisionMainLayout.getChildren().addAll(decisionPlayerPanel, decisionTextArea);

        //actual decision buttons are made in decision init meathod
        choiceButtons = new HBox(10);
        choiceButtons.getChildren().addAll();
        choiceButtons.setAlignment(Pos.CENTER);

        VBox decisionLayout = new VBox(110);
        decisionLayout.setAlignment(Pos.TOP_CENTER);
        decisionLayout.getChildren().addAll(decisionMainLayout, choiceButtons);

        decisionScreen = new Scene(decisionLayout, 900, 600);
        decisionScreen.getStylesheets().add(defaultTheme);

        window.setScene(titleScreen);
        window.show();

    }

    //initialization meathods
    private void initPlayer(String name) {
        player = new Player(name, 1);
        nameLabel.setText("Name: " + name);
        hpLabel.setText("HP: " + player.getCurHp());
        battleNameLabel.setText("Name: " + name);
        battleHpLabel.setText("HP: " + player.getCurHp());
        decisionNameLabel.setText("Name: " + name);
        decisionHpLabel.setText("HP: " + player.getCurHp());

        story = new Story(player);
    }

    private void initBattle() {
        Player enemy = story.getEnemy(enemyTracker);
        enemy.Restore();
        Battle battle = new Battle(player, enemy);
        curBattle = battle;
    }

    private void initDecision() {
        choiceButtons.getChildren().clear();
        String choice = "choice" + choiceTracker;
        Choices thisChoice = new Choices();
        String[] labels = thisChoice.getChoices(choice);
        int x = 0;

        for (String label : labels) {
            System.out.println(label);
            final int picked = x;
            Button button = new Button(label);
            button.setOnAction(e -> choose(picked));

            choiceButtons.getChildren().add(button);

            x++;
        }
    }

    private void progress() {
        String storyLine = story.getStory(storyTracker);
        System.out.println(storyLine);
        storyTracker++;
        if (storyLine.contains("BATTLE")) {
            initBattle();
            battleTextArea.setText("Quick, the battle has begun, Whats your move?! ");
            window.setScene(battleScreen);
        }
        else if (storyLine.contains("DECISION TREE")) {
            initDecision();
            window.setScene(decisionScreen);
            mainTextArea.setText("SEARCH!!!!");
        }
        else if (storyLine.contains("LEVEL UP!")) {
            player.levelUp();
            player.Restore();
            updatePlayerPanel();
        }
        else if (storyLine.contains("END")) {
            //rebuild the main screen
            continueButton.setVisible(false);
            mainTextArea.setText("Thats all for now! Thank you for playing and there will be more updates to come!\n");

            Hyperlink gitLink = new Hyperlink("Github link: https://github.com/Srcodesalot/trail-by-fire");
            gitLink.getStyleClass().add("hyper-link");
            gitLink.setOnAction(e-> getHostServices().showDocument("https://github.com/Srcodesalot/trail-by-fire"));

            mainLayout.getChildren().add(gitLink);

            fullLayout.setSpacing(90);
        }else{
            mainTextArea.setText(storyLine);
        }
    }

    //User Actions
    private void battleAction(int x) {
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

    private void choose(int x) {
        Choices thisChoice = new Choices();
        String result = thisChoice.Results(choiceTracker, x);
        mainTextArea.setText(result);
        System.out.println(result);
        if (result.contains("LEVEL UP!")) {
            player.levelUp();
            updatePlayerPanel();
        }
        window.setScene(mainScreen);
    }

    //Mutators
    private void updatePlayerPanel() {
        hpLabel.setText("HP: " + player.getCurHp());
        battleHpLabel.setText("HP: " + player.getCurHp());
        battleHpLabel.setText("HP: " + player.getCurHp());
        decisionHpLabel.setText("HP: " + player.getCurHp());
    }

    private void themeSwap(int x) {
        themeIdx++;
        String[] themes = {
                "dark-theme.css", "light-theme.css", "black-white-gradient.css",
                "blue-gradient.css", "rainbow-theme.css", "red-gradient.css",
                "super-bright-theme.css"
        };

        String theme = "themes/" + themes[x % themes.length];

        //clear
        titleScreen.getStylesheets().clear();
        characterScreen.getStylesheets().clear();
        mainScreen.getStylesheets().clear();
        battleScreen.getStylesheets().clear();
        decisionScreen.getStylesheets().clear();

        //set
        titleScreen.getStylesheets().add(theme);
        characterScreen.getStylesheets().add(theme);
        mainScreen.getStylesheets().add(theme);
        battleScreen.getStylesheets().add(theme);
        decisionScreen.getStylesheets().add(theme);

        if(x == themes.length*4){
            rapidTheme();
        }
    }

    public void rapidTheme(){

        Timer scheduler = new Timer();

        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                themeSwap(themeIdx);
            }
        };

        TimerTask stopper = new TimerTask() {
            @Override
            public void run() {
                themeIdx = 0;
                scheduler.cancel();
            }
        };

        scheduler.schedule(tasknew,300,100);
        scheduler.schedule(stopper,20000,200);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}