package com.persagy.yc.common.context;

/**
 * 默认的appContext工厂
 * @author Charlie Yu
 * @version 1.0 2021-03-04
 */
public class DefaultAppContextFactory implements IAppContextFactory {

    /** 线程级的上下文 */
    private ThreadLocal<AppContext> context = new ThreadLocal<>();

    @Override
    public AppContext getContext() {
        AppContext c = context.get();
        if(c == null){
            c = new DefaultAppContext();
            context.set(c);
        }
        return c;
    }

    @Override
    public void unloadContext() {
        context.remove();
    }
}
