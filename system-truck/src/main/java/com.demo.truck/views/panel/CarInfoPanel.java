
package com.demo.truck.views.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.common.util.Util;
import com.demo.truck.entity.CarInfo;

public class CarInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3972211797646155956L;


	private JTextField carNoField;

	private JTextField brandField;

	private JTextField tonnageField;

	private JTextField runNoField;
	
	private JComboBox<String> colorBrandBox;
	
	private JTextField statusField;

	private CarInfo carInfo;
	
	private boolean editable=true;

	public CarInfoPanel() {
		init();
	}

	/**
	 * 车辆信息板面初始化
	 * 
	 * @Date 2017年7月7日
	 */
	private void init() {
		setLayout(new GridLayout(3, 1));
	    JPanel panel_1 = new JPanel();
	    JPanel panel_2 = new JPanel();
		FlowLayout mgr = new FlowLayout(FlowLayout.LEFT);
		panel_1.setLayout(mgr);
		panel_2.setLayout(mgr);
		carNoField = new JTextField(10);
		brandField = new JTextField(10);
		tonnageField = new JTextField(10);
		runNoField = new JTextField(15);
		colorBrandBox = new JComboBox<String>(new String[]{"请选择","黄牌","蓝牌"});
	    colorBrandBox.setPreferredSize(new Dimension(112, 20));
		statusField = new JTextField(15);
		statusField.setEditable(false);
		JLabel title = new JLabel("  车辆信息：");
		add(title);
		panel_1.add(new JLabel("车牌号:"));
		panel_1.add(carNoField);
		panel_1.add(new JLabel("车     型:"));
		panel_1.add(brandField);
		panel_1.add(new JLabel("运  营  证:"));
		panel_1.add(runNoField);
		panel_2.add(new JLabel("黄蓝牌:"));
		panel_2.add(colorBrandBox);
		panel_2.add(new JLabel("吨 位(T):"));
		panel_2.add(tonnageField);
		panel_2.add(new JLabel("车辆状态:"));
		panel_2.add(statusField);
		add(panel_1);
		add(panel_2);
	}

	public CarInfo getCarInfo() {
		if(carInfo==null){
			carInfo = new CarInfo();
		}
		String carNo = carNoField.getText();
		String brand = brandField.getText();
		Integer cardColor = colorBrandBox.getSelectedIndex();
		String runNo = runNoField.getText();
		String tonage=tonnageField.getText();
		if (carNo == null || "".equals(carNo)) {
			// 车辆信息不为空示才能保存车辆信息
			JOptionPane.showMessageDialog(CarInfoPanel.this, "车牌号不能为空！");
			return null;
		}
		try {
			carInfo.setTonnage(Double.parseDouble((tonage)));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "吨位不能为空或格式不正确");	
			return null;
		}
		carInfo.setCarNo(carNo);
		carInfo.setBrand(brand);
		carInfo.setCardColor(cardColor);
		carInfo.setRunNo(runNo);
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		if (carInfo != null) {
			this.carInfo = carInfo;
			carNoField.setText(carInfo.getCarNo());
			brandField.setText(carInfo.getBrand());
			tonnageField.setText(String.valueOf(carInfo.getTonnage()));
			runNoField.setText(carInfo.getRunNo());
			Integer color=carInfo.getCardColor()==null?0:carInfo.getCardColor();
			colorBrandBox.setSelectedIndex(color);
			statusField.setText(Util.getCarStatus(carInfo.getStatus()));
			setViewEditAble(editable);
		}else{
			cleanAllField();
		}

	}

	/**
	 * 清除
	 * 
	 * @Date 2017年7月10日
	 */
	public void cleanAllField() {
		this.carInfo = null;
		carNoField.setText("");
		brandField.setText("");
		tonnageField.setText("");
		runNoField.setText("");
		colorBrandBox.setSelectedIndex(0);
		statusField.setText("");
		setViewEditAble(editable);
	}
    public void setEditable(boolean isEditable){
    	this.editable=isEditable;
    	setViewEditAble(isEditable);
    }
    /**
     * 设置面板是否可编辑
     *@Date 2017年7月12日
     */
    private void setViewEditAble(boolean isEditable){
    		carNoField.setEditable(isEditable);
    		brandField.setEditable(isEditable);
    		tonnageField.setEditable(isEditable);
    		runNoField.setEditable(isEditable);
    		colorBrandBox.setEnabled(isEditable);
    }
}