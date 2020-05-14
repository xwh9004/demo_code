
package com.demo.truck.views.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.demo.truck.entity.Owner;
import com.demo.truck.listener.FrameListener;
import com.demo.truck.remote.OwnerService;
import com.demo.truck.views.table.OwnerTable;

public class OwnerInfoFrame extends MyFrame {
	
	private JButton choseBtn;
	
	private OwnerService ownerService;
	
	private  OwnerTable ownerTable;
	
	private FrameListener frameListener;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7270937683387748413L;

	public OwnerInfoFrame() {
		super(600, 600);
		initFrame();
	}

	private void initFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("车主查询");
		choseBtn = new JButton("选择");
		choseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frameListener.getObject(ownerTable.getChoseOwner());
			}
		});
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel chosePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		chosePanel.add(choseBtn);
		ownerTable = new OwnerTable();
		ownerTable.setOwnerService(ownerService);
		northPanel.add(ownerTable,BorderLayout.NORTH);
		northPanel.add(chosePanel,BorderLayout.SOUTH);
		add(northPanel,BorderLayout.NORTH);
		setVisible(true);
	}

	public void setOwnerService(OwnerService ownerService) {
		this.ownerService = ownerService;
		ownerTable.setOwnerService(ownerService);
	}

	public void setFrameListener(FrameListener frameListener) {
		this.frameListener = frameListener;
	}
	
}
