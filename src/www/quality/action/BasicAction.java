package www.quality.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import www.quality.model.TblUser;
import www.quality.service.TblCzfwglService;
import www.quality.service.TblDepartmentService;
import www.quality.service.TblFunctionService;
import www.quality.service.TblGridService;
import www.quality.service.TblGrjkdaService;
import www.quality.service.TblGrjlService;
import www.quality.service.TblHjglService;
import www.quality.service.TblJkzxService;
import www.quality.service.TblJkzxflService;
import www.quality.service.TblJmxxService;
import www.quality.service.TblJnflService;
import www.quality.service.TblLcrkService;
import www.quality.service.TblLogService;
import www.quality.service.TblLoginLogService;
import www.quality.service.TblMdclService;
import www.quality.service.TblMdflService;
import www.quality.service.TblMlflService;
import www.quality.service.TblMlglService;
import www.quality.service.TblOperationService;
import www.quality.service.TblProjectService;
import www.quality.service.TblPxjnService;
import www.quality.service.TblQyzpService;
import www.quality.service.TblRegionService;
import www.quality.service.TblRoleAndOperationService;
import www.quality.service.TblRoleService;
import www.quality.service.TblSyfwglService;
import www.quality.service.TblTsjlService;
import www.quality.service.TblTsrysjService;
import www.quality.service.TblUserAndRoleService;
import www.quality.service.TblUserService;
import www.quality.service.TblWhflService;
import www.quality.service.TblWhylService;
import www.quality.service.TblYlzsService;
import www.quality.service.TblYlzsflService;
import www.quality.service.TblZwflService;
import www.quality.service.TblZzService;
import www.quality.service.TblZzrxxService;
import www.quality.util.Pager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类的描述:该类是对request、response、session作用域进行操作,所有的Action继承该类 作者:杜长吉 创建日期:2012-03-19
 * 
 * 修改人 修改日期 修改原因描述
 */
@SuppressWarnings("serial")
public class BasicAction extends ActionSupport {

	private Pager _page = new Pager();
	private static final String user_key = "user";

	/**** 公共权限service方法（逻辑服务层） ****/
	@Resource(name = TblUserService.TBLUSER_SERVICE_IMPL)
	protected TblUserService userser;// 用户表
	@Resource(name = TblRoleService.TBLROLE_SERVICE_IMPL)
	protected TblRoleService roleser;// 角色表
	@Resource(name = TblUserAndRoleService.TBLUSERANDROLE_SERVICE_IMPL)
	protected TblUserAndRoleService uarser;// 用户和角色的关联表
	@Resource(name = TblProjectService.TBLPROJECT_SERVICE_IMPL)
	protected TblProjectService proser;// 项目信息表
	@Resource(name = TblFunctionService.TBLFUNCTION_SERVICE_IMPL)
	protected TblFunctionService funser;// 功能表
	@Resource(name = TblOperationService.TBLOPERATION_SERVICE_IMPL)
	protected TblOperationService opser;// 功能操作表
	@Resource(name = TblRoleAndOperationService.TBLROLEANDOPERATION_SERVICE_IMPL)
	protected TblRoleAndOperationService raoser;// 角色和功能操作表的关联表
	@Resource(name = TblLoginLogService.TBLLOGINLOG_SERVICE_IMPL)
	protected TblLoginLogService loginlogser;// 用户登陆日志表
	@Resource(name = TblLogService.TBLLOG_SERVICE_IMPL)
	protected TblLogService logser;// 系统操作日志表
	@Resource(name = TblGridService.TBLGRID_SERVICE_IMPL)
	protected TblGridService gridser;// 网格表
	@Resource(name = TblYlzsflService.TBLYLZSFL_SERVICE_IMPL)
	protected TblYlzsflService ylzsflser;// 医疗知识分类表
	@Resource(name = TblYlzsService.TBLYLZS_SERVICE_IMPL)
	protected TblYlzsService ylzsser;// 医疗知识表
	@Resource(name = TblJkzxflService.TBLJKZXFL_SERVICE_IMPL)
	protected TblJkzxflService jkzxflser;// 健康咨询分类表
	@Resource(name = TblJkzxService.TBLJKZX_SERVICE_IMPL)
	protected TblJkzxService jkzxser;// 健康咨询
	@Resource(name = TblGrjkdaService.TBLGRJKDA_SERVICE_IMPL)
	protected TblGrjkdaService grjkdaser;// 个人健康档案
	@Resource(name = TblDepartmentService.TBLDEPARTMENT_SERVICE_IMPL)
	protected TblDepartmentService departmentser;// 部门表
	@Resource(name = TblMlflService.TBLMLFL_SERVICE_IMPL)
	protected TblMlflService mlflser;// 目录分类
	@Resource(name = TblMlglService.TBLMLGL_SERVICE_IMPL)
	protected TblMlglService mlglser;// 目录管理
	@Resource(name = TblMdflService.TBLMDFL_SERVICE_IMPL)
	protected TblMdflService mdflser;// 矛盾分类
	@Resource(name = TblMdclService.TBLMDCL_SERVICE_IMPL)
	protected TblMdclService mdclser;// 矛盾处理
	@Resource(name = TblGrjlService.TBLGRJL_SERVICE_IMPL)
	protected TblGrjlService grjlser;// 个人简历
	@Resource(name = TblQyzpService.TBLQYZP_SERVICE_IMPL)
	protected TblQyzpService qyzpser;// 企业招聘
	@Resource(name = TblPxjnService.TBLPXJN_SERVICE_IMPL)
	protected TblPxjnService pxjnser;// 培训技能
	@Resource(name = TblJnflService.TBLJNFL_SERVICE_IMPL)
	protected TblJnflService jnflser;// 技能分类
	@Resource(name = TblRegionService.TBLREGION_SERVICE_IMPL)
	protected TblRegionService regionser;// 行政区划
	@Resource(name = TblZwflService.TBLZWFL_SERVICE_IMPL)
	protected TblZwflService zwflser;// 职位分类
	@Resource(name = TblTsjlService.TBLTSJL_SERVICE_IMPL)
	protected TblTsjlService tsjlser;// 推送记录
	
