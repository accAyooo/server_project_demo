package com.accAyo.serverProjectDemo.framework.Exception;
/**
 * 成功 已奇数结尾,失败以偶数结尾
 * @author Tim
 *
 */
public enum EnumInfo {
	//ac1-10 登录用
	ACCOUNTS_EMAIL_PASSWORD_NOT_MATCH("ac-1","用户名密码不匹配", ""),
	ACCOUNTS_MODIFY_PASSWORD_SUCCESS("ac-2","修改密码成功", ""),
	ACCOUNTS_PASSWORD_FORMAT_WRONG_FAILED("ac-3","密码只能是6-20位字母数字特殊符号", ""),
	ACCOUNTS_OLD_PASSWORD_NOT_MATCH_FAILED("ac-4","旧密码输入错误", ""),
	ACCOUNTS_TWO_PASSWORD_NOT_MATCH_FAILED("ac-5","两次密码输入不一致", ""),
	ACCOUNTS_SET_SUCCESS("ac-6","个人信息修改成功", ""),
	ACCOUNTS_PASSWORD_ERROR("ac-7", "密码：请输入6-20位密码", ""),
	USER_NOT_EXIST("ac-8", "用户不存在", "10"),
	USER_SETTING_MATCH_NO_REPLY("ac-9", "根据用户设置，您不能进行操作", ""),
	USER_SETTING_MATCH_NO_FOLLLOW("ac-10", "根据用户设置，您不能加关注", ""),

	ACCOUNTS_ICON_SUCESS("ac-11", "头像设置成功", ""),
	ACCOUNTS_ICON_FAILURE("ac-12", "头像设置失败，请稍候再试", ""),
	ACCOUNTS_VERFIY_ERROR("ac-14", "验证码失效", ""),
	ACCOUNTS_VERFIY_AGAIN("ac-15", "重新获取验证码", ""),
	
	ACCOUNTS_NICKNAME_REQUIRED("ac-20", "昵称必须写", ""),
	ACCOUNTS_NICKNAME_ERROR("ac-21", "昵称不符合规则，请重新输入", ""),
	NICKNAME_LENGTH_ERROR("ac-22", "昵称设置失败:昵称长度为2-14位", ""),
	NICKNAME_CHAR_ERROR("ac-23", "昵称设置失败:昵称只能包括汉字、英文字母、数字和下划线(不能是纯数字)", ""),
	NICKNAME_EXIST_ERROR("ac-24", "昵称设置失败:该昵称已被使用", ""),
	NICKNAME_CRAB_ERROR("ac-25", "昵称设置失败:该昵称中含有屏蔽字符", ""),
	NICKNAME_RESERVE_ERROR("ac-26", "昵称设置失败:该昵称中含有保留字符", ""),
	NICKNAME_MODIFY_TIME("ac-27", "昵称设置失败:未到修改时间", ""),
	NICKNAME_MODIFY_SUCCESS("ac-28", "修改昵称成功", ""),
	NICKNAME_CONTRACT_AUTHOR_FAIL("ac-29", "昵称设置失败:有签约作品的作家不能修改昵称", ""),
	NICKNAME_NOT_AUTHOR_FAIL("ac-30", "昵称设置失败:不是作家", ""),		
	
	ACCOUNTS_EMAIL_INPUT_NEED("ac-31", "请输入邮箱", ""),
	ACCOUNTS_NAME_ERROR("ac-32", "用户名只能包括2-30位中文字、英文字母、数字和下划线", ""),
	ACCOUNTS_IM_ERROR("ac-33", "QQ|MSN:只能包括5-15位数字 或email", ""),
	ACCOUNTS_MOBILE_ERROR("ac-34", "请输入正确的手机号:7到15位的数字、加号", ""),
	ACCOUNTS_ADDRESS_ERROR("ac-35", "地址只能输入中英文字母,数字,下划线,减号", ""),
	ACCOUNTS_ZIP_ERROR("ac-36", "邮编：请输入4-8位字符", ""),
	ACCOUNTS_IDNO_ERROR("ac-37", "请输入正确的身份证号码,不能输入特殊字符", ""),
	ACCOUNTS_BECAME_AUTHOR_SUCCESS("ac-38","注册作家成功", ""),
	ACCOUNTS_BECAME_AUTHOR_FAILED("ac-39","注册作家失败", ""),
	ACCOUNTS_U_R_AUTHOR_ALREADY_FAILED("ac-40","您已经是作家", ""),
	ACCOUNTS_REGISTER_CODE_ERROR("ac-71", "您输入的验证码有误，请重新输入！", ""),
	ACCOUNTS_REGISTER_EMAIL_ALREADY("ac-72", "您输入的邮箱已注册，请重新输入！", ""),
	REGISTER_CODE_AGREE_PROTOCOL("ac-73", "请同意注册协议！", ""),
	ACCOUNTS_NOT_AUTHOR("ac-74", "您不是作家！", ""),
	ACCOUNTS_IDNO_ALREADY_EXISTS("ac-75", "您的身份证号已存在！", ""),
	ACCOUNTS_REGISTER_VERIFY_ERROR("ac-rg-76","信息验证失败", ""),
	
