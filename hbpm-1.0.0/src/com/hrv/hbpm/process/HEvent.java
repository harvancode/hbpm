package com.hrv.hbpm.process;

import java.sql.Clob;

public interface HEvent {
	public void setOnEnterScript(Clob onEnterScript);

	public void onEnter();

	public void setOnLeaveScript(Clob onLeaveScript);

	public void onLeave();
}