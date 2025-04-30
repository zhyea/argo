# Argo

Argo 是一个前端组件管理系统。目前Argo可以提供如下能力：
* 前端组件模型定义功能
* 前端组件实例维护功能
* 前端页面和组件的关联功能
* 后端接口聚合功能 

## 如何部署
Argo是一个前后端分离的系统，需要分别对前后端进行独立部署。

部署前需要先获取源码，获取源码的流程如下：

```shell
wget https://github.com/zhyea/argo/archive/refs/tags/v0.0.1-alpha.zip
unzip argo-0.0.1-alpha.zip
mv argo-0.0.1-alpha argo
cd argo
```

### 后端部署

后端部署流程如下

#### 步骤一：编译打包

要对项目编译打包可以执行如下命令：

```shell
cd argo-server
mvn clean package -DskipTests
```

#### 步骤二：启动服务

在项目下有独立的启动脚本 start.sh
启动命令如下：

```shell
sh start.sh
```

### 前端部署

回到argo根目录，执行如下命令完成前端部署：

```shell
cd argo-front
npm install
npm run dev
```
**注：** 以上部署流程都是在本地进行部署，仅做测试使用