	ACCOUNTS_EMAIL_SUCCESS("ac-41","邮件发送成功", ""),
	ACCOUNTS_RESET_PASSWORD_MAIL_FAILED("ac-42","重置密码邮件发送失败，请稍后再试", ""),
	ACCOUNTS_RESET_PASSWORD_SUCCESS("ac-43","重置密码成功", ""),
	ACCOUNTS_RESET_PASSWORD_FAILED("ac-44","验证码失效，请重新申请找回", ""),
	ACCOUNTS_EMAIL_STAFF_ERROR("ac-45","工作人员不允许修改邮箱", ""),
	ACCOUNTS_INVITED_FAILED("ac-46","请确认邮箱是否正确", ""),
	ACCOUNTS_EMAIL_SEND_FAILURE("ac-47","邮件发送失败，请稍后再试", ""),
	ACCOUNTS_EMAIL_SEND_SUCESS("ac-13","验证邮件已发送，请马上登录您的邮箱完成修改邮箱验证", ""),
	ACCOUNTS_EMAIL_ERROR("ac-49", "您注册的邮箱地址不符合规则", ""),
	ACCOUNTS_EMAIL_SEND_TOO_FAST("ac-50", "您发送邮件太快，请5分钟后再试", "3"),

	ACCOUNTS_ADD_STAFF_SUCCESS("ac-51","添加编辑成功", ""),
	ACCOUNTS_ADD_USER_FAILED("ac-52","添加用户失败", ""),
	ACCOUNTS_EMAIL_MUST_END_WITHS_HEIYAN("ac-53","邮箱只能以@zhangwen.cn结尾", ""),
	MODIFY_CITY_SUCCESS("ac-54", "居住地变更成功", ""),
	ACCOUNTS_EMAIL_NOT_EXISIT("ac-55", "该邮箱用户不存在，请检查", "10"), 
	ACCOUNTS_INVITED_REGISTER("ac-56","该邮箱已经被注册", ""),
	ACCOUNTS_INVITED_FREQUENTLY("ac-57","您刚邀请了该邮箱，请5分钟后再进行操作", ""),
	ACCOUNTS_TOPUP_NOT_ACTIVED("ac-58","为防止无法找回账号的情况，您需要激活账号后再充值", ""),
	
	ACCOUNTS_CONTENT_REQUIRED("ac-59", "必须填写！", ""),
	ACCOUNTS_LOCKED("ac-60", "您的上次购买行为未完成，请稍候再试", ""),
	ACCOUNTS_PRIVATE_SETTING("ac-61", "隐私设置成功", ""),
	ACCOUNTS_NO_LOGIN("ac-62", "您尚未登录，请先登录", "1"),
	
