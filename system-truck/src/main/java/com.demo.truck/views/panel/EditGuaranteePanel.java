
package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.Owner;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.remote.GuaranteeInfoService;
import com.demo.truck.views.table.GuaranteeTabel;

public class EditGuaranteePanel extends JPanel {

	private CarInfoPanel carInfoPanel;

	private OwnerInfoPanel ownerInfoPanel;

	private GuaranteeInfoDto guaranteeInfoDto;

	private GuaranteeTabel guaranteeTabel;

	private GuaranteeInfoService guaranteeInfoService;

	private JumpPageListener jumpPageListener;

	public void setJumpPageListener(JumpPageListener jumpPageListener) {
		this.jumpPageListener = jumpPageListener;
	}

	private boolean editable = true;

	public void setEditable(boolean isEditable) {
		this.editable = isEditable;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -187041378987818897L;

	public EditGuaranteePanel() {
		carInfoPanel = new CarInfoPanel();
		ownerInfoPanel = new OwnerInfoPanel();
		guaranteeTabel = new GuaranteeTabel();
		initPanel();
	}

	private void initPanel() {
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton backBtn = new JButton("返回");
		exitPanel.add(backBtn);
		northPanel.add(exitPanel, BorderLayout.NORTH);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(carInfoPanel, BorderLayout.NORTH);
		panel.add(new ContractInfoPanel());
		panel.add(ownerInfoPanel, BorderLayout.SOUTH);
		northPanel.add(panel);
		add(northPanel, BorderLayout.NORTH);
		add(guaranteeTabel);
		JPanel operatorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton submitBtn = new JButton("提交");
		JButton saveBtn = new JButton("保存");
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String fromPage = jumpPageListener.getFromPageName();
				if(fromPage == null){
					fromPage =MyPage.GUARANTEE_INFO_LIST;
				}
				if(fromPage.equals(MyPage.CAR_INFO_EDIT)){
					Object param=jumpPageListener.getParam();
					jumpPageListener.setJumpParam(MyPage.GUARANTEE_EDIT,fromPage, param);
				}else{
					jumpPageListener.setJumpParam(MyPage.GUARANTEE_EDIT,fromPage, null);
				}
				jumpPageListener.JumpToPage();

			}
		});
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveGuaranteeInfoDto();
			}

		});
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				submitGuaranteeInfoDto();
			}

		});
		operatorPanel.add(saveBtn);
		operatorPanel.add(submitBtn);
		add(operatorPanel, BorderLayout.SOUTH);
	}

	/**
	 * 保存保单
	 * 
	 * @Date 2017年7月11日
	 * @param i
	 * @param string
	 */
	private void saveGuaranteeInfoDto() {
		this.guaranteeInfoDto = getGuaranteeInfoDto();
		if (guaranteeInfoDto != null) {
			new Thread() {
				public void run() {
					guaranteeInfoDto.setStatus(0);
					if (guaranteeInfoService.saveGuaranteeInfo(guaranteeInfoDto)) {
						JOptionPane.showMessageDialog(EditGuaranteePanel.this, "保存成功!");
						jumpPageListener.setJumpParam(MyPage.GUARANTEE_EDIT,MyPage.GUARANTEE_INFO_LIST, null);
						jumpPageListener.JumpToPage();
					} else {
						JOptionPane.showMessageDialog(EditGuaranteePanel.this, "保存失败!");
					}
				};
			}.start();
		}
	}

	/**
	 * 提交保单
	 * 
	 * @Date 2017年7月14日
	 */
	private void submitGuaranteeInfoDto() {
		this.guaranteeInfoDto = getGuaranteeInfoDto();
		if (guaranteeInfoDto != null) {
			new Thread() {
				public void run() {
					guaranteeInfoDto.setStatus(1);
					if (guaranteeInfoService.submitGuaranteeInfo(guaranteeInfoDto)) {
						JOptionPane.showMessageDialog(EditGuaranteePanel.this, "提交成功!");
						jumpPageListener.setJumpParam(MyPage.GUARANTEE_EDIT,MyPage.GUARANTEE_INFO_LIST, null);
						jumpPageListener.JumpToPage();
					} else {
						JOptionPane.showMessageDialog(EditGuaranteePanel.this, "提交失败!");
					}
				};
			}.start();
		}
	}

	public void setGuaranteeInfoService(GuaranteeInfoService guaranteeInfoService) {
		this.guaranteeInfoService = guaranteeInfoService;
	}

	public void setGuaranteeInfoDto(GuaranteeInfoDto guaranteeInfoDto) {
		if (guaranteeInfoDto == null) {
			clearAllField();
		} else {
			// 重车辆编辑页面跳转到新增保单页面
			carInfoPanel.setEditable(false);
			carInfoPanel.setCarInfo(guaranteeInfoDto.getCarInfo());
			ownerInfoPanel.setEditable(false);
			ownerInfoPanel.setOwner(guaranteeInfoDto.getOwner());
			guaranteeTabel.setGuaranteeInfoDto(guaranteeInfoDto);
		}

	}

	/**
	 * 清除所有输入域
	 * 
	 * @Date 2017年7月10日
	 */
	public void clearAllField() {
		carInfoPanel.setEditable(editable);
		carInfoPanel.cleanAllField();
		ownerInfoPanel.setEditable(editable);
		ownerInfoPanel.cleanAllField();
		guaranteeTabel.setGuaranteeInfoDto(null);

	}

	public GuaranteeInfoDto getGuaranteeInfoDto() {
		GuaranteeInfoDto guaranteeInfoDto = guaranteeTabel.getGuaranteeInfoDto();
		CarInfo carInfo = carInfoPanel.getCarInfo();
		Owner owner = ownerInfoPanel.getOwner();
		if (carInfo == null || owner == null) {
			return null;
		}
		guaranteeInfoDto.setCarInfo(carInfo);
		guaranteeInfoDto.setOwner(owner);
		return guaranteeInfoDto;
	}
}
