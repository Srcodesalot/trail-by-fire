package com.company;

import java.util.ArrayList;
import java.util.Random;

//**
// Player class creates a player that follow these simple rules:
// Based on what "Race" your character is they will have various speed, strength, and, hp.
// Each player starts with a 'D5' or 5 sided dice, this means they will be able to get 5 hit points max per turn.
// Upon reaching the next level the dice will gain another side.
//**

public class Player {
    private int level, speed, strength, maxHp, curHp, hitRange, prevAttack =0;
    private String name;

    public Player(String nme, int lev) {
        name = nme;
        level = lev;
        maxHp = lev * 10;
        curHp = maxHp;
        speed = 1;
        strength = 1;
        hitRange = 5;
    }

    //retrieval
    public String getName() {
        return name;
    }

    public int getCurHp() {
        return curHp;
    }

    public int getSpeed() {
        return speed;
    }

    //MODIFIERS
    public void levelUp() {
        maxHp += 10;
        curHp = maxHp;
        level += 1;
        speed += 1;
        strength += 1;
        hitRange = 1;
    }

    public void setSpeed(int spd) {
        speed = spd;
    }

    public void setStrength(int str) {
        strength = str;
    }

    public void setHp(int hp) {
        curHp = hp;
    }

    public void Restore() {
        curHp = maxHp;
    }

    //ACTIONS
    //fast attack no modifiers
    public int quickAttack() {
        Random damage = new Random();
        int damageDealt = damage.nextInt(hitRange) + speed;
        return damageDealt;
    }

    //slower attack inclues strength modifier
    public int strongAttack() {
        Random damage = new Random();
        int damageDealt = damage.nextInt(hitRange) + strength;
        return damageDealt;
    }

    // Block is a roll plus strength
    public int block() {
        Random damage = new Random();
        int damageDealt = damage.nextInt(hitRange) + strength;
        return damageDealt;
    }

    //dodge is a roll plus speed
    public int dodge() {
        Random damage = new Random();
        int damageDealt = damage.nextInt(hitRange) + speed;
        return damageDealt;
    }

    //npc gen
    //logic may be better as a separate initial function
    public int choose() {
        //1 = attack 2 = quick attack 3= dodge 4 = block
        int weakAttack = speed < strength ? 2 :1;
        int strongAttack = weakAttack == 1 ? 2 :1;
        int weakBlock = speed < strength ? 3 :4;
        int strongBlock = weakBlock == 3 ? 4 : 3;
        ArrayList <Integer> moves = new ArrayList<>();
        Random gen = new Random();
        System.out.println(" WeakA: " + weakAttack + " StrongA: " + strongAttack +  " WeakB: " + weakBlock + " StrongB: " + strongBlock);

        int choice;

        if(level == 1){
            if(prevAttack == weakBlock){
                prevAttack = strongAttack;
                return strongAttack;
            }
            else if(prevAttack == strongAttack){
                prevAttack = strongBlock;
                return strongBlock;
            }
            else if(prevAttack == strongBlock){
                prevAttack = weakAttack;
                return weakAttack;
            }
             else if (prevAttack == weakAttack){
                prevAttack = weakBlock;
                return weakBlock;
            }
            else{
                moves.add(weakAttack);
                moves.add(weakBlock);
                moves.add(strongAttack);
                moves.add(strongBlock);
            }
        }
        else if(level <= 5){
            if(prevAttack == weakAttack){
                prevAttack = weakBlock;
                return weakBlock;
            }
            if(prevAttack == strongBlock){
                prevAttack = strongAttack;
                return strongAttack;
            }
            moves.add(weakBlock);
            for (int i = 0; i <= level%5; i++) {
                moves.add(weakAttack);
                moves.add(weakBlock);
                moves.add(strongAttack);
                moves.add(strongBlock);
            }
        }
        else if (5 < level && level <= 10){

            if(prevAttack == weakAttack){
                prevAttack = weakBlock;
                return weakBlock;
            }
            moves.add(weakBlock);
            for (int i = 0; i <= level%5; i++) {
                moves.add(weakAttack);
                moves.add(weakBlock);
                moves.add(strongAttack);
                moves.add(strongBlock);
            }

        } else{
            if(prevAttack == strongAttack){
                prevAttack = strongBlock;
                return strongBlock;
            }
            moves.add(weakBlock);
            for (int i = 0; i <= level%5; i++) {
                moves.add(weakAttack);
                moves.add(weakBlock);
                moves.add(strongAttack);
                moves.add(strongBlock);
            }

        }

        choice = gen.nextInt(moves.size());
        int move = moves.get(choice);
        prevAttack = move;
        return move;
    }
}