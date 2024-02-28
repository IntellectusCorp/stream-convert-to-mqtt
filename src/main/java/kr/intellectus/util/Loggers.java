//  https://github.com/reactor/reactor-core/blob/main/reactor-core/src/main/java/reactor/util/Loggers.java

package kr.intellectus.util;

import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.Function;

public abstract class Loggers {

    private static Function<String, ? extends Logger> LOGGER_FACTORY;

    static {
        resetLoggerFactory();
    }

    public static void resetLoggerFactory() {
        try {
            String name = Loggers.class.getName();
            Function<String, Logger> loggerFactory = new ConsoleLoggerFactory(false);
            LOGGER_FACTORY = loggerFactory;
            loggerFactory.apply(name).info("Using Console Logger");

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static Logger getLogger(String name) {
		return LOGGER_FACTORY.apply(name);
	}

    public static Logger getLogger(Class<?> cls) {
		return LOGGER_FACTORY.apply(cls.getName());
	}

    static final class ConsoleLogger implements Logger {

        private final ConsoleLoggerKey identifier;
        private final PrintStream err;
        private final PrintStream log;

        ConsoleLogger(ConsoleLoggerKey loggerKey, PrintStream logStream, PrintStream errStream) {
            identifier = loggerKey;
            log = logStream;
            err = errStream;
        }

        ConsoleLogger(ConsoleLoggerKey identifier) {
            this(identifier, System.out, System.err);
        }

        @Override
		public String getName() {
			return identifier.name;
		}

        @Override
		public synchronized void info(String msg) {
			this.log.format("[ INFO] (%s) %s\n", Thread.currentThread().getName(), msg);
		}
    }

    private static final class ConsoleLoggerKey {

        private final String name;
        private final boolean verbose;

        private ConsoleLoggerKey(String name, boolean verbose) {
            this.name = name;
            this.verbose = verbose;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ConsoleLoggerKey key = (ConsoleLoggerKey) o;
            return verbose == key.verbose && Objects.equals(name, key.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, verbose);
        }
    }

    static final class ConsoleLoggerFactory implements Function<String, Logger> {

        private static final Map<ConsoleLoggerKey, WeakReference<Logger>> consoleLoggers = new WeakHashMap<>();
        final boolean verbose;

        ConsoleLoggerFactory(boolean verbose) {
            this.verbose = verbose;
        }

        @Override
        public Logger apply(String name) {
            final ConsoleLoggerKey key = new ConsoleLoggerKey(name, verbose);
            synchronized (consoleLoggers) {
                final WeakReference<Logger> ref = consoleLoggers.get(key);
                Logger cached = ref == null ? null : ref.get();
                if (cached == null) {
                    cached = new ConsoleLogger(key);
                    consoleLoggers.put(key, new WeakReference<>(cached));
                }

                return cached;
            }
        }
    }

}
