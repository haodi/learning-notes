开发路上
=============

### URL和URI区别
    “A Uniform Resource Identifier (URI) 是一个紧凑的字符串用来标示抽象或物理资源。”
    “A URI 可以进一步被分为定位符、名字或两者都是. 术语“Uniform Resource Locator” (URL) 是URI的子集, 除了确定一个资源,还提供一种定位该资源的主要访问机制(如其网络“位置”)。“
    
    “URI可以分为URL,URN或同时具备locators 和names特性的一个东西。URN作用就好像一个人的名字，URL就像一个人的地址。换句话说：URN确定了东西的身份，URL提供了找到它的方式。”
    
### Maven将本地的JAR包部署到私服上

    用户名和密码通过servers标签设置，配置在仓库的settings.xml文件中

    mvn deploy:deploy-file -DgroupId=<group-id> \
    -DartifactId=<artifact-id> \
    -Dversion=<version> \
    -Dpackaging=<type-of-packaging> \
    -Dfile=<path-to-file> \
    -DrepositoryId=<id-to-map-on-server-section-of-settings.xml> \
    -Durl=<url-of-the-repository-to-deploy>

    eg: mvn -X deploy:deploy-file -DgroupId=org.json -DartifactId=json -Dversion=20160810 -Dpackaging=jar -Dfile=json-20160810.jar -DrepositoryId=jrepo2-public -Durl=http://jrepo2.yypm.com/nexus/content/repositories/releases/