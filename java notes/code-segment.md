### 删除指定文件夹里所有文件
        private static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);
    
        public static void delete(Path path) {
            try {
                if (Files.isDirectory(path)) {
                    Stream<Path> children = Files.list(path);
                    children.forEach(FileHelper::delete);
                }
                Files.deleteIfExists(path);
            } catch (IOException e) {
                LOGGER.error("delete file exception", e);
            }
        }
        
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