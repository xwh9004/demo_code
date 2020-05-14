
package com.demo.truck.views.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.demo.truck.common.util.DateUtils;
import org.apache.ibatis.annotations.Case;

import com.demo.truck.bean.CarValidateRecordBean;
import com.demo.truck.common.util.DateUtils;
import com.demo.truck.entity.CarCompesationRecord;
import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.CarValidateRecord;
import com.demo.truck.exception.ServiceException;
import com.demo.truck.remote.CarInfoService;
import com.demo.truck.views.common.MyDateCellEditor;
import com.demo.truck.views.panel.CarInfoPanel;

public class CarCompesationFrame extends com.demo.truck.views.frame.MyFrame {

	private CarInfoPanel carInfoPanel;

	private JTable compesationRecordTable;

	private List<CarCompesationRecord> recordList = new ArrayList<>();
	
	private CarInfo carInfo;
	
	private CarInfoService carInfoService;
	
	private int[] selectRows;

	
	

	public void setCarInfoService(CarInfoService carInfoService) {
		this.carInfoService = carInfoService;
	}

	private AbstractTableModel compesationRecordTableModel = new AbstractTableModel() {

		private String[] columnNames = { "序号", "出险日期", "理赔负责人", "理赔金额", "备注" };

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			if (recordList == null&&recordList.size()==0) {
				return null;
			}
			switch (columnIndex) {
			case 0:
				value = rowIndex + 1;
				break;
			case 1:
				value = DateUtils.format(recordList.get(rowIndex).getClaimTime(),"yyyy-MM-dd");
				break;
			case 2:
				value = recordList.get(rowIndex).getClaimAdjuster();
				break;
			case 3:
				value = recordList.get(rowIndex).getClaimAmount();
				break;
			case 4:
				value = recordList.get(rowIndex).getRemark();
				break;
			default:
				break;
			}
			return value;
		}

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
		
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 1:
				if (aValue instanceof Date) {
					Date claimDate = (Date) aValue;
					recordList.get(rowIndex).setClaimTime(claimDate);
				}

				break;
			case 2:
				if (aValue instanceof String) {
					String claimAdjuster = (String) aValue;
					recordList.get(rowIndex).setClaimAdjuster(claimAdjuster);
				}
				break;
			case 3:
				if (aValue instanceof String) {
					String value = (String) aValue;
					double claimAmount = Double.parseDouble(value);
					recordList.get(rowIndex).setClaimAmount(claimAmount);
				}
				break;
			case 4:
				if (aValue instanceof String) {
					String remark = (String) aValue;
					recordList.get(rowIndex).setRemark(remark);
				}
				break;
			default:
				break;
			}
		};
	};
	public CarCompesationFrame() {
		super(600, 600);
		initFrame();
	}

	private void initFrame() {
		carInfoPanel = new CarInfoPanel();
		carInfoPanel.setEditable(false);
		add(carInfoPanel, BorderLayout.NORTH);
		JPanel listPanel = new JPanel(new BorderLayout());
		compesationRecordTable = new JTable(compesationRecordTableModel);
		JScrollPane validateRdPanel = new JScrollPane(compesationRecordTable);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		compesationRecordTable.getColumnModel().getColumn(0).setCellRenderer(render);
		compesationRecordTable.getColumnModel().getColumn(1).setCellEditor(new MyDateCellEditor());
		compesationRecordTable.setRowHeight(30);
		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel(" 出险记录:"));
		JButton addBtn = new JButton("添加");
		JButton delBtn = new JButton("删除");
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				recordList.add(new CarCompesationRecord());
				compesationRecordTable.updateUI();
			}
		});
		delBtn.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectRows = compesationRecordTable.getSelectedRows();
				if (selectRows != null && selectRows.length > 0) {
					for (int i = selectRows.length - 1; i >= 0; i--) {
						recordList.remove(selectRows[i]);
					}
					compesationRecordTable.updateUI();
				} else {
					JOptionPane.showMessageDialog(CarCompesationFrame.this, "请选择要删除的记录");
				}

			}
		});
		northPanel.add(addBtn);
		northPanel.add(delBtn);
		listPanel.add(northPanel, BorderLayout.NORTH);
		listPanel.add(validateRdPanel);
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton comfireBtn = new JButton("确定");
		JButton cancelBtn = new JButton("取消");
		comfireBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						    try {
								carInfoService.addCarCompesationRecordList(recordList, carInfo.getId());
								JOptionPane.showMessageDialog(CarCompesationFrame.this, "添加成功！");
							} catch (ServiceException e) {
								JOptionPane.showMessageDialog(CarCompesationFrame.this, e.getMessage());
							}
							
							
					};
				}.start();		
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CarCompesationFrame.this.dispose();				
			}
		});
		btnPanel.add(cancelBtn);
		btnPanel.add(comfireBtn);
		add(listPanel);
		add(btnPanel,BorderLayout.SOUTH);
		setVisible(true);
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
		carInfoPanel.setCarInfo(carInfo);
	}
	
	
}
