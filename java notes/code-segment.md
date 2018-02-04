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