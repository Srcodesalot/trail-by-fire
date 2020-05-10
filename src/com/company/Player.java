package com.company;

import java.util.Random;

//**
// Player class creates a player that follow these simple rules:
// Based on what "Race" your character is they will have various speed, strength, and, hp.
// Each player starts with a 'D5' or 5 sided dice, this means they will be able to get 5 hit points max per turn.
// Upon reaching the next level the dice will gain another side.
//**

public class Player {
    private int level, speed, strength, maxHp, curHp, hitRange;
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
    public int choose() {
        Random gen = new Random();
        int choice = gen.nextInt(4);
        return choice + 1;
    }
}