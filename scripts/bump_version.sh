#!/bin/bash

FILE="version.properties"

CODE=$(grep VERSION_CODE $FILE | cut -d'=' -f2)
NAME=$(grep VERSION_NAME $FILE | cut -d'=' -f2)

NEW_CODE=$((CODE + 1))
NEW_NAME="1.0.$NEW_CODE"

sed -i "s/VERSION_CODE=$CODE/VERSION_CODE=$NEW_CODE/" $FILE
sed -i "s/VERSION_NAME=$NAME/VERSION_NAME=$NEW_NAME/" $FILE

echo "NEW_VERSION=$NEW_NAME" >> $GITHUB_ENV
echo "NEW_CODE=$NEW_CODE" >> $GITHUB_ENV
