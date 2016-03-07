package cn.ustc.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.ustc.domain.Administer;
import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.domain.User;
import cn.ustc.web.service.AdministerService;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private String usertype=null;//登录者类型
	
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	@Override
	public User getModel() {
		return user;
	}

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//写get方法用于注入方式获取对象
	private ProfessorService professorService;
	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	private CompanyService companyService;
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	private AdministerService administerService;
	public void setAdministerService(AdministerService administerService) {
		this.administerService = administerService;
	}

	/**
	 * 登录--要判断登录用户的类型
	 * @return
	 * @throws SQLException
	 */
	@InputConfig(resultName="loginINPUT")
	public String login() {
		if (user.getName() == null || "".equals(user.getName().trim())) {
			return "loginINPUT";
		}
		if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
			return "loginINPUT";
		}
		
		if(!this.verify()){
			this.addActionError("验证码错误");
			return "loginINPUT";
		}

		// TODO 判断用户类型 进行相应用户表查找
		if (user.getUsertype().equals("professor")) {
			Professor professor = new Professor();
			professor.setName(user.getName());
			professor.setPassword(user.getPassword());
			professor = professorService.login(professor);

			if (professor != null) {
				ServletActionContext.getServletContext().setAttribute("user",
						professor);
				return "professorloginSUCCESS";
			}

		} else if (user.getUsertype().equals("company")) {
			Company company = new Company();
			company.setName(user.getName());
			company.setPassword(user.getPassword());
			company = companyService.login(company);
			if (company != null) {
				ServletActionContext.getServletContext().setAttribute("user",
						company);
				return "companyloginSUCCESS";
			}

		} else if (user.getUsertype().equals("administer")) {
			Administer administer = new Administer();
			administer.setName(user.getName());
			administer.setPassword(user.getPassword());
			administer = administerService.login(administer);
			if (administer != null) {
				ServletActionContext.getServletContext().setAttribute("user",
						administer);
				return "administerloginSUCCESS";
			}
		}
		if(user.getUsertype().equals("normal")){
			User loginUser = userService.login(user);
			if(loginUser != null){
				ServletActionContext.getServletContext().setAttribute("user", loginUser);
				return "loginSUCCESS";
			}
		}
		
		addActionError("查无此人");
		return "loginINPUT";
	}
	
	/**
	 * 增加普通用户
	 * @return
	 * @throws SQLException 
	 */
	@InputConfig(resultName="registerINPUT")
	public String register() {
		boolean res = userService.insertUser(user);
		System.out.println(res);
		return "registerSUCCESS";
	}
	
	// 放入struts 值栈，用于所有普通用户显示
	private List<User> users;
	public List<User> getUsers() {
		return users;
	}
	/**
	 * 普通用户信息列表
	 * @return
	 */
	public String list(){
		users = userService.findAllUser();
		return "listSUCCESS";
	}
	
	/**
	 * 普通用户信息显示
	 * @return
	 */
	public String view(){
		user = userService.findUserById(user.getId());
		return "viewSUCCESS";
	}
	
	/**
	 * 修改普通用户信息前，先显示该用户信息
	 * @return
	 */
	public String editview(){
		user = userService.findUserById(user.getId());
		return "editviewSUCCESS";
	}
	
	/**
	 * 用户信息修改
	 * @return
	 */
	public String edit(){
		userService.update(user);
		return "editSUCCESS";
	}
	
	/**
	 * 用户信息删除
	 * @return
	 */
	public String delete(){
		userService.deleteUserById(user.getId());
		return "deleteSUCCESS";
	}
	
	/**
	 * 离焦判断用户名是否存在
	 * @return
	 */
	public String checkUserName(){
		if(!"".equals(user.getName())){
			List<User> list = userService.findUserByName(user.getName());
			if(list.size() > 0){
				user.setNameExsit(true);
			}else{
				user.setNameExsit(false);
			}
		}
		return SUCCESS;
	}
	
	/************************ 登录验证码 ****************************/
	public boolean verify(){
		String captcha = ServletActionContext.getRequest().getParameter("captcha");
		String sRand = (String) ServletActionContext.getRequest().getSession().getAttribute("captcha");
		if (captcha == null || !captcha.toLowerCase().equals(sRand.toLowerCase())) {
			return false;
		}else{
			return true;
		}
	}
	
	public void getCode() throws ServletException, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		
		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应图片,一定不能缺少这句话,否则错误.
		response.setContentType("image/jpeg");

		Random random = new Random();
		// 输出由英文，数字，和中文随机组成的验证文字，具体的组合方式根据生成随机数确定。
		StringBuilder sRand = new StringBuilder();
		String[] rBase = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
				"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
				"M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		// 制定输出的验证码为四位
		for (int i = 0; i < 4; i++) {
			int randNum = random.nextInt(60);
			sRand.append(rBase[randNum]);
		}

		ServletActionContext.getRequest().getSession().setAttribute("captcha", sRand.toString());
		// 生成图片
		int w = 100, h = 40;
		outputImage(w, h, response.getOutputStream(), sRand.toString());
	}

	public void outputImage(int w, int h, File outputFile, String code)
			throws IOException {
		if (outputFile == null) {
			return;
		}
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			outputFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outputFile);
			outputImage(w, h, fos, code);
			fos.close();
		} catch (IOException e) {
			throw e;
		}
	}

	public void outputImage(int w, int h, OutputStream os, String code)
			throws IOException {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
				Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
				Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		g2.setColor(Color.GRAY);// 设置边框色
		g2.fillRect(0, 0, w, h);

		Color c = getRandColor(200, 250);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 2, w, h - 4);

		// 绘制干扰线
		Random random = new Random();
		g2.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}

		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		shear(g2, w, h, c);// 使图片扭曲

		g2.setColor(getRandColor(100, 160));
		int fontSize = h - 4;
		Font font = new Font("Algerian", Font.ITALIC, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(
					Math.PI / 4 * rand.nextDouble()
							* (rand.nextBoolean() ? 1 : -1), (w / verifySize)
							* i + fontSize / 2, h / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2
					+ fontSize / 2 - 10);
		}

		g2.dispose();
		ImageIO.write(image, "jpg", os);
	}

	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private int[] getRandomRgb() {
		Random random = new Random();
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	private void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private static void shearX(Graphics g, int w1, int h1, Color color) {
		Random random = new Random();
		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}

	private static void shearY(Graphics g, int w1, int h1, Color color) {
		Random random = new Random();
		int period = random.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}
	/**********************************************************************/
	
	public String companyRegister(){
		return "compRegister";
	}
	public String professorRegister(){
		return "professorRegister";
	}
	
}
