package kr.infotake.cmm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.text.StringEscapeUtils;

/**
 * XSSRequestWrapper
 * HttpServletRequestWrapper를 상속받아 사용자 입력값을 필터링하고 
 * HTML 특수 문자를 이스케이프 처리하여 XSS(크로스 사이트 스크립팅) 공격을 방지하는 클래스.
 * getParameter, getParameterValues, getHeader 등의 메서드를 오버라이드하여 
 * 입력 데이터를 안전하게 처리한 후 사용되도록 보장합니다.
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 기본 생성자.
     * 전달받은 HttpServletRequest 객체를 래핑합니다.
     * @param request 사용자의 요청 객체
     */
    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 요청 파라미터 값을 가져옵니다.
     * 가져온 값을 sanitize 메서드로 필터링한 후 반환합니다.
     * @param name 요청 파라미터 이름
     * @return 필터링된 요청 파라미터 값
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return sanitize(value);
    }

    /**
     * 요청 파라미터 배열 값을 가져옵니다.
     * 배열 내 모든 값을 sanitize 메서드로 필터링한 후 반환합니다.
     * @param name 요청 파라미터 이름
     * @return 필터링된 요청 파라미터 값 배열
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                values[i] = sanitize(values[i]);
            }
        }
        return values;
    }

    /**
     * 요청 헤더 값을 가져옵니다.
     * 가져온 값을 sanitize 메서드로 필터링한 후 반환합니다.
     * @param name 요청 헤더 이름
     * @return 필터링된 요청 헤더 값
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return sanitize(value);
    }

    /**
     * 입력값을 필터링하여 XSS 공격을 방지하는 메서드.
     * HTML 특수 문자를 이스케이프 처리합니다. 예시) &lt;&gt; - 원하지않으면 해당메소드를 받은그대로 리턴 합니다.
     * @param value 입력값
     * @return 필터링된 문자열
     */
    private String sanitize(String value) {
        return value == null ? null : StringEscapeUtils.escapeHtml4(value);
        //return value;
    }
}
