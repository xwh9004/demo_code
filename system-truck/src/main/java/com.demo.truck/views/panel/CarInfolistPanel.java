
package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.bean.CarInfoBean;
import com.demo.truck.bean.CarInfoDetailBean;
import com.demo.truck.bean.CarInfoQueryBean;
import com.demo.truck.common.QueryPageBase;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.common.util.Util;
import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.OrgInfo;
import com.demo.truck.listener.FrameListener;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.remote.CarInfoService;
import com.demo.truck.views.common.PageTableModel;

public class CarInfolistPanel extends JPanel {

	private List<CarInfoBean> carInfoList;

	private CarInfoQueryBean queryBean;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8233090928383451295L;

	private JTextField ownerNameField;

	private JTextField brandField;

	private JTextField tonnageField;

	private JComboBox<String> colorBrandBox;

	private JComboBox<String> statusBox;

	private JTable listTable;

	private JumpPageListener jumpPageListener;

	private FrameListener frameListener;
	
	private List<OrgInfo> orgList = new ArrayList<>();
	
	private JComboBox<OrgInfo> companyBox;
	
	public void setOrgList(List<OrgInfo> orgList) {
		this.orgList = orgList;
		companyBox.removeAllItems();
		if(orgList!=null){
			OrgInfo element = new OrgInfo();
			element.setOrgName("请选择");
			orgList.add(0, element );
			for(OrgInfo item:orgList){
				companyBox.addItem(item);
			}
			this.updateUI();
		}
	}

	public void setFrameListener(FrameListener frameListener) {
		this.frameListener = frameListener;
	}

	public void setJumpPageListener(JumpPageListener jumpPageListener) {
		this.jumpPageListener = jumpPageListener;
	}

	private String[] columnNames = { "序号", "车主姓名", "车牌号", "车型", "吨位", "黄蓝牌", "状态","所属公司" };

