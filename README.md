# springblog
2019.1.29 增加redis缓存
#docker
docker mysql server
  #拉取镜像
  docker pull mysql:5.7
  #以守护方式创建mysql5.7容器(mysql一般不需要建立目录映射)
  sudo docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -di mysql:5.7
  
  #–name：容器名，此处命名为mysql
  #-e：配置信息，此处配置mysql的root用户的登陆密码
  #-p：端口映射，此处映射 主机3306端口 到 容器的3306端口
  #-d：源镜像名，此处为 mysql:5.7
  #建立文件映射
  duso docker run -p 3306:3306 --name mysql \
	-v /usr/local/docker/mysql/conf:/etc/mysql \
	-v /usr/local/docker/mysql/logs:/var/log/mysql \
	-v /usr/local/docker/mysql/data:/var/lib/mysql \
	-e MYSQL_ROOT_PASSWORD=123456 \
	-d mysql:5.7
  #检查容器是否正确运行
  docker container ls
  