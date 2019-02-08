package io.github.joyoungc.batch.config.client;

/**
 * @author joyoungc
 * @date 2018.06.25
 */
public interface RestClient<I, O> {
    public O connection(I item, String url);
}
