package com.reaction.topic.util;

public class NumberUtil {

    private NumberUtil() {
        super();
    }

    public static Long calculatePercentage(Long total, Long activity) {
        try {
            if (total == null || activity == null || total == 0) {
                return 0L;
            }

            double percentage = ((double) activity / total) * 100;
            return Math.round(Math.ceil(percentage));
        } catch (Exception e) {
            return 0L;
        }
    }
}
