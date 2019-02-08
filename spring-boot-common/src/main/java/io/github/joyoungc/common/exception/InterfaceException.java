package io.github.joyoungc.common.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterfaceException extends BaseException {

    private static final long serialVersionUID = 1L;

    public InterfaceException(final String exceptionCode) {
        super(exceptionCode);
    }

    /**
     * @param exceptionCode 예외 유형을 정의하는 코드.
     * @param cause         최초 발생한 예외. 해당 예외는 기본 예외로 전환된다.
     */
    public InterfaceException(final String exceptionCode, final Throwable cause) {
        super(exceptionCode, cause, (String[]) null);
    }

    /**
     * @param exceptionCode 예외 유형을 정의하는 코드.
     * @param messageArgs   예외 메시지 변환에 사용되는 메시지 파라미터.
     */
    public InterfaceException(final String exceptionCode, final String... messageArgs) {
        super(exceptionCode, null, messageArgs);
    }

    private String orgExceptionCode;
    private String orgMessage;
    private String interfaceType;
    private Object recvHeader;
    private List<Map<String, String>> errorStacks = new ArrayList<>();

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(final String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getOrgExceptionCode() {
        return orgExceptionCode;
    }

    public void setOrgExceptionCode(final String orgExceptionCode) {
        this.orgExceptionCode = orgExceptionCode;
    }

    public String getOrgMessage() {
        return orgMessage;
    }

    public void setOrgMessage(final String orgMessage) {
        this.orgMessage = orgMessage;
    }

    public List<Map<String, String>> getErrorStacks() {
        return errorStacks;
    }

    public void setErrorStacks(final List<Map<String, String>> errorStacks) {
        this.errorStacks = errorStacks;
    }

    public Object getRecvHeader() {
        return recvHeader;
    }

    public void setRecvHeader(final Object recvHeader) {
        this.recvHeader = recvHeader;
    }

}