	private PageTableModel pageTable = new PageTableModel() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7461481672181787995L;

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			if (carInfoList == null) {
				return null;
			}
			switch (columnIndex) {
			case 0:
				value = rowIndex + 1;
				break;
			case 1:
				value = carInfoList.get(rowIndex).getOwnerName();
				break;
			case 2:
				value = carInfoList.get(rowIndex).getCarNo();
				break;
			case 3:
				value = carInfoList.get(rowIndex).getBrand();
				break;
			case 4:
				value = carInfoList.get(rowIndex).getTonnage();
				break;
			case 5:
				value = Util.getCarCardColor(carInfoList.get(rowIndex).getCardColor());
				break;
			case 6:
				value = Util.getCarStatus(carInfoList.get(rowIndex).getStatus());
				break;
			case 7:
				value = carInfoList.get(rowIndex).getCompanyCode();
				break;
			default:
				break;
			}
			return value;
		}

		@Override
		public int getRowCount() {
			if (carInfoList == null) {
				return 0;
			}
			return carInfoList.size();
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
			queryBean = (CarInfoQueryBean) this.getQueryBean();
			jumpPageListener.setJumpParam(MyPage.CAR_INFO_LIST, MyPage.CAR_INFO_LIST, queryBean);
			jumpPageListener.JumpToPage();
		};
	};

	public CarInfolistPanel() {
		initJPanel();
	}

	private void initJPanel() {
		setLayout(new BorderLayout());
		JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		conditionPanel.add(new JLabel("车主姓名："));
		ownerNameField = new JTextField(5);
		brandField = new JTextField(5);
		tonnageField = new JTextField(5);
		colorBrandBox = new JComboBox<String>(new String[] { "请选择", "黄牌", "蓝牌" });
		statusBox = new JComboBox<String>(new String[] { "请选择", "待入", "已入", "报废" });
		companyBox = new JComboBox<>();
		conditionPanel.add(ownerNameField);
		conditionPanel.add(new JLabel("车   型："));
		conditionPanel.add(brandField);
		conditionPanel.add(new JLabel("吨   位："));
		conditionPanel.add(tonnageField);
		conditionPanel.add(new JLabel("黄蓝牌 ："));
		conditionPanel.add(colorBrandBox);
		conditionPanel.add(new JLabel("状   态 ："));
		conditionPanel.add(statusBox);
		conditionPanel.add(new JLabel("公  司 ："));
		conditionPanel.add(companyBox);
		JPanel operatorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton searchBtn = new JButton("查   询");
		JButton clearBtn = new JButton("清   空");
		operatorPanel.add(clearBtn);
		operatorPanel.add(searchBtn);
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel searchPanel = new JPanel(new GridLayout(2, 1));
		searchPanel.add(conditionPanel);
		searchPanel.add(operatorPanel);
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
				queryBean.setStart(0);
				jumpPageListener.setJumpParam(MyPage.CAR_INFO_LIST, MyPage.CAR_INFO_LIST, queryBean);
				jumpPageListener.JumpToPage();
			}
		});
		northPanel.add(searchPanel, BorderLayout.NORTH);
		northPanel.add(pageTable.getTablePanel());
		add(northPanel, BorderLayout.NORTH);
	}

	private CarInfoQueryBean getQueryBean() {
		if (pageTable.getQueryBean() == null) {
			queryBean = new CarInfoQueryBean();
			queryBean.setStart(0);
			queryBean.setLength(10);
		} else {
			queryBean = (CarInfoQueryBean) pageTable.getQueryBean();
		}
		queryBean.setBrand(brandField.getText());
		queryBean.setCardColor(colorBrandBox.getSelectedIndex());
		if (!"".equals(tonnageField.getText())) {
			queryBean.setTonnage(Double.valueOf(tonnageField.getText()));
		} else {
			queryBean.setTonnage(null);
		}
		Integer status;
		if(statusBox.getSelectedIndex()==0){
			 status=null;
		}else{
			status =statusBox.getSelectedIndex()-1;
		}
		String companyCode = null;
		if(companyBox.getSelectedIndex()!=0){
			companyCode =companyBox.getSelectedItem().toString();
			queryBean.setCompanyCode(companyCode);
		}else{
			queryBean.setCompanyCode(null);
		}
		queryBean.setStatus(status);
		queryBean.setOwnerName(ownerNameField.getText());
		return queryBean;
	}
	public void cleanQueryBean() {
		this.queryBean=null;
		ownerNameField.setText("");
		brandField.setText("");
		tonnageField.setText("");
		colorBrandBox.setSelectedIndex(0);
		statusBox.setSelectedIndex(0);
		if(orgList!=null&&orgList.size()!=0){
			companyBox.setSelectedIndex(0);
		}
		
	}
	/**
	 * 设置结果类
	 * 
	 * @Date 2017年7月13日
	 * @param queryBean
	 */
	public void setQueryBean(CarInfoQueryBean queryBean) {
		this.queryBean = queryBean;
		carInfoList = queryBean.getResultList();
		pageTable.setQueryBean(queryBean);
	}

	class MyMouseListener implements MouseListener {

		private JPopupMenu menu;

		private JMenuItem editItem;
		private JMenuItem detailItem;
		private JMenuItem newGuaranteeItem;
		private JMenuItem addCompesationItem;
		private JMenuItem addValideItem;
		private JMenuItem scrapItem;
		private int selectRow = -1;

		public MyMouseListener() {
			menu = new JPopupMenu();
			editItem = new JMenuItem("编辑车辆");
			detailItem = new JMenuItem("车辆详情");
			addCompesationItem = new JMenuItem("添加出险记录");
			addValideItem = new JMenuItem("添加验车记录");
			newGuaranteeItem = new JMenuItem("添加保单");
			scrapItem = new JMenuItem("报废车辆");
			menu.add(editItem);
			menu.add(detailItem);
			menu.add(addCompesationItem);
			menu.add(addValideItem);
			menu.add(newGuaranteeItem);
			menu.add(scrapItem);
			listTable.add(menu);
			AddListener();
		}

		private void AddListener() {

			editItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectRow != -1) {
						jumpPageListener.setJumpParam(MyPage.CAR_INFO_LIST, MyPage.CAR_INFO_EDIT,
								carInfoList.get(selectRow).getId());
						jumpPageListener.JumpToPage();
					}
				}
			});
			detailItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectRow != -1) {
						jumpPageListener.setJumpParam(MyPage.CAR_INFO_LIST, MyPage.CAR_INFO_DETAIL,
								carInfoList.get(selectRow).getId());
						jumpPageListener.JumpToPage();
					}
				}
			});
			newGuaranteeItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectRow != -1) {
						jumpPageListener.setJumpParam(MyPage.CAR_INFO_LIST, MyPage.GUARANTEE_EDIT,
								carInfoList.get(selectRow));
						jumpPageListener.JumpToPage();
					}

				}
			});
			addCompesationItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frameListener.setParam(carInfoList.get(selectRow).getId());
					frameListener.toFrame(MyPage.CAR_COMPESATION_FRAME);

				}
			});
			addValideItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frameListener.setParam(carInfoList.get(selectRow).getId());
					frameListener.toFrame(MyPage.CAR_VALIDATE_FRAME);

				}
			});
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			selectRow = listTable.getSelectedRow();
			if (selectRow != -1) {
				Integer status = carInfoList.get(selectRow).getStatus();
				if (status == null) {
					return;
				}
				if (status == 0) {
					editItem.setEnabled(true);
					detailItem.setEnabled(false);
					scrapItem.setEnabled(false);
					newGuaranteeItem.setEnabled(false);
					addCompesationItem.setEnabled(false);
					addValideItem.setEnabled(false);
				}
				if (status == 1) {
					editItem.setEnabled(false);
					detailItem.setEnabled(true);
					newGuaranteeItem.setEnabled(true);
					scrapItem.setEnabled(true);
					addCompesationItem.setEnabled(true);
					addValideItem.setEnabled(true);
				}
				if (status == 2) {
					editItem.setEnabled(false);
					detailItem.setEnabled(true);
					newGuaranteeItem.setEnabled(false);
					scrapItem.setEnabled(false);
					addCompesationItem.setEnabled(false);
					addValideItem.setEnabled(false);
				}
				// 鼠标右键点击事件
				if (e.getButton() == MouseEvent.BUTTON3) {
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
