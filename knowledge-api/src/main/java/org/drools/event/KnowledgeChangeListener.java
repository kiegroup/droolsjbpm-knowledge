package org.drools.event;
/* Interface to be implemented by Class containing KnowledgeBase*/
public interface KnowledgeChangeListener {

	public void knowledgeUpdated(KnowledgeEvent e);

}
