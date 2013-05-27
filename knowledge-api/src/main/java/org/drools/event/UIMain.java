package org.drools.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JPanel;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

public class UIMain extends JPanel implements ActionListener {
	static int counter = 0;
	JButton jbAdd;
	JButton jbBrowse;

	KnowledgeBuilder kb;

	UIMain() {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		knowledgeListeners = new LinkedList<KnowledgeChangeListener>();
		this.setLayout(null);

		jbAdd = new JButton("Update Knowledge Base everywhere");
		jbAdd.setBounds(20, 20, 300, 100);
		jbAdd.addActionListener(this);
		jbAdd.setVisible(true);
		this.add(jbAdd);

		jbBrowse = new JButton("Browse");
		jbBrowse.setBounds(350, 20, 100, 20);
		jbBrowse.addActionListener(this);
		jbBrowse.setVisible(true);
		this.add(jbBrowse);

		// System.out.print("Panel Created");
	}

	LinkedList<KnowledgeChangeListener> knowledgeListeners;

	public boolean removeKnowledgeListeners(
			KnowledgeChangeListener knowledgeListen) {
		return this.knowledgeListeners.remove(knowledgeListen);
	}

	public void addKnowledgeListeners(KnowledgeChangeListener knowledgeListen) {
		this.knowledgeListeners.add(knowledgeListen);
	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == jbBrowse) {
			JFileChooser choice = new JFileChooser();
			int option = choice.showOpenDialog(this);

			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					kb.add(ResourceFactory.newFileResource(choice
							.getSelectedFile().getPath()), ResourceType.DRL);
					if (kb.hasErrors()) {
						JOptionPane.showMessageDialog(this, kb.getErrors());
					} else {
						JOptionPane.showMessageDialog(this,
								"Successfully Compiled");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (ae.getSource() == jbAdd) {

			counter++;
			for (int i = 0; i < this.knowledgeListeners.size(); i++) {
				this.knowledgeListeners.get(i).knowledgeUpdated(
						new KnowledgeEvent(kb.getKnowledgePackages()));
			}
			System.out.println(counter);
		}
	}

}
