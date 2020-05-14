
package com.demo.truck.views.common;

import com.demo.truck.common.util.DateUtils;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

public class DatePicker extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1219106053159168492L;
	private JButton button;
	private Rectangle rectangle;
	private Point point;
	private Date date;
	private MyDatePickerFrame datePickerFrame;

	public DatePicker() {
		super(15);
		initDatePicker();
	}

	public DatePicker(int column) {
		super(column);
		initDatePicker();
	}

	private void initDatePicker() {
		button = new JButton("···");
		button.setVisible(false);
		add(button);
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				button.setVisible(false);
			}

			@Override
			public void focusGained(FocusEvent e) {
				if(datePickerFrame!=null){
					datePickerFrame.dispose();
				}
				button.setVisible(true);
			}
		});
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				datePickerFrame = new MyDatePickerFrame(point);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		point = this.getLocationOnScreen();
		rectangle = this.getBounds();
		button.setBounds(rectangle.width - 30, 0, 30, rectangle.height - 1);
	}

	public Date getDate() {
		String vaule =getText();
		if(vaule!=null&&!"".equals(vaule)){
			return date = DateUtils.format("yyyy-MM-dd", vaule);
		}
		return null;
	}

	public void setDate(Date date) {
		this.date = date;
		setText(DateUtils.format(date, "yyyy-MM-dd"));
	}

	class MyDatePickerFrame extends JFrame {
		private JDatePanel jp;
		private JButton comfirmBtn;
		private JButton cancelBtn;
		private Point point;

		/**
		 * 
		 */
		private static final long serialVersionUID = -8964730681987232963L;

		public MyDatePickerFrame(Point point) {
			this.point = point;
			initFrame();
		}

		private void initFrame() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jp = JDateComponentFactory.createJDatePanel(new UtilDateModel(new Date()));
			setBounds(point.x, point.y + 20, 300, 250);
			setUndecorated(true);
			setResizable(false);
			add((JPanel) jp);
			JPanel panel = new JPanel();
			comfirmBtn = new JButton("确定");
			cancelBtn = new JButton("取消");
			panel.add(comfirmBtn);
			panel.add(cancelBtn);
			panel.setBackground(Color.GRAY);
			add(panel, BorderLayout.SOUTH);
			setVisible(true);
			comfirmBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					date = (Date) jp.getModel().getValue();
					DatePicker.this.setText(DateUtils.format(date,"yyyy-MM-dd"));
					MyDatePickerFrame.this.dispose();
				}
			});
			cancelBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
						MyDatePickerFrame.this.dispose();
				}
			});
			addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					MyDatePickerFrame.this.dispose();
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					
				}
			});
		}
	}
}
