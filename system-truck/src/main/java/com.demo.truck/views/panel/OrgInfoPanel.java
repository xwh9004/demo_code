
package com.demo.truck.views.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.demo.truck.common.MyNode;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.entity.OrgInfo;
import com.demo.truck.exception.ServiceException;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.remote.SystemService;

public class OrgInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6379795675632977089L;
	private JButton addBtn;
	private JButton delBtn;
	private JTree tree;
	private DetailsPanel detailsPanel;
	private JPanel treePanel;
	private DefaultMutableTreeNode treeRoot;
	private MyNode<OrgInfo> rootNode;
	private SystemService systemService;
	private JumpPageListener jumpPageListener;

	public void setJumpPageListener(JumpPageListener jumpPageListener) {
		this.jumpPageListener = jumpPageListener;
	}

	public void setRootNode(MyNode<OrgInfo> rootNode) {
		this.rootNode = rootNode;
		treeRoot.setUserObject(rootNode);
		freshTree();
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public OrgInfoPanel() {
		initPanel();
	}

	private void initPanel() {
		setLayout(new BorderLayout());
		BorderLayout layout = new BorderLayout();
		JPanel firstPanel = new JPanel(new BorderLayout());
		JPanel northPanel = new JPanel(layout);
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addBtn = new JButton("添加");
		delBtn = new JButton("删除");
		btnPanel.add(addBtn);
		btnPanel.add(delBtn);
		northPanel.add(btnPanel);
		northPanel.add(new JLabel("组织树"), BorderLayout.NORTH);
		firstPanel.add(northPanel, BorderLayout.NORTH);
		treePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		treeRoot = new DefaultMutableTreeNode();
		tree = new JTree(treeRoot);
		tree.setPreferredSize(new Dimension(200, 200));
		treePanel.add(tree);
		firstPanel.add(treePanel);
		add(firstPanel, BorderLayout.WEST);
		detailsPanel = new DetailsPanel();
		detailsPanel.setVisible(false);
		add(detailsPanel, BorderLayout.EAST);
		registerListener();
	}

	private void registerListener() {
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				MyNode<OrgInfo> node = (MyNode<OrgInfo>) selectNode.getUserObject();
				if (selectNode != null && selectNode != treeRoot) {
					detailsPanel.setVisible(true);
					OrgInfo orgInfo = node.getElement();
					detailsPanel.setOrgInfo(orgInfo);
					detailsPanel.setEditable(false);
				}else{
					detailsPanel.setVisible(false);
				}
				
			}
		});
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				detailsPanel.setVisible(true);
				detailsPanel.cleanAll();
			}
		});
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				MyNode<OrgInfo> node = (MyNode<OrgInfo>) selectNode.getUserObject();
				try {
					systemService.deleteOrg(node.getElement());
					JOptionPane.showMessageDialog(OrgInfoPanel.this, "删除成功!");
					jumpPageListener.setJumpParam(MyPage.ORG_INFO_PAGE, MyPage.ORG_INFO_PAGE, null);
					jumpPageListener.JumpToPage();
				} catch (ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(OrgInfoPanel.this, "删除失败!");
				}
			}
		});
	}

	private DefaultMutableTreeNode createTree(MyNode<OrgInfo> dataRoot, DefaultMutableTreeNode treeRoot) {
		if (dataRoot == null) {
			return null;
		}
		if (dataRoot.getChildList() == null) {
			DefaultMutableTreeNode nodeTree = new DefaultMutableTreeNode(dataRoot);
			return nodeTree;
		}
		if (dataRoot.getChildList().size() != 0) {
			for (MyNode<OrgInfo> node : dataRoot.getChildList()) {
				DefaultMutableTreeNode nodeTree = new DefaultMutableTreeNode(node);
				DefaultMutableTreeNode tree = createTree(node, nodeTree);
				treeRoot.add(tree);
			}
		}
		return treeRoot;
	}

	private void freshTree() {
		treeRoot.removeAllChildren();
		treeRoot = createTree(rootNode, treeRoot);
		expandAll(tree, new TreePath(treeRoot), true);
		tree.updateUI();
	}

	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}

	class DetailsPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7761337908486033107L;
		private JTextField nameField;
		private JTextField codeField;
		private JComboBox<String> typeBox;
		private JTextField descField;
		private JButton submitBtn;
		private OrgInfo info;
		private boolean isEditable = true;
		private JButton edtBtn;
		public DetailsPanel() {
			initDetailsPanel();
		}

		/**
		 * 初始化节点详情面板
		 * 
		 * @Date 2017年7月23日
		 */
		private void initDetailsPanel() {
			BorderLayout layout = new BorderLayout();
			layout.setVgap(10);
			JPanel northPanel = new JPanel(layout);
			northPanel.add(new JLabel("组织明细"), BorderLayout.NORTH);
			GridLayout layout_2 = new GridLayout(2, 2);
			layout_2.setHgap(20);
			layout_2.setVgap(10);
			JPanel detailPanel = new JPanel(layout_2);
			JPanel orgNameP = new JPanel(new BorderLayout());
			orgNameP.add(new JLabel("组织名称"), BorderLayout.NORTH);
			nameField = new JTextField(20);
			orgNameP.add(nameField);
			JPanel orgCodeP = new JPanel(new BorderLayout());
			orgCodeP.add(new JLabel("组织编码"), BorderLayout.NORTH);
			codeField = new JTextField(20);
			orgCodeP.add(codeField);
			JPanel orgTypeP = new JPanel(new BorderLayout());
			orgTypeP.add(new JLabel("组织类型"), BorderLayout.NORTH);
			typeBox = new JComboBox<String>(new String[] { "请选择", "公司", "部门" });
			orgTypeP.add(typeBox);
			JPanel orgDescP = new JPanel(new BorderLayout());
			orgDescP.add(new JLabel("组织描述"), BorderLayout.NORTH);
			descField = new JTextField(20);
			orgDescP.add(descField);
			detailPanel.add(orgNameP);
			detailPanel.add(orgCodeP);
			detailPanel.add(orgTypeP);
			detailPanel.add(orgDescP);
			northPanel.add(detailPanel);
			JPanel btnP = new JPanel(new FlowLayout(FlowLayout.LEFT));
			submitBtn = new JButton("提交");
			edtBtn = new JButton("编辑");
			btnP.add(edtBtn);
			btnP.add(submitBtn);
			northPanel.add(detailPanel);
			northPanel.add(btnP, BorderLayout.SOUTH);
			add(northPanel, BorderLayout.NORTH);
			registerListener();
		}

		/**
		 * 设置节点信息
		 * 
		 * @Date 2017年7月23日
		 * @param orgInfo
		 */
		private void setOrgInfo(OrgInfo orgInfo) {
			nameField.setText(orgInfo.getOrgName());
			descField.setText(orgInfo.getOrgDesc());
			codeField.setText(orgInfo.getOrgCode());
			typeBox.setSelectedIndex(orgInfo.getOrgType());
		}

		/**
		 * 获得新节点信息
		 * 
		 * @Date 2017年7月23日
		 * @return
		 */
		private OrgInfo getOrgInfo() {

			String name = nameField.getText();
			String desc = descField.getText();
			String code = codeField.getText();
			int type = typeBox.getSelectedIndex();
			if (name == null || "".equals(name)) {
				JOptionPane.showMessageDialog(OrgInfoPanel.this, "组织名字不能为空");
				return null;
			}
			if (desc == null || "".equals(desc)) {
				JOptionPane.showMessageDialog(OrgInfoPanel.this, "组织描述不能为空");
				return null;
			}
			if (code == null || "".equals(code)) {
				JOptionPane.showMessageDialog(OrgInfoPanel.this, "组织代码不能为空");
				return null;
			}
			if (type == 0) {
				JOptionPane.showMessageDialog(OrgInfoPanel.this, "请选择组织类型");
				return null;
			}
			if (info == null) {
				info = new OrgInfo();
			}
			info.setOrgName(name);
			info.setOrgDesc(desc);
			info.setOrgCode(code);
			info.setOrgType(type);
			return info;
		}

		/**
		 * 清除数据
		 * 
		 * @Date 2017年7月23日
		 */
		public void cleanAll() {
			this.info = null;
			setEditable(true);
			nameField.setText("");
			descField.setText("");
			codeField.setText("");
			typeBox.setSelectedIndex(0);
		}

		/**
		 * 查看组织结构节点信息
		 * 
		 * @Date 2017年7月23日
		 * @param isEditable
		 */
		public void setEditable(boolean isEditable) {
			this.isEditable = isEditable;
			submitBtn.setVisible(isEditable);
			nameField.setEditable(isEditable);
			descField.setEditable(isEditable);
			codeField.setEditable(isEditable);
			typeBox.setEnabled(isEditable);
		}

		/**
		 * 注册监听事情
		 * 
		 * @Date 2017年7月23日
		 */
		private void registerListener() {
			edtBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					isEditable = true;
					setEditable(isEditable);
				}
			});
			submitBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					final OrgInfo orgInfo = getOrgInfo();
					if (orgInfo != null) {
						DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree
								.getLastSelectedPathComponent();
						if (selectNode != null && selectNode != treeRoot) {
							MyNode<OrgInfo> node = (MyNode<OrgInfo>) selectNode.getUserObject();
							orgInfo.setParentId(node.getElement().getId());
						}
						new Thread() {
							public void run() {
								try {
									systemService.addOrg(orgInfo);
									JOptionPane.showMessageDialog(OrgInfoPanel.this, "提交成功!");
									jumpPageListener.setJumpParam(MyPage.ORG_INFO_PAGE, MyPage.ORG_INFO_PAGE, null);
									jumpPageListener.JumpToPage();
								} catch (ServiceException e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(OrgInfoPanel.this, "提交失败!");
								}
							};
						}.start();
					}
				}
			});
		}
	}
}