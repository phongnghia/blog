#!/bin/bash

BASE_URL="https://elasticsearch:9200"

[ -z $ADMINUSER_PASSWORD ] && ADMINUSER_PASSWORD="password"
[ -z $ADMINUSER ] && ADMINUSER="adminuser"



echo "⏳  Waiting for Elasticsearch to be ready..."
until curl -k -u $ADMINUSER:$ADMINUSER_PASSWORD $BASE_URL >/dev/null 2>&1; do
  sleep 2
done
echo "✅  Elasticsearch is up!"

if [ ! -f /shared/enrollment_token.txt ]; then
  echo "🔐  Generating enrollment token..."
  token=$(curl -k -u $ADMINUSER:$ADMINUSER_PASSWORD -X POST $BASE_URL/_security/enroll/kibana | grep -oP '"token"\s*:\s*"\K[^"]+')
  echo "$token" > /shared/enrollment_token.txt
else
  token=$(cat /shared/enrollment_token.txt)
fi

echo "🚀  Starting Kibana with token..."
export KIBANA_ENROLLMENT_TOKEN=$token
/opt/kibana/bin/kibana
