package com.tank.stream;

import lombok.val;

/**
 * @author tank198435163.com
 */
public class App {
    public static void main(final String[] args) {
        val wordCounter = new WordCounter("hello", "welcome", "jack", "to", "to");
        wordCounter.count();
    }
}
