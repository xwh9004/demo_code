
package com.demo.truck.views.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.demo.truck.common.util.DateUtils;
import com.demo.truck.common.util.Util;
import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.CarValidateRecord;
import com.demo.truck.exception.ServiceException;
import com.demo.truck.remote.CarInfoService;
import com.demo.truck.views.common.MyDateCellEditor;
import com.demo.truck.views.panel.CarInfoPanel;

public class CarValidateFrame extends MyFrame {

	private CarInfoPanel carInfoPanel;

	private JTable validateRecordTable;

	private List<CarValidateRecord> recordList = new ArrayList<>();

	private int[] selectRows;

	private CarInfoService carInfoService;

	private CarInfo carInfo;

	public void setCarInfoService(CarInfoService carInfoService) {
		this.carInfoService = carInfoService;
	}

	private AbstractTableModel valideRecordTableModel = new AbstractTableModel() {

		private String[] columnNames = { "序号", "验车日期", "验车类型", "备注" };

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			if (recordList == null || recordList.size() == 0) {
				return null;
			}
			switch (columnIndex) {
			case 0:
				value = rowIndex + 1;
				break;
			case 1:
				value = DateUtils.format(recordList.get(rowIndex).getValidateTime(), "yyyy-MM-dd");
				break;
			case 2:
				value = Util.getValideType(recordList.get(rowIndex).getValidateType());
				break;
			case 3:
				value = recordList.get(rowIndex).getRemark();
				break;
			default:
				break;
			}
			return value;
		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 1:
				if (aValue instanceof Date) {
					Date validateTime = (Date) aValue;
					recordList.get(rowIndex).setValidateTime(validateTime);
				}

				break;
			case 2:
				if (aValue instanceof Integer) {
					Integer type = (Integer) aValue;
					recordList.get(rowIndex).setValidateType(type);
				}
				break;
			case 3:
				if (aValue instanceof String) {
					String remark = (String) aValue;
					recordList.get(rowIndex).setRemark(remark);
				}
				break;

			default:
				break;
			}

		};

		@Override
		public int getRowCount() {
			if (recordList == null) {
				return 0;
			}
			return recordList.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		};

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
				return false;
			}
			return true;
		};

	};

	public CarValidateFrame() {
		super(600, 600);
		initFrame();
	}

	private void initFrame() {
		Dimension demesion = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(demesion.width/2-300,demesion.height/2-300,600, 600);
		carInfoPanel = new CarInfoPanel();
		carInfoPanel.setEditable(false);
		add(carInfoPanel, BorderLayout.NORTH);
		JPanel listPanel = new JPanel(new BorderLayout());
		validateRecordTable = new JTable(valideRecordTableModel);
		JScrollPane validateRdPanel = new JScrollPane(validateRecordTable);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		validateRecordTable.getColumnModel().getColumn(0).setCellRenderer(render);
		validateRecordTable.getColumnModel().getColumn(1).setCellEditor(new MyDateCellEditor());
		validateRecordTable.getColumnModel().getColumn(2).setCellEditor(new MyRadioCellEditor());
		validateRecordTable.setRowHeight(30);
		validateRecordTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel(" 验车记录:"));
		JButton addBtn = new JButton("添加");
		JButton delBtn = new JButton("删除");
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				recordList.add(new CarValidateRecord());
				validateRecordTable.updateUI();
			}
		});
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectRows = validateRecordTable.getSelectedRows();
				if (selectRows != null && selectRows.length > 0) {
					for (int i = selectRows.length - 1; i >= 0; i--) {
						recordList.remove(selectRows[i]);
					}
					validateRecordTable.updateUI();
				} else {
					JOptionPane.showMessageDialog(CarValidateFrame.this, "请选择要删除的记录");
				}

			}
		});
		northPanel.add(addBtn);
		northPanel.add(delBtn);
		listPanel.add(northPanel, BorderLayout.NORTH);
		listPanel.add(validateRdPanel);
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton comfireBtn = new JButton("确定");
		comfireBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						try {
							carInfoService.addCarValidateRecordList(recordList, carInfo.getId());
							JOptionPane.showMessageDialog(CarValidateFrame.this, "添加成功！");
						} catch (ServiceException e) {
							JOptionPane.showMessageDialog(CarValidateFrame.this, e.getMessage());
						}
					};
				}.start();
			}
		});

		JButton cancelBtn = new JButton("取消");
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CarValidateFrame.this.dispose();
			}
		});
		btnPanel.add(cancelBtn);
		btnPanel.add(comfireBtn);
		add(listPanel);
		add(btnPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
		carInfoPanel.setCarInfo(carInfo);
	}

	class MyRadioCellEditor extends DefaultCellEditor {
		private ButtonGroup group;
		private JRadioButton button_1;
		private JRadioButton button_2;

		/**
		 * 
		 */
		private static final long serialVersionUID = 2705510251977238075L;

		public MyRadioCellEditor() {
			super(new JCheckBox());
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (column == 2) {
				JPanel panel = new JPanel();
				group = new ButtonGroup();
				button_1 = new JRadioButton("大验");
				button_2 = new JRadioButton("二维");
				group.add(button_1);
				group.add(button_2);
				panel.add(button_1);
				panel.add(button_2);
				return panel;
			}
			return super.getTableCellEditorComponent(table, value, isSelected, row, column);
		}

		@Override
		public Object getCellEditorValue() {
			if (button_1.isSelected()) {
				return 1;
			} else {
				return 2;
			}

		}
	}
}
