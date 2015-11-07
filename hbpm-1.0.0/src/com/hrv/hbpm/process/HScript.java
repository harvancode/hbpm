package com.hrv.hbpm.process;

import java.sql.Clob;

public interface HScript {
	public void setScript(Clob script);

	public Clob getScript();
}