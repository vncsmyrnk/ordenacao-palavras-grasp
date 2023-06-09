#!/bin/bash

cat $1 | tr -s ' ' '\n' | sort | uniq -c | sort -nr | awk '{ print $1" " $2 }'
