
package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.demo.truck.bean.CarInfoQueryBean;
import com.demo.truck.bean.GuaranteeBean;
import com.demo.truck.bean.GuaranteeQueryBean;
import com.demo.truck.common.util.DateUtils;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.common.util.Util;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.views.common.DatePicker;
import com.demo.truck.views.common.PageTableModel;

public class GuaranteeInfolistPanel extends JPanel {

	private List<GuaranteeBean> guaranteeList;

	private JTable listTable;

	private GuaranteeQueryBean queryBean;

	private JTextField ownerNameField;

	private JTextField guaranteeNoField;

	private JTextField carNoField;

	private DatePicker insuranceFrom;
	
	private DatePicker insuranceExpireFrom;
	
	private DatePicker insuranceExpireTo;

	private JComboBox<String> statusBox;
	
	private JComboBox<String> quickBox;

	private JumpPageListener jumpPageListener;

	public void setJumpPageListener(JumpPageListener jumpPageListener) {
		this.jumpPageListener = jumpPageListener;
	}

	public void setQueryBean(GuaranteeQueryBean queryBean) {
		this.queryBean = queryBean;
		guaranteeList = queryBean.getResultList();
		pageTable.setQueryBean(queryBean);
	}

	private String[] columnNames = { "序号", "保单号", "车牌号", "车主", "起始日期", "结束日期", "状态" };
	private PageTableModel pageTable = new PageTableModel() {

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			if (guaranteeList == null) {
				return null;
			}
			switch (columnIndex) {
			case 0:
				value = rowIndex + 1;
				break;
			case 1:
				value = guaranteeList.get(rowIndex).getGuaranteeNo();
				break;
			case 2:
				value = guaranteeList.get(rowIndex).getCarNo();
				break;
			case 3:
				value = guaranteeList.get(rowIndex).getOwnerName();
				break;
			case 4:
				value = DateUtils.format(guaranteeList.get(rowIndex).getCommercialInsuranceFrom(), "yyyy-MM-dd");
				break;
			case 5:
				value = DateUtils.format(guaranteeList.get(rowIndex).getCommercialInsuranceTo(), "yyyy-MM-dd");
				break;
			case 6:
				value = Util.getGuaranteeStatus(guaranteeList.get(rowIndex).getStatus());
				break;
			default:
				break;
			}
			return value;
		}

