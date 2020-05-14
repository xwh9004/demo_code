
package com.demo.truck.remote;

import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.bean.GuaranteeQueryBean;

/**
 * 
 * @author demo
 * @date 2017年7月7日
 */
public interface GuaranteeInfoService {

	/**
	 * 保存保单
	 *@Date 2017年7月7日
	 * @param guaranteeInfoDto
	 * @return
	 */
	public boolean saveGuaranteeInfo(GuaranteeInfoDto guaranteeInfoDto);
	/**
	 * 提交保单
	 *@Date 2017年7月14日
	 * @param guaranteeInfoDto
	 * @return
	 */
	public boolean submitGuaranteeInfo(GuaranteeInfoDto guaranteeInfoDto);
	
	/**
	 * 查询保单列表信息
	 *@Date 2017年7月7日
	 * @param queryBean
	 * @return
	 */
	public GuaranteeQueryBean findGuaranteeList(GuaranteeQueryBean queryBean);
	/**
	 * 查看保单详细信息
	 *@Date 2017年7月7日
	 * @param guaranteeId
	 * @return
	 */
	public GuaranteeInfoDto findGuaranteeDetailById(Integer guaranteeId);
}
