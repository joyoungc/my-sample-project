package io.github.joyoungc.batch.config;

/**
 * @author joyoungc
 * @date 2018.06.20
 */
public final class Constants {

    private Constants() {
    }

    ;

    public static final String JOB_TASK_EXECUTOR = "jobExecutor";

    public static final String STEP_TASK_EXECUTOR = "stepExecutor";

    public static final String ASYNC = "async";

    public static final String SYNC = "sync";

    public enum TransType {
        ASYNC(Constants.ASYNC), SYNC(Constants.SYNC);

        TransType(String type) {
        }
    }

}
