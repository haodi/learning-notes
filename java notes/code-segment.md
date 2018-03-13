### 删除指定文件夹里所有文件
        private static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);
    
        public static void delete(Path path) {
            try {
                Files.walk(pathToBeDeleted)
                      .sorted(Comparator.reverseOrder())
                      .map(Path::toFile)
                      .forEach(File::delete);
            } catch (IOException e) {
                LOGGER.error("delete file exception", e);
            }
        }
        
        # Using FileSystemUtils from Spring
        FileSystemUtils.deleteRecursively(file);
        
        # Using FileUtils from commons-io
        FileUtils.deleteDirectory(file);
        
### 获取本机所有IP地址
        private static final Logger LOGGER = LoggerFactory.getLogger(IPHelper.class);
        
            public static String getIp() {
                try {
                    return Collections.list(NetworkInterface.getNetworkInterfaces()).stream()
                            .map(it -> Collections.list(it.getInetAddresses()))
                            .flatMap(Collection::stream)
                            .filter(ip -> ip != null && ip instanceof Inet4Address && !"127.0.0.1".equals(ip.getHostAddress()))
                            .map(InetAddress::getHostAddress)
                            .collect(Collectors.joining("#"));
                } catch (Exception e) {
                    LOGGER.error("get network interfaces address exception", e);
                }
                return null;
            }
            
### 获取客户端IP地址
######    Warning: Be careful if you are implementing security, as all of these headers are easy to fake.
    
    private static final String[] IP_HEADER_CANDIDATES = {
                "X-Forwarded-For", "Proxy-Client-IP",
                "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};
    
        public static String getClientIpAddress(HttpServletRequest request) {
            for (String header : IP_HEADER_CANDIDATES) {
                String ip = request.getHeader(header);
                if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                    return ip;
                }
            }
            return request.getRemoteAddr();
        }
        
### Easy Way to Read Classpath Resource in Java

    Using Java NIO (java.nio.* package) it is extremely easy to read classpath resource as a string
    
    Path path = Paths.get(getClass().getResource("/myfile.txt").toURI());
    String content = new String(Files.readAllBytes(path));
    
### Reading a resource file from within jar

    private String readResourceWithinJar(String fileName) {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader;
            try (InputStream in = getClass().getResourceAsStream(fileName)) {
                reader = new BufferedReader(new InputStreamReader(in));
                String tempString;
                while ((tempString = reader.readLine()) != null) {
                    sb.append(" ").append(tempString);
                }
                reader.close();
            } catch (Exception e) {
                LOGGER.error("Read {} file error.", e);
                return null;
            }
            return sb.toString();
        }