
package com.demo.truck.views.panel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContractInfoPanel extends JPanel {

	private JTextField contractField;
	private JTextField manageExpenseField;
	private JTextField manageDateField;

	public ContractInfoPanel() {
		initPanel();
	}

	private void initPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		manageDateField = new JTextField(10);
		contractField = new JTextField(10);
		manageExpenseField = new JTextField(10);
		add(new JLabel("管理日期:"));
		add(manageDateField);
		add(new JLabel("合同号："));
		add(contractField);
		add(new JLabel("管理费/年:"));
		add(manageExpenseField);
	}
}