	ACCOUNTS_NOT_MASTER("ac-63", "该绑定账户不是主账户", ""),
	ACCOUNTS_NOT_SIGNED("ac-64", "该账户尚未签约，不能绑定", ""),
	ACCOUNTS_ALREADY_BIND("ac-65", "该账户已经绑定", ""),
	ACCOUNTS_ALREADY_APPLY("ac-66", "重复绑定申请", ""),
	ACCOUNTS_ALREADY_SIGN("ac-67", "该账户已经签约，不能申请主账户", ""),
	ACCOUNTS_ALREADY_BOOK_APPLY("ac-68", "您有作品正在申请签约，不能修改账户信息", ""),
	ACCOUNTS_ALREADY_BOOK_SIGN("ac-69", "您有作品已经签约或正在申请签约，不能申请绑定主账户", ""),
	ACCOUNTS_SHUTUP("ac-70", "您已经被禁言。", "4"),
	ACCOUNTS_STAFF_PASSWORD_ERROR("ac-76", "密码：请输入8-20位密码，必须包含字母大小写，数字和特殊符号。", ""),
	ACCOUNTS_USER_PASSWORD_ERROR("ac-77", "登录失败：用户名或者密码错误！", ""),
	ACCOUNTS_NOT_ACTIVED("ac-78", "您的邮箱尚未验证，不能进行该操作！", "2"),
	ACCOUNTS_EMAIL_SHOULD_QQ("ac-79", "您的QQ邮箱输入有误，QQ邮箱必须以qq.com结尾", ""),
	ACCOUNTS_STAFF_PASSWORD_REPEATED("ac-80", "密码：请输入与原密码不同的密码。", ""),
	ACCOUNTS_ALREADY_LOGIN("ac-81", "您已登录", ""),
	ACCOUNTS_MOBILE_CHAECK("ac-82", "请输入正确的手机号", ""),
	ACCOUNTS_ALREADY_ACTIVED("ac-83", "账户已激活", ""),
	NICKNAME_USED_BY_AUTHOR_ERROR("ac-84", "昵称设置失败:该昵称已被签约作家使用", ""),	
	ACCOUNTS_REGISTER_CLOSED("ac-85", "您好，黑岩现已关闭注册功能，您可使用第三方（微信、qq、微博，百度）登录使用，方便快捷，祝您阅读愉快。", ""),
	ACCOUNTS_NOT_THIRDPART("ac-86","您不是第三方帐号登录，不能绑定邮箱", ""),
	ACCOUNTS_IDNO_USED("ac-87","该身份证号已被其他签约作家使用", ""),
	ACCOUNTS_HAS_CHILD_ACCOUNT("ac-88","有子账号正在申请绑定该账户或已绑定子账户", ""),
	ACCOUNTS_MOBILE_NO_BING("ac-89", "您未绑定手机号，不能进行该操作！", ""),
	ACCOUNTS_MOBILE_EMAIL_ERROR("ac-90", "请输入正确的邮箱或手机号", ""),
	ACCOUNTS_NO_MOBILE("ac-91", "该手机号用户不存在，请检查", ""),
	ACCOUNTS_MOBILE_MAIL_NULL("ac-92", "邮箱或手机号不能为空", ""),
	ACCOUNTS_LONG_TIME_NO_LOGIN("ac-96","为了您的账号安全，请点击“忘记密码”修改密码", ""),
	
	PAY_MONEY_INSUFFICIENT("pay-1", "您的账户中余额不足，是否充值后再来支持作者？", "5"),
	PAY_SUCCESS("pay-2", "购买成功", ""),
	PAY_FAIL("pay-3", "购买失败", ""),
	PAYED_OVER("pay-4", "支付余额过量！", ""),
	PAYED_NONEED("pay-5", "免费，无须购买！", ""),
	PAYED_REPEAT("pay-6", "重复购买！", ""),
	PAY_BONUS_INSUFFICIENT("pay-7", "您的账户中余额不足", ""),
	PAY_BONUS_REPEATED("pay-8", "您已经奖励过该书评", ""),
	PAY_BONUS_MONEY_ERROR("pay-12", "请输入正确的打赏金额", ""),
	
//	PAYED_MONEY_LESS("pay-9", "充值金额不得少于" + Constants.PAY_MIN_TOPUP_MONEY + "元", ""),
	PAYED_MONEY_INT("pay-10", "充值金额请输入整数", ""),
	PAYED_INFO_NOT_OK("pay-11", "充值信息填写不正确", ""),
	PAYED_UMPAY_TOO_FAST("pay-13", "短信支付间隔需半小时，请稍候再提交", "8"),
	PAYED_NEED("pay-14", "需要购买", ""),
	PAYED_CLOSE("pay-15", "充值渠道暂时关闭，请稍候再试", ""),
//	PAYED_MONEY_MAX("pay-16", "充值金额不得大于" + Constants.PAY_MAX_TOPUP_MONEY + "元", ""),

