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
      <context:annotation-config />仅能够在已经注册过的bean上面起作用。@AutoWired等生效             
      (2)
      <context:component-scan>做了<context:annotation-config>要做的事情，
      还额外支持@Component，@Repository，@Service，@Controller @RestController
      ,@ControllerAdvice, 和 @Configuration注解。
      <context:component-scan>扫描base-package指定的包，
      将标注了上述注解的类自动注册为Spring bean。 所以配置<context:component-scan>就不需要配置
      <context:annotation-config/>
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
     
       logback-access ：提供通过http访问日志的功能，第三方软件可以通过logback-access这个模块来访问到
                       logback里面记录的日志     
       logback-classic：log4j的一个改良的版本
       logback-core   ：为以上两个模块提供了基础的服务

   2、Logback的主要标签
   
       logger：存放日志对象、定义日志类型、级别等
       appender：指定日志输出的目的地-可以是控制台/文件/远程套接字服务器等
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

      <!-- 图片处理 -->
         <!-- https://mvnrepository.com/artifact/net.coobird/thumbnailator -->
         <dependency>
             <groupId>net.coobird</groupId>
             <artifactId>thumbnailator</artifactId>
             <version>0.4.8</version>
         </dependency>

ImgUtil----->test1：图片加水印练习-1

            public class ImageUtil {
                private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                private static final Random r = new Random();
                private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
            
                /**
                 * 将CommonsMultipartFile转换成File
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
                 * 处理缩略图、并返回新生成图片的相对值路径
                 *
                 * @param thumbnail
                 * @param targetAddr
                 * @return
                 */
                public static String generateThumbnail(File thumbnail, String targetAddr) {
                    //获取文件随机名
                    String realFileName = getRandomFileName();
                    //获取文件扩展名
                    String extension = getFileExtension(thumbnail);
                    makeDirPath(targetAddr);
                    String relativeAddr = targetAddr + realFileName + extension;
                    logger.debug("current relativeAddr is：" + relativeAddr);
                    File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
                    logger.debug("current completeAddr is：" + PathUtil.getImgBasePath() + relativeAddr);
                    try {
                        Thumbnails.of(thumbnail).size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                                ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).outputQuality(0.8f)
                                .toFile(dest);
            
                    } catch (IOException e) {
                        logger.error(e.toString());
                        e.printStackTrace();
                    }
                    return relativeAddr;
                }
            
                /**
                 * 生成随机文件名，当前年月日时分秒+五位随机数
                 *
                 * @return
                 */
                private static String getRandomFileName() {
                    //获取随机的五位数>10000 <99999
                    int rannum = r.nextInt(89999) + 10000;
                    String nowTime = sDateFormat.format(new Date());
                    return nowTime + rannum;
                }
            
                /**
                 * 获取输入文件流的扩展名
                 *
                 * @param cFile
                 * @return
                 */
                private static String getFileExtension(File cFile) {
                    String originalFileName = cFile.getName();
                    return originalFileName.substring(originalFileName.lastIndexOf("."));
                }
            
                /**
                 * 创建目标路径所涉及到的目录，即/home/work/xufangfang/xxx.jpg
                 * 那么 home work xufangfang 这三个文件夹都得自动创建
                 *
                 * @param targetAddr 前端传过来的文件夹的相对路径
                 */
                private static void makeDirPath(String targetAddr) {
                    String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
                    File dirPath = new File(realFileParentPath);
                    if (!dirPath.exists()) {
                        dirPath.mkdirs();
                    }
                }
            
            
                public static void main(String[] args) throws IOException {
                    /**
                     * 1、输入的文件是什么
                     * 2、输出的文件是什么
                     */
                    //获取ClassPath的绝对值路径
                    Thumbnails.of(new File("E:\\资料\\1580800005(1).png"))
                            .size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).outputQuality(0.8f)
                            .toFile("E:\\资料\\xufangfang.png");
                }
            }
 
        
