/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50543
Source Host           : localhost:3306
Source Database       : ruanmeng

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2022-04-29 18:09:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `team_id` int(10) NOT NULL COMMENT '团队',
  `request_time` datetime DEFAULT NULL COMMENT '留言时间',
  `request` varchar(1000) NOT NULL COMMENT '留言内容',
  `response_time` datetime DEFAULT NULL COMMENT '回复时间',
  `response` varchar(1000) DEFAULT NULL COMMENT '回复内容',
  PRIMARY KEY (`id`),
  KEY `demand_ibfk_1` (`team_id`),
  CONSTRAINT `demand_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demand
-- ----------------------------
INSERT INTO `demand` VALUES ('1', '经费问题', '2', '2022-02-16 19:11:16', '老师，您好！我们团队正在做一个项目，需要一些资金，我想咨询一下学院是否有相应的注册支持？', '2022-03-01 09:53:33', '同学你好，学院有相应的政策，具体请咨询你们团队的指导老师。---');
INSERT INTO `demand` VALUES ('2', '资源问题', '5', '2022-03-01 18:11:14', '老师您好，我们团队做了一个项目，需要部署到服务器，请问学院是否能关于服务器的政策或者资源呢？', '2022-03-01 18:14:42', '有的，具体可到教学楼七楼机房管理室咨询。');
INSERT INTO `demand` VALUES ('3', '创新创业大赛', '5', '2022-03-01 18:05:41', '老师您好！请问学院下一届创新创业大赛什么时候开始？', null, null);
INSERT INTO `demand` VALUES ('4', '设备', '1', '2022-04-18 15:53:15', '老师您好！创业孵化基地的路由器不能使用了，能否找相关人员修理一下？', null, null);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `count` int(11) DEFAULT '0' COMMENT '点击数',
  `content` varchar(10000) DEFAULT NULL COMMENT '内容',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '团体程序设计天梯赛选拔赛举办通知', '2022-04-28 21:09:04', '124', '程序设计能力是大学生利用计算机分析问题、解决问题的重要基础能力。为了推进该能力的培养，同时培养学生的团队合作精神，提高其综合素质，丰富校园学术氛围，促进校际交流，提高全国高校及程序设计教学水平，教育部高等学校计算机类专业教学指导委员会，教育部高等学校软件工程专业教学指导委员会，教育部高等学校大学计算机课程教学指导委员会及全国高等学校计算机教育研究会于2016年创办了本项赛事，产生了广泛影响。该竞赛于2019年纳入全国普通高等学校学科竞赛排行榜竞赛项目，学校认定为一档竞赛。第五届“中国高校计算机大赛—团体程序设计天梯赛”由全国高等学校计算机教育研究会主办，定于近期线上举行。', 'b40b1bfa65e242498a3a968fb5d31f9f-QQ截图20220428210856.jpg');
INSERT INTO `notice` VALUES ('2', '软件学院举办安阳师范学院第五届创客文化节', '2022-02-24 11:32:39', '42', '由党委宣传部、党委学工部主办，软件学院承办的安阳师范学第五届创客文化节落下帷幕。12月3日下午，软件学院举行了安阳师范学院第五届创客文化节总结表彰大会。软件学院全体领导、辅导员、来自7个学院的300余人参加了大会。以菜鸡队和重光队为代表的两个团队向大会展示了自己的优秀成果。\n\n学院副院长刘凌霞致辞，她强调创客文化节旨在弘扬创客文化，进一步激发广大学生创新意识、提升创新能力，进而提高就业创业核心竞争力。党委副书记郭永刚宣读了表彰决定。一共12团队、65名学生受到了表彰。\n\n本次创客文化节以\"创业梦想，乘风而上\"为主题，内容涵盖创客图片展、创客大讲堂、第五届互联网+创新创业大赛、创新创业大赛成果展及颁奖典礼五个部分，吸引了来自计算机与信息工程学院、法学院等七个学院2000多名学生参与。创客文化节自2015年举办以来，打造了起到了丰富校园文化，培养创新意识，提高创新能力，提升创新素养的作用，为软件学院一批专业团队、营造了创新创业氛围、提高了学生就业核心竞争力，为创新创业教育打造了好的平台。', null);
INSERT INTO `notice` VALUES ('3', '第五届创客文化节创客大讲堂开讲', '2022-04-28 21:08:14', '42', '随着第五届创客文化节拉开帷幕，为增强同学们对创新创业的理解，点燃创新梦想，激发创造热情，创客大讲堂于10月29日晚19点，在文明大道校区学术报告厅开讲啦！创客大讲堂第一讲由千锋教育的李峰总监担任主讲师，2019级辅导员常孟阳老师主持。\n\n在本次活动中，李峰讲师用诙谐生动的语言带同学们走进了创客时代。他首先就百度、阿里、腾讯产业的结构与发展向同学们展示了这是怎样一个不同的时代，同时也通过对中国信息化产业布局的分析和对当代企业对所需人才的要求的介绍，为同学们以后的创新创业工作起到了良好的导向性作用。另外，他所指出的移动互联网的终极产品数据红利和关于未来生活的四大趋势，也让同学们更加清晰地了解到了未来行业的发展。而他所介绍的创业大赛更是鼓舞着在场所有同学的心想要一试身手，实现梦想。\n本讲是创客文化节中创客大讲堂的第一讲，不仅活跃了校园创新创业的氛围，还坚定了同学们创新创业的信心，同时也提升了同学们的理论水平和能力。后续还会有多场创客大讲堂陆续在文明大道校区开讲。', '42051008b3634850afea31544d69e15a-QQ截图20220428210804.jpg');
INSERT INTO `notice` VALUES ('4', '国务院办公厅印发意见加快众创空间发展服务实体经济转型升级', '2022-02-25 16:31:14', '29', '国务院办公厅日前印发《关于加快众创空间发展服务实体经济转型升级的指导意见》，提出促进众创空间专业化发展，为实施创新驱动发展战略、推进大众创业万众创新提供低成本、全方位、专业化服务，更大释放全社会创新创业活力，促进科技成果加快向现实生产力转化，增强实体经济发展新动能。\n《意见》指出，推进大众创业万众创新是增强发展新动能、促进社会就业、提高发展质量效益的重要途径，是实施创新驱动发展战略的重要支撑，国务院陆续出台了一系列重要支持政策和举措，为经济平稳较快发展发挥了关键作用。当前，全国各地涌现出一批有亮点、有潜力、有特色的众创空间，已经成为大众创业万众创新的重要阵地和创新创业者的聚集地，呈现蓬勃发展的良好势头。', null);
INSERT INTO `notice` VALUES ('5', '关于引进培育创新创业领军人才（团队）的意见', '2022-02-25 17:23:56', '13', '为加快实施开放创新双驱动战略，打造有利于人才集聚、创新创业的发展环境，发挥高层次人才对郑州都市区建设的引领支撑作用，特制定本意见。\n\n一、指导思想、总体要求和目标任务\n\n（一）指导思想。以党的十八大和十八届三中、四中全会精神为指导，按照市委十届九次全会关于实施开放创新双驱动战略部署，以引进培育科技创新创业领军人才（团队）为重点，坚持以市场为主导、企业为主体、政府为引导，以“两金一扶”为保障，以创新创业综合体为重要载体，坚持引进培育和服务管理并重，实现人才链、创新链、产业链和服务链的融合对接，着力构建产学研政资介相结合的科技创新体系，引领我市战略性新兴产业快速发展和传统优势产业转型升级，为郑州都市区建设提供坚实的人才智力支撑。\n\n（二）总体要求。按照“依托产业集聚人才、创新体制成就人才、优化环境留住人才”的要求，以服务重点产业和重大项目、促进科技成果转化和产业化、深化产业结构调整和转型升级、提升城市自主创新能力为着力点，加快发展众创空间等新型创新创业服务平台，实现人才、科技、金融、服务相结合，政府、市场、社会、环境相统一，健全市场配置机制，有效集成落实政策，优化创新创业环境，加快集聚培育一批创新创业领军人才，打造一批创新创业领军团队，大力推进创新创业高层次人才队伍建设，以激发全社会创新创业活力。\n\n（三）目标任务。围绕我市重点发展的电子信息、汽车与装备制造、现代商贸物流、文化创意旅游和新材料、生物医药、铝及铝精深加工、现代农业及食品加工、现代金融、高端服务业等“4+6”战略主导优势产业，航空经济、互联网技术、大数据技术、智能化制造等战略性新兴产业发展方向，用5年左右时间，力争重点引进1000名左右掌握核心技术、具有较强创新创业能力的领军人才和高层次创新创业紧缺人才，100个领军型科技创新创业团队；培养200名左右具有国际化视野和持续创新能力，拥有核心自主知识产权的科技创业企业家；汇聚50名左右“两院”院士、国家“千人计划”、“万人计划”等海内外顶尖人才（以下简称“智汇郑州·1125聚才计划”），形成人才与科技相互助益、创新与创业紧密结合、企业与产业协调发展的良好局面，把郑州建设成为人才智力竞相汇聚、新兴产业快速发展、体制机制充满活力的国际商都。\n\n二、引进培育对象\n\n结合我市经济社会发展的实际需要，重点引进培育在海内外具有创新创业经历，具有国际化视野和持续创新能力，拥有自主知识产权，技术成果国内外领先或填补国内空白，初步具备规模生产、实现产业化的条件，市场开发前景广阔，能够引领我市相关产业发展，带技术、带项目、带资金来郑创新创业的人才和团队。', null);
INSERT INTO `notice` VALUES ('6', '教育部关于举办首届中国“互联网+”大学生创新创业大赛的通知', '2022-04-28 21:05:07', '33', '各省、自治区、直辖市教育厅（教委），新疆生产建设兵团教育局，有关部门（单位）教育司（局），部属各高等学校：\n\n为贯彻落实《国务院办公厅关于深化高等学校创新创业教育改革的实施意见》（国办发〔2015〕36号），进一步激发高校学生创新创业热情，展示高校创新创业教育成果，定于2015年5月至10月举办首届中国“互联网+”大学生创新创业大赛。现将有关事项通知如下：\n\n一、大赛主题\n\n“互联网+”成就梦想 创新创业开辟未来\n\n二、大赛目的与任务\n\n旨在深化高等教育综合改革，激发大学生的创造力，培养造就“大众创业、万众创新”的生力军；推动赛事成果转化，促进“互联网+”新业态形成，服务经济提质增效升级；以创新引领创业、创业带动就业，推动高校毕业生更高质量创业就业。\n\n重在把大赛作为深化创新创业教育改革的重要抓手，引导各地各高校主动服务创新驱动发展战略，创新人才培养机制，切实提高高校学生的创新精神、创业意识和创新创业能力。\n\n三、组织机构\n\n本次大赛由教育部与有关部委和吉林省人民政府共同主办，吉林大学承办。\n\n大赛设立组织委员会（简称大赛组委会），由教育部部长袁贵仁和吉林省省长蒋超良担任主任，有关部门负责人作为成员，负责大赛的组织实施。\n\n大赛设立专家委员会，由大赛组委会邀请行业企业、创投风投机构、大学科技园、高校和科研院所专家组成，负责参赛项目的评审工作，指导大学生创新创业。\n\n各省（区、市）可根据实际成立相应的机构，负责本地初赛和复赛的组织实施、项目评审和推荐等工作。\n\n四、参赛项目要求\n\n参赛项目要求能够将移动互联网、云计算、大数据、物联网等新一代信息技术与行业产业紧密结合，培育产生基于互联网的新产品、新服务、新业态、新模式，以及推动互联网与教育、医疗、社区等深度融合的公共服务创新。主要包括以下类型：\n\n1.“互联网+”传统产业：新一代信息技术在传统产业（含一二三产业）领域应用的创新创业项目；\n\n2.“互联网+”新业态：基于互联网的新产品、新模式、新业态创新创业项目，优先鼓励人工智能产业、智能汽车、智能家居、可穿戴设备、互联网金融、线上线下互动的新兴消费、大规模个性定制等融合型新产品、新模式；\n\n3.“互联网+”公共服务：互联网与教育、医疗、社区等结合的创新创业项目；\n\n4.“互联网+”技术支撑平台：互联网、云计算、大数据、物联网等新一代信息技术创新创业项目。\n\n参赛项目内容须健康、合法，无任何不良信息。参赛项目所涉及的发明创造、专利技术、资源等必须拥有清晰合法的知识产权或物权，报名时需提交完整的具有法律效力的所有人书面授权许可书、项目鉴定证书、专利证书等。抄袭、盗用、提供虚假材料或违反相关法律法规一经发现即刻丧失参赛相关权利并自负一切法律责任。对于已注册运营的项目，在报名时需提交单位概况、法定代表人情况、组织机构代码复印件等相关证明材料。\n\n五、参赛对象\n\n大赛分为创意组和实践组。\n\n创意组参赛条件：申报人是团队负责人或创业企业法人，为普通高等学校在校生（不含在职）；团队尚未正式注册或注册时间晚于2015年5月1日。\n\n实践组参赛条件：申报人是创业企业法人，为普通高等学校在校生（不含在职）或毕业5年以内的毕业生；创业企业在2015年5月1日前已注册。\n\n以创新创业团队为单位报名参赛。允许跨校组建团队。每个参赛团队成员不少于3人。\n\n六、比赛方式\n\n大赛采用校级初赛、省级复赛、全国总决赛三级赛制。在校级初赛、省级复赛基础上，按照组委会配额择优遴选项目进入全国决赛。全国共产生300个团队入围全国总决赛，其中创意组100个团队，实践组200个团队。每所高校入选全国总决赛团队总数不超过3个。\n\n七、大赛奖励\n\n大赛设金奖30个、银奖70个、铜奖200个，奖励获奖项目。同时，设置集体奖，按照高校获奖情况奖励前20名；设置优秀组织奖，按照省级竞赛组织和获奖情况奖励8名。', '2aea8dfad2144e28aef122184efb6547-QQ截图20220428210454.jpg');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `team_id` int(10) NOT NULL COMMENT '团队',
  `time` datetime DEFAULT NULL COMMENT '获得时间',
  `note` varchar(1000) DEFAULT NULL COMMENT '成果介绍',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  `teacher_admit` int(2) DEFAULT '0' COMMENT '导师是否同意',
  `admin_admit` int(2) DEFAULT '0' COMMENT '管理员是否同意',
  `teacher_note` varchar(1000) DEFAULT NULL COMMENT '导师意见',
  `admin_note` varchar(1000) DEFAULT NULL COMMENT '管理员意见',
  PRIMARY KEY (`id`),
  KEY `project_ibfk_1` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '智能交通综合控制管理平台研究', '1', '2022-02-27 00:00:00', '该项目充分利用先进的计算机网络、多媒体、交通控制、模糊识别、人机交互等进行优化组合，获得校级创新创业大赛一等奖', 'http://localhost:8080/pictures/8403be55879a4fbc8a141584eb11a4ad-QQ截图20220428193117.jpg', '1', '1', '', '');
INSERT INTO `project` VALUES ('5', '甲骨文视频输入及编辑方法', '6', '2022-02-02 00:00:00', '甲骨文视频输入及编辑方法', 'http://localhost:8080/pictures/1a378515da5c4e4283d255bf33fc98f6-QQ截图20220428193256.jpg', '1', '1', '', '');
INSERT INTO `project` VALUES ('6', '基于物联网技术肉鸡饲养智能监控系统研究\r\n', '2', '2022-02-10 00:00:00', '本发明提出了一种基于物联网技术的家禽远程监控管理系统，包括鸡舍单元、控制管理单元和用户终端，其中鸡舍单元是整个系统的信息采集和指令执行的终端', null, '1', '1', null, null);
INSERT INTO `project` VALUES ('7', '多分辨率区域MRF影像分割算法研究与应用', '2', '2022-02-02 00:00:00', '对ICM,RL和HCF等3种基于MRF的图像Excel分割算法.在遥感图像领域的分割应用进行了理论和实验的研究分析.并且提出了改进后的HCF算法,可以实现对遥感图像Excel的快速分割并且得到较好的分割效果.通过实验.给出了它们的各自的性能特点和适用范围.对这3个算法的图像Excel分割性能和优缺点进行了比较.', null, '1', '0', '', '');
INSERT INTO `project` VALUES ('12', '微网系统混合储能关键技术研究', '1', '2022-03-16 00:00:00', '微电网作为一个小型发配电系统,能够将一定区域的分布式电源汇集起来,实现自我控制和管理。储能技术作为微电网中一个关键技术,在平抑可再生能源功率波动，系统运行的稳定性和可靠性等方面起着至关重要的作用。', 'http://localhost:8080/pictures/a65ed69b13e54cea96dbdba96ab4f2c2-QQ截图20220428193412.jpg', '-1', '0', '请添加成果的具体实现方式', '');
INSERT INTO `project` VALUES ('13', 'android平台教育游戏的设计和开发', '1', '2022-03-11 00:00:00', '本研究以人机交互、计算机图形学等理论为基础，基于 Kinect平台开发一种基于行为识别和虚拟现实技术的体感辅助形体交互系统', 'http://localhost:8080/pictures/093333cf31214bd4b0886abb01275d73-QQ截图20220428193515.jpg', '1', '-1', '', '请添加成果的具体实现方式');
INSERT INTO `project` VALUES ('14', '基于Android平台的手机教育游戏设计与开发', '3', '2022-04-28 19:16:48', '随着信息化教育的飞速发展,以移动设备作为学习平台的学习方式,越来越受到学者们的重视。在当今时代,手机是移动设备的主流,尤其是使用安卓系统的手机用户越来越多。安卓手机以其系统完全开源的独特优势,使得各类手机应用软件都能在安卓手机上运行。', null, '1', '1', null, null);
INSERT INTO `project` VALUES ('15', '校内人才招聘网', '1', '2022-04-28 00:00:00', '此网上招聘网站，采用JSP技术进行开发，选用MySq1作为后台数据库。\n', '', '0', '0', '', '');
INSERT INTO `project` VALUES ('22', '于JAVAWEB实现家教平台系统', '1', '2022-04-27 00:00:00', '基于B/S架构, 采用MVC开发模式，使用JAVA语言和ORACLE数据库，以TOMCAT作为WEB服务器，结合SSH框架进行整合，开发了基于JavaEE的开放式家教服务平台。平台前端以JSP实现与用户交互界面，信息的平台后端的业务逻辑用Java语言实现。', 'http://localhost:8080/pictures/9dc0d8a76f3b48e58e5543985b5bbc2d-QQ截图20220428193625.jpg', '1', '0', '', '');

-- ----------------------------
-- Table structure for project_student
-- ----------------------------
DROP TABLE IF EXISTS `project_student`;
CREATE TABLE `project_student` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_id` int(10) NOT NULL COMMENT '成果',
  `student_id` int(10) NOT NULL COMMENT '学生',
  PRIMARY KEY (`id`),
  KEY `project_student_ibfk_2` (`student_id`),
  KEY `project_student_ibfk_1` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_student