	ARTICLE_SAVE_SUCCESS("co-1", "文章已保存", ""),
	BOOK_FINISH_SUCCESS("co-2", "已设置完本", ""),
	CONTENT_LENGTH_LESS("co-3", "内容不得少于5字！", ""),
	CHAPTER_CONTENT_LENGTH_LESS("co-4", "内容字数不得少于", ""),
	NAME_LENGTH_FAILURE("co-5", "标题输入有误，字数必须在100以内", ""),
	SCORE_ADD_ERROR("co-6", "请打分！", ""),
	BOOK_OPEN_WORDS_ERROR("co-7", "公开的作品必须5千字以上！", ""),
	BOOK_APPLY_WORDS_ERROR("co-8", "申请作品的字数未达到要求！", ""),
	BOOK_FINISH_ADD_ERROR("co-9", "完本后不能新建收费章节！", ""),
	BOOK_SIGN_REPEATE_ERROR("co-10", "重复申请频率过快！", ""),
	BOOK_ADD_SORT_ERROR("co-11", "作品未选择分类", ""),
	NAME_LENGTH_20_FAILURE("co-12", "标题输入有误，字数必须在30以内", ""),
	BOOK_RECOMMEND_FAILURE("co-13", "推荐语不能为空，且字数必须在15字之内", "9"),
	ACTION_TOO_FREQUENT("co-14", "您操作的间隔时间太短，请一分钟后再发", ""),
	BOOK_COAUTHOR_LENGTH_OVER("co-15", "合著者字数必须在20以内", ""),
	BOOK_APPLY_CONTRACT_ERROR("co-16", "您的作品没有签约，不用申请完本", ""),
	CHAPTER_THANKS_CONTENT_LESS("co-17", "答谢感言字数不得少于20字", ""),
	CHAPTER_NAME_REPEATED("co-18", "您的章节可能已成功提交（章节名与最新章节名相同）", ""),
	BOOK_ADD_GROUP_ERROR("co-20", "作品未选择频道", ""),
	CHAPER_ADD_VOLUME_ERROR("co-21", "您所选的卷不存在！", "10"),
	CHAPER_UNCHAPTER_FREE_ERROR("co-22", "非章节内容不得收费！", ""),
	BOOK_TAG_LENGTH_OVER("bo-1", "标签数量超过5个", ""),
	
	APPLY_WAITING("ap-1", "申请正在等待审核", "6"),
	APPLY_PASSED("ap-2", "申请已经通过审核", ""),

	INVITE_WAITING("iv-1", "邀请正在等待确认", ""),
	INVITE_PASSED("iv-2", "邀请已被接受", ""),
	INVITE_NO_MORE("iv-3", "不再接收新的邀请", ""),
	
	OBJECT_NOT_EXIST("ob-1", "资源不存在", "10"),
	OBJECT_NO_RIGHT("ob-2", "无权限", "11"),
	OBJECT_SUCCESS("ob-3", "操作成功", ""),
	OBJECT_FAILURE("ob-4", "操作失败", ""),
	OBJECT_PARAM_ERROR("ob-5", "参数错误", ""),
	OBJECT_ALREADY_EXISTS("ob-6", "对象已存在", ""),
	
	BOX_POSITION_ERROR("box-1", "位置应为大小写英文字母、下划线和数字", ""),
	
	PAY_LOG_MAX_ERROR("pl-1", "Pay Log 超出最大数量", ""),
	PAY_LOG_FINISHED_ERROR("pl-2", "由于版权原因，Pay Log 不能生成", ""),
	
	
	FOLLOW_MAX_PEOPLE_OVER("fo-1", "关注量不能超过2000", ""),
	FOLLOW_MAX_BOOK_OVER("fo-2", "关注量不能超过1000", ""),
	FOLLOW_MAX_SITE_OVER("fo-3", "关注量不能超过500", ""),
	
