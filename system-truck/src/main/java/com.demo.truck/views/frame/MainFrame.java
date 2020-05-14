package com.demo.truck.views.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.bean.CarInfoBean;
import com.demo.truck.bean.CarInfoDetailBean;
import com.demo.truck.bean.CarInfoQueryBean;
import com.demo.truck.bean.GuaranteeQueryBean;
import com.demo.truck.bean.OwnerQueryBean;
import com.demo.truck.common.MyNode;
import com.demo.truck.common.util.MyMenu;
import com.demo.truck.common.util.MyPage;
import com.demo.truck.entity.OrgInfo;
import com.demo.truck.entity.Owner;
import com.demo.truck.exception.ServiceException;
import com.demo.truck.listener.FrameListener;
import com.demo.truck.listener.JumpPageListener;
import com.demo.truck.remote.CarInfoService;
import com.demo.truck.remote.GuaranteeInfoService;
import com.demo.truck.remote.OwnerService;
import com.demo.truck.remote.SystemService;
import com.demo.truck.views.panel.CarInfoDetailPanel;
import com.demo.truck.views.panel.CarInfolistPanel;
import com.demo.truck.views.panel.EditCarInfoPanel;
import com.demo.truck.views.panel.EditGuaranteePanel;
import com.demo.truck.views.panel.EditOwnerInfoPanel;
import com.demo.truck.views.panel.GuaranteeDetailPanel;
import com.demo.truck.views.panel.GuaranteeInfolistPanel;
import com.demo.truck.views.panel.MainPanel;
import com.demo.truck.views.panel.OrgInfoPanel;
import com.demo.truck.views.panel.OwnerInfoListPanel;

