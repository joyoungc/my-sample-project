package io.github.joyoungc.common.model.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

public class CommonMapper {

	/**
	 * 지정된 type의 Object로 반환
	 * @param source
	 * @param destinationType type to map to
	 * @return
	 */
	public static <T> T toModel(Object src, Class<T> type) {
		return src != null ? new ModelMapper().map(src, type) : null;
	}
	
	/**
	 * 지정된 type의 List로 반환
	 * @param list
	 * @param target
	 * @return
	 */
	public static <T> List<T> toList(Object list, Class<T> type) {
		return list != null ? new ModelMapper().map(list, new TypeToken<List<T>>() {}.getType()) : null;
	}
	
	/**
	 * src Object를 dest Object에 업데이트 한다.
	 * @param src
	 * @param dest
	 */
	public static void updateModel(Object src, Object dest) {
		new ModelMapper().map(src, dest);
	}

}
