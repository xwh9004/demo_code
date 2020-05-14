package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.common.util.OrgUtil;
import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.OrgInfo;
import com.demo.truck.entity.Owner;
import com.demo.truck.exception.ServiceException;
import com.demo.truck.listener.FrameListener;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.remote.CarInfoService;

public class EditCarInfoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5661568538221879148L;

	private JumpPageListener jumpPageListener;

	private FrameListener frameListener;
	
	private JComboBox<OrgInfo> companyBox;
	
	private List<OrgInfo> orgList;

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

	private CarInfoService carInfoService;
	private CarInfoPanel carInfoPanel = new CarInfoPanel();
	private OwnerInfoPanel ownerInfoPanel = new OwnerInfoPanel();

	private CarInfoDto carInfoDto;

	public EditCarInfoPanel() {
		JPanelInit();
	}

	private void JPanelInit() {
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel orgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton exitBtn = new JButton("退出");
		exitPanel.add(exitBtn);
		orgPanel.add(new JLabel("所属公司"));
		companyBox = new JComboBox<>();
		northPanel.add(exitPanel, BorderLayout.NORTH);
		northPanel.add(carInfoPanel);
		orgPanel.add(companyBox);
		northPanel.add(orgPanel, BorderLayout.SOUTH);
		add(northPanel, BorderLayout.NORTH);
		add(ownerInfoPanel);
		JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
		JPanel guaranteePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton chooseOwnerBtn = new JButton("选择车主");
		JButton addGuaranteeBtn = new JButton("添加保单");
		guaranteePanel.add(chooseOwnerBtn);
		guaranteePanel.add(addGuaranteeBtn);
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String fromPage = jumpPageListener.getFromPageName();
				if (fromPage==null||fromPage.equals(MyPage.CAR_INFO_LIST)||!fromPage.equals(MyPage.OWNER_INFO_LIST)) {
					fromPage = MyPage.CAR_INFO_LIST;
				}
				jumpPageListener.setJumpParam(MyPage.CAR_INFO_EDIT, fromPage, null);
				jumpPageListener.JumpToPage();

			}
		});
		chooseOwnerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameListener.toFrame(MyPage.OWNER_SEARCH_FRAME);

			}
		});
		addGuaranteeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CarInfoDto carInfoDto = getCarInfoDto();
				if (carInfoDto == null) {
					return;
				}
				jumpPageListener.setJumpParam(MyPage.CAR_INFO_EDIT, MyPage.GUARANTEE_EDIT, carInfoDto);
				jumpPageListener.JumpToPage();

			}
		});
		JPanel operatorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton submitBtn = new JButton("提交");
		JButton saveBtn = new JButton("保存");
		operatorPanel.add(saveBtn);
		operatorPanel.add(submitBtn);
		bottomPanel.add(guaranteePanel);
		bottomPanel.add(operatorPanel);
		add(bottomPanel, BorderLayout.SOUTH);
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveCarInfoDto();
			}
		});
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				submitCarInfoDto();
			}

		});
	}

	public void setCarInfoService(CarInfoService carInfoService) {
		this.carInfoService = carInfoService;
	}

	/**
	 * 保存carInfoDto
	 * 
	 * @Date 2017年7月10日
	 */
	private void saveCarInfoDto() {
		carInfoDto = getCarInfoDto();
		if(carInfoDto==null){
			return;
		}
		new Thread() {
			public void run() {
				try {
					carInfoDto.getCarInfo().setStatus(0);
					carInfoService.saveCarInfo(carInfoDto);
					JOptionPane.showMessageDialog(EditCarInfoPanel.this, "保存成功");
					jumpPageListener.setJumpParam(MyPage.CAR_INFO_EDIT, MyPage.CAR_INFO_LIST, null);
					jumpPageListener.JumpToPage();
				} catch (ServiceException e) {
					JOptionPane.showMessageDialog(EditCarInfoPanel.this, e.getMessage());
				}
			};
		}.start();

	}

	/**
	 * 
	 * @Date 2017年7月14日
	 * @param message
	 */
	private void submitCarInfoDto() {
		carInfoDto = getCarInfoDto();
		if(carInfoDto==null){
			return;
		}
		Owner owner = carInfoDto.getOwner();
		if (owner==null){
			JOptionPane.showMessageDialog(EditCarInfoPanel.this, "车主名不能为空！");
			return;
		}
		new Thread() {
			public void run() {
				try {
					carInfoDto.getCarInfo().setStatus(1);
					carInfoService.submitCarInfo(carInfoDto);
					JOptionPane.showMessageDialog(EditCarInfoPanel.this, "提交成功");
					jumpPageListener.setJumpParam(MyPage.CAR_INFO_EDIT, MyPage.CAR_INFO_LIST, null);
					jumpPageListener.JumpToPage();
				} catch (ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(EditCarInfoPanel.this, e.getMessage());
				}
			}
		}.start();
	}

	public CarInfoDto getCarInfoDto() {
		CarInfo carInfo = carInfoPanel.getCarInfo();
		if(carInfo ==null){
			return null;
		}
		Owner owner = ownerInfoPanel.getOwner();
		if (carInfoDto == null) {
			carInfoDto = new CarInfoDto();
		}
		String companyCode = null;
		if(companyBox.getSelectedIndex()!=0){
			companyCode =companyBox.getSelectedItem().toString();
			carInfo.setCompanyCode(companyCode);
		}else{
			carInfo.setCompanyCode(null);
		}
		carInfoDto.setCarInfo(carInfo);
		carInfoDto.setOwner(owner);
		return carInfoDto;
	}

	public void setCarInfoDto(CarInfoDto carInfoDto) {
		this.carInfoDto = carInfoDto;
		if (carInfoDto == null) {
			carInfoPanel.setCarInfo(null);
			ownerInfoPanel.setEditable(true);
			ownerInfoPanel.setOwner(null);
			companyBox.setSelectedIndex(0);
		} else {
			CarInfo carInfo=carInfoDto.getCarInfo();
			carInfoPanel.setCarInfo(carInfo);
			if(carInfo!=null){
				OrgInfo orgInfo=OrgUtil.getOrgInfoByCode(carInfo.getCompanyCode(), orgList);
				if(orgInfo!=null){
					companyBox.setSelectedItem(orgInfo);
				}else{
					companyBox.setSelectedIndex(0);
				}
			}
			ownerInfoPanel.setEditable(false);
			ownerInfoPanel.setOwner(carInfoDto.getOwner());
		}
		carInfoPanel.setEditable(true);
	}
}
