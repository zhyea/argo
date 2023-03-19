package org.chobit.tiger.except;

/**
 * @author robin
 */
public class CalfAdminException extends RuntimeException {

    public CalfAdminException(String msg) {
        super(msg);
    }

    public CalfAdminException(String msg, Throwable t) {
        super(msg, t);
    }

}