		@Override
		public int getRowCount() {
			if (guaranteeList == null) {
				return 0;
			}
			return guaranteeList.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public void changePage() {
			queryBean = (GuaranteeQueryBean) this.getQueryBean();
			jumpPageListener.setJumpParam(MyPage.GUARANTEE_INFO_LIST, MyPage.GUARANTEE_INFO_LIST, queryBean);
			jumpPageListener.JumpToPage();

		};
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233090928383451295L;

	public GuaranteeInfolistPanel() {
		initJPanel();
	}

	private void initJPanel() {
		setLayout(new BorderLayout());
		JPanel conditionPanel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel conditionPanel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		guaranteeNoField = new JTextField(7);
		ownerNameField = new JTextField(5);
		carNoField = new JTextField(5);
		statusBox = new JComboBox<String>(new String[] { "请选择", "草稿", "已提交" });
		quickBox = new JComboBox<>(new String[] { "请选择", "一周内到期", "30天内到期","当月到期" });
		insuranceFrom = new DatePicker(10);
		insuranceExpireFrom = new DatePicker(10);
		insuranceExpireTo = new DatePicker(10);
		conditionPanel_1.add(new JLabel("保单号："));
		conditionPanel_1.add(guaranteeNoField);
		conditionPanel_1.add(new JLabel("车牌号："));
		conditionPanel_1.add(carNoField);
		conditionPanel_1.add(new JLabel("车   主："));
		conditionPanel_1.add(ownerNameField);
		conditionPanel_1.add(new JLabel("起始时间 ："));
		conditionPanel_1.add(insuranceFrom);
		conditionPanel_1.add(new JLabel("状 态 ："));
		conditionPanel_1.add(statusBox);
		conditionPanel_2.add(new JLabel("到期时间 从："));
		conditionPanel_2.add(insuranceExpireFrom);
		conditionPanel_2.add(new JLabel("至："));
		conditionPanel_2.add(insuranceExpireTo);
		conditionPanel_2.add(new JLabel("快速查询"));
		conditionPanel_2.add(quickBox);
		JPanel operatorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton searchBtn = new JButton("查   询");
		JButton clearBtn = new JButton("清   空");
		operatorPanel.add(clearBtn);
		operatorPanel.add(searchBtn);
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel searchPanel = new JPanel(new GridLayout(3, 1));
		searchPanel.add(conditionPanel_1);
		searchPanel.add(conditionPanel_2);
		searchPanel.add(operatorPanel);
		add(searchPanel, BorderLayout.NORTH);
		listTable = pageTable.getTable();
		listTable.addMouseListener(new MyMouseListener());
		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cleanQueryBean();
			}

			
		});
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				queryBean = getQueryBean();
				jumpPageListener.setJumpParam(MyPage.GUARANTEE_INFO_LIST, MyPage.GUARANTEE_INFO_LIST, queryBean);
				jumpPageListener.JumpToPage();
			}
		});
		northPanel.add(searchPanel,BorderLayout.NORTH);
		northPanel.add(pageTable.getTablePanel());
		add(northPanel,BorderLayout.NORTH);

	}
	public void cleanQueryBean() {
		queryBean = null;
		guaranteeNoField.setText("");
		ownerNameField.setText("");
		carNoField.setText("");
		insuranceFrom.setText("");
		insuranceExpireFrom.setText("");
		insuranceExpireTo.setText("");
		statusBox.setSelectedIndex(0);
		quickBox.setSelectedIndex(0);
	}
	/**
	 * 获取queryBean
	 * 
	 * @Date 2017年7月10日
	 * @return
	 */
	private GuaranteeQueryBean getQueryBean() {
		if (queryBean == null) {
			queryBean = new GuaranteeQueryBean();
			queryBean.setStart(0);
			queryBean.setLength(10);
		} else {
			queryBean = (GuaranteeQueryBean) pageTable.getQueryBean();
		}
		queryBean.setGuaranteeNo(guaranteeNoField.getText());
		queryBean.setCarNo(carNoField.getText());
		queryBean.setOwnerName(ownerNameField.getText());
		queryBean.setCommercialInsuranceFrom(insuranceFrom.getDate());
		queryBean.setInsuranceExpireFrom(insuranceExpireFrom.getDate());
		queryBean.setInsuranceExpireTo(insuranceExpireTo.getDate());
		Integer status;
		if(statusBox.getSelectedIndex()==0){
			 status=null;
		}else{
			status =statusBox.getSelectedIndex()-1;
		}
		Integer quickType=quickBox.getSelectedIndex();
		queryBean.setQuickType(quickType);
		queryBean.setStatus(status);
		return queryBean;
	}

	/**
	 * 保单列表鼠标监听事件
	 * 
	 * @author Administrator
	 * @date 2017年7月10日
	 */
	class MyMouseListener implements MouseListener {

		JPopupMenu menu;
		JMenuItem detailItem;
		JMenuItem editlItem;
		private int selectRow = -1;

		public MyMouseListener() {
			menu = new JPopupMenu();
			editlItem = new JMenuItem("编辑保单");
			detailItem = new JMenuItem("保单详情");
			menu.add(editlItem);
			menu.add(detailItem);
			listTable.add(menu);
			addListener();
		}

		private void addListener() {
			editlItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectRow != -1) {
						jumpPageListener.setJumpParam(MyPage.GUARANTEE_INFO_LIST, MyPage.GUARANTEE_EDIT,
								guaranteeList.get(selectRow).getId());
						jumpPageListener.JumpToPage();
					}
				}
			});
			detailItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectRow != -1) {
						jumpPageListener.setJumpParam(MyPage.GUARANTEE_INFO_LIST, MyPage.GUARANTEE_INFO_DETAIL,
								guaranteeList.get(selectRow).getId());
						jumpPageListener.JumpToPage();
					}
				}
			});
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// 鼠标右键点击事件
			if (e.getButton() == MouseEvent.BUTTON3) {
				selectRow = listTable.getSelectedRow();
				if (selectRow != -1) {
					if (guaranteeList.get(selectRow).getStatus() == 0) {
						editlItem.setEnabled(true);
					}
					if (guaranteeList.get(selectRow).getStatus() == 1) {
						editlItem.setEnabled(false);
					}
					menu.show(listTable, e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
