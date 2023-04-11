package com.micaelops.livebrief2.game;

import java.util.Arrays;

/**
 * Question bank.
 *
 * By design, we would have to fetch our questions from an online
 * source. However since we don't want to overcomplicate the project
 * we developed a set questions
 *
 * Every question has a correct answer and has a required progress from the child
 * for it to be used.
 */
public enum Questions {

    CLOUDS("What are clouds made of?", "water liquid", 0),
    SKY_COLOR("Why is the sky blue?", "sun radiation", 0),
    OREO("If you had to measure your height in Oreos, how many Oreos tall do you think you would be?", "7", 100);

    final String questionText, answer;
    final long progressRequired;

    Questions(String questionText, String answer, long progressRequired) {
        this.questionText = questionText;
        this.answer = answer;
        this.progressRequired = progressRequired;
    }

    /**
     * Gets the progress required for the question
     * @return long value
     */

    public long getProgressRequired() {
        return progressRequired;
    }

    /**
     * Gets the answer of the question
     * @return String text
     */

    public String getAnswer() {
        return answer;
    }

    /**
     * Gets the actual question
     * @return String text
     */

    public String getQuestionText() {
        return questionText;
    }

    /**
     * Gets a random question with less or equal
     * progress provided
     * @param progress long value of minimum progress required
     * @return Question if exists or else null
     */

    public static Questions getQuestionByProgress(long progress) {
        return Arrays.stream(values()).filter(question -> question.getProgressRequired() <= progress).findAny().orElse(null);
    }
}