public class MainFrame extends MyFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5255793316977904261L;
	private JTree menuTree;
	private CarInfolistPanel carInfolistPanel;
	private OwnerInfoListPanel ownerInfoListPanel;
	private EditCarInfoPanel editCarInfoPanel;
	private EditOwnerInfoPanel editOwnerInfoPanel;
	private CarInfoDetailPanel carInfoDetailPanel;
	private GuaranteeInfolistPanel guaranteeInfolistPanel;
	private GuaranteeDetailPanel guaranteeDetailPanel;
	private EditGuaranteePanel editGuaranteePanel;
	private OrgInfoPanel orgInfoPanel;
	private CardLayout cardLayout;
	private JPanel contentPanel;
	private MainPanel mainPanel;

	// 服务类
	private ClassPathXmlApplicationContext applicationContext;

	private CarInfoService carInfoService;

	private GuaranteeInfoService guaranteeInfoService;

	private OwnerService ownerService;

	private SystemService systemService;

	private FrameListener frameListener = new FrameListener() {

		private Object param;

		@Override
		public void getObject(Object obj) {
			if (obj != null) {
				if (obj instanceof Owner) {
					Owner owner = (Owner) obj;
					CarInfoDto carInfoDto = editCarInfoPanel.getCarInfoDto();
					carInfoDto.setOwner(owner);
					editCarInfoPanel.setCarInfoDto(carInfoDto);
				}
			}

		}

		@Override
		public void toFrame(String frameName) {
			if (frameName.equals(MyPage.OWNER_SEARCH_FRAME)) {
				OwnerInfoFrame ownerInfoFrame = new OwnerInfoFrame();
				ownerInfoFrame.setOwnerService(ownerService);
				ownerInfoFrame.setFrameListener(frameListener);
			}
			if (frameName.equals(MyPage.CAR_VALIDATE_FRAME)) {
				if (param instanceof Integer) {
					try {
						Integer id = (Integer) param;
						CarValidateFrame carValidateFrame = new CarValidateFrame();
						carValidateFrame.setCarInfoService(carInfoService);
						carValidateFrame.setCarInfo(carInfoService.findCarInfoDtoById(id).getCarInfo());
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				}
			}
			if (frameName.equals(MyPage.CAR_COMPESATION_FRAME)) {
				if (param instanceof Integer) {
					try {
						Integer id = (Integer) param;
						CarCompesationFrame crCompesationFrame = new CarCompesationFrame();
						crCompesationFrame.setCarInfoService(carInfoService);
						crCompesationFrame.setCarInfo(carInfoService.findCarInfoDtoById(id).getCarInfo());
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				}
			}

		}

		@Override
		public void setParam(Object param) {
			this.param = param;

		}
	};

	private JumpPageListener jumpPageListener = new JumpPageListener() {

		private String fromPage;

		private String toPage = null;

		private Object param;

		@Override
		public void afterJump() {
			// TODO Auto-generated method stub
		}

		@Override
		public void JumpToPage() {

			switch (toPage) {
			case MyPage.CAR_INFO_DETAIL:
				jumpToCarInfoDetail(MyPage.CAR_INFO_DETAIL, param);
				break;
			case MyPage.CAR_INFO_LIST:
				jumpToCarInfoListPage(MyPage.CAR_INFO_LIST, param);
				break;
			case MyPage.CAR_INFO_EDIT:
				jumpToCarEditPage(fromPage, MyPage.CAR_INFO_EDIT, param);
				break;
			case MyPage.GUARANTEE_INFO_LIST:
				jumpToGuaranteeListPage(MyPage.GUARANTEE_INFO_LIST, param);
				break;
			case MyPage.GUARANTEE_INFO_DETAIL:
				jumpToGuaranteeDetailPage(MyPage.GUARANTEE_INFO_DETAIL, param);
				break;
			case MyPage.GUARANTEE_EDIT:
				jumpToGuaranteeEditPage(MyPage.GUARANTEE_EDIT, param);
				break;
			case MyPage.OWNER_INFO_LIST:
				jumpToOwnerListPage(MyPage.OWNER_INFO_LIST, param);
				break;
			case MyPage.OWNER_EDIT:
				jumpToOwnerEditPage(MyPage.OWNER_EDIT, param, true);
				break;
			case MyPage.OWNER_DETAIL:
				jumpToOwnerEditPage(MyPage.OWNER_EDIT, param, false);
				break;
			case MyPage.ORG_INFO_PAGE:
				jumpToOrgInfoPage(MyPage.ORG_INFO_PAGE, param);
				break;
			default:
				break;
			}
		}

		@Override
		public void setJumpParam(String fromPage, String toPage, Object param) {
			this.fromPage = fromPage;
			this.toPage = toPage;
			this.param = param;
		}

		public String getFromPageName() {
			return fromPage;
		}

		@Override
		public Object getParam() {
			return this.param;
		}
	};

	private void jumpToOrgInfoPage(String pageName, Object param) {
		if (param == null) {
			new Thread() {
				public void run() {
					MyNode<OrgInfo> node = systemService.findOrgTree();
					orgInfoPanel.setRootNode(node);
				};
			}.start();

		}
		cardLayout.show(contentPanel, pageName);
	}

	private void jumpToOwnerEditPage(String pageName, final Object param, final boolean editable) {
		if (param instanceof Integer) {
			new Thread() {
				public void run() {
					Integer ownerId = (Integer) param;
					Owner owner = ownerService.findOwnerById(ownerId);
					editOwnerInfoPanel.setEditable(editable);
					editOwnerInfoPanel.setOwner(owner);
				};
			}.start();
		}
		if (param == null) {
			editOwnerInfoPanel.setEditable(editable);
			editOwnerInfoPanel.setOwner(null);
		}
		cardLayout.show(contentPanel, pageName);
	}

	/**
	 * 
	 * @Date 2017年7月15日
	 * @param pageName
	 * @param param
	 */
	private void jumpToOwnerListPage(String pageName, final Object param) {
		if (param instanceof OwnerQueryBean) {
			new Thread() {
				public void run() {
					OwnerQueryBean queryBean = (OwnerQueryBean) param;
					queryBean = ownerService.findOwnersList(queryBean);
					ownerInfoListPanel.setQueryBean(queryBean);
				};
			}.start();
		}
		if (param == null) {
			new Thread() {
				public void run() {
					ownerInfoListPanel.cleanQueryBean();
					OwnerQueryBean queryBean = new OwnerQueryBean();
					queryBean.setStart(0);
					queryBean.setLength(10);
					queryBean = ownerService.findOwnersList(queryBean);
					ownerInfoListPanel.setQueryBean(queryBean);
				};
			}.start();
		}
		cardLayout.show(contentPanel, pageName);
	}

	/**
	 * 跳转到保单列表页面
	 * 
	 * @Date 2017年7月14日
	 * @param pageName
	 * @param param
	 */
	private void jumpToGuaranteeListPage(String pageName, final Object param) {
		if (param instanceof GuaranteeQueryBean) {
			new Thread() {
				public void run() {
					GuaranteeQueryBean queryBean = (GuaranteeQueryBean) param;
					queryBean = guaranteeInfoService.findGuaranteeList(queryBean);
					guaranteeInfolistPanel.setQueryBean(queryBean);
				};
			}.start();
		}
		if (param == null) {
			new Thread() {
				public void run() {
					guaranteeInfolistPanel.cleanQueryBean();
					GuaranteeQueryBean queryBean = new GuaranteeQueryBean();
					queryBean.setStart(0);
					queryBean.setLength(10);
					queryBean = guaranteeInfoService.findGuaranteeList(queryBean);
					guaranteeInfolistPanel.setQueryBean(queryBean);
				};
			}.start();
		}
		cardLayout.show(contentPanel, pageName);
	}

	/**
	 * 跳转到新增保单页面
	 * 
	 * @Date 2017年7月14日
	 * @param pageName
	 * @param param
	 */
	private void jumpToGuaranteeEditPage(String pageName, Object param) {
		if (param instanceof Integer) {
			Integer guaranteeId = (Integer) param;
			GuaranteeInfoDto guaranteeInfoDto = guaranteeInfoService.findGuaranteeDetailById(guaranteeId);
			editGuaranteePanel.setGuaranteeInfoDto(guaranteeInfoDto);
		}
		if (param instanceof CarInfoDto) {
			CarInfoDto carInfoDto = (CarInfoDto) param;
			GuaranteeInfoDto guaranteeInfoDto = new GuaranteeInfoDto();
			guaranteeInfoDto.setCarInfo(carInfoDto.getCarInfo());
			guaranteeInfoDto.setOwner(carInfoDto.getOwner());
			editGuaranteePanel.setGuaranteeInfoDto(guaranteeInfoDto);
		}
		if (param instanceof CarInfoBean) {
			// 从车列添加保单跳转页面
			CarInfoBean carInfoBean = (CarInfoBean) param;
			Integer carId = carInfoBean.getId();
			CarInfoDto carInfoDto;
			try {
				carInfoDto = carInfoService.findCarInfoDtoById(carId);
				GuaranteeInfoDto guaranteeInfoDto = new GuaranteeInfoDto();
				guaranteeInfoDto.setCarInfo(carInfoDto.getCarInfo());
				guaranteeInfoDto.setOwner(carInfoDto.getOwner());
				editGuaranteePanel.setGuaranteeInfoDto(guaranteeInfoDto);
			} catch (ServiceException e) {
				e.printStackTrace();
			}

		}
		if (param == null) {
			// 从菜单页面跳转到新增保单页面
			editGuaranteePanel.setEditable(true);
			editGuaranteePanel.setGuaranteeInfoDto(null);

		}
		cardLayout.show(contentPanel, pageName);
	}

	/**
	 * 跳转到保单详情页面
	 * 
	 * @Date 2017年7月14日
	 * @param pageName
	 * @param param
	 */
	private void jumpToGuaranteeDetailPage(String pageName, Object param) {
		if (param instanceof Integer) {
			Integer guaranteeId = (Integer) param;
			guaranteeDetailPanel.setGuaranteeInfoDto(guaranteeInfoService.findGuaranteeDetailById(guaranteeId));
		}
		if (param instanceof GuaranteeInfoDto) {
			GuaranteeInfoDto guaranteeInfoDto = (GuaranteeInfoDto) param;
			guaranteeDetailPanel.setGuaranteeInfoDto(guaranteeInfoDto);
		}
		if (param == null) {
			guaranteeDetailPanel.setGuaranteeInfoDto(null);
		}
		cardLayout.show(contentPanel, pageName);
	}

	/**
	 * 跳转到车辆编辑页面
	 * 
	 * @Date 2017年7月14日
	 * @param pageName
	 * @param param
	 */
	private void jumpToCarEditPage(String fromPage, String pageName, final Object param) {
		if (MyPage.OWNER_INFO_LIST.equals(fromPage)) {
			// 从用户列表跳到车辆编辑页面
			if (param instanceof Owner) {
				new Thread() {
					public void run() {
						List<OrgInfo> orgList = systemService.findToprgs(); // 查找公司组织
						editCarInfoPanel.setOrgList(orgList);
						CarInfoDto carInfoDto = new CarInfoDto();
						Owner owner = (Owner) param;
						carInfoDto.setOwner(owner);
						editCarInfoPanel.setCarInfoDto(carInfoDto);
					};
				}.start();
			}
		}
		if (MyPage.CAR_INFO_LIST.equals(fromPage)) {
			// 从车辆列表跳到车辆编辑页面
			if (param instanceof Integer) {
				new Thread() {
					public void run() {
						Integer carId = (Integer) param;
						CarInfoDto carInfoDto;
						try {
							List<OrgInfo> orgList = systemService.findToprgs(); // 查找公司组织
							editCarInfoPanel.setOrgList(orgList);
							carInfoDto = carInfoService.findCarInfoDtoById(carId);
							editCarInfoPanel.setCarInfoDto(carInfoDto);
						} catch (ServiceException e) {
							e.printStackTrace();
						}
					};
				}.start();
			}
		}

		if (param instanceof CarInfoDto) {
			new Thread() {
				public void run() {
					List<OrgInfo> orgList = systemService.findToprgs(); // 查找公司组织
					editCarInfoPanel.setOrgList(orgList);
					CarInfoDto carInfoDto = (CarInfoDto) param;
					editCarInfoPanel.setCarInfoDto(carInfoDto);
				};
			}.start();
		}
		if (param == null) {
			new Thread() {
				public void run() {
					List<OrgInfo> orgList = systemService.findToprgs(); // 查找公司组织
					editCarInfoPanel.setOrgList(orgList);
					editCarInfoPanel.setCarInfoDto(null);
				};
			}.start();
		}
		cardLayout.show(contentPanel, pageName);
	}

	/**
	 * 跳转到车辆详情页面
	 * 
	 * @Date 2017年7月14日
	 * @param pageName
	 * @param param
	 */
	private void jumpToCarInfoDetail(String pageName, Object param) {
		if (param instanceof Integer) {
			try {
				Integer carId = (Integer) param;
				CarInfoDetailBean carInfoDetailBean = carInfoService.findCarInfoDetailById(carId);
				carInfoDetailPanel.setCarInfoDetailBean(carInfoDetailBean);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		cardLayout.show(contentPanel, pageName);
	}

	/**
	 * 跳转到车辆列表页面
	 * 
	 * @Date 2017年7月14日
	 * @param pageName
	 * @param param
	 */
	private void jumpToCarInfoListPage(String pageName, final Object param) {
		if (param instanceof CarInfoQueryBean) {
			new Thread() {
				public void run() {
					try {
						CarInfoQueryBean queryBean = (CarInfoQueryBean) param;
						queryBean = carInfoService.findCarInfoList(queryBean);
						carInfolistPanel.setQueryBean(queryBean);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				};
			}.start();
			menuTree.setSelectionRow(MyMenu.CAR_LIST);
		}
		if (param == null) {
			carInfolistPanel.setOrgList(systemService.findToprgs());
			new Thread() {
				public void run() {
					try {
						CarInfoQueryBean queryBean = new CarInfoQueryBean();
						carInfolistPanel.cleanQueryBean();
						queryBean.setStart(0);
						queryBean.setLength(10);
						queryBean = carInfoService.findCarInfoList(queryBean);
						carInfolistPanel.setQueryBean(queryBean);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				};
			}.start();

		}
		cardLayout.show(contentPanel, pageName);
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}

	public MainFrame() {
		super(1000, 700);
		initFrame();
		setResizable(true);
	}

	/**
	 * 窗体初始化
	 * 
	 * @Date 2017年7月9日
	 */
	private void initFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("车辆管理系统");
		setLayout(new BorderLayout());
		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout);
		carInfolistPanel = new CarInfolistPanel();
		editCarInfoPanel = new EditCarInfoPanel();
		carInfoDetailPanel = new CarInfoDetailPanel();
		ownerInfoListPanel = new OwnerInfoListPanel();
		editOwnerInfoPanel = new EditOwnerInfoPanel();
		guaranteeInfolistPanel = new GuaranteeInfolistPanel();
		guaranteeDetailPanel = new GuaranteeDetailPanel();
		editGuaranteePanel = new EditGuaranteePanel();
		orgInfoPanel = new OrgInfoPanel();
		mainPanel = new MainPanel();
		cardLayout.addLayoutComponent(carInfolistPanel, MyPage.CAR_INFO_LIST);
		cardLayout.addLayoutComponent(editCarInfoPanel, MyPage.CAR_INFO_EDIT);
		cardLayout.addLayoutComponent(carInfoDetailPanel, MyPage.CAR_INFO_DETAIL);
		cardLayout.addLayoutComponent(ownerInfoListPanel, MyPage.OWNER_INFO_LIST);
		cardLayout.addLayoutComponent(editOwnerInfoPanel, MyPage.OWNER_EDIT);
		cardLayout.addLayoutComponent(guaranteeInfolistPanel, MyPage.GUARANTEE_INFO_LIST);
		cardLayout.addLayoutComponent(guaranteeDetailPanel, MyPage.GUARANTEE_INFO_DETAIL);
		cardLayout.addLayoutComponent(editGuaranteePanel, MyPage.GUARANTEE_EDIT);
		cardLayout.addLayoutComponent(orgInfoPanel, MyPage.ORG_INFO_PAGE);
		cardLayout.addLayoutComponent(mainPanel, MyPage.MAIN_PAGE);
		contentPanel.add(mainPanel);
		contentPanel.add(carInfolistPanel);
		contentPanel.add(editCarInfoPanel);
		contentPanel.add(carInfoDetailPanel);
		contentPanel.add(ownerInfoListPanel);
		contentPanel.add(editOwnerInfoPanel);
		contentPanel.add(guaranteeInfolistPanel);
		contentPanel.add(guaranteeDetailPanel);
		contentPanel.add(editGuaranteePanel);
		contentPanel.add(orgInfoPanel);
		createMenu();
		addListener();
		fillDate();
		setVisible(true);
	}

	/**
	 * 填充数据源
	 * 
	 * @Date 2017年7月9日
	 */
	private void fillDate() {
		applicationContext = new ClassPathXmlApplicationContext("/spring-context.xml");
		carInfoService = applicationContext.getBean(CarInfoService.class);
		guaranteeInfoService = applicationContext.getBean(GuaranteeInfoService.class);
		ownerService = applicationContext.getBean(OwnerService.class);
		systemService = applicationContext.getBean(SystemService.class);
		editCarInfoPanel.setJumpPageListener(jumpPageListener);
		editCarInfoPanel.setFrameListener(frameListener);
		carInfolistPanel.setJumpPageListener(jumpPageListener);
		carInfolistPanel.setFrameListener(frameListener);
		carInfoDetailPanel.setJumpPageListener(jumpPageListener);
		ownerInfoListPanel.setJumpPageListener(jumpPageListener);
		editOwnerInfoPanel.setJumpPageListener(jumpPageListener);
		editOwnerInfoPanel.setOwnerService(ownerService);
		guaranteeInfolistPanel.setJumpPageListener(jumpPageListener);
		guaranteeDetailPanel.setJumpPageListener(jumpPageListener);
		editGuaranteePanel.setJumpPageListener(jumpPageListener);
		editGuaranteePanel.setGuaranteeInfoService(guaranteeInfoService);
		editCarInfoPanel.setCarInfoService(carInfoService);
		orgInfoPanel.setSystemService(systemService);
		orgInfoPanel.setJumpPageListener(jumpPageListener);
	}

	/**
	 * 创建主菜单
	 * 
	 * @Date 2017年7月9日
	 */
	private void createMenu() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的管理系统");
		menuTree = new JTree(root);
		DefaultMutableTreeNode carManageNode = new DefaultMutableTreeNode("车辆管理");
		DefaultMutableTreeNode carInfoListNode = new DefaultMutableTreeNode("车辆信息");
		DefaultMutableTreeNode addCarInfoNode = new DefaultMutableTreeNode("新增车辆");
		carManageNode.add(carInfoListNode);
		carManageNode.add(addCarInfoNode);

		DefaultMutableTreeNode ownerManageNode = new DefaultMutableTreeNode("车主管理");
		DefaultMutableTreeNode ownerInfoListNode = new DefaultMutableTreeNode("车主信息");
		DefaultMutableTreeNode addOwnerInfoNode = new DefaultMutableTreeNode("新增车主");
		ownerManageNode.add(ownerInfoListNode);
		ownerManageNode.add(addOwnerInfoNode);

		DefaultMutableTreeNode guaranteeManageNode = new DefaultMutableTreeNode("保单管理");
		DefaultMutableTreeNode guaranteeInfoListNode = new DefaultMutableTreeNode("保单信息");
		DefaultMutableTreeNode addguaranteeInfoNode = new DefaultMutableTreeNode("新增保单");
		guaranteeManageNode.add(guaranteeInfoListNode);
		guaranteeManageNode.add(addguaranteeInfoNode);

		DefaultMutableTreeNode finacialManageNode = new DefaultMutableTreeNode("财务管理");
		DefaultMutableTreeNode finacialInfoListNode = new DefaultMutableTreeNode("账目列表");
		DefaultMutableTreeNode addFinacialInfoNode = new DefaultMutableTreeNode("新增账目");
		finacialManageNode.add(finacialInfoListNode);
		finacialManageNode.add(addFinacialInfoNode);

		DefaultMutableTreeNode contractManageNode = new DefaultMutableTreeNode("合同管理");
		DefaultMutableTreeNode contractInfoListNode = new DefaultMutableTreeNode("合同列表");
		DefaultMutableTreeNode addcontractInfoNode = new DefaultMutableTreeNode("新增合同");
		contractManageNode.add(contractInfoListNode);
		contractManageNode.add(addcontractInfoNode);

		DefaultMutableTreeNode sysManageNode = new DefaultMutableTreeNode("系统管理");
		DefaultMutableTreeNode orgNode = new DefaultMutableTreeNode("组织结构");
		sysManageNode.add(orgNode);

		root.add(carManageNode);
		root.add(ownerManageNode);
		root.add(guaranteeManageNode);
		root.add(finacialManageNode);
		root.add(contractManageNode);
		root.add(sysManageNode);
		menuTree.setPreferredSize(new Dimension(120, 100));
		add(menuTree, BorderLayout.WEST);
		add(contentPanel);
	}

	private void addListener() {
		menuTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				String node = e.getPath().getLastPathComponent().toString();
//				int a=3/0;
//				System.out.println(a);
				switch (node) {
				case MyMenu.NODE_CAR_LIST:
					jumpPageListener.setJumpParam(null, MyPage.CAR_INFO_LIST, null);
					break;
				case MyMenu.NODE_CAR_ADD:
					jumpPageListener.setJumpParam(null, MyPage.CAR_INFO_EDIT, null);
					break;
				case MyMenu.NODE_OWNER_LIST:
					jumpPageListener.setJumpParam(null, MyPage.OWNER_INFO_LIST, null);
					break;
				case MyMenu.NODE_OWNER_ADD:
					jumpPageListener.setJumpParam(null, MyPage.OWNER_EDIT, null);
					break;
				case MyMenu.NODE_GUARANTEE_LIST:
					jumpPageListener.setJumpParam(null, MyPage.GUARANTEE_INFO_LIST, null);
					break;
				case MyMenu.NODE_GUARANTEE_ADD:
					jumpPageListener.setJumpParam(null, MyPage.GUARANTEE_EDIT, null);
					break;
				case MyMenu.NODE_ORG_MENU:
					jumpPageListener.setJumpParam(null, MyPage.ORG_INFO_PAGE, null);
					break;
				default:
					return;
				}
				jumpPageListener.JumpToPage();
			}
		});
	}
}