	@Resource(name = TblWhflService.TBLWHFL_SERVICE_IMPL)
	protected TblWhflService whflser;// 文化娱乐分类
	@Resource(name = TblWhylService.TBLWHYL_SERVICE_IMPL)
	protected TblWhylService whylser;// 文化娱乐

	@Resource(name = TblJmxxService.TBLJMXX_SERVICE_IMPL)
	protected TblJmxxService jmxxser;// 居民信息
	@Resource(name = TblZzService.TBLZZ_SERVICE_IMPL)
	protected TblZzService zzser;// 住宅信息
	@Resource(name = TblSyfwglService.TBLSYFWGL_SERVICE_IMPL)
	protected TblSyfwglService syfwglser;// 实有房屋管理
	@Resource(name = TblHjglService.TBLHJGL_SERVICE_IMPL)
	protected TblHjglService hjglser;// 户籍管理
	@Resource(name = TblTsrysjService.TBLTSRYSJ_SERVICE_IMPL)
	protected TblTsrysjService tsryser;// 特殊人员管理
	@Resource(name = TblCzfwglService.TBLCZFWGL_SERVICE_IMPL)
	protected TblCzfwglService czfwglser;// 特殊人员管理
	@Resource(name = TblZzrxxService.TBLZZRXX_SERVICE_IMPL)
	protected TblZzrxxService zzrxxser;// 特殊人员管理
	@Resource(name = TblLcrkService.TBLLCRK_SERVICE_IMPL)
	protected TblLcrkService lcrkser;// 特殊人员管理
	
	/********/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void setAttribute(String key, Object value) {
		Map m = (Map) ActionContext.getContext().get("request");
		m.put(key, value);
	}

