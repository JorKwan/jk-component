文件服务客户端
============ 
文件服务客户端
- 支持MinIO文件服务器
- 支持文件管理
- 图片自动压缩（压缩至200K以下）
文件管理服务只存储文件信息，文件上传由客户端直接上传至文件服务器中，不在文件管理服务做额外中转。

说明
---------------
文件助手使用
* 示例代码
  ```
  FileInfo fileInfo = FileInfoCreator.of(groupCode, businessId, appName, fileName);
  // 可对fileInfo属性额外配置。如有效期等
  FileStorageHelper.uploadFile(fileInfo, inputStream);
  ```

配置说明
* 参数配置
  ```
  # 使用的文件服务器，默认为MinIO。详见FileStorageEnum
  persagy.dc.file.storage=0
  # MinIO服务地址、账号、密码
  persagy.dc.file.url=http://192.168.25.129:9000/
  persagy.dc.file.accessKey=admin
  persagy.dc.file.secretKey=********
  # typeHandler扫描
  mybatis-plus.typeHandlersPackage=com.persagy.**.typehandler
  ```
  
安装说明
* MinIO安装
  ```
  # 拉镜像
  docker pull minio/minio
  # 启动
  docker run -p 9000:9000 --name minio \
  -d --restart=always \
  -e "MINIO_ACCESS_KEY=admin" \
  -e "MINIO_SECRET_KEY=********" \
  -v /home/data:/data \
  -v /home/config:/root/.minio \
  minio/minio server /usr/local/minio/files
  ```
最新变化
---------------

