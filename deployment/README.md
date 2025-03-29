# 📱 Blog Application Deployment

## 🌍 Update system variable
```code
sudo vi /etc/sysctl.conf

# vm.max_map_count=262144

sudo sysctl -p
```

## 🛠️ Manual Deployment

### 🔍 Start elasticsearch

```code
docker network create elasticsearch

docker run -d --name elasticsearch \
           --net elastic -p 9200:9200 \
           -it -m 6GB -e "xpack.ml.use_auto_machine_memory_percent=true" \
           -e "xpack.security.enabled=false" \
           elasticsearch:8.17.1

# Get password from container logs

docker logs $(docker ps -a | grep elasticsearch | awk '{print $1}')

# Reset elastic password

docker exec -it es01 /usr/share/elasticsearch/bin/elasticsearch-reset-password -u elastic

# Create enrollment token to kibana

docker exec -it es01 /usr/share/elasticsearch/bin/elasticsearch-create-enrollment-token -s kibana

# Copy cacert

docker cp elasticsearch:/usr/share/elasticsearch/config/certs/http_ca.crt .
```
### 📊 Start Kibana


```code
docker run -d \
           --name kibana \
           --net elastic \
           -p 5601:5601 
           kibana:8.17.1
```

## 🤖 Automatic Deployment (via Shell script)

```code

cd deployment/elasticsearch

# Main script process

./startESvKibana

# Usage option

./startESvKibana -h

Start Elasticsearch and Kibana
 -r| --reset     Reset Elastic password
 -e| --enroll    Enrollment token for Kibana
 -c| --clean     Clean existing containers
 -h| --help      Usage

# Reset elastic password

./startESvKibana -r

# Regenerate Enrollment Token

./startESvKibana -e

# Clean existing containers

./startESvKibana -c

```

## 🔒 Update cacert when running spring boot application

### 🖥️ Windows

#### Run with root user

```code
echo %JAVA_HOME%

%JAVA_HOME%\lib\security\cacerts

keytool -import -trustcacerts -keystore "%JAVA_HOME%\lib\security\cacerts" ^
        -storepass changeit -noprompt -alias elasticsearch -file http_ca.crt
```

#### Remove alias (Run as Administration)

```code
keytool -delete -alias elasticsearch -keystore "%JAVA_HOME%\lib\security\cacerts"
```

### 🐧 Linux

#### Run with root user

```code

export JAVA_HOME=<Path_to_java>

echo $JAVA_HOME

sudo keytool -import -trustcacerts -keystore "$JAVA_HOME/lib/security/cacerts" \
             -storepass changeit -noprompt -alias elasticsearch -file http_ca.crt
```

#### Remove alias (Run as Administration)

```code
sudo keytool -delete -alias elasticsearch -keystore "$JAVA_HOME/lib/security/cacerts"
```

#### (Optional) Fix permission if needed

```code
sudo chmod 644 "$JAVA_HOME/lib/security/cacerts"
sudo chown root:root "$JAVA_HOME/lib/security/cacerts"
```

## 🌱 Start the Spring Boot application

### For developers only
```code
mvn clean install -DskipTests \
                  -Des_username=<ES_USERNAME> \
                  -Des_password=<ES_PASSWORD> \
                  -Dmysql_username=<MYSQL_USERNAME> \
                  -Dmysql_password=<MYSQL_PASSWORD> \
                  -Dsc_username=<SECURITY_USERNAME> \
                  -Dsc_password=<SECURITY_PASSWORD> \
                   spring-boot:run
```

### For production use only
# 🔜
