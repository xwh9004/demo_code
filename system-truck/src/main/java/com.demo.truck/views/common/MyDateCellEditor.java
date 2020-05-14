
package com.demo.truck.views.common;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;

public class MyDateCellEditor extends DefaultCellEditor {
	private DatePicker date;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2705510251977238075L;

	public MyDateCellEditor() {
		super(new JCheckBox());
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
			int column) {
		if (column == 1) {
			JPanel panel = new JPanel();
			date = new DatePicker(10);
			panel.add(date);
			return panel;
		}
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);

	}

	@Override
	public Object getCellEditorValue() {
		return date.getDate();
	}
}
