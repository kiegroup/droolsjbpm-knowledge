package org.kie.internal.fluent;

import org.kie.api.runtime.Context;

import java.util.Map;

public interface RequestContext extends Context {
    long getRequestId();

    long getConversationId();

    Map<String, Object> getOut();

    Object getResult();
}
