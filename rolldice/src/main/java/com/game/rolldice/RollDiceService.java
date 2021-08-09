package com.game.rolldice;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Component
public class RollDiceService {

    List<Integer> firstDiceResultsList;

    void calculateAverageOneDice(int NUM_ROLLS){
        firstDiceResultsList = Stream.generate(() -> (int) (Math.random() * 6 + 1)).limit(NUM_ROLLS)
                .collect(Collectors.toList());
        if (NUM_ROLLS == 24) {
            for (int i= 0; i <=NUM_ROLLS; i++){
                Integer wins = 0;
                int roll1 = (int) (Math.random() * 6) + 1;
                int roll2 = (int) (Math.random() * 6) + 1;
                List result = Arrays.asList(roll1, roll2);
                if (roll1 == 6 && roll2 == 6) {
                    wins++;
                    printResults(wins.longValue(), NUM_ROLLS, result.size());
                }
            }
        }

        firstDiceResultsList.stream()
                .filter(num -> num == 6)
                .collect(Collectors.groupingBy(num -> num,
                        Collectors.counting()))
                .forEach((num, count) -> {
                    printResults(count, NUM_ROLLS, 6);
                });
    }

    public void printResults (Long count, int rolls, int diceRange) {
        if (rolls ==4) {
            System.out.println("~~~ 1* GAME ~~~");
        } else {
            System.out.println("~~~ 2* GAME ~~~");
        }

        System.out.println("Total $ won: " + count + " , Mean: " + getMean(diceRange) + " , Variance: " +getVariance(diceRange)+ " , Standard deviation: " +getStdDev(diceRange));

    }

    double getMean(int diceRange) {
        double sum = 0.0;
        for(double a : firstDiceResultsList)
            sum += a;
        return sum/diceRange;
    }

    double getVariance(int diceRange) {
        double mean = getMean(diceRange);
        double temp = 0;
        for(double a :firstDiceResultsList)
            temp += (a-mean)*(a-mean);
        return temp/(diceRange-1);
    }

    double getStdDev(int diceRange) {
        return Math.sqrt(getVariance(diceRange));
    }


}
