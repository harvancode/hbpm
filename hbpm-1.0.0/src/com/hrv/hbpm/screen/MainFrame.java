/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on Aug 6, 2015, 6:55:51 PM
 */
package com.hrv.hbpm.screen;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

/**
 * 
 * @author User
 */
public class MainFrame extends javax.swing.JFrame {

	private mxGraph graph = new mxGraph();
	private Object parent = graph.getDefaultParent();

	public MainFrame() {
		initComponents();
		initComponents2();
	}

	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		JSplitPane pane = new JSplitPane();
		pane.setDividerSize(2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(pane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(pane, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
		);
		
		JSplitPane leftPane = new JSplitPane();
		leftPane.setPreferredSize(new Dimension(250, 28));
		leftPane.setDividerSize(2);
		leftPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
//		leftPane.setDividerLocation(100);
//		leftPane.setDividerLocation(0.5);
		leftPane.setResizeWeight(0.5); 
		
		pane.setLeftComponent(leftPane);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		leftPane.setRightComponent(scrollPane_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		leftPane.setLeftComponent(scrollPane_1);
		
		JPanel panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);
		jButton1 = new javax.swing.JButton();
		jButton1.setBounds(10, 62, 95, 25);
		panel_2.add(jButton1);
		jButton1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/middleware/start.gif")));
		
				jButton1.setText("start");
				jButton1.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButton1ActionPerformed(evt);
					}
				});
				
						jToggleButton1 = new javax.swing.JToggleButton();
						jToggleButton1.setBounds(10, 11, 95, 25);
						panel_2.add(jToggleButton1);
						jToggleButton1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/middleware/start.gif")));
						
								jToggleButton1.setText("start");
								jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
									public void actionPerformed(java.awt.event.ActionEvent evt) {
										jToggleButton1ActionPerformed(evt);
									}
								});
		
		JSplitPane rightPane = new JSplitPane();
		rightPane.setResizeWeight(1.0);
		rightPane.setDividerSize(3);
		rightPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pane.setRightComponent(rightPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(3);
		splitPane.setResizeWeight(1.0);
		rightPane.setLeftComponent(splitPane);
						
						JScrollPane paletteScrollPane = new JScrollPane();
						paletteScrollPane.setPreferredSize(new Dimension(200, 2));
						splitPane.setRightComponent(paletteScrollPane);
						mxGraphComponent graphComponent = new mxGraphComponent(graph);
						splitPane.setLeftComponent(graphComponent);
												
												JScrollPane scrollPane = new JScrollPane();
												scrollPane.setPreferredSize(new Dimension(2, 150));
												rightPane.setRightComponent(scrollPane);
												
														JPanel panel = new JPanel();
														scrollPane.setViewportView(panel);
														panel.setMinimumSize(new Dimension(0, 0));
														panel.setBorder(new TitledBorder(null, "Properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
														panel.setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().setLayout(layout);
		setPreferredSize(new Dimension(600, 500));
		pack();
	}

	public void initComponents2() {
		graph.getModel().beginUpdate();
	}

	private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton1ActionPerformed
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		try {
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
			graph.insertEdge(parent, null, null, v1, v2);
		} finally {
			graph.getModel().endUpdate();
		}
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JToggleButton jToggleButton1;
}
