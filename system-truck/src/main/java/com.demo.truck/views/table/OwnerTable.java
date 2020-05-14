
package com.demo.truck.views.table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.demo.truck.bean.OwnerQueryBean;
import com.demo.truck.entity.Owner;
import com.demo.truck.remote.OwnerService;
import com.demo.truck.views.common.PageTableModel;

public class OwnerTable extends JPanel {
	
	private List<Owner> ownerList;
	
	private OwnerQueryBean queryBean;
	
	private OwnerService ownerService;
	
	private JTable ownerTable;
	
	private JButton cleanBtn;
	
	private JButton searchBtn;
	
	private TextField ownerNameField;

	private TextField ownerIdNoField;
	
	private TextField ownerPhoneField;
	
	private String[] columnNames = { "序号", "姓名", "电话", "备注" };
	
	private PageTableModel pageTable = new PageTableModel() {
		
		@Override
		public int getRowCount() {
			if(ownerList==null){
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
			Object value=null;
			if(ownerList ==null){
				return null;
			}
			switch (column) {
			case 0:
				value=row+1;
				break;
			case 1:
				value=ownerList.get(row).getOwnerName();
				break;
			case 2:
				value=ownerList.get(row).getTelephone();
				break;
			case 3:
				value=ownerList.get(row).getRemark();
				break;
			default:
				break;
			}
			return value;
		}

		@Override
		public void changePage() {
			queryBean= (OwnerQueryBean) this.getQueryBean();
			queryBean=ownerService.findOwnersList(queryBean);
			ownerList=queryBean.getResultList();
			pageTable.setQueryBean(queryBean);
			
		}
	    
	};

	
     public OwnerTable() {
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
		ownerNameField=new TextField(10);
		ownerIdNoField=new TextField(20);
		ownerPhoneField = new TextField(15);
		conditionPanel.add(ownerNameField);
		conditionPanel.add(new JLabel("电话:"));
		conditionPanel.add(ownerPhoneField);
		conditionPanel.add(new JLabel("身份证号:"));
		conditionPanel.add(ownerIdNoField);
		cleanBtn= new JButton("清除");
		searchBtn = new JButton("查询");
		cleanBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanField();
			}
		});
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run() {
						queryBean= getQueryBean();
						queryBean=ownerService.findOwnersList(queryBean);
						ownerList=queryBean.getResultList();
						pageTable.setQueryBean(queryBean);
					};
				}.start();
			}
		});
		btnPanel.add(cleanBtn);
		btnPanel.add(searchBtn);
		add(serachPanel, BorderLayout.NORTH);
	    ownerTable =pageTable.getTable();
	    JPanel northPanel = new JPanel(new BorderLayout());
	    northPanel.add(serachPanel, BorderLayout.NORTH);
	    northPanel.add(pageTable.getTablePanel());
	    add(northPanel,BorderLayout.NORTH);
	}
	private void cleanField(){
		ownerNameField.setText("");
		ownerIdNoField.setText("");
		ownerPhoneField.setText("");
	}
	public OwnerQueryBean getQueryBean() {
		if(queryBean==null){
			queryBean = new OwnerQueryBean();
			queryBean.setStart(0);
			queryBean.setLength(10);
		}else{
			queryBean = (OwnerQueryBean) pageTable.getQueryBean();
		}
		queryBean.setIdNo(ownerIdNoField.getText());
		queryBean.setOwnerName(ownerNameField.getText());
		queryBean.setTelephone(ownerPhoneField.getText());
		return queryBean;
	}
	public void setOwnerService(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
    public Owner getChoseOwner(){
    	if(ownerList!=null){
    		int row=ownerTable.getSelectedRow();
    		if(row!=-1){
    			return ownerList.get(row);
    		}
    	}
		return null;
    	
    }
}
