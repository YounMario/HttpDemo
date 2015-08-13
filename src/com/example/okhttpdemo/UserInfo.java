package com.example.okhttpdemo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class UserInfo implements Serializable {
	/**
	 * {"code":0,"message":"登录完成","data":{"token":"aaanlvm0Xetl5-cEuVzOu",
	 * "userInfo":{"id":1,"userName":"13911314474","nickName":"小鬼","headUrl":
	 * "http://pic2.sc.chinaz.com/files/pic/pic9/201411/apic7695.jpg"
	 * ,"userPhone":"13911314474","userPwd":"123123","sex":null,"birthday":null,
	 * "userProfessional"
	 * :null,"userHobby":null,"signture":null,"userCreateTime":
	 * "2014-12-01 16:49:37.0"
	 * ,"userAddress":null,"reportNumber":0,"userStatus":1,
	 * "userCommunity":[{"id"
	 * :72,"userId":1,"communityId":"2","ucDate":"2014-12-02 16:38:52.0"
	 * ,"currentCommunity"
	 * :1,"buildingNumber":0,"roomNumber":0,"userStatus":1,"villageName"
	 * :"后现代城"}]}}}
	 */
	private static final long serialVersionUID = 7615986043665731199L;

	private ArrayList<UserCommunity> userCommunity = new ArrayList<UserCommunity>();
	private String userAddress;

	private int id = -1;// 默认-1, 错误用户信息
	private int reportNumber = -1;
	private int userStatus = -1;// 正常，冻结，
	private int userType = 0;// 用户类型 1：用户 2：物业 3:代理商 4:小区运营人员',
	private String userName = "";
	private String headUrl = "";
	private String background = "";
	private String userPhone = "";// 手機號
	private String nickName = "";// 暱稱
	private Integer sex = -1;// 性別
	private String birthday = "";// 生日
	private String userProfessional = "";// 職業
	private String userHobby = "";// 愛好
	private String signture = "";// 個性簽名
	private String userPwd = "";
	private Integer age = -1;
	private String userCreateTime = "";
	private String guizuCode = "";

	private int guizuCodeSwitch = 1;
	private int phoneSwitch = 1;
	private int userNameSwitch = 1;
	private int size;

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getGuizuCodeSwitch() {
		return guizuCodeSwitch;
	}

	public void setGuizuCodeSwitch(int guizuCodeSwitch) {
		this.guizuCodeSwitch = guizuCodeSwitch;
	}

	public int getPhoneSwitch() {
		return phoneSwitch;
	}

	public void setPhoneSwitch(int phoneSwitch) {
		this.phoneSwitch = phoneSwitch;
	}

	public int getUserNameSwitch() {
		return userNameSwitch;
	}

	public void setUserNameSwitch(int userNameSwitch) {
		this.userNameSwitch = userNameSwitch;
	}

	public String getGuizuCode() {
		return guizuCode;
	}

	public void setGuizuCode(String guizuCode) {
		this.guizuCode = guizuCode;
	}

	private UserCommunity currentCommunity = new UserCommunity();// 当前关注社区

	private File headFile;

	public UserCommunity getCurrentCommunity() {
		return currentCommunity;
	}

	public void setCurrentCommunity(UserCommunity currentCommunity) {
		this.currentCommunity = currentCommunity;
	}

	public ArrayList<UserCommunity> getUserCommunity() {
		return userCommunity;
	}

	public void setUserCommunity(ArrayList<UserCommunity> userCommunity) {
		this.userCommunity = userCommunity;
	}

	/**
	 * 判断这个小区id是否是在我的小区列表中
	 * 
	 * @param id
	 * @return
	 */
	public boolean isCommunityInMyCommunityList(String id) {
		for (UserCommunity uc : userCommunity) {
			if (id.equals(uc.getCommunityId())) {
				return true;
			}
		}
		return false;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(int reportNumber) {
		this.reportNumber = reportNumber;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUserProfessional() {
		return userProfessional;
	}

	public void setUserProfessional(String userProfessional) {
		this.userProfessional = userProfessional;
	}

	public String getUserHobby() {
		return userHobby;
	}

	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}

	public String getSignture() {
		return signture;
	}

	public void setSignture(String signture) {
		this.signture = signture;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserCreateTime(String userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	public UserInfo clone() {
		UserInfo user = new UserInfo();
		user.setId(id);
		user.setNickName(nickName);
		user.setSex(sex);
		user.setUserPhone(userPhone);
		user.setBirthday(birthday);
		user.setUserProfessional(userProfessional);
		user.setUserHobby(userHobby);
		user.setSignture(signture);
		user.setAge(age);
		return user;
	}

	public File getHeadFile() {
		return headFile;
	}

	public void setHeadFile(File headFile) {
		this.headFile = headFile;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 用户加入的小区
	 * 
	 * @author sky
	 *
	 */
	public static class UserCommunity implements Serializable {
		private static final long serialVersionUID = -800431059967674703L;

		private Integer id;

		private Integer userId;
		private String villageName = "";
		private String villageAddress = "";
		private String currentCommunity = "";
		private String communityId = "-1";
		private String province = "";
		private String city = "";
		private String county = "";
		private String companyName = "";
		private String propertyTell = "";
		private String propertyHead = "";
		private String propertyPresent = "";

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		/**
		 * 小区类型
		 */
		private String buildType = "";

		public String getBuildType() {
			return buildType;
		}

		public void setBuildType(String buildType) {
			this.buildType = buildType;
		}

		public String getCommunityId() {
			return communityId;
		}

		public void setCommunityId(String communityId) {
			this.communityId = communityId;
		}

		public String getVillageName() {
			return villageName;
		}

		public void setVillageName(String villageName) {
			this.villageName = villageName;
		}

		public String getVillageAddress() {
			return villageAddress;
		}

		public void setVillageAddress(String villageAddress) {
			this.villageAddress = villageAddress;
		}

		public String getCurrentCommunity() {
			return currentCommunity;
		}

		public void setCurrentCommunity(String currentCommunity) {
			this.currentCommunity = currentCommunity;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCounty() {
			return county;
		}

		public void setCounty(String county) {
			this.county = county;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getPropertyTell() {
			return propertyTell;
		}

		public void setPropertyTell(String propertyTell) {
			this.propertyTell = propertyTell;
		}

		public String getPropertyHead() {
			return propertyHead;
		}

		public void setPropertyHead(String propertyHead) {
			this.propertyHead = propertyHead;
		}

		public String getPropertyPresent() {
			return propertyPresent;
		}

		public void setPropertyPresent(String propertyPresent) {
			this.propertyPresent = propertyPresent;
		}

	}

}
