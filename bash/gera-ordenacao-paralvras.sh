#!/bin/bash

cat $1 | tr -s ' ' '\n' | LC_COLLATE=C sort
