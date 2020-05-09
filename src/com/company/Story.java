package com.company;

public class Story {
    private Player player;
    private String [] story;
    private Player [] enemies;

    public Story(Player p1){
        player = p1;
        setscript();
        setEnemies();
    }

    public String getStory(int x){
        return story[x];
    }

    public Player getEnemy(int x) {
        return enemies[x];
    }

    //instantiate all enemies and enter them into enemies array.
    //To add an enemy simply create the enemy and assign attributes.
    //Then add the enemy into the array IN ORDER of when they appear in the story
    private void setEnemies(){

        //instantiate the players may turn this into just a constructor thing however just stick woth this for now.
        Player racheal1 = new Player("Rachael", 0);
        racheal1.setSpeed(3);
        racheal1.setStrength(2);

        //add player to array.
        Player [] enemy ={racheal1};
        enemies = enemy;
    }


    private void setscript(){
        String [] theScript = {
                "Mom: “Good Morning "+ player.getName() + ", you slept in this morning! Rachael is waiting out in the field for your first fighting lesson.”",
                "You open your eyes and sit up in your bed. Mom is standing in the doorway holding a tiny sack.",
                "Mom: “Take this snack just in case you are slow to learn. You better hurry before she comes looking for you!”",
                "You walk out of the house down the lane to the field where Rachael is waiting for you.",
                "Rachael: “Hey Sleepyhead!”",
                "A silhouette is sitting in the shade under the lone oak in the middle of the field.",
                "It is Rachael!",
                "Rachael: “I’m going to go much harder on you since you made me wait!”",
                "Rachael: “Do you think you are really ready to learn how to fight?”",
                "Rachael: “Take this sword and try not to make a fool of yourself!”",
                "Rachael tosses you a small wooden sword. She stands up and grabs a second sword that was lying down in the grass.",
                "Rachael: “There are four basic moves while fighting; Quick Attack, Heavy Attack, Dodge, and Block.”",
                "Rachael: “Quick attacks are fast lunges that will break past a Dodge but are useless against a sturdy Block.”",
                "Rachael: “Heavy Attacks are a slow attack using the full weight of your body. They are evaded by a Dodge but can break through a Block.”",
                "Rachael: “Come on Shrimp, let’s see if you are really wasting my time.”",
                "BATTLE",//Rachael
                "Rachael: “You still have a long way to go but that was a good first lesson. Maybe one day you will be able to protect this little village.”",
                "Rachael: “Why don’t we call it for the afternoon and go berry picking to finish off the day?”",
                "You have learned the basics of fighting from Rachael.\nLEVEL UP!",
                "BERRY DECISION TREE",
                "Rachael: “"+ player.getName() + ", why do we stay in our tiny village? The world is so much stronger and larger than what we see each day.”",
                "Rachael: “I’m afraid that one day someone scary and powerful will come and destroy us if we do not leave soon. I need to become stronger and I want you to come with me.”",
                "Rachael: “Listen to me, you would never come with me. You’re just a kid.”",
                "Rachael: “I’ll see you later, "+ player.getName() + ".”",
                "You walk back down the lane to your house. The aroma of Mom’s dinner pours from the house as you walk closer.",
                "You eat dinner with Mom. You finish your dinner and prepare to enter a coma from the arduous day and full stomach.",
                "The strong light from an early afternoon hits your eyes waking you up. You feel a panic that you have overslept and are keeping Rachael waiting.",
                "Why didn’t Mom wake you up?",
                "You race down the lane to the field. As you approach the lone oak, the usual silhouette in the shade is missing.",
                "Did she give up on you? Were you too late?",
                "You head back to your house. Maybe she went to teach you a lesson.",
                "When you arrive back home you notice a silence has befallen the village. No one is out.",
                "Where are you Mom?",
                "Rachael?"
        };
        story = theScript;
    }
}
