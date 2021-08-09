package com.game.rolldice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class RollDiceApplication {

	static RollDiceService rollDiceService= new RollDiceService();

	public static void main(String[] args) {
		SpringApplication.run(RollDiceApplication.class, args);

		final int NUM_ROLLS_4_DIES = 4;
		final int NUM_ROLLS_24_DIES = 24;

		IntStream.range(1, 1000000).forEach(
				result -> rollDiceService.calculateAverageOneDice(NUM_ROLLS_4_DIES)
		);

		IntStream.range(1, 1000000).forEach(
				result -> rollDiceService.calculateAverageOneDice(NUM_ROLLS_24_DIES)
		);
	}

}
