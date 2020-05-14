
package com.demo.truck.views.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.demo.truck.entity.Owner;

public class OwnerInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2188576700396908909L;
	
	private JTextField owerNameField;
	
	private JTextField ownerIdNoField;
	
	private JTextField telephoneField;
	
	private JTextField homeAddressField;
	
	private JTextField presentAddressField;
	
	private JTextField remarkField;
	
	private Owner owner;
	
	private boolean editable;

	public OwnerInfoPanel() {
		init();
	}

	private void init() {
		setLayout(new GridLayout(5, 1));
		add(new JLabel(" 车主信息："));
		JPanel ownerInfoPanel = new JPanel();
		FlowLayout mgr = new FlowLayout(FlowLayout.LEFT);
		ownerInfoPanel.setLayout(mgr);
		owerNameField = new JTextField(10);
		ownerIdNoField = new JTextField(15);
		telephoneField = new JTextField(10);
		homeAddressField = new JTextField(48);
		presentAddressField = new JTextField(48);
		remarkField = new JTextField(48);
		ownerInfoPanel.add(new JLabel("车主姓名:"));
		ownerInfoPanel.add(owerNameField);
		ownerInfoPanel.add(new JLabel("身份证号:"));
		ownerInfoPanel.add(ownerIdNoField);
		ownerInfoPanel.add(new JLabel("联系电话:"));
		ownerInfoPanel.add(telephoneField);
		JPanel homeAddressPanel = new JPanel();
		homeAddressPanel.add(new JLabel("家庭地址:"));
		homeAddressPanel.add(homeAddressField);
		homeAddressPanel.setLayout(mgr);
		JPanel presentAddressPanel = new JPanel();
		presentAddressPanel.add(new JLabel("现住地址:"));
		presentAddressPanel.add(presentAddressField);
		presentAddressPanel.setLayout(mgr);
		JPanel remarkPanel = new JPanel();
		remarkPanel.add(new JLabel("备        注:"));
		remarkPanel.add(remarkField);
		remarkPanel.setLayout(mgr);
		add(ownerInfoPanel);
		add(homeAddressPanel);
		add(presentAddressPanel);
		add(remarkPanel);
	}
 
	public Owner getOwner() {
		String ownerName=owerNameField.getText();
		if(ownerName==null||"".equals(ownerName)){
			return null;
		}
		if(owner==null){
			owner = new Owner();
		}
		owner.setOwnerName(ownerName);
		owner.setIdNo(ownerIdNoField.getText());
		owner.setRemark(remarkField.getText());
		owner.setHomeAddress(homeAddressField.getText());
		owner.setPresentAddress(presentAddressField.getText());
		owner.setTelephone(telephoneField.getText());
		return owner;
	}
	   /**
     * 设置车主信息
     *@Date 2017年7月10日
     */
	public void setOwner(Owner owner) {
		
		if(owner!=null){
			this.owner=owner;
			owerNameField.setText(owner.getOwnerName());
			ownerIdNoField.setText(owner.getIdNo());
			telephoneField.setText(owner.getTelephone());
			homeAddressField.setText(owner.getHomeAddress());
			presentAddressField.setText(owner.getPresentAddress());
			remarkField.setText(owner.getRemark());
			setViewEditable(editable);
		}else{
			cleanAllField();
		}
	}
	/**
	 * 清除owner信息
	 *@Date 2017年7月10日
	 */
	public void cleanAllField(){
		this.owner=null;
		owerNameField.setText("");
		ownerIdNoField.setText("");
		telephoneField.setText("");
		homeAddressField.setText("");
		presentAddressField.setText("");
		remarkField.setText("");
		setViewEditable(true);
	}
    private void setViewEditable(boolean editable){
    	owerNameField.setEditable(editable);
		ownerIdNoField.setEditable(editable);
		telephoneField.setEditable(editable);
		homeAddressField.setEditable(editable);
		presentAddressField.setEditable(editable);
		remarkField.setEditable(editable);
    }
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
   
}