	VOTE_MAX_OVER("vo-1", "选择的选项超过最大限", ""),
	VOTE_END_TIME_ERROR("vo-2", "投票的结束日期不正确", ""),
	VOTE_OPTION_COUNT_ERROR("vo-3", "投票选择项不正确", ""),
	VOTE_OPTION_ALREADY_VOTED("vo-4", "已经投过此票", ""),
	VOTE_OPTION_NOT_EXISIT("vo-5", "选项不存在", "10"),
	VOTE_END("vo-6", "投票已经结束", ""),
	VOTE_SUCCESS("vo-7", "投票成功", ""),
	
	FINANCIAL_END("fi-1", "财务已关闭", ""),
	
	AUTHOR_MESSAGE_NULL("au-1", "作者信息必填项不能为空", "9"),
	AUTHOR_NAME_LENGTH_ERROR("au-2", "真实姓名长度为2-14位", ""),
	INPUT_NUMBER_ERROR("do-1", "请输入数字", ""),
	
	REPLY_OBJECT_NO_MATCH("re-1", "对象类型不匹配", ""),
	REPLY_TOO_FREQUENCE("re-2", "回应太频繁，请过10秒再回应", ""),
	ACTIVE_USER_STATUS("re-3", "请激活后再操作", ""),
	REPLY_CONTENT_NULL("re-4", "回应内容不能为空", "9"),
	
	AUTHOR_CASH_NOT_SUFFICENT("auc-1", "申请兑现金额不得低于一百", ""),
	AUTHOR_CASH_OVER_MAX("auc-3", "申请兑现金额不得超过最大兑现金额", ""),
	AUTHOR_CASH_OVER("auc-2", "充值金额不得低于10元且不得超过200元", ""),
	AUTHOR_CASH_ALREADY("auc-4", "本月已经充值过一次，请下月再充值", ""),
	AUTHOR_CASH_NOT_AUTO("auc-5", "无法转充值，请联系责编", ""),
	AUTHOR_ACCOUNTS_NOT_READY("auc-6", "账号信息不完全，请联系责编", ""),
	AUTHOR_CASH_LESS_100("auc-7", "申请兑现金额不得小于100", ""),
	
	BOOK_VOTE_TICKET_USE_OVER("bookvote-1", "您的票已经使用完！", ""),
	BOOK_VOTE_NO_YANQING("bookvote-2", "您投的不是言情大赛参赛作品！", ""),
	BOOK_VOTE_MONEY_NO_ENOUGH("bookvote-3", "您的充值尚不足1000贝币，还不能投票！", ""),
	BOOK_VOTE_NO_JOIN("bookvote-4", "您投的言情大赛参赛作品没有晋级！", ""),
	BOOK_VOTE_OUT_TIME("bookvote-5", "投票时间已结束！", ""),
	BOOK_VOTE_NOT_ACTIVITED("bookvote-6", "您的帐户还没激活，不能投票！", ""),
	BOOK_AWAIT_TICKET_USE_OVER("bookawait-1", "您今天已经期待过本书了！", ""),
	BOOK_AWAIT_NOEXIST("bookawait-2", "您期待的书不存在！", "10"),
	BOOK_AWAIT_NOT_OPEN("bookawait-3", "您期待的书还没有公开！", "11"),
	
	COINCOME_DELETE_ERROR("coincome-1", "该记录已通过一审，不能删除！", ""),
	
	NEW_BOOK_STATUS_HAS_AUTHOR("newbook-0", "此书的作者已有责编", ""),
	NEW_BOOK_STATUS_XUAN_ZHE("newbook-1", "你已经选择了这本书", ""),
	NEW_BOOK_STATUS_OTHER_XUAN_ZHE("newbook-2", "他人已经选择了这本书", ""),
	
	BOOK_SHELF_NORMAL_SUCCESS("bs-1", "收藏成功", ""),
	BOOK_SHELF_REMOVE_SUCCESS("bs-2", "已成功取消收藏", ""),
	
	DONATE_NEED_NUM("donate-1", "打赏数量必须大于0", ""),
	DONATE_TYPE_NOT_EXISTS("donate-2", "打赏物品不存在，请重新选择打赏", "10"),
	
	BOOK_REVIEW_NOT_ALLOW("ac-9", "根据用户设置，您不能进行操作", ""),

