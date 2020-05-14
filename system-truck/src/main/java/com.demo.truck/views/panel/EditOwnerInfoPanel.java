
package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.demo.truck.common.util.MyPage;
import com.demo.truck.entity.Owner;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.remote.OwnerService;

public class EditOwnerInfoPanel extends JPanel {

	private OwnerInfoPanel ownerInfoPanel;

	private JumpPageListener jumpPageListener;
	
	private OwnerService ownerService;

	public void setOwnerService(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	private boolean editable;
	private JButton submitBtn;

	private JButton comfirmBtn;

	public void setEditable(boolean editable) {
		this.editable = editable;
		ownerInfoPanel.setEditable(editable);
		submitBtn.setVisible(editable);
		comfirmBtn.setVisible(!editable);
	}

	public void setJumpPageListener(JumpPageListener jumpPageListener) {
		this.jumpPageListener = jumpPageListener;
	}

	public EditOwnerInfoPanel() {
		initPanel();
	}

	private void initPanel() {
		setLayout(new BorderLayout());
		ownerInfoPanel = new OwnerInfoPanel();
		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton backBtn = new JButton("退出");
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jumpPageListener.setJumpParam(MyPage.OWNER_EDIT, MyPage.OWNER_INFO_LIST, null);
				jumpPageListener.JumpToPage();
			}
		});
		northPanel.add(backBtn);
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		submitBtn = new JButton("提交");
		comfirmBtn = new JButton("确定");

		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final Owner owner = getOwner();
				if (owner != null && owner.getOwnerName() != null && !"".equals(owner.getOwnerName())) {
					new Thread(){
						@Override
						public void run() {
							if(ownerService.saveOwner(owner)){
								JOptionPane.showMessageDialog(EditOwnerInfoPanel.this, "提交成功！");
								jumpPageListener.setJumpParam(MyPage.OWNER_EDIT, MyPage.OWNER_INFO_LIST, null);
								jumpPageListener.JumpToPage();
							}else{
								JOptionPane.showMessageDialog(EditOwnerInfoPanel.this, "提交失败！");
							}
						}
					}.start();
					
				} else {
					JOptionPane.showMessageDialog(EditOwnerInfoPanel.this, "请填写车主信息");
				}

			}
		});
		comfirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jumpPageListener.setJumpParam(MyPage.OWNER_EDIT, MyPage.OWNER_INFO_LIST, null);
				jumpPageListener.JumpToPage();

			}
		});
		southPanel.add(comfirmBtn);
		southPanel.add(submitBtn);
		add(northPanel, BorderLayout.NORTH);
		add(ownerInfoPanel);
		add(southPanel, BorderLayout.SOUTH);
	}

	public void setOwner(Owner owner) {
		ownerInfoPanel.setOwner(owner);
	}

	public Owner getOwner() {
		return ownerInfoPanel.getOwner();
	}
}
