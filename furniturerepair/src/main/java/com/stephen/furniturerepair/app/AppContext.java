package com.stephen.furniturerepair.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

public class AppContext extends ContextWrapper {

	public Resources 		mAPPResources 		= null;
	public ClassLoader 		mAPPClassLoader		= null;

	public AppContext(Context baseContext){
		super(baseContext);
		mAPPResources = baseContext.getApplicationContext().getResources();
		mAPPClassLoader = baseContext.getClassLoader();
	}
	
	@Override
	public Resources getResources() {
		return mAPPResources;
	}
	
	@Override
	public ClassLoader getClassLoader() {
		return mAPPClassLoader;
	}
}
