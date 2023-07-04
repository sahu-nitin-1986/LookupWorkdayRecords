package com.pkg.service;

import intradoc.data.DataException;
import intradoc.data.DataResultSet;
import intradoc.data.Workspace;
import intradoc.provider.Provider;
import intradoc.provider.Providers;
import intradoc.server.ServiceHandler;

public class LookupWorkdayRecordsService extends ServiceHandler {

	public void lookupData() throws DataException {

		String providerName = m_currentAction.getParamAt(0);
		String queryName = m_currentAction.getParamAt(1);
		String returnRsName = m_currentAction.getParamAt(2);
		
		Workspace ws = getProviderWorkspace(providerName);
		DataResultSet drs = new DataResultSet();
		drs.copy(ws.createResultSet(queryName, m_binder));
		
		this.m_binder.setContentType("text/plain");
		m_binder.addResultSet(returnRsName, drs);

	}

	private Workspace getProviderWorkspace(String provider) {
		Workspace workspace = null;
		Provider wsProvider = Providers.getProvider(provider);
		if (wsProvider != null) {
			workspace = (Workspace) wsProvider.getProvider();
		}
		return workspace;
	}

}
