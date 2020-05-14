
package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.views.table.GuaranteeTabel;

public class GuaranteeDetailPanel extends JPanel {

	private CarInfoPanel carInfoPanel;

	private OwnerInfoPanel ownerInfoPanel;

	private GuaranteeInfoDto guaranteeInfoDto;

	private GuaranteeTabel guaranteeTabel;

	private JumpPageListener jumpPageListener;

	public void setJumpPageListener(JumpPageListener jumpPageListener) {
		this.jumpPageListener = jumpPageListener;
	}

	private boolean isEditable = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = -187041378987818897L;

	public GuaranteeDetailPanel() {
		carInfoPanel = new CarInfoPanel();
		ownerInfoPanel = new OwnerInfoPanel();
		guaranteeTabel = new GuaranteeTabel();
		initPanel();
	}

	private void initPanel() {
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(carInfoPanel, BorderLayout.NORTH);
		northPanel.add(new ContractInfoPanel());
		northPanel.add(ownerInfoPanel, BorderLayout.SOUTH);
		add(northPanel, BorderLayout.NORTH);
		add(guaranteeTabel);
		JPanel operatorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton comfirmBtn = new JButton("确定");
		comfirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String fromPage=jumpPageListener.getFromPageName();
				jumpPageListener.setJumpParam(MyPage.GUARANTEE_INFO_DETAIL,fromPage, null);
				jumpPageListener.JumpToPage();

			}
		});
		operatorPanel.add(comfirmBtn);
		add(operatorPanel, BorderLayout.SOUTH);
	}

	public void setGuaranteeInfoDto(GuaranteeInfoDto guaranteeInfoDto) {
		if (guaranteeInfoDto == null) {
			clearAllField();
		} else {
			carInfoPanel.setEditable(isEditable);
			carInfoPanel.setCarInfo(guaranteeInfoDto.getCarInfo());
			ownerInfoPanel.setEditable(isEditable);
			ownerInfoPanel.setOwner(guaranteeInfoDto.getOwner());
			guaranteeTabel.setEditable(isEditable);
			guaranteeTabel.setGuaranteeInfoDto(guaranteeInfoDto);
			
			
		}

	}

	/**
	 * 清除所有输入域
	 * 
	 * @Date 2017年7月10日
	 */
	public void clearAllField() {
		carInfoPanel.cleanAllField();
		ownerInfoPanel.cleanAllField();
		guaranteeTabel.setGuaranteeInfoDto(null);

	}
}
