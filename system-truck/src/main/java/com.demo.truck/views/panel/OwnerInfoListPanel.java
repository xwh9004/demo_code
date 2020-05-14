
package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.demo.truck.bean.GuaranteeQueryBean;
import com.demo.truck.bean.OwnerQueryBean;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.entity.Owner;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.remote.OwnerService;
import com.demo.truck.views.common.PageTableModel;

public class OwnerInfoListPanel extends JPanel {

	private List<Owner> ownerList;

	private OwnerQueryBean queryBean;

	private JumpPageListener jumpPageListener;

	private JTable ownerTable;

	private JTextField ownerNameField;

	private JTextField ownerIdNoField;

	private JTextField ownerTelField;

	private String[] columnNames = { "序号", "姓名", "电话", "身份证号", "备注" };

	private PageTableModel pageTable = new PageTableModel() {
		@Override
		public int getRowCount() {
			if (ownerList == null) {
				return 0;
			}
			return ownerList.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		};

		@Override
		public Object getValueAt(int row, int column) {
			Object value = null;
			if (ownerList == null) {
				return null;
			}
			switch (column) {
			case 0:
				value = row + 1;
				break;
			case 1:
				value = ownerList.get(row).getOwnerName();
				break;
			case 2:
				value = ownerList.get(row).getTelephone();
				break;
			case 3:
				value = ownerList.get(row).getIdNo();
				break;
			case 4:
				value = ownerList.get(row).getRemark();
				break;
			default:
				break;
			}
			return value;
		}

		@Override
		public void changePage() {
			queryBean = (OwnerQueryBean) this.getQueryBean();
			jumpPageListener.setJumpParam(MyPage.OWNER_INFO_LIST, MyPage.OWNER_INFO_LIST, queryBean);
			jumpPageListener.JumpToPage();
		}

	};

	public OwnerInfoListPanel() {
		initJPanel();
	}

	private void initJPanel() {
		setLayout(new BorderLayout());
		JPanel serachPanel = new JPanel(new GridLayout(2, 1));
		JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		serachPanel.add(conditionPanel);
		serachPanel.add(btnPanel);
		conditionPanel.add(new JLabel("车主姓名:"));
		ownerNameField = new JTextField(10);
		ownerIdNoField = new JTextField(20);
		ownerTelField = new JTextField(15);
		conditionPanel.add(ownerNameField);
		conditionPanel.add(new JLabel("身份证号:"));
		conditionPanel.add(ownerIdNoField);
		conditionPanel.add(new JLabel("电话:"));
		conditionPanel.add(ownerTelField);
		JButton searchBtn = new JButton("查询");
		JButton cleanBtn = new JButton("清除");
		cleanBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanQueryBean();
			}
		});
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				queryBean = getQueryBean();
				jumpPageListener.setJumpParam(MyPage.OWNER_INFO_LIST, MyPage.OWNER_INFO_LIST, queryBean);
				jumpPageListener.JumpToPage();
			}
		});
		btnPanel.add(cleanBtn);
		btnPanel.add(searchBtn);
		add(serachPanel, BorderLayout.NORTH);
		ownerTable = pageTable.getTable();
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(serachPanel, BorderLayout.NORTH);
		northPanel.add(pageTable.getTablePanel());
		add(northPanel, BorderLayout.NORTH);
		ownerTable.addMouseListener(new MyMouseListener());
	}

	public void cleanQueryBean() {
		this.queryBean = null;
		ownerNameField.setText("");
		ownerIdNoField.setText("");
		ownerTelField.setText("");
	}

	public void setQueryBean(OwnerQueryBean queryBean) {
		this.queryBean = queryBean;
		if (queryBean != null) {
			ownerList = queryBean.getResultList();
			pageTable.setQueryBean(queryBean);
		}
	}

	public OwnerQueryBean getQueryBean() {
		if (queryBean == null) {
			queryBean = new OwnerQueryBean();
			queryBean.setStart(0);
			queryBean.setLength(10);
		} else {
			queryBean = (OwnerQueryBean) pageTable.getQueryBean();
		}
		queryBean.setIdNo(ownerIdNoField.getText());
		queryBean.setOwnerName(ownerNameField.getText());
		queryBean.setTelephone(ownerTelField.getText());
		return queryBean;
	}

	public void setJumpPageListener(JumpPageListener jumpPageListener) {
		this.jumpPageListener = jumpPageListener;
	}

	public Owner getChoseOwner() {
		if (ownerList != null) {
			int row = ownerTable.getSelectedRow();
			if (row != -1) {
				return ownerList.get(row);
			}
		}
		return null;
	}

	class MyMouseListener implements MouseListener {

		private JPopupMenu menu;
		private JMenuItem editItem;
		private JMenuItem detailItem;
		private JMenuItem addCarItem;
		private int selectRow = -1;

		public MyMouseListener() {
			menu = new JPopupMenu();
			editItem = new JMenuItem("编辑车主");
			detailItem = new JMenuItem("车主详情");
			addCarItem = new JMenuItem("添加车辆");
			menu.add(editItem);
			menu.add(detailItem);
			menu.add(addCarItem);
			ownerTable.add(menu);
			AddListener();
		}

		private void AddListener() {

			editItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectRow != -1) {
						jumpPageListener.setJumpParam(MyPage.OWNER_INFO_LIST, MyPage.OWNER_EDIT,
								ownerList.get(selectRow).getId());
						jumpPageListener.JumpToPage();
					}
				}
			});
			detailItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectRow != -1) {
						jumpPageListener.setJumpParam(MyPage.OWNER_INFO_LIST, MyPage.OWNER_DETAIL,
								ownerList.get(selectRow).getId());
						jumpPageListener.JumpToPage();
					}
				}
			});
			addCarItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectRow != -1) {
						jumpPageListener.setJumpParam(MyPage.OWNER_INFO_LIST, MyPage.CAR_INFO_EDIT,
								ownerList.get(selectRow));
						jumpPageListener.JumpToPage();
					}
				}
			});
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			selectRow = ownerTable.getSelectedRow();
			if (selectRow != -1) {
				// 鼠标右键点击事件
				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.show(ownerTable, e.getX(), e.getY());
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
