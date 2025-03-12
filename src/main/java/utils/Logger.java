package utils;

import java.text.MessageFormat;

public class Logger {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.class.getName());

    /**
     * Log.
     *
     * @param message the message to be logged.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Log.
     *
     * @param message the message to be logged.
     * @param exception the exception to be logged with the message.
     */
    public static void debug(String message, String exception) {
        logger.info(MessageFormat.format(message, exception));
    }
}