	GROUP_ALREADY_ADMIN_ERROR("gr-1", "已经是管理员", ""),
	GROUP_ALREADY_INVITE_ERROR("gr-2", "已经被邀请", ""),
	GROUP_LEADER_ADMIN_ERROR("gr-3", "只能转让给小组管理员", ""),
	
	GROUP_NAME_LENGTH_ERROR("gr-7", "组名不能超过16字", ""),
	GROUP_NAME_NEED_ERROR("gr-8", "组名必须写", ""),
	GROUP_MEMBER_OVER("gr-9", "组员数量已达到最大值", ""),
	GROUP_MAX_ADMIN_ERROR("gr-11", "创建组不能超过15个", ""),
	GROUP_NOPASS_INSPECTED("gr-10", "该组未通过审核，暂时不能发帖", ""),
	
	TOPIC_TOP_LENGTH_OVER("to-1", "每组置顶贴不能超过15贴", ""),
	TOPIC_NAME_REQUIRED("to-2", "话题标题必须写", ""),
	TOPIC_CONTENT_REQUIRED("to-3", "话题内容不得少于5个字", ""),
	TOPIC_FORBIDDEN_REQUIRED("to-4", "根据管理员的设置，您不能发帖", ""),
	
	HONGBAO_BI_NOT_RIGHT("hb_1", "红包总金额有误，最低10元，最高10万元", ""),
	HONGBAO_COUNT_NOT_RIGHT("hb_2", "红包数量有误，最低10个，最高(总金额/2)个", ""),
	HONGBAO_DOING_ERROR("hb-3", "正在进行中，请稍候", "6"),
	HONGBAO_DONE("hb-4", "已抢完", "7"),
	
	NETWORK_ERROR("net-1", "网络异常", ""),
	
	MISSION_DEADLINE_ERROR("miss-1", "任务时间有问题", "21"),
	MISSION_CANNOT_REWARD("miss-2", "该任务无法再追加", "22"),
	MISSION_PRICE_ERROR("miss-3", "任务赏金最少1000贝币", "23"),
	MISSION_REWARD_PRICE_ERROR("miss-4", "追加赏金最少100贝币", "23"),
	MISSION_ALREADY_OVERDUE("miss-5", "该任务已过期", "24"),
	
	
	LOTTERY_TICKET_NO_LEFT("lottery-1", "您的该活动票已用完！", ""),
	LOTTERY_NOT_AVAILABLE("lottery-3", "该活动已停止！", ""),
	LOTTERY_BEING("lottery-2", "您的抽奖正在进行中！", ""),
	LOTTERY_FAIL("lottery-4", "服务器繁忙！", ""),
	LOTTERY_NO_PRIZE("lottery-5", "无奖品！", ""),
	LOTTERY_PRIZE_GOTING("lottery-6", "奖品已经领取！", ""),
	LOTTERY_PRIZE_ALREADY_GOT("lottery-7", "奖品已经领取！", ""),
	LOTTERY_PRIZE_POST("lottery-8", "地址已确认，不能修改！", ""),
	LOTTERY_PRIZE_ADDRESS("lottery-9", "领奖收货人、地址、联系电话必须填写", ""),
	
	
	PARTNER_GRAB_TASK_RUNING("grabtask-1", "有抓取任务正在执行中", "有抓取任务正在执行中"),
	PARTNER_GRAB_TASK_PARAM_ERROR("grabtask-2", "抓取任务参数错误", "抓取任务参数错误"),
	
	PARTNER_PARAM_ERROR("partner-zhangwen-1", "参数错误", "参数错误"),
	
	BOOK_NOT_EXISTS("book-1", "作品不存在", "作品不存在"),
	
	CHAPTER_NOT_EXISTS("chapter-1", "章节不存在", "章节不存在"),
	
	CONTENT_NOT_EXISTS("content-1", "内容不存在", "内容不存在"),
	;
	
	private String desc;
	private String value;
	private String code;
	
	private EnumInfo(String value, String desc, String code) {
		this.value = value;
		this.desc = desc;
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	public String getCode() {
		return code;
	}
	
	public static EnumInfo getEnum(String type) {
		EnumInfo[] status = EnumInfo.values();
		for(int i = 0; i < status.length; i++) {
			if(status[i].getValue().equals(type) ) {
				return status[i];
			}
		}
		
		return null;
	}
	
}
