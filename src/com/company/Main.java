package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static String healerHeal = "";
    public static int[] heroesHealth = {260, 270, 250, 280};
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Healer",};
    public static int roundNumber = 0;
    public static int count = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void healerHeals() {
        Random r = new Random();
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i]!=0 && count==0 ) {
                System.out.println("Healer heal "+heroesAttackType[i]);
                heroesHealth[i] = heroesHealth[i]+r.nextInt(5) * 10;
                count++;

            }
        }
        if (count==0){
            System.out.println("Healer didn't heal");
        }
        count =0;
    }

    public static void round() {
        roundNumber++;
        System.out.println("Round №" + roundNumber);
        changeDefence();
        healerHeals();
        if (bossHealth > 0) {
            bossHits();
        }
        heroesHit();
        printStatistics();
    }

    public static void changeDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("The choice boss: " + bossDefenceType);

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("______________________");
        System.out.println("Boss Health: " + bossHealth + " [" + bossDamage + "] ");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + "[" + heroesDamage[i] + "]");

        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coefficient = random.nextInt(8) + 2;
                    if (bossHealth - heroesDamage[i] * coefficient < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coefficient;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coefficient);

                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

}




