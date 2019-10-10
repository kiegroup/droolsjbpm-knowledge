package org.kie.api.pmml;

import org.kie.api.KieBase;

public interface ModelApplier {

    public PMML4Result applyModel(PMMLRequestData request, KieBase kbase, PMMLRuleUnit ruleUnit);

}
