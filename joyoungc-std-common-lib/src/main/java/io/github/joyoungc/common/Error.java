package io.github.joyoungc.common;

public enum Error {
	
	INTERNAL_SERVER_ERROR(0, "서버 에러가 발생하였습니다."), 
	NO_DATA_FOUND(1, "대상 데이터가 없습니다."),
	BAD_REQUEST(2, "파라미터가 유효하지 않습니다.");

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
