# o2o
项目实战-校园商铺ssm

1、新建实体类、数据库表结构

2、配置pom

     <?xml version="1.0" encoding="UTF-8"?>
     <project xmlns="http://maven.apache.org/POM/4.0.0"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
         <modelVersion>4.0.0</modelVersion>

    <groupId>com.xufangfang</groupId>
    <artifactId>o2o</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <properties>
        <spring.version>4.3.7.RELEASE</spring.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/ch.qos.logback/logback-classic -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
        </dependency>

        <!-- Spring -->
        <!-- 1)包含Spring 框架基本的核心工具类。Spring 其它组件要都要使用到这个包里的类，是其它组件的基本核心 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 2)这个jar 文件是所有应用都要用到的，它包含访问配置文件、创建和管理bean 以及进行Inversion of Control
            / Dependency Injection（IoC/DI）操作相关的所有类。如果应用只需基本的IoC/DI 支持，引入spring-core.jar
            及spring-beans.jar 文件就可以了。 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 3)这个jar 文件为Spring 核心提供了大量扩展。可以找到使用Spring ApplicationContext特性时所需的全部类，JDNI
            所需的全部类，instrumentation组件以及校验Validation 方面的相关类。 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 4) 这个jar 文件包含对Spring 对JDBC 数据访问进行封装的所有类。 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 5) 为JDBC、Hibernate、JDO、JPA等提供的一致的声明式和编程式事务管理。 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 6)Spring web 包含Web应用开发时，用到Spring框架时所需的核心类，包括自动载入WebApplicationContext特性的类、Struts与JSF集成类、文件上传的支持类、Filter类和大量工具辅助类。 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 7)包含SpringMVC框架相关的所有类。 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 8)Spring test 对JUNIT等测试框架的简单封装 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Servlet web -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
        </dependency>
        <!-- json解析 -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.8.7</version>
        </dependency>
        <!-- Map工具类 对标准java Collection的扩展 spring-core.jar需commons-collections.jar -->
        <dependency>
            <groupId>commons-collections</groupId>
            <artifactId>commons-collections</artifactId>
            <version>3.2</version>
        </dependency>

        <!-- DAO: MyBatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.2</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.1</version>
        </dependency>
        <!-- 数据库 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.16</version>
        </dependency>

        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>
    </dependencies>
     <build>
         <finalName>o2o</finalName>
         <plugins>
             <plugin>
                 <groupId>org.apache.maven.plugins</groupId>
                 <artifactId>maven-compiler-plugin</artifactId>
                 <version>3.8.1</version>
                 <configuration>
                     <source>1.8</source>
                     <target>1.8</target>
                     <encoding>UTF8</encoding>
                 </configuration>
             </plugin>
         </plugins>
     </build>
     </project>
     
3、逐层完成SSM的各项配置

1、jdbc.properties
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc://localhost:3306/o2o?useUnicode=true&characterEncoding=utf8
    jdbc.username=root
    jdbc.password=123456
    
2、mybatis-config.xml

           <?xml version="1.0" encoding="UTF-8"?>
           <!DOCTYPE configuration
                   PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
                   "http://mybatis.org/dtd/mybatis-3-config.dtd">
           <configuration>
               <!-- 配置全局属性 -->
               <settings>
                   <!-- 使用jdbc的getGeneratedKeys获取数据库自增主键值 -->
                   <setting name="useGeneratedKeys" value="true" />
    
            <!-- 使用列标签替换列别名 默认:true -->
            <setting name="useColumnLabel" value="true" />
    
            <!-- 开启驼峰命名转换:Table{create_time} -> Entity{createTime} -->
            <setting name="mapUnderscoreToCamelCase" value="true" />
        </settings>
    
    </configuration>
    