-- ----------------------------
INSERT INTO `project_student` VALUES ('53', '6', '2');
INSERT INTO `project_student` VALUES ('58', '18', '1');
INSERT INTO `project_student` VALUES ('60', '20', '1');
INSERT INTO `project_student` VALUES ('69', '14', '16');
INSERT INTO `project_student` VALUES ('70', '14', '3');
INSERT INTO `project_student` VALUES ('75', '8', '27');
INSERT INTO `project_student` VALUES ('83', '7', '1');
INSERT INTO `project_student` VALUES ('94', '1', '1');
INSERT INTO `project_student` VALUES ('95', '1', '7');
INSERT INTO `project_student` VALUES ('96', '5', '6');
INSERT INTO `project_student` VALUES ('99', '13', '1');
INSERT INTO `project_student` VALUES ('100', '13', '7');
INSERT INTO `project_student` VALUES ('101', '13', '11');
INSERT INTO `project_student` VALUES ('102', '22', '7');
INSERT INTO `project_student` VALUES ('103', '22', '11');
INSERT INTO `project_student` VALUES ('104', '12', '7');
INSERT INTO `project_student` VALUES ('105', '12', '11');
INSERT INTO `project_student` VALUES ('106', '15', '4');
INSERT INTO `project_student` VALUES ('107', '15', '7');
INSERT INTO `project_student` VALUES ('108', '15', '11');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stu_id` int(10) DEFAULT NULL COMMENT '学号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `team_id` int(10) DEFAULT NULL COMMENT '团队',
  `classname` varchar(255) DEFAULT NULL COMMENT '班级',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `join_time` date DEFAULT NULL COMMENT '加入时间',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '184804013', '王德森\r\n', '男', '20', '1', 'Java1', '16638682466', '2021-10-22', '获得过河南省创新创业大赛一等奖，校级创新创业大赛特等奖。');
INSERT INTO `student` VALUES ('2', '184804068', '闫高亮\r\n', '男', '20', '2', 'Java2', '13936566826', '2021-01-01', 'Java2班学习委员，获得过校级创新创业大赛二等奖。');
INSERT INTO `student` VALUES ('3', '194804014', '李栋\r\n', '男', '21', '3', '.net1', '15536596289', '2022-01-27', '');
INSERT INTO `student` VALUES ('4', '204804001', '周岩\r\n', '男', '22', '1', 'Java4', '12369522322', '2022-01-25', '');
INSERT INTO `student` VALUES ('5', '214804011', '李芬\r\n', '女', '22', '6', '大数据1班', '12345659874', '2022-01-18', '');
INSERT INTO `student` VALUES ('6', '204804098', '闫博\r\n', '男', '21', '6', 'Java1', '12378945621', '2022-01-24', '');
INSERT INTO `student` VALUES ('7', '184804333', '张梦洁\r\n', '女', '20', '1', 'Java5', '14598745633', '2021-12-30', '三好学生');
INSERT INTO `student` VALUES ('8', '184804986', '姜李高\r\n', '男', '21', '6', 'Java3', '16847896524', '2022-01-30', '');
INSERT INTO `student` VALUES ('9', '204804456', '孙倩\r\n', '女', '20', '2', 'Java1', '16589647821', '2022-01-27', '');
INSERT INTO `student` VALUES ('10', '184804036', '周芳洁\r\n', '女', '20', '5', '大数据1', '13689645121', '2022-02-10', '');
INSERT INTO `student` VALUES ('11', '184804456', '罗辑', '男', '20', '1', 'Java2', '12369587456', '2022-02-23', '');
INSERT INTO `student` VALUES ('12', '204804369', '史强', '男', '22', '3', 'Java4', '12365485214', '2022-02-21', '');
INSERT INTO `student` VALUES ('13', '204804132', '王淼', '男', '21', '5', 'Java5', '14785236985', '2022-02-24', '');
INSERT INTO `student` VALUES ('14', '194804065', '杜书林\r\n', '男', '20', '2', 'Java', '14785698741', '2022-02-22', '');
INSERT INTO `student` VALUES ('15', '194804457', '王振江\r\n', '男', '20', '4', 'Java1', '12358698511', '2022-03-26', '');
INSERT INTO `student` VALUES ('16', '204804165', '祁建朋\r\n', '男', '20', '3', 'Java1', '13726411564', '2022-04-13', '');
INSERT INTO `student` VALUES ('27', '204804354', '郭旭阳\r\n', '男', '12', '2', 'Java3', '16334515169', '2022-04-06', '');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `teacher_status` varchar(255) NOT NULL COMMENT '校内/企业导师',
  `title` varchar(255) DEFAULT NULL COMMENT '职称',
  `education` varchar(255) DEFAULT NULL COMMENT '学历',
  `field` varchar(255) DEFAULT NULL COMMENT '擅长领域',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `admin_admit` int(2) DEFAULT '0' COMMENT '管理员是否同意',
  `admin_note` varchar(1000) DEFAULT NULL COMMENT '管理员留言',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '刘星', '女', '校内导师', '副教授', '硕士', '计算机应用，软件工程.', '13623568947', '1', '通过');
INSERT INTO `teacher` VALUES ('2', '王强', '男', '校内导师', '副教授', '硕士', '操作系统，计算机应用', '15866578574', '1', '');
INSERT INTO `teacher` VALUES ('3', '李华', '女', '校内导师', '讲师', '硕士', '人工智能、计算机软件与理论、信息融合、智能信息处理等领域，包括模糊集合理论及应用、粗糙集合理论及应用、智能决策支持系统、数据融合系统测试技术', '14785698595', '1', '通过');
INSERT INTO `teacher` VALUES ('4', '林丹', '男', '校内导师', '讲师', '博士', '计算机视觉、模式识别、智能检测', '13698563258', '1', null);
INSERT INTO `teacher` VALUES ('6', '王刚', '男', '企业导师', '工程师', '硕士', '网络安全，主要集中于基于位置的网络安全、IPV6安全', '13698965200', '0', '');
INSERT INTO `teacher` VALUES ('7', '张炜', '男', '企业导师', '', '', '', '16561629616', '-1', '请输入完整信息（职称、学历等）');

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '团队名称',
  `teacher_id` int(10) NOT NULL COMMENT '导师',
  `caption_id` int(10) DEFAULT NULL COMMENT '队长',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `setup` date DEFAULT NULL COMMENT '成立时间',
  `description` varchar(255) DEFAULT NULL COMMENT '团队介绍',
  `teacher_admit` int(2) DEFAULT '0' COMMENT '导师是否同意',
  `teacher_note` varchar(1000) DEFAULT NULL COMMENT '导师留言',
  `admin_admit` int(2) DEFAULT '0' COMMENT '管理员是否同意',
  `admin_note` varchar(1000) DEFAULT NULL COMMENT '管理员留言',
  `app_form` varchar(255) DEFAULT NULL COMMENT '设计表',
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `teacher_id` (`teacher_id`),
  KEY `caption_id` (`caption_id`),
  CONSTRAINT `team_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `team_ibfk_2` FOREIGN KEY (`caption_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1', '算法艺术社', '1', '1', '123@163.com', '2016-04-01', '专注于算法训练和算法比赛，并在实际运用中用算法思想解决难题。', '1', null, '1', null, null);
INSERT INTO `team` VALUES ('2', '人工智能队', '2', '2', '111@163.com', '2019-09-20', '队长是李四，团队特长是在开发人工智能相关应用。', '1', '加油', '1', '加油', null);
INSERT INTO `team` VALUES ('3', '勇往直前队', '3', '3', '123654@163.com', '2020-07-01', '团队特长是微信小程序，可以为企业定制小程序。', '1', null, '1', null, null);
INSERT INTO `team` VALUES ('5', 'Spring队', '4', '4', 'zzz@163.com', '2022-02-16', '队伍成员精通Java各种开发框架，擅长做一些企业级服务网站。', '1', null, '1', null, null);
INSERT INTO `team` VALUES ('6', '大数据队', '1', '6', 'dddd@163.com', '2022-03-02', '擅长运用大数据建立信息模型，对其发展趋势做出预测。', '1', null, '1', '通过', null);
INSERT INTO `team` VALUES ('7', '网络安全队', '1', null, 'ccc@163.com', '2022-04-14', null, '0', null, '0', null, 'http://localhost:8080/pictures/bc6ff8336bed467cb112df44a9e53723-大学生创新创业孵化基地团队入驻申请表.doc');
INSERT INTO `team` VALUES ('21', '梦之队', '1', null, '139@qq.com', '2022-04-13', '', '1', null, '-1', '请添加团队介绍', null);
INSERT INTO `team` VALUES ('22', '星之队', '1', null, '135@qq.com', '2022-04-06', '', '1', null, '0', '', null);
INSERT INTO `team` VALUES ('24', '天天向上队', '1', null, '1356683220@qq.com', '2022-04-26', '', '-1', '请添加团队介绍', '0', null, 'http://localhost:8080/pictures/bc6ff8336bed467cb112df44a9e53723-大学生创新创业孵化基地团队入驻申请表.doc');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `status` int(10) NOT NULL COMMENT '用户身份',
  `status_id` int(10) NOT NULL COMMENT '用户id',
  `login_name` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, '1', '1', 'admin', '$2a$10$rKSmClgtBS6uWTf18PIeyeu6sjIZms/23O8VSdRU8RP0w2zMG6W3e', '2022-01-21 00:00:00');
INSERT INTO `user` VALUES ('2', null, '2', '1', 'teacher', '$2a$10$cDap/b5mZhhF1FCiIeoaqOLlfX/62RnVP3oWvBFWFjDpSN8agIlGa', '2022-02-01 11:10:11');
INSERT INTO `user` VALUES ('3', null, '3', '1', 'team', '$2a$10$cDap/b5mZhhF1FCiIeoaqOLlfX/62RnVP3oWvBFWFjDpSN8agIlGa', '2022-02-01 11:10:21');
INSERT INTO `user` VALUES ('23', null, '3', '2', 'team2', '$2a$10$Lzui9XCc4hOxrqF4nsJft.bNajXwhYMcHL6inWjewr5zy0JHWwS0S', '2022-02-14 08:22:42');
INSERT INTO `user` VALUES ('36', '', '2', '2', 'teacher2', '$2a$10$cDap/b5mZhhF1FCiIeoaqOLlfX/62RnVP3oWvBFWFjDpSN8agIlGa', '2022-04-19 10:20:26');
INSERT INTO `user` VALUES ('37', '', '2', '3', 'teacher3', '$2a$10$NGs7IH7NZ0SVOqbtSGe0WOrhtjyHHSqdsPts6wVoYig/bUDbFlwXe', '2022-04-19 10:25:12');
INSERT INTO `user` VALUES ('38', '', '2', '4', 'teacher4', '$2a$10$ddFk0H6ZQANOLSDXoDHPuOXhgmMc71E1zt6DdcCmV9Po3GEWS.n0G', '2022-04-19 12:19:20');
INSERT INTO `user` VALUES ('39', '', '2', '6', 'teacher5', '$2a$10$PDbmPwguVRSfdEGapX19B.2In9Gh0ihiW4FkAJWURX7lY9ioQ1PVe', '2022-04-19 12:20:13');
INSERT INTO `user` VALUES ('40', '', '3', '3', 'team3', '$2a$10$3A4gSrSdKninm20KjIhQGeyMQAB9lQV8a6FzLvsVS62Z1E9Icb5S.', '2022-04-19 12:25:52');
INSERT INTO `user` VALUES ('41', '', '3', '7', 'team7', '$2a$10$taJ7dc2TkIbZqCxiEeDmDe3LGPpE2Qi7vro1bduRXec75NNgoLqny', '2022-04-20 17:07:43');
INSERT INTO `user` VALUES ('42', '', '3', '5', 'team5', '$2a$10$AdZmFZmoefAQdUfc/erxyeI4KBLXUExmW7N27p2y751.o6Yr3V9x.', '2022-04-20 17:53:51');
INSERT INTO `user` VALUES ('43', null, '3', '6', 'team6', '$2a$10$AdZmFZmoefAQdUfc/erxyeI4KBLXUExmW7N27p2y751.o6Yr3V9x.', '2022-04-05 11:14:16');
INSERT INTO `user` VALUES ('44', '梦之队', '3', '21', 'team8', '$2a$10$aWXV0MPNzo3VTeNZaAOdMu2oz9zZ79IHcrP7ZiUlrBBcwGRObVdpG', '2022-04-21 11:54:55');
INSERT INTO `user` VALUES ('45', '星之队', '3', '22', 'team9', '$2a$10$jfCHesTzjCggCEwPovJjHu2GMPtcaNv.oOBBeuCjHKg6iDggFWNYK', '2022-04-21 14:45:22');
INSERT INTO `user` VALUES ('46', '天天向上队', '3', '24', 'team1', '$2a$10$W9oNOr51R7km.e6TaQ5skekm2ntVXKYNO9dHRCeCC/Fnrq7O3AGzC', '2022-04-26 20:03:34');
INSERT INTO `user` VALUES ('47', null, '2', '7', 'teacher1', '$2a$10$rKSmClgtBS6uWTf18PIeyeu6sjIZms/23O8VSdRU8RP0w2zMG6W3e', '2022-04-28 21:12:36');
SET FOREIGN_KEY_CHECKS=1;
