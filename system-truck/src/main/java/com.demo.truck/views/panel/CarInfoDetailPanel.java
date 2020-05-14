package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.demo.truck.bean.CarInfoDetailBean;
import com.demo.truck.bean.CarValidateRecordBean;
import com.demo.truck.bean.CompesationRecordBean;
import com.demo.truck.bean.GuaranteeBean;
import com.demo.truck.common.util.DateUtils;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.common.util.Util;
import com.demo.truck.listener.JumpPageListener;

public class CarInfoDetailPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8961536976197792125L;

	private boolean editable = false;


	private CarInfoPanel carInfoPanel = new CarInfoPanel();
	private OwnerInfoPanel ownerInfoPanel = new OwnerInfoPanel();
	private ContractInfoPanel contractInfoPanel = new ContractInfoPanel();

	private CarInfoDetailBean carInfoDetailBean;

	private List<GuaranteeBean> guaranteeRecordList;

	private List<CompesationRecordBean> compesationRecordlist;

	private List<CarValidateRecordBean> carValidateRecordList;
	

	private JTable guaranteeRecordTable;
	private JTable compesationRecordTable;
	private JTable validateRecordTable;

	private JumpPageListener jumpPageListener;
	public void setJumpPageListener(JumpPageListener jumpPageListener) {
		this.jumpPageListener = jumpPageListener;
	}
	private AbstractTableModel guaranteeRecordTableModel = new AbstractTableModel() {

		private String[] columnNames = { "序号", "交保日期", "保单号", "交保金额", "办理人", "备注" };

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			if (guaranteeRecordList == null) {
				return null;
			}
			switch (columnIndex) {
			case 0:
				value = rowIndex + 1;
				break;
			case 1:
				value =DateUtils.format(guaranteeRecordList.get(rowIndex).getCommercialInsuranceFrom(),"yyyy-MM-dd");
				break;
			case 2:
				value = guaranteeRecordList.get(rowIndex).getGuaranteeNo();
				break;
			case 3:
				value = guaranteeRecordList.get(rowIndex).getCommercialInsurancePremium();
				break;
			case 4:
				value = guaranteeRecordList.get(rowIndex).getTransactor();
				break;
			case 5:
				value = guaranteeRecordList.get(rowIndex).getRemark();
				break;
			default:
				break;
			}
			return value;
		}

		@Override
		public int getRowCount() {
			if (guaranteeRecordList == null) {
				return 0;
			}
			return guaranteeRecordList.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		};
		
	};
	private AbstractTableModel compesationRecordTableModel = new AbstractTableModel() {

		private String[] columnNames = { "序号", "出险日期", "理赔负责人", "理赔金额", "备注" };

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			if (compesationRecordlist == null) {
				return null;
			}
			switch (columnIndex) {
			case 0:
				value = rowIndex + 1;
				break;
			case 1:
				value = DateUtils.format(compesationRecordlist.get(rowIndex).getClaimTime(),"yyyy-MM-dd");
				break;
			case 2:
				value = compesationRecordlist.get(rowIndex).getClaimAdjuster();
				break;
			case 3:
				value = compesationRecordlist.get(rowIndex).getClaimAmount();
				break;
			case 4:
				value = compesationRecordlist.get(rowIndex).getRemark();
				break;
			default:
				break;
			}
			return value;
		}

		@Override
		public int getRowCount() {
			if (compesationRecordlist == null) {
				return 0;
			}
			return compesationRecordlist.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		};
	};
	private AbstractTableModel valideRecordTableModel = new AbstractTableModel() {

		private String[] columnNames = { "序号", "验车日期", "验车类型", "备注" };

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			if (carValidateRecordList == null) {
				return null;
			}
			switch (columnIndex) {
			case 0:
				value = rowIndex + 1;
				break;
			case 1:
				value = DateUtils.format(carValidateRecordList.get(rowIndex).getValidateTime(), "yyyy-MM-dd");
				break;
			case 2:
				value = Util.getValideType(carValidateRecordList.get(rowIndex).getValidateType());
				break;
			case 3:
				value = carValidateRecordList.get(rowIndex).getRemark();
				break;
			default:
				break;
			}
			return value;
		}

		@Override
		public int getRowCount() {
			if (carValidateRecordList == null) {
				return 0;
			}
			return carValidateRecordList.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		};
	};

	public CarInfoDetailPanel() {
		JPanelInit();
	}

	private void JPanelInit() {
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout());
		carInfoPanel.setEditable(false);
		ownerInfoPanel.setEditable(false);
		northPanel.add(carInfoPanel, BorderLayout.NORTH);
		northPanel.add(contractInfoPanel);
		northPanel.add(ownerInfoPanel, BorderLayout.SOUTH);

		JPanel operatorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton sureBtn = new JButton("确定");
		operatorPanel.add(sureBtn);
		sureBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jumpPageListener.setJumpParam(MyPage.CAR_INFO_DETAIL,MyPage.CAR_INFO_LIST, null);
				jumpPageListener.JumpToPage();
			}
		});
		add(operatorPanel, BorderLayout.SOUTH);
		add(northPanel, BorderLayout.NORTH);
		JPanel listTable_1 = new JPanel(new BorderLayout());
		JPanel listTable_2 = new JPanel(new BorderLayout());
		JPanel listTable_3 = new JPanel(new BorderLayout());
		guaranteeRecordTable = new JTable(guaranteeRecordTableModel);
		guaranteeRecordTable.addMouseListener(new MyMouseListener());
		compesationRecordTable = new JTable(compesationRecordTableModel);
		validateRecordTable = new JTable(valideRecordTableModel);
		JScrollPane guaranteeRdPanel = new JScrollPane(guaranteeRecordTable);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		guaranteeRecordTable.getColumnModel().getColumn(0).setCellRenderer(render);
		guaranteeRecordTable.setRowHeight(20);
		guaranteeRecordTable.setPreferredSize(new Dimension(700, 100));
		listTable_1.add(new JLabel(" 交保记录:"), BorderLayout.NORTH);
		listTable_1.add(guaranteeRdPanel);
		JScrollPane compesationRdPanel = new JScrollPane(compesationRecordTable);
		compesationRecordTable.getColumnModel().getColumn(0).setCellRenderer(render);
		compesationRecordTable.setRowHeight(20);
		compesationRecordTable.setPreferredSize(new Dimension(700, 100));
		listTable_2.add(new JLabel(" 出险记录:"), BorderLayout.NORTH);
		listTable_2.add(compesationRdPanel);
		JScrollPane validateRdPanel = new JScrollPane(validateRecordTable);
		validateRecordTable.getColumnModel().getColumn(0).setCellRenderer(render);
		validateRecordTable.setRowHeight(20);
		validateRecordTable.setPreferredSize(new Dimension(700, 100));
		listTable_3.add(new JLabel(" 验车记录:"), BorderLayout.NORTH);
		listTable_3.add(validateRdPanel);
		JPanel listTable = new JPanel(new GridLayout(3, 1));
		listTable.add(listTable_1);
		listTable.add(listTable_2);
		listTable.add(listTable_3);
		add(listTable);
	}
	/**
	 * 刷新数据源
	 *@Date 2017年7月13日
	 */
	private void fillDate(){
		carInfoPanel.setEditable(false);
		ownerInfoPanel.setEditable(false);
		carInfoPanel.setCarInfo(carInfoDetailBean);
		ownerInfoPanel.setOwner(carInfoDetailBean.getOwner());
		guaranteeRecordTable.updateUI();
		compesationRecordTable.updateUI();
		validateRecordTable.updateUI();
	}

	public void setCarInfoDetailBean(CarInfoDetailBean carInfoDetailBean) {
		this.carInfoDetailBean = carInfoDetailBean;
		this.guaranteeRecordList = carInfoDetailBean.getGuaranteeRecordList();
		this.compesationRecordlist = carInfoDetailBean.getComesationRecordlist();
		this.carValidateRecordList = carInfoDetailBean.getCarValidateRecordList();
		fillDate();
	}
	
	/**
	 * 保单列表鼠标监听事件
	 * 
	 * @author Administrator
	 * @date 2017年7月10日
	 */
	class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// 鼠标右键点击事件
			if (e.getButton() == MouseEvent.BUTTON3) {
				final int selectRow = guaranteeRecordTable.getSelectedRow();
				JPopupMenu menu = new JPopupMenu();
				JMenuItem detailItem = new JMenuItem("保单详情");
				menu.add(detailItem);
				guaranteeRecordTable.add(menu);
				menu.show(guaranteeRecordTable, e.getX(), e.getY());
				detailItem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (selectRow != -1) {
							jumpPageListener.setJumpParam(MyPage.CAR_INFO_DETAIL,MyPage.GUARANTEE_INFO_DETAIL, guaranteeRecordList.get(selectRow).getId());
							jumpPageListener.JumpToPage();
						}
					}
				});
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
