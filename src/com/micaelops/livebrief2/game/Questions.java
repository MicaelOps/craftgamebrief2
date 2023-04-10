package com.micaelops.livebrief2.game;

import java.util.Arrays;

/**
 * Question bank.
 *
 * By design, we would have to fetch our questions from an online
 * source. However since we don't want to overcomplicate the project
 * we have a set of pre-defined questions
 *
 * Every question has a correct answer and has a required progress
 * for it to be used.
 */
public enum Questions {

    CLOUDS("What are clouds made of?", "water gas", 0),
    SKY_COLOR("Why is the sky blue?", "sun radiation", 0),
    OREO("If you had to measure your height in Oreos, how many Oreos tall do you think you would be?", "7", 100);

    final String questionText, answer;
    final long progressRequired;

    Questions(String questionText, String answer, long progressRequired) {
        this.questionText = questionText;
        this.answer = answer;
        this.progressRequired = progressRequired;
    }

    public long getProgressRequired() {
        return progressRequired;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public static Questions getQuestionByProgress(long progress) {
        return Arrays.stream(values()).filter(question -> question.getProgressRequired() <= progress).findAny().orElse(null);
    }
}
