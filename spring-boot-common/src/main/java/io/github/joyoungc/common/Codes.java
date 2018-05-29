package io.github.joyoungc.common;

public final class Codes {

	public enum Code {
		
		USER_ID(Constants.USER_ID, Constants.USER_ID_POS),
		USER_NAME(Constants.USER_NAME, Constants.USER_NAME_POS),
		USER_NUMBER(Constants.USER_NUMBER, Constants.USER_NUMBER_POS),
		BIRTHDAY(Constants.BIRTHDAY, Constants.BIRTHDAY_POS),

		PRODUCT_ID(Constants.PRODUCT_ID, Constants.PRODUCT_ID_POS), 
		PRODUCT_NAME(Constants.PRODUCT_NAME, Constants.PRODUCT_NAME_POS), 
		PRICE(Constants.PRICE, Constants.PRICE_POS);

		private String description;
		private int position;

		Code(String desc, int pos) {
			this.description = desc;
			this.position = pos;
		}

		public String getDescription() {
			return description;
		}

		public int getPosition() {
			return position;
		}

	}

	public enum Error {

		INTERNAL_SERVER_ERROR(0, "서버 에러가 발생하였습니다."), 
		NO_DATA_FOUND(1, "대상 데이터가 없습니다."), 
		BAD_REQUEST(2, "파라미터가 유효하지 않습니다."), 
		METHOD_NOT_ALLOWED(3, "유효하지 않는 API 요청입니다.");

		private final int code;
		private final String description;

		private Error(int code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public int getCode() {
			return code;
		}

		@Override
		public String toString() {
			return code + ": " + description;
		}

	}

}
