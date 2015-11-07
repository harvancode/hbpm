package com.hrv.core.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Harvan Irsyadi
 * @version 1.0, 10/30/15
 * 
 */
public class ListUtils {
	public static <T> List<List<T>> createSubList(int maxSize, List<T> sourceList) {
		final int listSize = sourceList.size();
		int splitSize = (int) Math.ceil(sourceList.size() / (double) maxSize);
		List<List<T>> newSubList = new ArrayList<List<T>>(splitSize);

		for (int i = 0; i < splitSize; i++) {
			final int from = i * maxSize;
			final int to = Math.min(from + maxSize, listSize);

			newSubList.add(getListByRange(from, to, sourceList));
		}

		return newSubList;
	}

	private static <T> List<T> getListByRange(int start, int end, List<T> sourceList) {
		return sourceList.subList(start, end);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> getSingleTypeList(List sourceList, String methodName, Class<T> valueType) throws Exception {
		Object temp = null;

		if (sourceList != null && !sourceList.isEmpty()) {
			temp = sourceList.get(0);
		}

		if (temp == null) {
			throw new Exception("Parameter list cannot be empty.");
		}

		Method method = temp.getClass().getMethod(methodName, new Class[] {});
		List<T> listId = new ArrayList<T>();

		for (Object obj : sourceList) {
			listId.add((T) method.invoke(obj));
		}

		return listId;
	}
}
