# Liens

---

Liens营销系统

## Install

启动文件是`liens.service`。

启动文件需要被置于如下路径：

```shell script
/usr/lib/systemd/system/
```

相关指令：

```shell script
# 修改 service 文件之后需要刷新 Systemd
sudo systemctl daemon-reload

# 使 Liens 开机自启
sudo systemctl enable liens

# 启动 Liens
sudo service liens start

# 重启 Liens
sudo service liens restart

# 停止 Liens
sudo service liens stop

# 查看 Liens 的运行状态
sudo service liens status
```

## Domain

安装Nginx

```shell script
# 添加 Nginx 源
sudo rpm -Uvh https://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm

# 安装 Nginx
sudo yum install -y nginx

# 启动 Nginx
sudo systemctl start nginx.service

# 设置开机自启 Nginx
sudo systemctl enable nginx.service
```

将配置文件liens.conf移动到如下位置

```text
/etc/nginx/conf.d/
```

修改liens.conf文件中的域名和端口信息。

然后执行如下操作：

```shell script
# 检查配置是否有误
sudo nginx -t

# 重载 Nginx 配置
sudo nginx -s reload
```

配置证书

```shell script

# 安装 certbot 以及 certbot nginx 插件
yum install -y epel-release

yum install -y python2-certbot-nginx 

# 安装 certbot 以及 certbot nginx 插件
yum install -y certbot

# 执行配置，中途会询问你的邮箱，如实填写即可
certbot --nginx

# 自动续约
certbot renew --dry-run
```

# 服务器

