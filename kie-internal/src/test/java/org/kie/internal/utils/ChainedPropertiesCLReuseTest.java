package org.kie.internal.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.Assert;
import org.junit.Test;

public class ChainedPropertiesCLReuseTest {

    @Test
    public void testClassLoaderReuse() {
    	TestClassLoader classLoader = new TestClassLoader(getClass().getClassLoader());
    	for (int index = 0; index < 100; index++) {
    		ChainedProperties.getChainedProperties(classLoader);
    	}
    	Assert.assertEquals(2, classLoader.getResourceCallCount());
    }

    public static class TestClassLoader extends ClassLoader {

    	private int getResourcesCallsCount = 0;

    	public TestClassLoader() {
    		super();
    	}

    	public TestClassLoader(ClassLoader parent) {
    		super(parent);
    	}

    	@Override
    	public Enumeration<URL> getResources(String name) throws IOException {
    		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
    		boolean inChainedPropertiesPath = false;
    		for (StackTraceElement trace : traces) {
    			if (trace.getClassName().equals(ChainedProperties.class.getName())) {
    				inChainedPropertiesPath = true;
    				break;
    			}
    		}
    		if (inChainedPropertiesPath) {
    			getResourcesCallsCount++;
    		}
    		return super.getResources(name);
    	}

    	public int getResourceCallCount() {
    		return getResourcesCallsCount;
    	}
    }

}
