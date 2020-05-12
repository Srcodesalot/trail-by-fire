package com.company;

import java.util.HashMap;

public class Battle {

    private HashMap<Integer, String> choices = new HashMap();
    private Player player, enemy;

    public Battle(Player p1, Player en) {
        player = p1;
        enemy = en;
        choices.put(1, "Quick Attack");
        choices.put(2, "Strong Attack");
        choices.put(3, "Dodge");
        choices.put(4, "Block");
    }

    //this battle function will be run every time there is a battle
    String battle(int x) {
        //1 = attack 2 = quick attack 3= dodge 4 = attack
        //using ints cause they will be less tedious than text
        String actions = "";
        int playerChoice = x;
        int enemyChoice = enemy.choose();
        int playerRoll, enemyRoll;
        int playerHp = player.getCurHp();
        int enemyHp = enemy.getCurHp();

        //choose attack,block
        //if else to choose what hits land.
        //Player Quick Attack
        if (playerChoice == 1) {
            playerRoll = player.quickAttack();
            //enemy quick attack
            if (enemyChoice == 1) {
                enemyRoll = enemy.quickAttack();
                if (player.getSpeed() > enemy.getSpeed()) {
                    enemyHp -= playerRoll;
                    if (enemyHp < 0) return "PASS";
                    playerHp -= enemyRoll;
                    actions = player.getName() + " Dealt: " + playerRoll + " damage\t" + enemy.getName() + " Dealt: " + enemyRoll + " damage";
                } else {
                    playerHp -= enemyRoll;
                    if (playerHp < 0) return "FAIL";
                    enemyHp -= playerRoll;
                    actions = player.getName() + " Dealt: " + playerRoll + " damage\t" + enemy.getName() + " Dealt: " + enemyRoll + " damage";
                }
            }
            //enemy strong attack
            else if (enemyChoice == 2) {
                enemyRoll = enemy.strongAttack();
                enemyHp -= playerRoll;
                actions = player.getName() + " Dealt: " + playerRoll + " damage";
                if (enemyHp < 0) return "PASS";
                playerHp -= enemyRoll;
                actions += "\t"+ enemy.getName() + " Dealt: " + enemyRoll + " damage";
            }
            //enemy dodge
            else if (enemyChoice == 3) {
                enemyRoll = enemy.dodge();
                int damage = playerRoll - enemyRoll;
                if (damage > 0) {
                    enemyHp -= damage;
                    actions = player.getName() + " Dealt: " + damage + " damage";
                } else
                    actions = enemy.getName() + " was lucky and succeeded in evading even your quick attack";

            }
            //enemy block
            else if (enemyChoice == 4) {
                //enemy blocked which stopped your attack
                actions = "The enemy Blocked rendering your quick attack useless";
            }
        }

        //Player Strong Attack
        if (playerChoice == 2) {
            playerRoll = player.strongAttack();
            //enemy quick attack
            if (enemyChoice == 1) {
                enemyRoll = enemy.quickAttack();
                playerHp -= enemyRoll;
                if (enemyHp < 0) return "PASS";
                enemyHp -= playerRoll;
                actions = player.getName() + " Dealt: " + playerRoll + " damage\t" + enemy.getName() + " Dealt: " + enemyRoll + " damage";
            }
            //enemy strong attack
            else if (enemyChoice == 2) {
                enemyRoll = enemy.strongAttack();
                if (player.getSpeed() > enemy.getSpeed()) {
                    enemyHp -= playerRoll;
                    if (enemyHp < 0) return "PASS";
                    playerHp -= enemyRoll;
                    actions = player.getName() + " Dealt: " + playerRoll + " damage\t" + enemy.getName() + " Dealt: " + enemyRoll + " damage";
                } else {
                    playerHp -= enemyRoll;
                    if (playerHp < 0) return "FAIL";
                    enemyHp -= playerRoll;
                    actions = player.getName() + " Dealt: " + playerRoll + " damage\t" + enemy.getName() + " Dealt: " + enemyRoll + " damage";
                }
            }
            //enemy dodge
            else if (enemyChoice == 3) {
                actions = "The enemy dodged evading your strong attack";
            }
            //enemy block
            else if (enemyChoice == 4) {
                enemyRoll = enemy.block();
                int damage = playerRoll - enemyRoll;
                if (damage > 0) {
                    enemyHp -= damage;
                    actions = player.getName() + " Dealt: " + damage + " damage";
                } else actions = "The enemy successfully blocked your attack.";
            }
        }

        //player dodge
        if (playerChoice == 3) {
            playerRoll = player.dodge();
            //enemy quick attack
            if (enemyChoice == 1) {
                enemyRoll = enemy.quickAttack();
                int damage = enemyRoll - playerRoll;
                if (damage > 0) {
                    playerHp -= damage;
                    actions = enemy.getName() + " Dealt: " + damage + " damage";
                } else actions = "you were really on your toes and even evaded a quick attack!";
            }
            //enemy strong attack
            else if (enemyChoice == 2) {
                actions = "you dodged evading the enemy's strong attack!";
            }
            //enemy dodge
            else if (enemyChoice == 3) {
                actions = "You both dodge mimicking each others moves.";
            }
            //enemy block
            else if (enemyChoice == 4) {
                actions = "You move anticipating the enemy's move Prompting him to hold back.";
            }
        }

        //player block
        if (playerChoice == 4) {
            playerRoll = player.block();
            //enemy quick attack
            if (enemyChoice == 1) {
                actions = "you block brushing off the weak attack!";
            }
            //enemy strong attack
            else if (enemyChoice == 2) {
                enemyRoll = enemy.strongAttack();
                int damage = enemyRoll - playerRoll;
                if (damage > 0) {
                    playerHp -= damage;
                    actions = enemy.getName() + "Dealt: " + damage + " damage";
                }else actions = "Your Block Was a SUCCESS!!! blocking all damage!";
            }
            //enemy dodge
            else if (enemyChoice == 3) {
                actions = "the enemy moves anticipating the your attack Prompting you to hold back.";
            }
            //enemy block
            else if (enemyChoice == 4) {
                actions = "Both poised to block you Stare each other down.";
            }

        }

        actions += "\n" + player.getName() + " chose: " + choices.get(playerChoice) + "\t"
                + enemy.getName() + " chose: " + choices.get(enemyChoice) + "\n"
                + player.getName() + " HP: " + playerHp + "\t"
                + enemy.getName() + " HP: " + enemyHp + "\n";

        player.setHp(playerHp);
        enemy.setHp(enemyHp);
        if (playerHp <= 0) return "FAIL";
        else if (enemyHp <= 0) return "PASS";
        else return actions;
    }
}
