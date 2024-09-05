# nihonwords
* [📦 Install ](#install) -- Install relevant dependencies and the project
* [🔧 Usage ](#usage) -- Commands to run the server
* [📦 Directory structure ](#directory) -- What does each file do
## Install
This part records how to pack and deploy the project on linux system from scratch.

### Install environments
#### Install Java
```shell
# Upload jdk installation package or download
cd  /usr/local
jdk-8u152-linux-x64.tar.gz

# unpack
tar -zxvf jdk-8u152-linux-x64.tar.gz

# make soft connection
ln -s /usr/local/jdk1.8.0_152/  /usr/local/jdk

# modify the path variables
vim /etc/profile

export JAVA_HOME=/usr/local/jdk
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:$CLASSPATH:$JAVA_HOME/lib:$JRE_HOME/lib
export PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin

# make the profile take effect
source /etc/profile

# test if successfully installed
java -version
```
#### 2.1.2 Install maven

```shell
# Upload maven installation package or download
cd  /usr/local
apache-maven-3.6.1-bin.tar.gz

# unpack
tar  -zxvf  apache-maven-3.6.1-bin.tar.gz

# make soft connection
ln  -s  /usr/local/apache-maven-3.6.1/  /usr/local/maven

# modify the path environment
vim /etc/profile

export MAVEN_HOME=/usr/local/maven
export PATH=$PATH:$MAVEN_HOME/bin

# make the profile file take effect
source /etc/profile

# test success
mvn -v
```

#### Install Docker

```shell
# environment and tools
yum -y install gcc-c++
yum install -y yum-utils device-mapper-persistent-data lvm2

# source information
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# update and install docker-ce
yum makecache fast
yum -y install docker-ce

# start docker service
service docker start
systemctl enable docker

# test success
docker -v

# 镜像加速器
# 您可以通过修改daemon配置文件/etc/docker/daemon.json来使用加速器
mkdir -p /etc/docker
vim /etc/docker/daemon.json

{
 "registry-mirrors": ["https://registry.docker-cn.com"]
}

# restart docker to make the modifications work
systemctl restart docker
```
#### Install MySQL

```shell
# pull mysql image
docker pull mysql:5.7
('docker search mysql' to see all the mysql images, choose one from official)
# start mysql server
docker run --name mysql --restart=always -v /home/ljaer/mysql:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7

# test mysql
# enter the container
docker exec -it sun_mysql /bin/bash
# login mysql
mysql -u root -p 
root

```

#### Install RabbitMQ

```shell
# pull image
docker pull rabbitmq:management

# run
docker run -d -p 5672:5672 -p 15672:15672 --restart=always --name rabbitmq rabbitmq:management
```

#### Install redis
 
```shell
# pull image
docker pull redis:latest

# run
docker run -d -p 6379:6379  --restart=always redis:latest redis-server
```
#### Install nacos

```shell
# pull image
docker pull nacos/nacos-server

# run
docker run --env MODE=standalone --name nacos --restart=always -d -p 8848:8848 -e JVM_XMS=128m -e JVM_XMX=128m nacos/nacos-server
```

#### Install Elasticsearch

```shell
# pull image
docker pull elasticsearch:7.8.0

# make two directory on server

mkdir -p /mydata/elasticsearch/plugins
mkdir -p /mydata/elasticsearch/data

# to authorize:  chmod 777 /mydata/elasticsearch/data

docker run -p 9200:9200 -p 9300:9300 --name elasticsearch --restart=always \-e "discovery.type=single-node" \-e ES_JAVA_OPTS="-Xms512m -Xmx512m" \-v /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins \-v /mydata/elasticsearch/data:/usr/share/elasticsearch/data \-d elasticsearch:7.8.0

# install日文分词器

# 1. 下载elasticsearch-analysis-ik-7.8.0.zip

# 2. 上传解压：unzip elasticsearch-analysis-ik-7.8.0.zip -d ik-analyzer

# 3. 上传到es容器：docker cp ./ik-analyzer a24eb9941759:/usr/share/elasticsearch/plugins

# 4. 重启es：docker restart a24eb9941759
# a24eb9941759：表示容器ID 运行时，需要改成自己的容器ID
```

## Usage
### modify code
modify the server address in 'application.yml' and 'application-dev.yml' in every module
Because I run it on WSL2, so its still localhost. But you can modify the address to the address of your linux virtual machine.

### pack the project to jar package
mvn clean package
![image-20230320093501181](images\image-20230320093501181.png)

![image-20230320091737844](images\image-20230320091737844.png)

### make Docker image from jar package

#### write Dockerfile

```dockerfile
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./service-gateway.jar service-gateway.jar
ENTRYPOINT ["java","-jar","/service-gateway.jar", "&"]
```

#### run Docker command

**把jar包和Dockerfile文件放到同一个目录下，**
```shell
cd \opt
mkdir nihonwords
cd nihonwords
# put jar package and Dockerfile in this directory
```

**执行命令：docker build -t service-gateway:1.0.0 .**

**查看制作好的镜像**
```shell
docker images
```
**启动**
```shell
docker run -d -p 8200:8200 service-gateway:1.0.0
```


## Directory

