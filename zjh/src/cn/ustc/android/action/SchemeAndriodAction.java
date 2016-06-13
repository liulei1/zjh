package cn.ustc.android.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Professor;
import cn.ustc.domain.Scheme;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.SchemeService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 方案处理类
 * @author liu
 *
 */
public class SchemeAndriodAction extends ActionSupport implements ModelDriven<Scheme>, ServletResponseAware {
	private Scheme model = new Scheme();
	private HttpServletResponse response;
	@Autowired
	private SchemeService schemeService;
	@Autowired
	private ProfessorService professorService;
	
	/**
	 * 方案详情
	 * 调用条件：传入方案Id
	 * @return
	 */
	public String findSchemeDetailsById(){
		model = schemeService.findById(model.getId());
		return SUCCESS;
	}
	
	/**
	 * 发布方案
	 * 调用条件：传入方案信息
	 * @return
	 */
	public void publishScheme(){
		schemeService.publish(model);
		DetachedCriteria criteria = DetachedCriteria.forClass(Scheme.class);
		criteria.add(Restrictions.eq("cons_id", model.getCons_id()));
		Professor professor = professorService.findProfessorById(model.getProfessor().getId());
		criteria.add(Restrictions.eq("professor", professor));
		
		// 不允许对一个项目发布多个方案，只保存最新的方案
		List<Scheme> list = schemeService.findByDetachedCriteria(criteria);
		if(list.size() > 0){
			for(int i=0; i<list.size(); i++){
				Scheme scheme = list.get(i);
				// 删除之前上传的文件
				if(scheme.getFilePath() != null){
					File f = new File(scheme.getFilePath());
					if(f != null){
						f.delete();
					}
				}
				schemeService.delete(scheme);
			}
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write("success");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pw.flush();
		pw.close();
	}
	
	/****************************成员变量set，get方法*********************************/
	@Override
	public Scheme getModel() {
		return model;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
