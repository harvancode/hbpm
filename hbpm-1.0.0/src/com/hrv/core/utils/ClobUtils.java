package com.hrv.core.utils;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobUtils {
	public static String convertClobToString(Clob clob) throws IOException, SQLException {
		Reader reader = clob.getCharacterStream();
		StringBuilder sb = new StringBuilder();
		int c = -1;

		while ((c = reader.read()) != -1) {
			sb.append(((char) c));
		}

		return sb.toString();
	}
}