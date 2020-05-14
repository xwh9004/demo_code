
package com.demo.truck.views.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.demo.truck.common.QueryPageBase;


public abstract class PageTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8849175763772332033L;

	private JTable table;

	private String title;

	private JPanel listPanel;

	private JPanel panel;

	private JPanel pagePanel;

	private JButton preBtn;

	private JButton nextBtn;

	private JButton firstBtn;

	private JButton lastBtn;

	private int start;

	private int length = 10;

	private int currentPage = 1;

	private int entrySize;

	private int totalPage;

	private JLabel showLabel;

	public JComboBox<Integer> showBox = new JComboBox<>(new Integer[] { 5, 10, 25, 50, 75, 100 });

	public JComboBox<Integer> pageBox = new JComboBox<>();

	private QueryPageBase querybean;

	public void setTitle(String title) {
		this.title = title;
	}

	private PageItemListener pageItemListener;

	private boolean recreatePage = true;

	public PageTableModel() {
		initTable();
		registerListener();
	}

	public abstract void changePage();

	private String createLabelText(int start, int length, int entrySize) {
		StringBuilder sb = new StringBuilder();
		sb.append("showing ");
		sb.append(start);
		sb.append(" to ");
		if (start + length > entrySize) {
			sb.append(entrySize);
		} else {
			sb.append((start + length));
		}
		sb.append(" of ");
		sb.append(entrySize);
		sb.append(" entries");
		return sb.toString();
	}

	private int getPages() {
		return entrySize % length == 0 ? entrySize / length : entrySize / length + 1;
	}

	private JPanel getPageEntryList() {
		JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		entryPanel.add(new JLabel("show"));
		showBox.setSelectedIndex(1);
		entryPanel.add(showBox);
		entryPanel.add(new JLabel("entries"));
		return entryPanel;
	}

	public QueryPageBase getQueryBean() {
		if (querybean != null) {
			querybean.setStart(start);
			querybean.setLength(length);
			return querybean;
		}
		return null;
	}

	public int getSelectedRow() {
		return table.getSelectedRow();
	}

	public JTable getTable() {
		return table;
	}

	public JPanel getTablePanel() {
		return panel;
	}

	private void initTable() {
		panel = new JPanel(new BorderLayout());
		table = new JTable(this);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(render);
		table.setRowHeight(20);
		FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		layout.setHgap(0);
		pagePanel = new JPanel(layout);
		firstBtn = new JButton("<<");
		lastBtn = new JButton(">>");
		nextBtn = new JButton(">");
		preBtn = new JButton("<");
		pagePanel.add(firstBtn);
		pagePanel.add(preBtn);
		pagePanel.add(pageBox);
		pagePanel.add(nextBtn);
		pagePanel.add(lastBtn);
		listPanel = new JPanel(new BorderLayout());
		if (title != null) {
			listPanel.add(new JLabel(title), BorderLayout.NORTH);
		}
		JScrollPane tablePanel = new JScrollPane(table);
		tablePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		listPanel.add(tablePanel);
		panel.add(listPanel);
		JPanel northPanel = new JPanel(new GridLayout(1, 3));
		showLabel = new JLabel("showing 0 to 0 entries");
		northPanel.add(getPageEntryList());
		northPanel.add(showLabel);
		northPanel.add(pagePanel);
		panel.add(northPanel, BorderLayout.SOUTH);
		setSelectPage(0);
	}
    /**
     * 注册监听
     *@Date 2017年7月20日
     */
	private void registerListener() {
		pageItemListener = new PageItemListener();
		firstBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start = 0;
				currentPage = 1;
				pageBox.setSelectedIndex(currentPage - 1);
			}
		});
		lastBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage = totalPage;
				start = (totalPage - 1) * length;
				pageBox.setSelectedIndex(currentPage - 1);
			}
		});
		preBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage--;
				start = (currentPage - 1) * length;
				pageBox.setSelectedIndex(currentPage - 1);
			}
		});
		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPage++;
				start = (currentPage - 1) * length;
				pageBox.setSelectedIndex(currentPage - 1);
			}
		});
		showBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					start = 0;
					length = (int) showBox.getSelectedItem();
					totalPage = getPages();
					recreatePage = true;
					changePage();
				}
			}
		});
		pageBox.addItemListener(pageItemListener);
	}

	private void reSizeTabel() {
		int width = table.getWidth();
		int height = (getRowCount() + 1) * table.getRowHeight();
		if (height > 500) {
			height = 500;
		}
		listPanel.setPreferredSize(new Dimension(width, height));
		table.updateUI();
		showLabel.setText(createLabelText(start, length, entrySize));
	}

	/**
	 * 设置查询结果
	 * 
	 * @Date 2017年7月19日
	 * @param querybean
	 */
	public void setQueryBean(QueryPageBase querybean) {
		this.querybean = querybean;
		entrySize = querybean.getTotalRows();
		if(totalPage != getPages()){
			recreatePage =true;
		}
		totalPage = getPages();
		if (recreatePage) {
			updatePageItem();
		}
		reSizeTabel();
	}

	public void setRecreatePage(boolean recreatePage) {
		this.recreatePage = recreatePage;
	}

	private void setSelectPage(int page) {
		if (totalPage == 1||totalPage==0) {
			firstBtn.setEnabled(false);
			preBtn.setEnabled(false);
			nextBtn.setEnabled(false);
			lastBtn.setEnabled(false);
			return;
		}
		if (page == 1 && totalPage != 1) {
			preBtn.setEnabled(false);
			firstBtn.setEnabled(false);
			nextBtn.setEnabled(true);
			lastBtn.setEnabled(true);
		} else if (page == totalPage) {
			preBtn.setEnabled(true);
			firstBtn.setEnabled(true);
			nextBtn.setEnabled(false);
			lastBtn.setEnabled(false);
		} else {
			preBtn.setEnabled(true);
			firstBtn.setEnabled(true);
			nextBtn.setEnabled(true);
			lastBtn.setEnabled(true);
		}
	}

	private void updatePageItem() {
		// 移除所有的pageBox item项
		pageBox.removeAllItems();
		if (totalPage == 0) {
			return;
		}
		// 移除监听器
		pageBox.removeItemListener(pageItemListener);
		for (int i = 0; i < totalPage; i++) {
			pageBox.addItem(i + 1);
		}
		// 注册监听
		pageBox.addItemListener(pageItemListener);
		// 选择第一页
		currentPage = 1;
		pageBox.setSelectedIndex(currentPage - 1);
		setSelectPage(currentPage);
	}

	class PageItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				currentPage = (int) pageBox.getSelectedItem();
				start = (currentPage - 1) * length;
				querybean.setLength(length);
				querybean.setStart(start);
				recreatePage = false;
				changePage();
				setSelectPage(currentPage);
			}

		}

	}
}