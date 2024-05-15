# Argo

用户管理功能

接口文档：[用户管理相关接口](https://github.com/zhyea/liens/wiki)

## 如何部署

### 1. 获取源码

```shell
git clone git@github.com:zhyea/argo.git
```
或者

```shell
wget https://github.com/zhyea/argo/archive/refs/heads/main.zip
unzip main.zip
mv liens-main liens
```

### 2. 编译打包

```shell
cd liens
mvn clean package -DskipTests
```

### 3. 启动服务

```shell
sh start.sh
```
