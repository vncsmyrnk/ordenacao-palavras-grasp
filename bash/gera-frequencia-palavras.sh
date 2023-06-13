#!/bin/bash

cat $1 | tr -s ' ' '\n' | sort | uniq -c | LC_COLLATE=C sort -k1,1nr -k2,2 | awk '{ print $1" " $2 }'
