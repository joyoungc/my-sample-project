package io.github.joyoungc.common.util;

import org.springframework.stereotype.Component;

public class ExceptionUtil {

    static StackTraceElement getBusinessClassStackTraceElement(final Throwable ex, final String basePackageName) {
        // StackTrace에서 업무 package명이 최초 발생한 stackTraceElementd에서 최종 호출한 업무 Class 정보를 추출함
        StackTraceElement lastStackTraceElement = null;

        try {
            StackTraceElement[] se = ex.getStackTrace();

            boolean isFind = false;

            for (int i = se.length - 1; i >= 0; i--) {
                StackTraceElement st = se[i];
                if (st.getClassName().indexOf(basePackageName) != -1) {
                    if (st.getClass().getAnnotation(Component.class) != null) {
                        isFind = true;
                    }
                    lastStackTraceElement = st;
                } else {
                    if (isFind) {
                        return lastStackTraceElement;
                    }
                }
            }

        } catch (Exception ssse) {

        }
        return lastStackTraceElement;
    }

}
