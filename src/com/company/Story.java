package com.company;

public class Story {
    private Player player;
    private String[] story;
    private Player[] enemies;

    public Story(Player p1) {
        player = p1;
        setscript();
        setEnemies();
    }

    public String getStory(int x) { return story[x]; }

    public Player getEnemy(int x) { return enemies[x]; }

    //instantiate all enemies and enter them into enemies array.
    //To add an enemy simply create the enemy and assign attributes.
    //Then add the enemy into the array IN ORDER of when they appear in the story
    private void setEnemies() {

        //instantiate the players may turn this into just a constructor thing however just stick woth this for now.
        Player racheal1 = new Player("Rachel", 1);
        racheal1.setSpeed(3);
        racheal1.setStrength(2);

        //add player to array.
        Player[] enemy = {racheal1};
        enemies = enemy;
    }



    private void setscript(){
        String [] theScript = {
                "Mom: “Good Morning "+ player.getName() + ", you slept in this morning! Rachel is waiting out in the field for your first fighting lesson.”",
                "You open your eyes and sit up in your bed. Mom is standing in the doorway holding a tiny sack.",
                "Mom: “Take this snack just in case you are slow to learn. You better hurry before she comes looking for you!”",
                "You walk out of the house down the lane to the field where Rachel is waiting for you.",
                "Rachel: “Hey Sleepyhead!”",
                "A silhouette is sitting in the shade under the lone oak in the middle of the field.",
                "It is Rachel!",
                "Rachel: “I’m going to go much harder on you since you made me wait!”",
                "Rachel: “Do you think you are really ready to learn how to fight?”",
                "Rachel: “Take this sword and try not to make a fool of yourself!”",
                "Rachel tosses you a small wooden sword. She stands up and grabs a second sword that was lying down in the grass.",
                "Rachel: “There are four basic moves while fighting; Quick Attack, Heavy Attack, Dodge, and Block.”",
                "Rachel: “Quick attacks are fast lunges that will break past a Dodge but are useless against a sturdy Block.”",
                "Rachel: “Heavy Attacks are a slow attack using the full weight of your body. They are evaded by a Dodge but can break through a Block.”",
                "Rachel: “Come on Shrimp, let’s see if you are really wasting my time.”",
                "BATTLE",//Rachel (Level 1)
                "Rachel: “You still have a long way to go but that was a good first lesson. Maybe one day you will be able to protect this little village.”",
                "Rachel: “Why don’t we call it for the afternoon and go berry picking to finish off the day?”",
                "You have learned the basics of fighting from Rachel.\nLEVEL UP!",
                "DECISION TREE", //Berries (Level 2)
                "Rachel: “"+ player.getName() + ", why do we stay in our tiny village? The world is so much stronger and larger than what we see each day.”",
                "Rachel: “I’m afraid that one day someone scary and powerful will come and destroy us if we do not leave soon. I need to become stronger and I want you to come with me.”",
                "Rachel: “Listen to me, you would never come with me. You’re just a kid.”",
                "Rachel: “I’ll see you later, "+ player.getName() + ".”",
                "You walk back down the lane to your house. The aroma of Mom’s dinner pours from the house as you walk closer.",
                "You eat dinner with Mom. \nAfter your meal you prepare to enter a coma from the arduous day and full stomach.",
                "The strong light from an early afternoon hits your eyes waking you up. You feel a panic that you have overslept and are keeping Rachel waiting.",
                "Why didn’t Mom wake you up?",
                "You race down the lane to the field. As you approach the lone oak, the usual silhouette in the shade is missing.",
                "Did she give up on you? Were you too late?",
                "You head back to your house. Maybe she went to teach you a lesson.",
                "When you arrive back home you notice a silence has befallen the village. No one is out.",
                "Where are you Mom?",
                "Rachel?",
                "You run through the house then out the door. \nSomething catches your eye as you run into town.",
                "A worn path in the grass veers off the road heading towards the woods.",
                "You are drawn to follow the trail as if Rachel and Mom are walking by your side.",
                "With each step into the forest the world around you feels darker.",
                "And darker…",
                "A screech cuts through the forest to your right.",
                "The cracking of twigs and rustling of leaves begins to get louder and louder.",
                "A large black shadow is charging straight for you.",
                "Prepare for the charge of a large black boar.",
                "BATTLE", //Boar (Level 3)
                "The shadow that was charging you moments ago now disappears as the boar flees for its life.",
                "You fall to your knees, exhausted. \nEvery inch of your body tells you to turn back and go to the safety of your home.",
                "Life is meaningless without Mom and Rachel. You must continue on.",
                "Even if it costs your life.",
                "LEVEL UP!",
                "You gather your strength and rise to your feet to continue down the trail.",
                "After a while you start to smell the wonderful scent of a campfire.",
                "If there are people up ahead, they could have seen Rachel or Mom.",
                "You increase your pace following the path and your nose.",
                "The crackling of firewood becomes audible.",
                "As you round a turn in the path you come to a small clearing in the forest with two small huts.",
                "As you step into the clearing a guttural roar from the threshold of one of the huts.",
                "Goblin: “Haven’t you all done enough? Do you wish to die this time?”",
                "BATTLE", //Goblin (Level 4)
                "Goblin: “Foiled again!”",
                "LEVEL UP!",
                "The path led right through his camp. The Goblin must know where Rachel is!",
                "DECISION TREE", //Goblin Interrogation (Level 5)
                "It is clear that the Goblin will not give up more information.",
                "Goblin: “Please leave me be, any time wasted on me is time those monsters get further away.“",
                "Suddenly a howl is heard in the distance. A wave of panic rushes over the Goblin's face.",
                "Goblin: “Leave now! They know you are here! We both will be killed!“",
                "Goblin: “Take the path to the East! If you run they may lose your scent.“",
                "You start to run down the path that was pointed out by the Goblin.",
                "The path is narrow. Branches smack you in the face as you run as fast as you can.",
                "The thoughts of Rachel give you strength to carry on.",
                "Your best friend and reason for life.",
                "“HOWL“",
                "The wolves are much closer then before.",
                "You look behind you as you run. They are not behind you.",
                "You can get away.",
                "As you turn back around, your foot in caught on a root in the path.",
                "You head slams on the ground as your speed throws you to the ground.",
                "Voice: “It is no use human.“",
                "A voice is heard through the bushes in front of you.",
                "Wolf: A tasty lost prey for a famished lone wolf",
                "Emerging from bushes is a large gray wolf, \nhis unwavering eyes calculate every tremble of your body",
                "The Wolf shifts its weight back and then lunges towards you!",
                "BATTLE", //Wolf (Level 6)
                "Evading the piercing teeth of the Wolf, you strike it down.",
                "Its eyes stare up at you, reading your thoughts.",
                "A twig cracks off the trail which causes you to turn your head. \nAt that second, the Wolf darts back into the darkness.",
                "LEVEL UP!",
                "END"
        };
        story = theScript;
    }
}
