#!/bin/bash
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
docker run -it -p 8080:8080 -v $DIR:/io sweaters:0.0.1