PathUtil----->

           /**
            * 路径处理工具类
            * (1)根据执行环境的不同，提供不同的根路径
            */
           public class PathUtil {
               //System.getProperty获取系统的属性
               //file.seperator获取系统文件的分隔符
               private static String seperator = System.getProperty("file.seperator");
           
               public static String getImgBasePath() {
                   //System.getProperty获取系统的属性
                   //os.name获取系统的名称
                   String os = System.getProperty("os.name");
                   String basePath = "";
                   if (os.toLowerCase().startsWith("win")) {
                       basePath = "E:/资料/img/";
                   } else {
                       basePath = "/home/xiangze/image/";
                   }
                   //路径中的分隔符对于Linux是反斜杠、对于windows是斜杠，需要对分隔符进行处理
                   basePath = basePath.replace("/", seperator);
                   return basePath;
               }
           
               /**
                * 获取店铺图片存储路径
                * 业务需要：将店铺图片分别存储在各自店铺的路径下
                *
                * @return
                */
               public static String getShopImagePath(long shopId) {
                   String imagePath = "/upload/item/shop/" + shopId + "/";
                   return imagePath.replace("/", seperator);
               }
               
           }     

(2)程序当且仅当抛出RuntimeException或者继承RuntimeException的时候，事务才会得以终止并回滚。      

(3)负责将实体类转换成json、或者将json转换成实体类
        <!-- json解析 -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.8.7</version>
        </dependency>   
 
 (4)Sui Mobile（自适应网页设计框架）： https://sui.ctolog.com/
 
 1、由阿里巴巴团队开发的
 
 2、轻量、小巧且精美的UI库，方便迅速搭建手机H5应用：基于ios风格，提供了20+轻量的常用的组件，兼容ios6.0+、android4.0+，
      
   非常适合开发跨平台Web App   
 
 3、自适应网页设计框架
 
 
(5)Kaptcha实现验证码
     
     1、pom.xml：
     <!-- https://mvnrepository.com/artifact/com.github.penggle/kaptcha -->
     <dependency>
         <groupId>com.github.penggle</groupId>
         <artifactId>kaptcha</artifactId>
         <version>2.3.2</version>
     </dependency>

     2、web.xml
     
         <servlet>
             <servlet-name>Kaptcha</servlet-name>
             <servlet-class>com.google.code.kaptcha.servlet.KaptchaServlet</servlet-class>
             <!--是否有边框-->
             <init-param>
                 <param-name>kaptcha.border</param-name>
                 <param-value>no</param-value>
             </init-param>
             <!--字体颜色-->
             <init-param>
                 <param-name>kaptcha.textproducer.font.color</param-name>
                 <param-value>red</param-value>
             </init-param>
             <!--图片宽度-->
             <init-param>
                 <param-name>kaptcha.image.width</param-name>
                 <param-value>135</param-value>
             </init-param>
             <!--使用哪些字符生成验证码-->
             <init-param>
                 <param-name>kaptcha.textproducer.char.string</param-name>
                 <param-value>ACDEFHKPRSTWX345679</param-value>
             </init-param>
             <!--使用哪些字符生成验证码-->
             <init-param>
                 <param-name>kaptcha.image.height</param-name>
                 <param-value>50</param-value>
             </init-param>
             <!--字体大小-->
             <init-param>
                 <param-name>kaptcha.textproducer.font.size</param-name>
                 <param-value>43</param-value>
             </init-param>
             <!--干扰线大小-->
             <init-param>
                 <param-name>kaptcha.noise.color</param-name>
                 <param-value>black</param-value>
             </init-param>
             <!--验证码的字符个数-->
             <init-param>
                 <param-name>kaptcha.textproducer.char.length</param-name>
                 <param-value>4</param-value>
             </init-param>
             <!--字体-->
             <init-param>
                 <param-name>kaptcha.textproducer.font.names</param-name>
                 <param-value>Arial</param-value>
             </init-param>
         </servlet>
         <servlet-mapping>
             <servlet-name>Kaptcha</servlet-name>
             <url-pattern></url-pattern>
         </servlet-mapping>
         
         
     html:
 
     <!--验证码 kaptcha-->
                     <li class="align-top">
                         <div class="item-content">
                             <div class="item-inner">
                                 <div class="item-title label">验证码</div>
                                 <input type="text" id="j_captcha" placeholder="验证码">
                                 <div class="item-input">
                                     <img id="captcha_img" alt="点击更换" title="点击更换"
                                          onclick="changeVerifyCode(this)" src="../Kaptcha"></img>
                                 </div>
                             </div>
                         </div>
                     </li>
 
 
     js:
     function changeVerifyCode(img) {
         // img.src="../Kaptcha?"+Math.floor(Math.random()*100)  ;
         img.src = "../Kaptcha?";
     
     }
     
      if (!verifyCodeActual) {
                     $.toast('请输入验证码！');
                     return;
                 }
                 formData.append('verifyCodeActual', verifyCodeActual);
     
     java：
         public static boolean checkVerifyCode(HttpServletRequest request){
             String verifyCodeExpected=(String)request.getSession().getAttribute(
                     Constants.KAPTCHA_SESSION_KEY
             );
             String verifyCodeActual=HttpServletRequestUtil.getString(request,"verifyCodeActual");
             if(verifyCodeActual==null || !verifyCodeActual.equals(verifyCodeExpected)){
                 return false;
             }
             return true;
     
         }
 
 