	protected void setSessionsetAttribute(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	/**
	 * ajax 请求返回视图
	 * 
	 * @param data
	 *            ajax结果
	 * @return
	 */
	protected String setAjaxData(String data) {
		try {
			if (data != null && !data.trim().equals("")) {
				// 此时请求 的 字符集 为 struts.i18n.encoding
				String ENDODING = ServletActionContext.getRequest()
						.getCharacterEncoding();
				// 设置相应的 字符集 为 struts.i18n.encoding
				ServletActionContext.getResponse().setCharacterEncoding(
						ENDODING);
				PrintWriter pw = ServletActionContext.getResponse().getWriter();
				pw.append(data);
				pw.flush();
				pw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 此方法为 iframe 请求的返回视图 页面为空页面 只把 参数 script 写入 <script><script/> 标签内部
	 * 
	 * @param script
	 *            写入 <script><script/> 标签内部的代码
	 * @param isAlert
	 *            是否 alert true alert('${script}') false $(script);
	 * @throws IOException
	 */
	protected String setIframeRequestMes(String script, boolean isAlert) {
		try {
			String ENDODING = ServletActionContext.getRequest()
					.getCharacterEncoding();
			ServletActionContext.getResponse().setCharacterEncoding(ENDODING);
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			ServletActionContext.getResponse().setHeader("content",
					"text/html; charset=" + ENDODING);
			pw.append("<html><head>"
					+ "<meta http-equiv=\"content-type\" content=\"text/html; charset="
					+ ENDODING + "\">" + "<script>");
			if (isAlert)
				script = "alert('" + script + "')";
			pw.append(script);
			pw.append("</script></head><body></body></html>");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @param key
	 *            获取 请求作用于参数
	 * @return
	 */
	protected String getParameter(String key) {
		try {
			String[] param = (String[]) ActionContext.getContext()
					.getParameters().get(key);
			return param[0];
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param key
	 *            获取 请求作用于参数 返回值 为 String []
	 * @return
	 */
	protected String[] getParameterValues(String key) {
		try {
			return (String[]) ActionContext.getContext().getParameters()
					.get(key);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param key
	 * @return
	 */
	protected Object getParameterFromSession(Object key) {
		return ActionContext.getContext().getSession().get(key);
	}

	protected TblUser getCurrentUser() {
		return (TblUser) getParameterFromSession(user_key);
	}

	/**
	 * 下载方法
	 * 
	 * @param path
	 *            文件所在路径
	 * @param ContentType
	 *            下载类型
	 * @throws IOException
	 */
	protected String downFile(String path, String ContentType)
			throws IOException {
		if (ContentType == null || ContentType.equals(""))
			ContentType = "application/octet-stream";

		File file = new File(path);
		// 取得文件名。
		String filename = file.getName();
		// 取得文件的后缀名。
		// String ext = filename.substring(filename.lastIndexOf(".") +
		// 1).toUpperCase();
		// 获取文件读取刘
		InputStream fis = new FileInputStream(path);
		// 获取response;
		HttpServletResponse response = ServletActionContext.getResponse();
		// 响应类型
		response.setContentType(ContentType);
		// 获取写流
		ServletOutputStream os = ServletActionContext.getResponse()
				.getOutputStream();
		;
		// 设置下载文件名
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(filename.getBytes()));
		// 设置下载文件大小
		response.addHeader("Content-Length", "" + file.length());
		int i = 0;
		byte[] b = new byte[1024 * 8];
		while ((i = fis.read(b)) > 0) {
			os.write(b, 0, i);
		}
		fis.close();
		os.flush();
		os.close();
		return null;
	}

	public Pager get_page() {
		return _page;
	}

	public void set_page(Pager _page) {
		this._page = _page;
	}

	// 隐藏字段
	public void setFieldToSession() {
		String myfield = getParameter("myfield");// 获取前台传来的参数格式 为
													// project_field:_1_2_3_
		if (myfield != null && !"".equals(myfield)) {// 判断myfield是否为空。
			String sfield = (String) (getParameterFromSession("myfield"));	// 在session里获取已有的参数格式为
																			// {'user_field':'_1_2_3_4_','project_field':'_1_2_3_'}
			if (sfield == null || "".equals(sfield))
				sfield = "{'myfield':'2_3'}";// 判断sfield是否为空，为空 则 初始化

			// 如果sfield中已经包括myfield
			// 将sfield中的myfield更新，否则将myfield填进到sfield中。最后将sfield保存到session中。
			String nfield = myfield.split(":")[0];	// 将myfield按“：”分割
													// 获得“project_field”,和“_1_2_3_”
			String xfield = "'" + nfield + "':'" + myfield.split(":")[1] + "'";	// 把分割好的myfield拼成
																				// ‘project_field’：'_1_2_3_'
																				// 格式
			if (sfield.contains("'" + nfield + "'")) {	// 如果sfield
														// 已经包含了project_field
														// 就通过截字符串，把刚拼好的字符串更新到sfield中。
				int i = sfield.indexOf("'" + nfield + "'");
				int j = sfield.indexOf(",", i + 1);
				if (j == -1)
					j = sfield.length() - 1;
				String str = sfield.substring(i, j);
				sfield = sfield.replace(str, xfield);
			} else {// 如果sfield不存在project_field 就把刚拼好的字符串添加到sfield中
				sfield = sfield.replace("}", "," + xfield + "}");
			}
			setSessionsetAttribute("myfield", sfield);// 把sfield保存到session中
		}
	}

}