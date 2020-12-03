package org.kie.internal.pmml;

import org.junit.Test;
import org.kie.api.pmml.PMMLConstants;

import static org.junit.Assert.assertEquals;
import static org.kie.api.pmml.PMMLConstants.LEGACY;
import static org.kie.api.pmml.PMMLConstants.NEW;

public class PMMLImplementationsUtilTest {

    @Test
    public void testReturnImplementationLegacyPresent() {
        PMMLConstants retrieved = PMMLImplementationsUtil.returnImplementation(LEGACY, true);
        assertEquals(LEGACY, retrieved);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReturnImplementationLegacyNotPresent() {
        PMMLImplementationsUtil.returnImplementation(LEGACY, false);
    }

    @Test
    public void testReturnImplementationNewPresent() {
        PMMLConstants retrieved = PMMLImplementationsUtil.returnImplementation(NEW, true);
        assertEquals(NEW, retrieved);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReturnImplementationNewNotPresent() {
        PMMLImplementationsUtil.returnImplementation(NEW, false);
    }

}