（6）在web开发过程中，服务器可以为每个用户浏览器创建一个session对象，一个浏览器独占一个session
    
   对象，因此在保存用户数据时，服务器层可以把用户信息写到浏览器独占的session中，当用户使用浏览器
   
   访问服务器程序时，服务器可以从用户的session中取出用户的数据为用户服务，服务器创建session出来后，
    
   会把session的一个ID号以Cookie的形式回写给客户机，这样只要客户端的浏览器不关再去访问服务器的时候
   
   都会带着Session的ID号过去，服务器发现客户端带着Session的ID号过来，就会使用内容中与之对应的session
   
   为之服务。Session是有过期时间的，Tomcat默认的Session的过期时间为30分钟。   
    
    获取登陆保存的Session信息 ：key是user
    
    从session取：
    request.getSession().getAttribute("user");
    向session放：
    request.getSession().setAttribute("shopList",shopList);
 
(7)Mybatis-mapper中的模糊查询：

    s.shop_name like '%${shopCondition.shopName}%' 
 
(8)

      /**
       *  封装json对象，所有返回结果都是用它
       * @param <T>
       */
      @Data
      public class Result<T> {
          private boolean success;//是否成功标志
          private T data;//成功时返回的数据
          private String errorMsg;//错误信息
          private int errorCode;
      
          public Result(){
      
          }
      
          /**
           * 成功时的构造器
           * @param success
           * @param data
           */
          public Result(boolean success,T data){
                this.success=success;
                this.data=data;
          }
      
          /**
           * 错误时的构造器
           */
          public Result(boolean success,int errorCode,String errorMsg){
              this.success=success;
              this.errorCode=errorCode;
              this.errorMsg=errorMsg;
          }
      } 
 
 (9)枚举类
 
      public enum ProductCategoryStateEnum {
          SUCCESS(1, "创建成功"),
          INNER_ERROR(-1001, "操作失败"),
          EMPTY_LIST(-1002, "添加数少于1");
 
     private int state;
 
     private String stateInfo;
 
     private ProductCategoryStateEnum(int state, String stateInfo) {
         this.state = state;
         this.stateInfo = stateInfo;
     }
 
     public int getState() {
         return state;
     }
 
     public String getStateInfo() {
         return stateInfo;
     }
 
     public static ProductCategoryStateEnum stateOf(int index) {
         for (ProductCategoryStateEnum state : values()) {
             if (state.getState() == index) {
                 return state;
             }
         }
         return null;
     }
    }
    
(10)自定义异常


     public class ProductCategoryOperationException extends RuntimeException {
         private static final long serialVersionUID = 6849139913459719040L;
     
    public ProductCategoryOperationException(String msg) {
        super(msg);
         }
     }
    
                