3、spring-dao.xml

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
        <!-- 配置整合mybatis过程 -->
        <!-- 1.配置数据库相关参数properties的属性：${url} -->
        <context:property-placeholder location="classpath:jdbc.properties"/>
        <!-- 2.数据库连接池 -->
        <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
            <!--配置连接池属性-->
            <property name="driverClass" value="${jdbc.driver}"/>
            <property name="jdbcUrl" value="${jdbc.url}"/>
            <property name="user" value="${jdbc.username}"/>
            <property name="password" value="${jdbc.password}"/>
    
            <!-- c3p0连接池的私有属性 -->
            <property name="maxPoolSize" value="40" />
            <property name="minPoolSize" value="10" />
            <!-- 关闭连接后不自动commit -->
            <property name="autoCommitOnClose" value="false" />
            <!-- 获取连接超时时间 -->
            <property name="checkoutTimeout" value="10000" />
            <!-- 当获取连接失败重试次数 -->
            <property name="acquireRetryAttempts" value="2" />
        </bean>
    
        <!-- 3.配置SqlSessionFactory对象 -->
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!-- 注入数据库连接池 -->
            <property name="dataSource" ref="dataSource" />
            <!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
            <property name="configLocation" value="classpath:mybatis-config.xml" />
            <!-- 扫描entity包 使用别名 -->
            <property name="typeAliasesPackage" value="com.imooc.o2o.entity" />
            <!-- 扫描sql配置文件:mapper需要的xml文件 -->
            <property name="mapperLocations" value="classpath:mapper/*.xml" />
        </bean>
    
        <!-- 4.配置扫描Dao接口包，动态实现Dao接口，注入到spring容器中 -->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <!-- 注入sqlSessionFactory -->
            <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
            <!-- 给出需要扫描Dao接口包 -->
            <property name="basePackage" value="com.imooc.o2o.dao" />
        </bean>
    </beans>
    
4、spring-service.xml
   
       <?xml version="1.0" encoding="UTF-8"?>
       <beans xmlns="http://www.springframework.org/schema/beans"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xmlns:context="http://www.springframework.org/schema/context"
              xmlns:tx="http://www.springframework.org/schema/tx"
              xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/tx
           http://www.springframework.org/schema/tx/spring-tx.xsd">
          <import resource="spring-dao.xml"/>
           <!--扫描service包下所有使用注解的类型-->
           <context:component-scan base-package="com.xufangfang.o2o.service"/>
           <!--配置事务管理器-->
           <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
           <!--注入数据库连接池-->
               <property name="dataSource" ref="dataSource"/>
           </bean>
           <!--配置基于注解的声明式事务-->
           <tx:annotation-driven transaction-manager="transactionManager"/>
       </beans>
 
 
 5、spring-web.xml
 
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xmlns:mvc="http://www.springframework.org/schema/mvc"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd
         http://www.springframework.org/schema/mvc
         http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd">
         <!--配置SpringMVC-->
         <!--开启SpringMVC注解模式-->
         <mvc:annotation-driven/>
         <!-- 2.静态资源默认servlet配置 (1)加入对静态资源的处理：js,gif,png (2)允许使用"/"做整体映射 -->
         <mvc:resources mapping="/resources/**" location="/resources/" />
         <mvc:default-servlet-handler />
     
         <!-- 3.定义视图解析器 -->
         <bean id="viewResolver"
               class="org.springframework.web.servlet.view.InternalResourceViewResolver">
             <property name="prefix" value="/WEB-INF/html/"></property>
             <property name="suffix" value=".html"></property>
         </bean>
     
         <!-- 4.扫描web相关的bean -->
         <context:component-scan base-package="com.xufangfang.o2o.web" />
     </beans>


6、将mybatis、spring配置整合在一起----配置web.xml

    ?xml version="1.0" encoding="UTF-8"?>
    web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
            version="3.1">
   
       <servlet>
           <servlet-name>spring-dispatcher</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:spring/spring-*.xml</param-value>
           </init-param>
       </servlet>
   
       <servlet-mapping>
           <servlet-name>spring-dispatcher</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   
   
       <welcome-file-list>
           <welcome-file>hello.jsp</welcome-file>
       </welcome-file-list>
   
     </web-app>
     
扩展：     
      
 
      (1)
      <context:annotation-config />仅能够在已经在已经注册过的bean上面起作用。@AutoWired等生效             
      (2)
      <context:component-scan>做了<context:annotation-config>要做的事情，
      还额外支持@Component，@Repository，@Service，@Controller @RestController
      ,@ControllerAdvice, 和 @Configuration注解。
      <context:component-scan>扫描base-package指定的包，
      将标注了上述注解的类自动注册为Spring bean。 所以配置<context:component-scan>就不需要配置
      <context:annotation- config/>
      （3）mvc:annotation-driven 标签
       这个标签以mvc作为命名空间，毫无疑问它是用在Spring MVC中的配置。用于配置注释驱动的
       Spring MVC Controller编程模型
      
           对应的实现类是：org.springframework.web.servlet.config.AnnotationDrivenBeanDefinitionParser。
           
           这个类主要是用来向Spring 容器中注册以下Bean：
           
           RequestMappingHandlerMapping
           BeanNameUrlHandlerMapping
           RequestMappingHandlerAdapter
           HttpRequestHandlerAdapter
           SimpleControllerHandlerAdapter
           ExceptionHandlerExceptionResolver
           ResponseStatusExceptionResolver
           DefaultHandlerExceptionResolver

