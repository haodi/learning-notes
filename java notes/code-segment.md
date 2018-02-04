### 删除一个文件夹里所有文件
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