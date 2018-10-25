package io.github.joyoungc.common;

import org.springframework.http.HttpStatus;

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

}