7、Logback日志的配置与使用

   1、Logback的主要模块
     
       logback-access
       logback-classic
       logback-core  为以上两个模块提供了基础的服务

   2、Logback的主要标签
   
       logger：存放日志对象、定义日志类型、级别等
       appender：指定日志输出的目的地
       layout：格式化日志信息的输出

   3、Logback的配置

      logback.xml:
      
      <?xml version="1.0" encoding="UTF-8"?>
      <!--   scan属性设置为true时，当配置文件发生变化时将会重新加载配置文件
         scanPeriod属性设置扫描配置文件看是否有变化的时间间隔，若配置文件有变化则重新加载配置文件，不需要重启服务器
         debug:为true时会实时查看logback的运行状态-->
      <configuration scan="true" scanPeriod="60 seconds" debug="false">
          <!--定义参数常量-->
          <!--TRACE<DEBUG<INFO<WARN<ERROR-->
          <!--logger对象日志输出级别-->
          <property name="log.level" value="debug"/>
          <!--文件要保留多长时间-->
          <property name="log.maxHistory" value="10"/>
          <!--日志存储的根路径-->
          <property name="log.filePath" value="${catalina.base}/logs/webapps"/>
          <!--日志展现的格式-->
          <property name="log.pattern"
                    value="%d{yyyy-MM-dd HH:mm:ss.SSS}[%thread]%-5level%logger{50}-%msg%n"/>
          <!--将日志输出到什么地方-->
      
          <!--控制台设置-->
          <appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
              <encoder>
                  <pattern>${log.pattern}</pattern>
              </encoder>
          </appender>
      
          <!--DEBUG-->
          <appender name="debugAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
              <file>${log.filePath}/debug.log</file>
              <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                  <fileNamePattern>${log.filePath}/debug/debug.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
                  <maxHistory>${log.maxHistory}</maxHistory>
              </rollingPolicy>
              <encoder>
                  <pattern>${log.pattern}</pattern>
              </encoder>
              <filter class="ch.qos.logback.classic.filter.LevelFilter">
                  <level>DEBUG</level>
                  <onMatch>ACCEPT</onMatch>
                  <onMismatch>DENY</onMismatch>
              </filter>
          </appender>
      
          <!--INFO-->
          <appender name="infoAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
              <!--文件路径:往info.log中去写当天日志-->
              <file>${log.filePath}/info.log</file>
              <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                  <!--文件名称：分离出来的文件的命名夹规则${log.filePath}/info/，第二天压缩前一天的日志文件进入压缩包.gz：info.%d{yyyy-MM-dd}.log.gz-->
                  <fileNamePattern>${log.filePath}/info/info.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
                  <!--文件最大保存历史数量：${log.filePath}/info下最大保存的压缩文件数量-->
                  <maxHistory>${log.maxHistory}</maxHistory>
              </rollingPolicy>
              <encoder>
                  <pattern>${log.pattern}</pattern>
              </encoder>
              <filter class="ch.qos.logback.classic.filter.LevelFilter">
                  <level>INFO</level>
                  <onMatch>ACCEPT</onMatch>
                  <onMismatch>DENY</onMismatch>
              </filter>
          </appender>
      
          <!--ERROR-->
          <appender name="errorAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
              <file>${log.filePath}/error.log</file>
              <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                  <fileNamePattern>${log.filePath}/error/error.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
                  <maxHistory>${log.maxHistory}</maxHistory>
              </rollingPolicy>
              <encoder>
                  <pattern>${log.pattern}</pattern>
              </encoder>
              <filter class="ch.qos.logback.classic.filter.LevelFilter">
                  <level>ERROR</level>
                  <onMatch>ACCEPT</onMatch>
                  <onMismatch>DENY</onMismatch>
              </filter>
          </appender>
      
          <!--存放日志对象、告诉logback需要关注哪个package下的信息,level告诉logback我们的logger仅
          记录哪个日志级别以上的信息-->
          <!-- additivity="true":logger会将root下的appender也放到logger中，
          即logger也支持在控制台输出相关信息，并且此时level也遵循looger下指定的level-->
          <logger name="com.xufangfang.o2o" level="${log.level}" additivity="true">
              <appender-ref ref="debugAppender"/>
              <appender-ref ref="infoAppender"/>
              <appender-ref ref="errorAppender"/>
          </logger>
      
          <!--父logger:若logger没有指定level，则默认的继承root下的level-->
          <root level="info">
              <appender-ref ref="consoleAppender"/>
          </root>
      
      </configuration>
  
   4、验证Logback的配置

        1、pom：
               <dependency>
                   <groupId>ch.qos.logback</groupId>
                   <artifactId>logback-classic</artifactId>
                   <version>1.2.3</version>
               </dependency>

         2、Controller：

        public class AreaController {        
            Logger logger=LoggerFactory.getLogger(AreaController.class);       
            @Autowired
            private AreaService areaService;               
            @RequestMapping(value = "/listarea",method = RequestMethod.GET)
            @ResponseBody
            private Map<String, Object> listArea() {
                logger.info("=======start========");
                long startTime=System.currentTimeMillis();
                Map<String, Object> modelMap = new HashMap<>();
                try {
                    List<Area> areas = areaService.getAreaList();
                    modelMap.put("rows", areas);
                    modelMap.put("total", areas.size());
                } catch (Exception e) {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", e.toString());
                }
                logger.error("test error!");
                long endTime=System.currentTimeMillis();
                logger.debug("costTime[{}ms]",(endTime-startTime));
                logger.info("=======end========");
                return modelMap;               
            }
          

8、店家管理系统的开发(店铺管理)

(1)Thumbnailator图片处理和封装Util

 a.pom:
 
                <!-- 图片处理 -->
                  <!-- https://mvnrepository.com/artifact/net.coobird/thumbnailator -->
                  <dependency>
                      <groupId>net.coobird</groupId>
                      <artifactId>thumbnailator</artifactId>
                      <version>0.4.8</version>
                  </dependency>
             
b.ImageUtil
             
     eg:制作缩略图并增加水印：原图+水印 生成增加水印的图片并对原来的图片大小进行压缩
     public class ImageUtil {

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    /**
     * CommonsMultipartFile 转换成 File
     *
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();

        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }


    /**
     * @param thumbnail  处理缩略图、并返回新生成图片的相对值路径
     * @param targetAddr 将文件保存到哪里
     * @return 返回相对地址
     * CommonsMultipartFile 后端没有能初始化他的方式、只能前端文件上传调用后端时，才触发
     * 构造方法进行初始化，改成File更合适一点、但CommonsMultipartFile
     * 可以直接转换成File
     * 在调用这个函数之前我们需要把  CommonsMultipartFile 转换成 File
     */
    public static String generateThumbnail(File thumbnail, String targetAddr) {
        //获取随机名
        String realFileName = getRandomFileName();
        //获取扩展名
        String extension = getFileExtension(thumbnail);
        //若文件目录不存在、则创建出来
        makeDirPath(targetAddr);
        //相对路径
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is :" + relativeAddr);
        //最后的文件路径=根路径+相对路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        //创建缩略图
        try {
     //            Thumbnails.of(thumbnail.getInputStream())
                 Thumbnails.of(thumbnail)
                         .size(200, 200)
                         //添加水印
                         .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\watermark.jpg")),
                                 0.25f)
                         //压缩图片
                         .outputQuality(0.8f)
                         //指定保存到哪个文件里
                         .toFile(dest);
             } catch (IOException e) {
                 logger.error(e.toString());
                 e.printStackTrace();
             }
             //返回相对地址
             return relativeAddr;
         }

    /**
     * 生成随机文件名，当前年月日时分秒+五位随机数
     *
     * @return
     */
    private static String getRandomFileName() {
        //获取随机五位数:10000~99999之间
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @param cFile
     * @return
     */
     //    private static String getFileExtension(CommonsMultipartFile cFile) {
         private static String getFileExtension(File cFile) {
             //获取输入文件流的文件名
     //        String originalFileName = cFile.getOriginalFilename();
             String originalFileName = cFile.getName();
             return originalFileName.substring(originalFileName.lastIndexOf("."));
         }


    /**
     * 创建目标路径所涉及到的目录，即/home/work/fangfang/xxx.jpg,
     * 那么 home work fangfang 这三个文件夹都得自动创建
     * /home/work/fangfang/xxx.jpg 是目标文件所属的文件夹的相对路径
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        //获取目标文件要存储的绝对路径(全路径)
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }


    }


    public static void main(String[] args) throws IOException {
        //获取classPath的绝对值路径 /target/classes
        //0.25f：定义透明度
        //压缩图片压缩比：0.8f
        Thumbnails.of(new File("C:\\Users\\47284\\Desktop\\weixin.png"))
                .size(500, 500).watermark(
                Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\watermark.jpg")),
                0.25f
        ).outputQuality(0.8f).toFile("C:\\Users\\47284\\Desktop\\weixin6.png");
    }
    }
             
c.PathUtil
             
           
        /**
         * 路径处理工具类
         */
        public class PathUtil {
            //获取文件的分隔符
            private static String seperator = System.getProperty("file.separator");
        
            /**
             * 获取文件输出路径
             *
             * @return
             */
            public static String getImgBasePath() {
                //获取操作系统的名称
                String os = System.getProperty("os.name");
                System.out.println(os);
                String basePath = "";
                if (os.toLowerCase().startsWith("win")) {
                    basePath = "D:/projectdev/image/";
                } else {
                    basePath = "/home/fangfang/image";
                }
                //处理windows和Linux路径的斜杠问题：
                basePath = basePath.replace("/", seperator);
                return basePath;
            }
        
            /**
             * 获取店铺图片的存储路径
             * 将这些图片分别存储在各自店铺的路径下
             */
            public static String getShopImagePath(long shopId) {
                String imagePath = "/upload/item/shop/" + shopId + "/";
                return imagePath.replace("/", seperator);
            }
        
        }
d.  @Transactional

                //必须当且仅当抛出RuntimeException或者继承
                //RuntimeException时，事务才会得以终止并回滚。
                //如果是Exception，那么事务是没办法终止，该提交
                //的就已经提交了并不会回滚
                
(2)jackson-databind json解析：将实体类、json互相转换                

(3)从请求的session会话的上下文中获取文件流

       代码如下：
       CommonsMultipartFile shopImg = null;
             CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
             if (commonsMultipartResolver.isMultipart(request)) {
                 MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                 shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
             } else {
                 modelMap.put("success", false);
                 modelMap.put("errMsg", "上传图片不能为空！");
                 return modelMap;
             }
             //2、注册店铺
             if (shop != null && shopImg != null) {
                 PersonInfo owner = new PersonInfo();
                 owner.setUserId(1L);
                 shop.setOwner(owner);
     
                 File imgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
                 try {
                     imgFile.createNewFile();
                 } catch (IOException e) {
                     modelMap.put("success", false);
                     modelMap.put("errMsg", e.getMessage());
                     return modelMap;
                 }
                 try {
                     inputStreamToFile(shopImg.getInputStream(), imgFile);
                 } catch (IOException e) {
                     modelMap.put("success", false);
                     modelMap.put("errMsg", e.getMessage());
                     return modelMap;
                 }
                 ShopExecution shopExecution = shopService.addShop(shop, imgFile);
                 if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                     modelMap.put("success", true);
                 } else {
                     modelMap.put("success", false);
                 }
                 return modelMap;
             } else {
                 modelMap.put("success", false);
                 modelMap.put("errMsg", "请输入店铺信息！");
                 return modelMap;
             }


          /**
              * 输入流转换成File
              *
              * @param ins
              * @param file
              */
             private static void inputStreamToFile(InputStream ins, File file) {
                 FileOutputStream os = null;
                 try {
                     os = new FileOutputStream(file);
                     int bytesRead = 0;
                     byte[] buffer = new byte[1024];
                     while ((bytesRead = ins.read(buffer)) != -1) {
                         os.write(buffer, 0, bytesRead);
                     }
                 } catch (Exception e) {
                     throw new RuntimeException("调用inputStreamToFile产生异常:" + e.getMessage());
                 } finally {
                     try {
                         if (os != null) {
                             os.close();
                         }
                         if (ins != null) {
         
                             ins.close();
                         }
                     } catch (IOException e) {
                         throw new RuntimeException("inputStreamToFile关闭io产生异常:" + e.getMessage());
                     }
                 }
         
             }
                