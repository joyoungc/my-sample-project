package io.github.joyoungc.common;

public final class Constants {

    private Constants() {
    }

    ;

    /**
     * User
     */
    public static final String USER = "사용자";

    public static final String API_TAG_USER = USER + " API";

    public static final String USER_ID = USER + "아이디";
    public static final int USER_ID_POS = 1;

    public static final String USER_NAME = USER + "이름";
    public static final int USER_NAME_POS = 2;

    public static final String USER_NUMBER = USER + "번호";
    public static final int USER_NUMBER_POS = 3;

    public static final String BIRTHDAY = "생년월일(yyyyMMdd)";
    public static final int BIRTHDAY_POS = 4;

    /**
     * Product
     */
    public static final String PRODUCT = "상품";

    public static final String API_TAG_PRODUCT = PRODUCT + " API";

    public static final String PRODUCT_ID = PRODUCT + "아이디";
    public static final int PRODUCT_ID_POS = 1;

    public static final String PRODUCT_NAME = PRODUCT + "명";
    public static final int PRODUCT_NAME_POS = 2;

    public static final String PRICE = "가격";
    public static final int PRICE_POS = 3;

    public static final String PRODUCT_DESC = PRODUCT + "설명";
    public static final int PRODUCT_DESC_POS = 4;

}
