package org.kie.api.pmml;


public interface PMMLRuleUnit {

    public ModelInitializer getModelInitializer();

    public ModelApplier getModelApplier();

    public PMMLRuleExecutor getRuleExecutor();
}
