package org.kie.api.pmml;

import java.util.List;

import org.kie.api.KieBase;

public interface ModelApplier {

    public List<PMML4Result> applyModel(PMMLRequestData request, KieBase kbase, PMMLRuleUnit ruleUnit);

}
