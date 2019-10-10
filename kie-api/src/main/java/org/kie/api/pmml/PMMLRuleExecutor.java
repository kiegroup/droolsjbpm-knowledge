package org.kie.api.pmml;

import org.kie.api.runtime.rule.RuleUnitExecutor;

public interface PMMLRuleExecutor {

    public int executeRules(RuleUnitExecutor executor, PMMLRuleUnit ruleUnit);
}
