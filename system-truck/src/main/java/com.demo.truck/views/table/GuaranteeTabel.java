
package com.demo.truck.views.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.views.common.DatePicker;

public class GuaranteeTabel extends JPanel {

	private boolean editable = true;

	private GuaranteeInfoDto guaranteeInfoDto;
	
	private String[] columnNames = { "保修年份", "商业险金额(元)", "强制险金额(元)", "车船税(元)", "审证(元)", "合计金额", "备注" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233090928383451295L;

	private JTable detaileTable;

	private DefaultTableModel tableModel = new DefaultTableModel() {
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			if (guaranteeInfoDto == null) {
				return null;
			}
			switch (columnIndex) {
			case 0:
				Date date =commercialInsuranceFrom.getDate();
				if(date!=null){
					value = date.getYear() + 1900;
				}
				
				break;
			case 1:
				value = guaranteeInfoDto.getCommercialInsurancePremium();
				break;
			case 2:
				value = guaranteeInfoDto.getCompulsoryInsurancePremium();
				break;
			case 3:
				value = guaranteeInfoDto.getTravelTax();
				break;
			case 4:
				value = guaranteeInfoDto.getVerifiyCreditCost();
				break;
			case 5:
				value = guaranteeInfoDto.getTotalCost();
				break;
			case 6:
				value = guaranteeInfoDto.getRemark();
				break;
			default:
				break;
			}
			return value;
		}

		@Override
		public int getRowCount() {
			return 1;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		};

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (rowIndex == 0) {
				if (columnIndex == 0 || columnIndex == 5) {
					return false;
				}
			}
			return editable;

		};

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if (guaranteeInfoDto == null) {
				guaranteeInfoDto = new GuaranteeInfoDto();
			}
			String value = null;
			value = (String) aValue;
			switch (columnIndex) {
			case 1:
				if (value != null) {
					guaranteeInfoDto.setCommercialInsurancePremium(Double.valueOf(value));
				}
				break;
			case 2:
				guaranteeInfoDto.setCompulsoryInsurancePremium(Double.valueOf(value));
				break;
			case 3:
				guaranteeInfoDto.setTravelTax(Double.valueOf(value));
				break;
			case 4:
				guaranteeInfoDto.setVerifiyCreditCost(Double.valueOf(value));
				break;
			case 5:
				guaranteeInfoDto.setTotalCost(getTotalCost());
				break;
			case 6:
				guaranteeInfoDto.setRemark(value);
				break;
			default:
				break;
			}
			fireTableCellUpdated(rowIndex, columnIndex);
		};

	};
	private JTextField commercialInsuranceCoverageField;

	private JTextField remartField;

	private JTextField transactorField;

	private JTextField guaranteeNoField;
	
	private DatePicker commercialInsuranceFrom;
	private DatePicker compulsoryInsuranceFrom;
	private DatePicker commercialInsuranceTo;
	private DatePicker compulsoryInsuranceTo;

	public GuaranteeTabel() {
		initJPanel();
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	private void initJPanel() {
		setLayout(new BorderLayout());
		commercialInsuranceCoverageField = new JTextField(10);
		guaranteeNoField = new JTextField(20);
		remartField = new JTextField(10);
		transactorField = new JTextField(20);
		commercialInsuranceFrom = new DatePicker(10);
		commercialInsuranceTo = new DatePicker(10);
		compulsoryInsuranceFrom = new DatePicker(10);
		compulsoryInsuranceTo = new DatePicker(10);
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(new JLabel("保单编号："));
		panel1.add(guaranteeNoField);
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(new JLabel("商业保险日期从："));
		panel2.add(commercialInsuranceFrom);
		panel2.add(new JLabel("  至："));
		panel2.add(commercialInsuranceTo);
		panel2.add(new JLabel("第三责任险金额："));
		panel2.add(commercialInsuranceCoverageField);
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.add(new JLabel("强制保险日期从："));
		panel3.add(compulsoryInsuranceFrom);
		panel3.add(new JLabel("  至："));
		panel3.add(compulsoryInsuranceTo);
		panel3.add(new JLabel("备                     注："));
		panel3.add(remartField);
		JPanel northPanel = new JPanel(new GridLayout(3, 1));
		northPanel.add(panel1);
		northPanel.add(panel2);
		northPanel.add(panel3);
		add(northPanel, BorderLayout.NORTH);
		JPanel detailPanel = new JPanel(new BorderLayout());
		detaileTable = new JTable(tableModel);
		detaileTable.setRowHeight(30);
		tableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				getTotalCost();
			}
		});

		JScrollPane tablePanel = new JScrollPane(detaileTable);
		tablePanel.setPreferredSize(new Dimension(700, 100));
		detailPanel.add(new JLabel(" 保险明细信息"), BorderLayout.NORTH);
		detailPanel.add(tablePanel);
		add(detailPanel);
		JPanel transactorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		transactorPanel.add(new JLabel("办理人："));
		transactorPanel.add(transactorField);
		add(transactorPanel, BorderLayout.SOUTH);

	}

	public GuaranteeInfoDto getGuaranteeInfoDto() {
		if(guaranteeInfoDto==null){
			return null;
		}
		String guaranteeNo=guaranteeNoField.getText();
		if(guaranteeNo==null||"".equals(guaranteeNo)){
			JOptionPane.showMessageDialog(this, "请输入保单号");
			return null;
		}
		guaranteeInfoDto.setGuaranteeNo(guaranteeNoField.getText());
		guaranteeInfoDto.setCommercialInsuranceFrom(commercialInsuranceFrom.getDate());
		guaranteeInfoDto.setCommercialInsuranceTo((Date) commercialInsuranceTo.getDate());
		guaranteeInfoDto.setCompulsoryInsuranceFrom((Date) compulsoryInsuranceFrom.getDate());
		guaranteeInfoDto.setCompulsoryInsuranceTo((Date) compulsoryInsuranceTo.getDate());
		guaranteeInfoDto.setTransactor(transactorField.getText());
		if (commercialInsuranceCoverageField.getText() != null
				&& !"".equals(commercialInsuranceCoverageField.getText())) {
			guaranteeInfoDto.setCommercialInsuranceCoverage(Double.valueOf(commercialInsuranceCoverageField.getText()));
		}
		guaranteeInfoDto.setRemark(remartField.getText());
		return guaranteeInfoDto;
	}

	private Double getTotalCost() {
		Double totalCost = 0.0;
		if (guaranteeInfoDto.getCommercialInsurancePremium() != null) {
			totalCost = guaranteeInfoDto.getCommercialInsurancePremium();
		}
		if (guaranteeInfoDto.getCompulsoryInsurancePremium() != null) {
			totalCost += guaranteeInfoDto.getCompulsoryInsurancePremium();
		}
		if (guaranteeInfoDto.getTravelTax() != null) {
			totalCost += guaranteeInfoDto.getTravelTax();
		}
		if (guaranteeInfoDto.getVerifiyCreditCost() != null) {
			totalCost += guaranteeInfoDto.getVerifiyCreditCost();
		}
		if (totalCost.equals(0.0)) {
			totalCost = null;
		}
		guaranteeInfoDto.setTotalCost(totalCost);
		return totalCost;
	}

	public void setGuaranteeInfoDto(GuaranteeInfoDto guaranteeInfoDto) {
		this.guaranteeInfoDto = guaranteeInfoDto;
		if (guaranteeInfoDto != null && guaranteeInfoDto.getGuaranteeId() != null) {
			// 查看详情
			if (guaranteeInfoDto.getCommercialInsuranceFrom() != null) {
				commercialInsuranceFrom.setDate(guaranteeInfoDto.getCommercialInsuranceFrom());
			}
			if (guaranteeInfoDto.getCompulsoryInsuranceFrom() != null) {
				compulsoryInsuranceFrom.setDate(guaranteeInfoDto.getCompulsoryInsuranceFrom());
			}
			if (guaranteeInfoDto.getCommercialInsuranceTo() != null) {
				commercialInsuranceTo.setDate(guaranteeInfoDto.getCommercialInsuranceTo());
			}
			if (guaranteeInfoDto.getCompulsoryInsuranceTo() != null) {
				compulsoryInsuranceTo.setDate(guaranteeInfoDto.getCompulsoryInsuranceTo());
			}
			if (guaranteeInfoDto.getCommercialInsuranceCoverage() != null) {
				commercialInsuranceCoverageField
						.setText(String.valueOf(guaranteeInfoDto.getCommercialInsuranceCoverage()));
			}
			remartField.setText(guaranteeInfoDto.getRemark());
			transactorField.setText(guaranteeInfoDto.getTransactor());
			guaranteeNoField.setText(guaranteeInfoDto.getGuaranteeNo());
		} else {
			commercialInsuranceCoverageField.setText("");
			remartField.setText("");
			transactorField.setText("");
			guaranteeNoField.setText("");
			commercialInsuranceFrom.setText("");
			compulsoryInsuranceFrom.setText("");
			commercialInsuranceTo.setText("");
		    compulsoryInsuranceTo.setText("");
		}
		setViewEditable(editable);
		detaileTable.updateUI();
	}

	private void setViewEditable(boolean isEditable) {
		commercialInsuranceFrom.setEnabled(isEditable);
		commercialInsuranceTo.setEnabled(isEditable);
		compulsoryInsuranceFrom.setEnabled(isEditable);
		compulsoryInsuranceTo.setEnabled(isEditable);
		commercialInsuranceCoverageField.setEditable(isEditable);
		remartField.setEditable(isEditable);
		transactorField.setEditable(isEditable);
		guaranteeNoField.setEditable(isEditable);
	}
}
